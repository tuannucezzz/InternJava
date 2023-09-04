package tuna.intern.internvnpt.service;

import org.springframework.stereotype.Service;
import tuna.intern.internvnpt.domain.Classes;

import java.util.List;

@Service
public interface ClassService {
    public List<Classes> listAll();

    public void save(Classes classes);

    public Classes get(int id);

    public void delete(int id);
    public void deleteClassRegist(int studentId);

    public List<Object[]> listClassOfStudent(Integer id);
    public List<Object[]> listClassNotRegist(Integer id);
}
