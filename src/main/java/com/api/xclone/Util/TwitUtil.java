package com.api.xclone.Util;

import com.api.xclone.model.Like;
import com.api.xclone.model.Twit;
import com.api.xclone.model.User;

public class TwitUtil {

  public final static boolean isLikedByReqUser(User reqUser, Twit twit) {
    for (Like like : twit.getLikes()) {
      if (like.getUser().getId().equals((reqUser.getId()))) {
        return true;
      }
    }
    return false;

  }

  public final static boolean isRetwitedByReqUser(User reqUser, Twit twit) {
    for (User user : twit.getRetwitUser()) {
      if (user.getId().equals(reqUser.getId())) {
        return true;
      }
    }
    return false;
  }

}
