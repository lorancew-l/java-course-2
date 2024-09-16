package m.somov.MySecondTestAppSpringBoot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import m.somov.MySecondTestAppSpringBoot.model.Positions;

public class BonusServiceImplTest {
  @Test
  void calculateAnnualBonus() {
    // given
    Positions position = Positions.HR;
    double bonus = 2.0;
    int workDays = 243;
    double salary = 100_000.00;

    // when
    var result = new BonusServiceImpl().calculateAnnualBonus(position, salary, bonus, workDays);

    // then
    double expected = 360493.8271604938;
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void calculateQuarterlyBonusThrowsErrorForNonManager() {
    Positions position = Positions.DEV;
    double bonus = 2.0;
    int workDays = 243;
    double salary = 100_000.00;

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);
    });

    String expectedMessage = "Премия доступна только для управленцев";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));

  }

  @Test
  void calculateQuarterlyBonusReturnCorrectBonus() {
    // given
    Positions position = Positions.PM;
    double bonus = 2.0;
    int workDays = 243;
    double salary = 100_000.00;

    // when
    var result = new BonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);

    // then
    double expected = 224691.35802469135;
    assertThat(result).isEqualTo(expected);
  }
}
