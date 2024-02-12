package com.orm.onetomany.service;

import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.dto.mapper.CourseMapper;

import com.orm.onetomany.entity.Course;

import com.orm.onetomany.exception.course.CourseNotFoundException;
import com.orm.onetomany.exception.course.CourseTitleCannotChangeException;
import com.orm.onetomany.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;


    public CourseDTO createUpdateCourse(CourseDTO courseDTO) {
        if (courseDTO.getId() == null){
            return createNewCourse(courseDTO);
        }
        return updateCourseDetails(courseDTO);
    }

    @Transactional
    private CourseDTO createNewCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        if (course.getInstructor() != null) {
            course.getInstructor().assignCourseToInstructor(course);
        }
        return courseMapper.toDTO(courseRepository.save(course));
    }

    @Transactional
    private CourseDTO updateCourseDetails(CourseDTO courseDTO) {

        Course existingCourse = findCourseById(courseDTO.getId());
        if (!existingCourse.getTitle().trim().equals(courseDTO.getTitle().trim())) {
           throw new CourseTitleCannotChangeException("Course title can not change.");
        }
        existingCourse.setDescription(courseDTO.getDescription());
        return courseMapper.toDTO(courseRepository.save(existingCourse));
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
    }

    public Course findCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty()) {
            throw new CourseNotFoundException("Course with id " + id + " not found.");
        }
        return course.get();
    }

    public CourseDTO getCourseById(Long id) {
        return courseMapper.toDTO(findCourseById(id));
    }
    @Transactional
    public String deleteCourseById(Long id) {
        Course course = findCourseById(id);
        if (course.getInstructor() != null) {
            course.getInstructor().removeCourseFromInstructor(course);
        }
        courseRepository.delete(course);
        return "Course deleted successfully";
    }


    public Optional<Course> findCourseByTitle(String courseTitle) {
        return courseRepository.findCourseByTitle(courseTitle);
    }

    public List<CourseDTO> findAllCoursesForInstructor(Long instructorId) {
        return getAllCourses().stream().filter(courseDTO -> courseDTO.getInstructorId().equals(instructorId)).toList();
    }


}
