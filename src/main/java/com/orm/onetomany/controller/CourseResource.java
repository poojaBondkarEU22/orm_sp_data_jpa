package com.orm.onetomany.controller;

import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseResource {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createNewCourse(@RequestBody CourseDTO courseDTO) {

        courseDTO = courseService.createUpdateCourse(courseDTO);
        return ResponseEntity.ok(courseDTO);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO) {

        courseDTO = courseService.createUpdateCourse(courseDTO);
        return ResponseEntity.ok(courseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseDTO>> getAllCoursesForInstructor(@PathVariable("instructorId") Long instructorId) {
        return ResponseEntity.ok(courseService.findAllCoursesForInstructor(instructorId));
    }



}
