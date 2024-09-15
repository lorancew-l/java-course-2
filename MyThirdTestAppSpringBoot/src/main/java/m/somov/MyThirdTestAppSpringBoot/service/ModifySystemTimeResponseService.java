package m.somov.MyThirdTestAppSpringBoot.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import m.somov.MyThirdTestAppSpringBoot.model.Response;
import m.somov.MyThirdTestAppSpringBoot.util.DateTimeUtil;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {
  @Override
  public Response modify(Response response) {
    response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
    return response;
  }
}
