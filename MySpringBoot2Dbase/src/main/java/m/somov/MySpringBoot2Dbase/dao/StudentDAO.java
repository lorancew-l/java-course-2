package m.somov.MySpringBoot2Dbase.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import m.somov.MySpringBoot2Dbase.entity.Student;

@Repository
public interface StudentDAO {
  List<Student> getAllStudents();

  Student saveStudent(Student student);

  Student getStudent(int id);

  void deleteStudent(int id);
}