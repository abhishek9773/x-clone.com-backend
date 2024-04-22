package com.api.xclone.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.api.xclone.Util.TwitUtil;
import com.api.xclone.dto.TwitDto;
import com.api.xclone.dto.UserDto;
import com.api.xclone.model.Twit;
import com.api.xclone.model.User;

public class TwitDtoMapper {

  public static TwitDto toTwitDto(Twit twit, User reqUser) {

    UserDto user = UserDtoMapper.toUserDto(twit.getUser());
    boolean isLiked = TwitUtil.isLikedByReqUser(reqUser, twit);

    List<Long> retwitUserId = new ArrayList<>();

    for(User user1: twit.getRetwitUser()){
      retwitUserId.add(user1.getId());
    }

    TwitDto twitDto = new TwitDto();
    twitDto.setId(twit.getId());
    twitDto.setContent(twit.getContent());
    twitDto.setCreatedAt(twit.getCreatedAt());
    twitDto.setImage(twit.getImage());
    twitDto.setTotalLIkes(twit.)

    return null;

  }

}
