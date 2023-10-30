package cgg.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUserName(String userName);
}