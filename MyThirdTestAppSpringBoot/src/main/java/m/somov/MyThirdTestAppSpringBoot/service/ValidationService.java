package m.somov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ValidationService {
  void isValid(BindingResult bindingResult) throws Exception;
}