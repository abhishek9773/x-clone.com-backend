package com.api.xclone.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Twit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String image;
  private String video;

  @ManyToOne
  private User user;

  private String content;

  @OneToMany(mappedBy = "twit", cascade = CascadeType.ALL)
  private List<Like> likes = new ArrayList<>();

  @OneToMany
  private List<Twit> replyTwits = new ArrayList<>();

  @ManyToMany
  private List<User> retwitUser = new ArrayList<>();

  @ManyToOne
  private Twit replayFor;

  private boolean isReply;
  private boolean isTwit;

}
