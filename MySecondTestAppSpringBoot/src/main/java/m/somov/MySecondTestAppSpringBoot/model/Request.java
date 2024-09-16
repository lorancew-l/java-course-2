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
  /*
   * Уникальный идентификатор сообщения
   */
  @Size(min = 1, max = 32)
  private String uid;

  /*
   * Уникальный идентификатор операции
   */
  @Size(min = 1, max = 32)
  private String operationUid;

  /*
   * Имя системы отправителя
   */
  private Systems systemName;

  /*
   * Время создания сообщения
   */
  @NotBlank
  private String systemTime;

  /*
   * Наименование ресурса
   */
  private String source;

  /*
   * Должность
   */
  private Positions position;

  /*
   * Зарплата
   */
  private Double salary;

  /*
   * Бонус
   */
  private Double bonus;

  /*
   * Количество отработанных дней
   */
  private Integer workDays;

  /*
   * Уникальный идентификатор коммуникации
   */
  @Min(1)
  @Max(100_000)
  private int communicationId;

  /*
   * Уникальный идентификатор шаблона
   */
  private int templateId;

  /*
   * Код продукта
   */
  private int productCode;

  /*
   * Смс код
   */
  private int smsCode;

  @Override
  public String toString() {
    return "{" +
        "uid=" + uid + '\'' +
        ", operationUid='" + operationUid + '\'' +
        ", systemName='" + systemName + '\'' +
        ", systemTime='" + systemTime + '\'' +
        ", source='" + source + '\'' +
        ", communicationId='" + communicationId + '\'' +
        ", templateId='" + templateId + '\'' +
        ", productCode='" + productCode + '\'' +
        ", smsCode='" + smsCode + '\'' +
        '}';
  }
}
