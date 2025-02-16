package com.project.basicloginsignuprole.Services;


import com.project.basicloginsignuprole.Entities.LoginResponse;
import com.project.basicloginsignuprole.Entities.User;
import com.project.basicloginsignuprole.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SessionService sessionService;


//
//    public LoginResponse login(String username, String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//        if (authentication.isAuthenticated()) {
//            return LoginResponse.builder()
//                    .userId( ( (User) authentication.getPrincipal() ).getId())
//                    .accessToken(jwtService.generateAccessToken(username))
//                    .refreshToken(jwtService.generateRefreshToken(username)).build();
//        }
//        throw new RuntimeException("Invalid credentials");
//    }

    public LoginResponse login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            LoginResponse response = new LoginResponse();
            response.setUserId(((User) authentication.getPrincipal()).getId());
            response.setAccessToken(jwtService.generateAccessToken(username));
            String refreshToken = jwtService.generateRefreshToken(username);
            response.setRefreshToken(refreshToken);
            User userr = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
            sessionService.generateNewSession(userr,refreshToken);
            return response;
        }

        throw new RuntimeException("Invalid credentials");
    }

    public User signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

//    public LoginResponse refreshToken(String refreshToken) {
//
//        String Username = jwtService.extractUsername(refreshToken);
//        if(!(jwtService.validateToken(refreshToken,Username))){
//            throw new RuntimeException("Invalid refresh token");
//        }
//        return LoginResponse.builder()
//                .userId(userRepository.findByUsername(Username).get().getId())
//                .accessToken(jwtService.generateAccessToken(Username))
//                .refreshToken(refreshToken).build();
//    }

    public LoginResponse refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        sessionService.validateSession(refreshToken);
        if (!jwtService.validateToken(refreshToken, username)) {
            throw new RuntimeException("Invalid refresh token");
        }

        LoginResponse response = new LoginResponse();
        response.setUserId(userRepository.findByUsername(username).get().getId());
        response.setAccessToken(jwtService.generateAccessToken(username));
        response.setRefreshToken(refreshToken);
        return response;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByUsername(email).orElse(null);
    }

    //public String login(String username, String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateAccessToken(username);
//        }
//        throw new RuntimeException("Invalid credentials");
//    }
}
