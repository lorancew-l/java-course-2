package m.somov.MySpringBoot2Dbase.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import m.somov.MySpringBoot2Dbase.entity.Subject;

@Repository
public interface SubjectDAO {
  List<Subject> getAllSubjects();

  Subject saveSubject(Subject subject);

  Subject getSubject(int id);

  void deleteSubject(int id);
}