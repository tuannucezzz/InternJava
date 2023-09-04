package tuna.intern.internvnpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.domain.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByEmail(String email);

    @Query(value = "select * from users order by id asc ", nativeQuery = true)
    List<UserEntity> listUser();

    @Query(value = "select * from users where id = :id", nativeQuery = true)
    UserEntity findUserById(Integer id);
}
