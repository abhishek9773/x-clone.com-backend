package com.api.xclone.service;

import java.util.List;

import com.api.xclone.exception.TwitException;
import com.api.xclone.exception.UserException;
import com.api.xclone.model.Like;
import com.api.xclone.model.User;

public interface LikeService {

  public Like likeTwit(Long twitId, User user) throws UserException, TwitException;

  public List<Like> getAllLike(Long twitId) throws TwitException;

}
