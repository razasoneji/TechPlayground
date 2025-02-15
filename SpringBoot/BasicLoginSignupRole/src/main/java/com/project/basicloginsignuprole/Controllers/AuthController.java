package com.project.basicloginsignuprole.Controllers;

//public class AuthController {
//}


import com.project.basicloginsignuprole.Entities.LoginResponse;
import com.project.basicloginsignuprole.Entities.User;
import com.project.basicloginsignuprole.Services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        LoginResponse loginresponse = authService.login(username, password);
        Cookie cookie = new Cookie("refreshToken", loginresponse.getRefreshToken());
        cookie.setHttpOnly(true);
        //cookie.setSecure(true); cookie only sent via https , only server can set this to true and no one can intercept it
        // in local host not possible but if deployed and ssl then it is possible.
        response.addCookie(cookie);
        return ResponseEntity.ok(loginresponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh( HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("cookie not found , login again cannot refresh."));

        LoginResponse loginResponse = authService.refreshToken(refreshToken);
        //No need to set cookie again as already set in user's browser.
        return ResponseEntity.ok(loginResponse);
        // we get array of cookies in request
        //Cookie[] cookies = request.getCookies();
//        String refreshToken;
//        for(Cookie cookie : request.getCookies()) {
//            if("refreshToken".equals(cookie.getName())) {
//                refreshToken = cookie.getValue();
//            }
//        }
//        if(refreshToken==null||refreshToken.length()==0||refreshToken.equals("")){
//            throw new RuntimeException("Could not find Refresh token, please login again");
//        }

    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User newUser = authService.signup(user);
        return ResponseEntity.ok(newUser);
    }
}
