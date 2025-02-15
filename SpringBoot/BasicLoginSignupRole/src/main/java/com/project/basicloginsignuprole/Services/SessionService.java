package com.project.basicloginsignuprole.Services;


import com.project.basicloginsignuprole.Entities.Session;
import com.project.basicloginsignuprole.Entities.User;
import com.project.basicloginsignuprole.Repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    @Autowired
    private final SessionRepository sessionRepository;
    private final int sessionLimit=2;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void generateNewSession(User user, String refreshToken) {
        List<Session> sessions = sessionRepository.findByUser(user);
        if(sessions.size()>=sessionLimit) {
            sessions.sort(Comparator.comparing(Session::getLastUsedAt));
            Session leastRecentlyUsed = sessions.getFirst();
            sessionRepository.delete(leastRecentlyUsed);
        }
        Session newSession = new Session();
        newSession.setUser(user);
        newSession.setRefreshToken(refreshToken);
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken).orElseThrow(()->new SessionAuthenticationException("Refresh token either invalid or not found , login again."));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
