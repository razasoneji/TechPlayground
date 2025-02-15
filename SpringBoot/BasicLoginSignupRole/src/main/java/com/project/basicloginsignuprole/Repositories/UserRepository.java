package com.project.basicloginsignuprole.Repositories;

import com.project.basicloginsignuprole.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);




}
