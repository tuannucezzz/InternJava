package tuna.intern.internvnpt.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.dto.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "student.birthday", target = "birthday", dateFormat = "dd/MM/yyyy")
    StudentDto modelToDto(Student student);

    @InheritInverseConfiguration
    Student dtoToModel(StudentDto studentDto);
}
