package me.blog.v1.repository;

import me.blog.v1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findUserByEmail(String email);
}
