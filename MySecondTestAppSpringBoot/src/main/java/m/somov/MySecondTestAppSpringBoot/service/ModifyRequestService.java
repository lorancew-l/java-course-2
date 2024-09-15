package m.somov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;

import m.somov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
  void modify(Request request);
}
