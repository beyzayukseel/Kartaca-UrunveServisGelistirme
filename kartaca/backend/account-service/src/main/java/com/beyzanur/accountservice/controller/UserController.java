package com.beyzanur.accountservice.controller;

import com.beyzanur.accountservice.VO.ResponseTemplateVO;
import com.beyzanur.accountservice.dto.LoginRequest;
import com.beyzanur.accountservice.dto.RegisterRequest;
import com.beyzanur.accountservice.model.Role;
import com.beyzanur.accountservice.model.User;
import com.beyzanur.accountservice.service.MyUserDetails;
import com.beyzanur.accountservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin( origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public User authenticate(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        return User.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .email(userDetails.getEmail())
                .role(Role.User)
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody RegisterRequest registerRequest
    ) throws UsernameNotFoundException {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        user.setRole(Role.User);
        return ResponseEntity.ok(userService.createNewUser(user));
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithCalendar(@PathVariable("id") Long id) {
        return userService.getUserWithCalendar(id);
    }
}
