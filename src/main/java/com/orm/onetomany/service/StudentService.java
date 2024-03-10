package com.orm.onetomany.service;

import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.dto.StudentDTO;
import com.orm.onetomany.dto.mapper.CourseMapper;
import com.orm.onetomany.dto.mapper.StudentMapper;
import com.orm.onetomany.entity.Course;
import com.orm.onetomany.entity.Student;
import com.orm.onetomany.exception.student.StudentNotFound;
import com.orm.onetomany.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final CourseService courseService;

    private final CourseMapper courseMapper;

    public StudentDTO createUpdateStudent(StudentDTO studentDTO) {
        if (studentDTO.getId() == null) {
            return createNewStudent(studentDTO);
        }
        return updateStudentData(studentDTO);
    }
    @Transactional
    private StudentDTO createNewStudent(StudentDTO studentDTO) {
        Student newStudent = studentMapper.toEntity(studentDTO);
        if (newStudent.getCourses() != null) {
            newStudent.getCourses().forEach(course -> course.addStudent(newStudent));
        }
        return studentMapper.toDTO(studentRepository.save(newStudent));
    }

    private StudentDTO updateStudentData(StudentDTO studentDTO) {
        Student existingStudent = findStudentById(studentDTO.getId());
        existingStudent.setFirstName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setEmail(studentDTO.getEmail());
        return studentMapper.toDTO(studentRepository.save(existingStudent));
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toDTO).toList();
    }


    public StudentDTO getStudentById(Long id) {
        return studentMapper.toDTO(findStudentById(id));
    }

    public Student findStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFound("Student with id " + id +" not found.");
        }

        return student.get();
    }

    @Transactional
    public StudentDTO assignCourseToStudent(Long studentId, Long courseId) {
        Course course = courseService.findCourseById(courseId);
        Student student = findStudentById(studentId);
        student.addCourse(course);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public StudentDTO removeCourseFromStudent(Long studentId, Long courseId) {
        Course course = courseService.findCourseById(courseId);
        Student student = findStudentById(studentId);
        if (student.getCourses() != null) {
            student.removeCourse(course);
        }
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public List<CourseDTO> getStudentAllCourses(Long studentId) {
        Student student = findStudentById(studentId);
        return student.getCourses().stream().map(courseMapper::toDTO).toList();
    }

    @Transactional
    public String deleteStudentById(Long studentId) {
        Student student = findStudentById(studentId);
        if (student.getCourses() != null) {
            student.getCourses().forEach(course -> course.removeStudent(student));
        }
        studentRepository.delete(student);
        return "Student deleted successfully";
    }

    public List<StudentDTO> getAllStudentOfCourse(Long courseId) {
        Course course = courseService.findCourseById(courseId);
        return course.getStudents().stream().map(studentMapper::toDTO).toList();
    }
}
