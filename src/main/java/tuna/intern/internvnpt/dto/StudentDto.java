package tuna.intern.internvnpt.dto;

import lombok.Data;
import tuna.intern.internvnpt.domain.Classes;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDto {
    private int id;
    private String name;
    private boolean gender;
//        @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String birthday;
    private String phone;
    private String email;
    private Set<Classes> classes;
}
