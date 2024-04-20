package com.api.xclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.xclone.config.JwtProvider;
import com.api.xclone.exception.UserException;
import com.api.xclone.model.User;
import com.api.xclone.model.Varification;
import com.api.xclone.repository.UserRepository;
import com.api.xclone.response.AuthResponse;
import com.api.xclone.service.CustomUserDetailsServiceImplementaton;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserRepository passwordEncoder;

  @Autowired
  private JwtProvider jwtProvider;

  @Autowired
  private CustomUserDetailsServiceImplementaton customeUserDetails;

  @PostMapping("/signup")
  public String postMethodName(@RequestBody String entity) {
    // TODO: process POST request

    return entity;
  }

  public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

    String email = user.getEmail();
    String password = user.getPassword();
    String fullName = user.getFullName();
    String birthDate = user.getBirthDate();

    User isEmailExist = UserRepository.findByEmail(email);
    if (isEmailExist != null) {
      throw new UserException("Email is already used with another account");
    }

    User createdUser = new User();
    createdUser.setEmail(email);
    createdUser.setFullName(fullName);
    createdUser.setPassword(birthDate);
    createdUser.setVerification(new Varification());

    User savedUser = UserRepository.save(createdUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtProvider.generateToken(authentication);
    AuthResponse res = new AuthResponse(token, true);

    return new ResponseEntity<AuthResponse>(res, HttpStatus.CREATED);

  }

  @PostMapping("/signin")
  public ResponseEntity<AuthResponse> signin(@RequestBody User user) {
    String username = user.getEmail();
    String password = user.getPassword();

    Authentication authentication = authenticate(username, password);

    String token = jwtProvider.generateToken(authentication);
    AuthResponse res = new AuthResponse(token, true);

    return new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);

  }

  private Authentication authenticate(String username, String password) {
    UserDetails userDetails = customeUserDetails.loadUserByUsername(username);

    if (userDetails == null) {
      throw new BadCredentialsException("Invalid username");
    }
    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("invalid username or password");
    }

    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

  }
}
