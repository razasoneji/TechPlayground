package com.project.basicloginsignuprole.Handlers;

import com.project.basicloginsignuprole.Entities.Role;
import com.project.basicloginsignuprole.Entities.User;
import com.project.basicloginsignuprole.Repositories.UserRepository;
import com.project.basicloginsignuprole.Services.AuthService;
import com.project.basicloginsignuprole.Services.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OAuth2SuccessHandler.class);



    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    public OAuth2SuccessHandler( UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User user = (DefaultOAuth2User) token.getPrincipal();
        log.info(user.getAttribute("email"));

        User userOfSystem = userRepository.findByUsername(user.getAttribute("email")).orElse(null);
        if(userOfSystem == null) {
            User newUser = new User();
            newUser.setUsername(user.getAttribute("email"));
            newUser.setName(user.getAttribute("name"));
            newUser.setPassword(UUID.randomUUID().toString()); // Generate a random string as a placeholder
            newUser.setRole(Role.USER);

            userOfSystem = userRepository.save(newUser);




            String accessToken = jwtService.generateAccessToken(userOfSystem.getUsername());
            String refreshToken = jwtService.generateRefreshToken(userOfSystem.getUsername());
            Cookie cookie = new Cookie("refreshToken", refreshToken);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            String frondEndUrl = "http://localhost:8080/home.html?token=" + accessToken;

            // Also have to make this home.html as non restricted url as springboot also takes in consideration protection.
            getRedirectStrategy().sendRedirect(request, response, frondEndUrl);
        }

    }
}
