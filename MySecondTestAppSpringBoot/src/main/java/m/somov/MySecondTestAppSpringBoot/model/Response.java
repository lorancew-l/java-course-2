package m.somov.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
  /*
   * Уникальный идентификатор сообщения
   */
  private String uid;

  /*
   * Уникальный идентификатор операции
   */
  private String operationUid;

  /*
   * Время создания сообщения
   */
  private String systemTime;

  /*
   * Код операции
   */
  private Codes code;

  /*
   * Значение годового бонуса
   */
  private Double annualBonus;

  /*
   * Код ошибки
   */
  private ErrorCodes errorCode;

  /*
   * Текст ошибки
   */
  private ErrorMessages errorMessage;
}
