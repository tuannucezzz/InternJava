package tuna.intern.internvnpt.service;

import org.springframework.stereotype.Service;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.dto.StudentDto;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    public Student getStudentById(Integer studentId);

    public List<StudentDto> listAll();
    public List<Student> listAllTest();

    public void save(Student student);

    public Student createStudent(StudentDto studentDto);

    public Student get(int id);

    public void delete(int id);

//    public Student updateStudentClasses(Integer studentId, Integer classId) {
//        Set<Classes> classesSet = null;
//        Student student = studentRepository.findById(studentId).get();
//        Classes classes = classRepository.findById(classId).get();
//        classesSet = student.getAssignClass();
//        classesSet.add(classes);
//        student.setAssignClass(classesSet);
//        return studentRepository.save(student);
//    }
}

