package com.orm.onetomany.controller;

import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.dto.StudentDTO;
import com.orm.onetomany.repository.ReviewRepository;
import com.orm.onetomany.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class StudentResource {
    
    private final StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody StudentDTO studentDTO) {
        studentDTO = studentService.createUpdateStudent(studentDTO);
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/students")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        studentDTO = studentService.createUpdateStudent(studentDTO);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }

    @GetMapping("students/{studentId}/courses")
    public ResponseEntity<List<CourseDTO>> getStudentAllCourses(@PathVariable("studentId") Long id) {
        return ResponseEntity.ok(studentService.getStudentAllCourses(id));
    }

    @PostMapping("students/{studentId}/courses/{courseId}/assign")
    public ResponseEntity<StudentDTO> addNewCourseToStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(studentService.assignCourseToStudent(studentId,courseId));
    }

    @PostMapping("students/{studentId}/courses/{courseId}/remove")
    public ResponseEntity<StudentDTO> removeCourseFromStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(studentService.removeCourseFromStudent(studentId,courseId));
    }

    @GetMapping("/courses/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getAllStudentsOfCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(studentService.getAllStudentOfCourse(courseId));
    }


}
