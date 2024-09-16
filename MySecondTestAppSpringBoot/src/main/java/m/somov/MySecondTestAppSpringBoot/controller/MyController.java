package m.somov.MySecondTestAppSpringBoot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import m.somov.MySecondTestAppSpringBoot.service.ModifyRequestService;
import m.somov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import m.somov.MySecondTestAppSpringBoot.service.ValidationService;
import m.somov.MySecondTestAppSpringBoot.util.DateTimeUtil;

@Slf4j
@RestController
@RequestMapping("/feedback")
public class MyController {
  private final ValidationService validationService;
  private final ModifyResponseService modifyResponseService;
  private final ModifyRequestService modifyRequestService;

  @Autowired
  public MyController(ValidationService validationService,
      @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
      @Qualifier("ModifyRequestDataService") ModifyRequestService modifyRequestService) {
    this.validationService = validationService;
    this.modifyResponseService = modifyResponseService;
    this.modifyRequestService = modifyRequestService;
  }

  @PostMapping
  public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
    log.info("Request: {}", request);

    Response response = buildInitialResponse(request);
    log.info("Initial response: {}", response);

    try {
      validationService.isValid(bindingResult);
      log.info("Validation passed for request: {}", request);
    } catch (ValidationFailedException | UnsupportedCodeException e) {
      return handleValidationException(e, bindingResult, request, response);
    } catch (Exception e) {
      return handleUnknownException(e, request, response);
    }

    modifyResponseService.modify(response);
    modifyRequestService.modify(request);
    log.info("Final response: {}", response);

    return ResponseEntity.ok(response);
  }

  private Response buildInitialResponse(Request request) {
    return Response.builder()
        .uid(request.getUid())
        .operationUid(request.getOperationUid())
        .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
        .code(Codes.SUCCESS)
        .errorCode(ErrorCodes.EMPTY)
        .errorMessage(ErrorMessages.EMPTY)
        .build();
  }

  private void setResponseError(Response response, ErrorCodes errorCode, ErrorMessages errorMessage) {
    response.setCode(Codes.FAILED);
    response.setErrorCode(errorCode);
    response.setErrorMessage(errorMessage);
  }

  private ResponseEntity<Response> handleValidationException(Exception e, BindingResult bindingResult,
      Request request, Response response) {
    bindingResult.getFieldErrors().forEach(error -> log.error("Validation error \"{}: {}\" occurred for request {}",
        error.getField(), error.getDefaultMessage(), request));
    setResponseError(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Response> handleUnknownException(Exception e, Request request, Response response) {
    log.error("Unknown exception \"{}\" occurred for request {}", e.getMessage(), request);
    setResponseError(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
