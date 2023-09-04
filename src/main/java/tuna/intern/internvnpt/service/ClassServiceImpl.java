package tuna.intern.internvnpt.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuna.intern.internvnpt.domain.Classes;
import tuna.intern.internvnpt.repository.ClassRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<Classes> listAll() {
        return classRepository.findAll();
    }

    @Override
    public void save(Classes classes) {
        classRepository.save(classes);
    }

    @Override
    public Classes get(int id) {
        return classRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        classRepository.deleteById(id);
    }

    @Override
    public void deleteClassRegist(int studentId) {
        classRepository.unregistClass(studentId);
    }

    @Override
    public List<Object[]> listClassOfStudent(Integer id) {
        return classRepository.listClassOfStudent(id);
    }

    @Override
    public List<Object[]> listClassNotRegist(Integer id) {
        return classRepository.listClassNotRegist(id);
    }
}
