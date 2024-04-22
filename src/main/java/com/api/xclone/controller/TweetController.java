package com.api.xclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.xclone.service.TwitService;

@RestController
@RequestMapping("/api/twits")
public class TweetController {

  @Autowired
  private TwitService twitService;

  @Autowired
  private UserService userService;

}
