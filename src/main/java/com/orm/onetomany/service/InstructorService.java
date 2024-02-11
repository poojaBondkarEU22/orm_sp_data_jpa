package com.orm.onetomany.service;

import com.orm.onetomany.dto.InstructorDTO;
import com.orm.onetomany.dto.mapper.InstructorMapper;
import com.orm.onetomany.entity.Course;
import com.orm.onetomany.entity.Instructor;
import com.orm.onetomany.exception.instructor.CourseAlreadyAssignToInstructorException;
import com.orm.onetomany.exception.instructor.CourseBelongToDifferentInstructorException;
import com.orm.onetomany.exception.instructor.InstructorNotFoundException;
import com.orm.onetomany.exception.instructor.NotSupportedOperationException;
import com.orm.onetomany.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class InstructorService {

    private final InstructorMapper instructorMapper;
    private final InstructorRepository instructorRepository;

    private final CourseService courseService;

    public InstructorDTO createUpdateInstructor(InstructorDTO instructorDTO) {
        if (instructorDTO.getId() == null) {
            return createNewInstructor(instructorDTO);
        }
       return updateInstructorDetails(instructorDTO);
    }

    @Transactional
    private InstructorDTO createNewInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        if (instructor.getCourses() != null) {
            instructor.getCourses().forEach(instructor::assignCourseToInstructor);
        }

        Instructor savedInstructor = instructorRepository.save(instructor);
        return instructorMapper.toDTO(savedInstructor);
    }
    @Transactional
    public InstructorDTO assignCourseToInstructor(Long instructorId , Long courseId) {
        Instructor instructor = findByInstructorId(instructorId);
        Course course = courseService.findCourseById(courseId);
        if(Objects.nonNull(course.getInstructor()) && !course.getInstructor().equals(instructor)) {
            throw new CourseAlreadyAssignToInstructorException("Course is with course id " +courseId + " is assign to instructor with id "+ course.getInstructor().getId());
        }
        instructor.assignCourseToInstructor(course);
        return instructorMapper.toDTO(instructorRepository.save(instructor));
    }
    @Transactional
    public InstructorDTO removeCourseFromInstructor(Long instructorId , Long courseId) {
        log.info("Removing course to instructor");
        Instructor instructor = findByInstructorId(instructorId);
        Course course = courseService.findCourseById(courseId);
        if (!instructor.getCourses().contains(course)) {
            throw new CourseBelongToDifferentInstructorException("The course with id "+ courseId + " does not belongs to instructor with id " + instructorId);
        }
        instructor.removeCourseFromInstructor(course);
        return instructorMapper.toDTO(instructorRepository.save(instructor));
    }


    @Transactional
    private InstructorDTO updateInstructorDetails(InstructorDTO instructorDTO)  {
        Instructor existingInstructor = findByInstructorId(instructorDTO.getId());
        Instructor instructor = instructorMapper.toEntity(instructorDTO);

        List<Course> newCoursesForInstructor = new ArrayList<>();
        if (instructor.getCourses() != null) {
            newCoursesForInstructor = instructor.getCourses().stream().peek(course -> {
                Optional<Course> existingCourse = courseService.findCourseByTitle(course.getTitle());
                if (existingCourse.isPresent()) {
                    throw new NotSupportedOperationException("Currently api only support to add new course to the instructor." );
                }
            }).toList();
        }

        newCoursesForInstructor.stream().filter(Objects::nonNull).forEach(existingInstructor::assignCourseToInstructor);

        existingInstructor.setFirstName(instructor.getFirstName());
        existingInstructor.setLastName(instructor.getLastName());
        existingInstructor.setEmail(instructor.getEmail());
        return instructorMapper.toDTO(instructorRepository.save(existingInstructor));
    }

    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository.findAll().stream().map(instructorMapper::toDTO).toList();
    }

    private Instructor findByInstructorId(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isEmpty()) {
            throw new InstructorNotFoundException("Instructor with id " + id +" not found.");
        }

        return instructor.get();
    }

    public InstructorDTO getInstructorById(Long instructorId) {
        return instructorMapper.toDTO(findByInstructorId(instructorId));
    }

    public String deleteInstructorById(Long id) {
        log.info("Deleting instructor without deleting courses");
        Instructor instructor = findByInstructorId(id);
        if (instructor.getCourses() != null) {
            for (Course course : new ArrayList<>(instructor.getCourses())) {
                instructor.removeCourseFromInstructor(course);
            }
        }
        instructorRepository.delete(instructor);
        return "Instructor deleted successfully";
    }


}



