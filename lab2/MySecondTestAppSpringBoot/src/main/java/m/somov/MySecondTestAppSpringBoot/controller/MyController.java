package m.somov.MySecondTestAppSpringBoot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import m.somov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import m.somov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import m.somov.MySecondTestAppSpringBoot.model.Request;
import m.somov.MySecondTestAppSpringBoot.model.Response;
import m.somov.MySecondTestAppSpringBoot.service.ValidationService;

@RestController
public class MyController {
  private final ValidationService validationService;

  @Autowired
  public MyController(ValidationService validationService) {
    this.validationService = validationService;
  }

  @PostMapping("/feedback")
  public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
    var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    var response = Response.builder()
        .uid(request.getUid())
        .operationUid(request.getOperationUid())
        .systemTime(simpleDateFormat.format(new Date()))
        .code("success").errorCode("")
        .errorMessage("")
        .build();

    try {
      validationService.isValid(bindingResult);
    } catch (ValidationFailedException | UnsupportedCodeException e) {
      response.setCode("failed");
      response.setErrorCode("ValidationException");
      response.setErrorMessage(e.getMessage());
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      response.setCode("failed");
      response.setErrorCode("UnknownException");
      response.setErrorMessage("Произошла непредвиденная ошибка");
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
