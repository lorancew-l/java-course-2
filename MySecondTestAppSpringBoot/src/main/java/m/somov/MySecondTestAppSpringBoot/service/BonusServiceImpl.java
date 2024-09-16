package m.somov.MySecondTestAppSpringBoot.service;

import java.time.Year;

import org.springframework.stereotype.Service;

import m.somov.MySecondTestAppSpringBoot.model.Positions;

@Service
public class BonusServiceImpl implements BonusService {
  private boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
  }

  private double calculateBonusPerPeriod(Positions positions, double salary, double bonus, int workDays,
      int totalDays) {
    return salary * bonus * totalDays * positions.getPositionCoefficient() / workDays;
  }

  @Override
  public double calculateAnnualBonus(Positions positions, double salary, double bonus, int workDays) {
    int daysInYear = isLeapYear(Year.now().getValue()) ? 366 : 365;
    return calculateBonusPerPeriod(positions, salary, bonus, workDays, daysInYear);
  }

  @Override
  public double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays) {
    if (!positions.isManager()) {
      throw new IllegalArgumentException("Премия доступна только для управленцев");
    }

    int daysInQuarter = 91;
    return calculateBonusPerPeriod(positions, salary, bonus, workDays, daysInQuarter);
  }
}
