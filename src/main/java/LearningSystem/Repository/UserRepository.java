package LearningSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import LearningSystem.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}