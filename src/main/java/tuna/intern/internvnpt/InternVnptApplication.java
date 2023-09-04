package tuna.intern.internvnpt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import tuna.intern.internvnpt.domain.Student;

@SpringBootApplication
@EnableCaching
public class InternVnptApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(InternVnptApplication.class, args);
    }
}
