package m.somov.MySpringBoot2Dbase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import m.somov.MySpringBoot2Dbase.entity.Student;
import m.somov.MySpringBoot2Dbase.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/students")
  public ResponseEntity<List<Student>> allStudents() {
    var students = studentService.getAllStudents();
    return ResponseEntity.ok(students);
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
    var student = studentService.getStudent(id);
    if (student != null) {
      return ResponseEntity.ok(student);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/students")
  public ResponseEntity<?> saveStudent(@RequestBody Student student) {
    var savedStudent = studentService.saveStudent(student);
    if (savedStudent != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании студента");
    }
  }

  @PutMapping("/students")
  public ResponseEntity<?> updateStudent(@RequestBody Student student) {
    var patchedStudent = studentService.updateStudent(student);

    if (patchedStudent != null) {
      return ResponseEntity.ok(patchedStudent);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с ID " + student.getId() + " не найден");
    }
  }

  @DeleteMapping("/students/{id}")
  public ResponseEntity<Integer> deleteStudent(@PathVariable("id") int id) {
    studentService.deleteStudent(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}