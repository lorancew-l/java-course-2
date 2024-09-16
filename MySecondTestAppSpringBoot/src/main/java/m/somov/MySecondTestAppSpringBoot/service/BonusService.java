package m.somov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;

import m.somov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface BonusService {
  double calculateAnnualBonus(Positions positions, double salary, double bonus, int workDays);

  double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays);
}
