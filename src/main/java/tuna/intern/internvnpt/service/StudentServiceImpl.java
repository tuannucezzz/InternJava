package tuna.intern.internvnpt.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.dto.StudentDto;
import tuna.intern.internvnpt.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public List<StudentDto> listAll() {
        List<Student> students = studentRepository.listStudent();
        return students.stream().map((student) -> modelToDto(student)).collect(Collectors.toList());
    }

    @Override
    public List<Student> listAllTest() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = dtoToModel(studentDto);
        Student newStudent = studentRepository.save(student);

        return newStudent;
    }

    @Override
    public Student get(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    private Student dtoToModel(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        return student;
    }

    private StudentDto modelToDto(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

//    public Student updateStudentClasses(Integer studentId, Integer classId) {
//        Set<Classes> classesSet = null;
//        Student student = studentRepository.findById(studentId).get();
//        Classes classes = classRepository.findById(classId).get();
//        classesSet = student.getAssignClass();
//        classesSet.add(classes);
//        student.setAssignClass(classesSet);
//        return studentRepository.save(student);
//    }

//    public StudentDto getUserById(Integer id) {
//        String sql = "SELECT * FROM users WHERE id = ?";
//        RowMapper<Student> rowMapper = (resultSet, rowNum) -> {
//            Student user = new Student();
//            user.setId(resultSet.getInt("id"));
//            user.setName(resultSet.getString("name"));
//            return user;
//        };
//        Student user = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
//        StudentDto userDTO = modelMapper.map(user, StudentDto.class);
//        return userDTO;
//    }
}
