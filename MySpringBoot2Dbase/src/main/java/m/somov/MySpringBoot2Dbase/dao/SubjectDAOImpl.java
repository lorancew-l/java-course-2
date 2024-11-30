package m.somov.MySpringBoot2Dbase.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import m.somov.MySpringBoot2Dbase.entity.Subject;

@Slf4j
@Repository
public class SubjectDAOImpl implements SubjectDAO {

  @Autowired
  private EntityManager entityManager;

  @Override
  public List<Subject> getAllSubjects() {
    Query query = entityManager.createQuery("from Subject");
    return query.getResultList();
  }

  @Override
  public Subject saveSubject(Subject subject) {
    return entityManager.merge(subject);
  }

  @Override
  public Subject getSubject(int id) {
    return entityManager.find(Subject.class, id);
  }

  @Override
  public void deleteSubject(int id) {
    Query query = entityManager.createQuery("delete from Subject where id =:SubjectId");
    query.setParameter("SubjectId", id);
    query.executeUpdate();
  }
}