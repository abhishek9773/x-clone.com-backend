package com.api.xclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.xclone.model.Twit;
import com.api.xclone.model.User;

public interface TwitRepository extends JpaRepository<Twit, Long> {

  List<Twit> findAllByIsTwitTrueOrderByCreatedAtDesc();

  List<Twit> findByRetwituserContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(User user, Long userId);

  List<Twit> findByLikesContainingOrderByCreatedAtDesc(User user);

  @Query("SELECT t FROM Twit t JOIN t.likes I where I.user.id=:userId")
  List<Twit> findByLikesUser_id(Long userId);

}
