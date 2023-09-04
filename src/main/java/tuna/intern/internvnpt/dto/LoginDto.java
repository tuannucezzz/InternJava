package tuna.intern.internvnpt.dto;

import lombok.Data;
import tuna.intern.internvnpt.annotation.ValidUser;

@Data
public class LoginDto {
    @ValidUser(message = "Email: ${validatedValue} is not present in DB.")
    private String email;
    private String password;
}
