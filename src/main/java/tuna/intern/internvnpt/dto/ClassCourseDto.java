package tuna.intern.internvnpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCourseDto {
    private int id;
    private String className;
    private String courseName;

    public ClassCourseDto(int id, String className) {
        this.id = id;
        this.className = className;
    }
}
