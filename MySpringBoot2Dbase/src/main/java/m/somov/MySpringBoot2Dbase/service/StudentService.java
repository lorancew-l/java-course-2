package m.somov.MySpringBoot2Dbase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import m.somov.MySpringBoot2Dbase.entity.Student;

@Service
public interface StudentService {
  List<Student> getAllStudents();

  Student saveStudent(Student student);

  Student updateStudent(Student student);

  Student getStudent(int id);

  void deleteStudent(int id);
}