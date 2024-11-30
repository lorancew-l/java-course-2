package m.somov.MySpringBoot2Dbase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import m.somov.MySpringBoot2Dbase.dao.StudentDAO;
import m.somov.MySpringBoot2Dbase.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  private StudentDAO studentDAO;

  @Override
  @Transactional
  public List<Student> getAllStudents() {
    return studentDAO.getAllStudents();
  }

  @Override
  @Transactional
  public Student saveStudent(Student student) {
    return studentDAO.saveStudent(student);
  }

  @Override
  @Transactional
  public Student updateStudent(Student student) {
    if (studentDAO.getStudent(student.getId()) != null) {
      studentDAO.saveStudent(student);
      return student;
    }

    return null;
  }

  @Override
  @Transactional
  public Student getStudent(int id) {
    return studentDAO.getStudent(id);
  }

  @Override
  @Transactional
  public void deleteStudent(int id) {
    studentDAO.deleteStudent(id);
  }
}