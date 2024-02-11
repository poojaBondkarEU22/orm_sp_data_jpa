package com.orm.onetomany.controller;


import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.dto.InstructorDTO;
import com.orm.onetomany.service.InstructorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
@Log4j2
public class InstructorResource {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructor() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    @PostMapping
    public ResponseEntity<InstructorDTO> createNewInstructor(@RequestBody InstructorDTO instructorDTO) {
        log.info("Request to create new instructor");
        instructorDTO = instructorService.createUpdateInstructor(instructorDTO);
        return ResponseEntity.ok(instructorDTO);
    }

    @PutMapping
    public ResponseEntity<InstructorDTO> updateInstructorDetails(@RequestBody InstructorDTO instructorDTO) {
        log.info("Request to update  instructor");
        instructorDTO = instructorService.createUpdateInstructor(instructorDTO);
        return ResponseEntity.ok(instructorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable("id") Long id) {
        log.info("Requested to delete instructor with id {} " , id);
       return ResponseEntity.ok(instructorService.deleteInstructorById(id));
    }

    @PostMapping("/{instructorId}/course/{courseId}/assign")
    public ResponseEntity<InstructorDTO> assignCourseToInstructor(@PathVariable("instructorId") Long instructorId , @PathVariable("courseId") Long courseId) {
        log.info("Request to add course with id {} to an instructor with id {} ", courseId,instructorId);
        InstructorDTO  instructorDTO = instructorService.assignCourseToInstructor(instructorId,courseId);
        return new ResponseEntity<>(instructorDTO, HttpStatus.OK);
    }

    @PostMapping("/{instructorId}/course/{courseId}/remove")
    public ResponseEntity<InstructorDTO> removeCourseToInstructor(@PathVariable("instructorId") Long instructorId , @PathVariable("courseId") Long courseId) {
        log.info("Request to remove course with id {} from an instructor with id {} ", courseId,instructorId);
        InstructorDTO instructorDTO = instructorService.removeCourseFromInstructor(instructorId,courseId);
        return new ResponseEntity<>(instructorDTO, HttpStatus.OK);
    }

}
