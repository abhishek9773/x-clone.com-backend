package com.api.xclone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.xclone.config.JwtProvider;
import com.api.xclone.exception.UserException;
import com.api.xclone.model.User;
import com.api.xclone.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtProvider jwtProvider;

  @Override
  public User findUserById(Long userId) throws UserException {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserException("user not found with id" + userId));

    return user;
  }

  @Override
  public User findUserProfileByJwt(String jwt) throws UserException {
    String email = jwtProvider.getEmailFromToken(jwt);
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UserException("user not found with email" + email);
    }
    return user;
  }

  @Override
  public User updateUser(Long userId, User req) throws UserException {
    User user = findUserById(userId);

    if (req.getFullName() != null) {
      user.setFullName(req.getFullName());

    }

    if (req.getImage() != null) {
      user.setImage(req.getImage());
    }

    if (req.getBackgroundImage() != null) {
      user.setBackgroundImage(req.getBackgroundImage());
    }

    if (req.getBirthDate() != null) {
      user.setBirthDate(req.getBirthDate());
    }

    if (req.getLocation() != null) {
      user.setLocation(req.getLocation());
    }

    if (req.getBio() != null) {
      user.setBio(req.getBio());
    }
    if (req.getWebside() != null) {
      user.setWebside(req.getWebside());
    }

    return userRepository.save(user);

  }

  @Override
  public User followUser(Long userId, User user) throws UserException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'followUser'");
  }

  @Override
  public List<User> searchUser(String query) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchUser'");
  }

}
