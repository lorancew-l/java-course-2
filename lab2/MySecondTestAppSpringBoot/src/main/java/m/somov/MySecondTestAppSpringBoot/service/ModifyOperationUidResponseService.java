package m.somov.MySecondTestAppSpringBoot.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import m.somov.MySecondTestAppSpringBoot.model.Response;

@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService {
  @Override
  public Response modify(Response response) {
    var uuid = UUID.randomUUID();
    response.setOperationUid(uuid.toString());
    return response;
  }
}
