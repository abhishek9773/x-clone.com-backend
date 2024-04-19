package com.api.xclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.xclone.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findBy()

}
