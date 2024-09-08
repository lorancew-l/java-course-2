package m.somov.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
  @Size(min = 1, max = 32)
  private String uid;

  @Size(min = 1, max = 32)
  private String operationUid;

  private String systemName;

  @NotBlank
  private String systemTime;

  private String source;

  @Min(1)
  @Max(100_000)
  private int communicationId;

  private int templateId;

  private int productCode;

  private int smsCode;
}
