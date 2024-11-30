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

import m.somov.MySpringBoot2Dbase.entity.Subject;
import m.somov.MySpringBoot2Dbase.service.SubjectService;

@RestController
@RequestMapping("/api")
public class SubjectController {

  @Autowired
  private SubjectService subjectService;

  @GetMapping("/subjects")
  public ResponseEntity<List<Subject>> allSubjects() {
    var subjects = subjectService.getAllSubjects();
    return ResponseEntity.ok(subjects);
  }

  @GetMapping("/subjects/{id}")
  public ResponseEntity<Subject> getSubject(@PathVariable("id") int id) {
    var Subject = subjectService.getSubject(id);
    if (Subject != null) {
      return ResponseEntity.ok(Subject);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/subjects")
  public ResponseEntity<?> saveSubject(@RequestBody Subject Subject) {
    var savedSubject = subjectService.saveSubject(Subject);
    if (savedSubject != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании студента");
    }
  }

  @PutMapping("/subjects")
  public ResponseEntity<?> updateSubject(@RequestBody Subject Subject) {
    var patchedSubject = subjectService.updateSubject(Subject);

    if (patchedSubject != null) {
      return ResponseEntity.ok(patchedSubject);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с ID " + Subject.getId() + " не найден");
    }
  }

  @DeleteMapping("/subjects/{id}")
  public ResponseEntity<Integer> deleteSubject(@PathVariable("id") int id) {
    subjectService.deleteSubject(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
  }
}