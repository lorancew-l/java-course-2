package m.somov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import m.somov.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import m.somov.MyThirdTestAppSpringBoot.exception.ValidationFailedException;
import m.somov.MyThirdTestAppSpringBoot.model.Request;

@Service
public class RequestValidationService implements ValidationService {
  @Override
  public void isValid(BindingResult bindingResult)
      throws ValidationFailedException, UnsupportedCodeException {
    if (bindingResult.hasErrors()) {
      var error = bindingResult.getFieldError();
      var message = String.format("%s: %s", error.getField(), error.getDefaultMessage());
      throw new ValidationFailedException(message);
    }

    var request = (Request) bindingResult.getTarget();
    if ("123".equals(request.getUid())) {
      throw new UnsupportedCodeException("uid равный '123' не поддерживается");
    }
  }
}
