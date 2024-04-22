package com.api.xclone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.xclone.exception.TwitException;
import com.api.xclone.exception.UserException;
import com.api.xclone.model.Like;
import com.api.xclone.model.Twit;
import com.api.xclone.model.User;
import com.api.xclone.repository.LikeRepository;
import com.api.xclone.repository.TwitRepository;

@Service
public class LikeServiceImplementation implements LikeService {
  @Autowired
  private LikeRepository likeRepository;

  @Autowired
  private TwitService twitService;

  @Autowired
  private TwitRepository twitReopsitory;

  @Override
  public Like likeTwit(Long twitId, User user) throws UserException, TwitException {
    Like isLikeExist = likeRepository.isLikeExist(user.getId(), twitId);

    if (isLikeExist != null) {
      likeRepository.deleteById(isLikeExist.getId());
      return isLikeExist;
    }

    Twit twit = twitService.findById(twitId);

    Like like = new Like();
    like.setTwit(twit);
    like.setUser(user);

    Like savedLike = likeRepository.save(like);

    twit.getLikes().add(savedLike);
    twitReopsitory.save(twit);

    return null;
  }

  @Override
  public List<Like> getAllLike(Long twitId) throws TwitException {
    Twit twit = twitService.findById(twitId);

    List<Like> likes = likeRepository.findByTwitId(twitId);

    return likes;

  }

}
