package m.somov.MySpringBoot2Dbase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import m.somov.MySpringBoot2Dbase.dao.SubjectDAO;
import m.somov.MySpringBoot2Dbase.entity.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {
  @Autowired
  private SubjectDAO subjectDAO;

  @Override
  @Transactional
  public List<Subject> getAllSubjects() {
    return subjectDAO.getAllSubjects();
  }

  @Override
  @Transactional
  public Subject saveSubject(Subject subject) {
    return subjectDAO.saveSubject(subject);
  }

  @Override
  @Transactional
  public Subject updateSubject(Subject subject) {
    if (subjectDAO.getSubject(subject.getId()) != null) {
      subjectDAO.saveSubject(subject);
      return subject;
    }

    return null;
  }

  @Override
  @Transactional
  public Subject getSubject(int id) {
    return subjectDAO.getSubject(id);
  }

  @Override
  @Transactional
  public void deleteSubject(int id) {
    subjectDAO.deleteSubject(id);
  }
}