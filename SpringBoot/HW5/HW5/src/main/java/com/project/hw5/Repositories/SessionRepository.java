package com.project.hw5.Repositories;

import com.project.hw5.Entities.SessionEntity;
import com.project.hw5.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    Optional<SessionEntity> findByUser(User user);

    Optional<SessionEntity> findByToken(String token);

    void deleteByUser(User user); // To remove old sessions
}
