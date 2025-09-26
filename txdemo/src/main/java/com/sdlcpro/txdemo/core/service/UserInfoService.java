package com.sdlcpro.txdemo.core.service;

import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.core.dto.UserInfoDto;
import com.sdlcpro.txdemo.core.param.UserInfoParam;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface UserInfoService {

    UserInfoDto saveUserInfo(final UserInfoParam param);

    List<UserInfoDto> getUserInfos();

    Page<UserInfoDto> getUserInfos(final PageableDto pageableDto);

    UserInfoDto findUserInfoById(final UUID id);

    void deleteUserInfoById(final UUID id);
}
