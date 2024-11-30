package m.somov.MySpringBoot2Dbase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import m.somov.MySpringBoot2Dbase.entity.Subject;

@Service
public interface SubjectService {
  List<Subject> getAllSubjects();

  Subject saveSubject(Subject subject);

  Subject updateSubject(Subject subject);

  Subject getSubject(int id);

  void deleteSubject(int id);
}