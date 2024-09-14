package m.somov.MySecondTestAppSpringBoot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import m.somov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import m.somov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import m.somov.MySecondTestAppSpringBoot.model.Codes;
import m.somov.MySecondTestAppSpringBoot.model.ErrorCodes;
import m.somov.MySecondTestAppSpringBoot.model.ErrorMessages;
import m.somov.MySecondTestAppSpringBoot.model.Request;
import m.somov.MySecondTestAppSpringBoot.model.Response;
import m.somov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import m.somov.MySecondTestAppSpringBoot.service.ValidationService;
import m.somov.MySecondTestAppSpringBoot.util.DateTimeUtil;

@Slf4j
@RestController
public class MyController {
  private final ValidationService validationService;
  private final ModifyResponseService modifyResponseService;

  @Autowired
  public MyController(ValidationService validationService,
      @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
    this.validationService = validationService;
    this.modifyResponseService = modifyResponseService;
  }

  @PostMapping("/feedback")
  public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
    log.info("Request: {}", request);

    var response = Response.builder()
        .uid(request.getUid())
        .operationUid(request.getOperationUid())
        .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
        .code(Codes.SUCCESS)
        .errorCode(ErrorCodes.EMPTY)
        .errorMessage(ErrorMessages.EMPTY)
        .build();

    log.info("Initial response: {}", response);

    try {
      validationService.isValid(bindingResult);
      log.info("Validation passed for request: {}", request);
    } catch (ValidationFailedException | UnsupportedCodeException e) {
      bindingResult.getFieldErrors().forEach(error -> {
        log.error("Validation error \"{}: {}\" occurred for request {}", error.getField(), error.getDefaultMessage(),
            request);
      });
      response.setCode(Codes.FAILED);
      response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
      response.setErrorMessage(ErrorMessages.VALIDATION);
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error("Unknown exception \"{}\" occurred for request {}", e.getMessage(), request);
      response.setCode(Codes.FAILED);
      response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
      response.setErrorMessage(ErrorMessages.UNKNOWN);
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    modifyResponseService.modify(response);
    log.info("Final response: {}", response);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
