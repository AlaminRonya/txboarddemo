package com.sdlcpro.txdemo.core.mapper;

import com.sdlcpro.txdemo.core.dto.UserInfoDto;
import com.sdlcpro.txdemo.data.entity.UserInfo;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ResponseUserInfoMapper implements Function<UserInfo, UserInfoDto> {
    @Override
    public UserInfoDto apply(UserInfo entity) {
        return entity == null ? null : new UserInfoDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhoneNumber(), entity.getAddress());
    }

}