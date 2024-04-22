package com.api.xclone.service;

import java.util.List;

import com.api.xclone.exception.TwitException;
import com.api.xclone.exception.UserException;
import com.api.xclone.model.Twit;
import com.api.xclone.model.User;
import com.api.xclone.request.TwitReplyRequest;

public interface TwitService {

  public Twit createTwit(Twit req, User user) throws UserException;

  public List<Twit> findAllTwit();

  public Twit retTwit(Long twitId, User user) throws UserException, TwitException;

  public Twit findById(Long twitId) throws TwitException;

  public void deleteTwitById(Long twitId, Long userId) throws TwitException, UserException;

  public Twit removeFromRetTwit(Long twitId, User user) throws TwitException, UserException;

  public Twit createReply(TwitReplyRequest req, User user) throws TwitException;

  public List<Twit> getUserTwit(User user);

  public List<Twit> findByLikesContainsUser(User user);

}
