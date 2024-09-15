package m.somov.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import m.somov.MySecondTestAppSpringBoot.model.Request;

@Service
@Qualifier("ModifySourceRequestService")
public class ModifySourceRequestService implements ModifyRequestService {
  @Override
  public void modify(Request request) {
    request.setSource("Source1");

    HttpEntity<Request> httpEntity = new HttpEntity<>(request);

    new RestTemplate().exchange("http://localhost:8084/feedback",
        HttpMethod.POST,
        httpEntity,
        new ParameterizedTypeReference<>() {
        });
  }
}
