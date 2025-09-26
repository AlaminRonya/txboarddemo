package com.sdlcpro.txdemo.core.service.client;

import com.sdlcpro.txdemo.data.entity.UserInfo;

import java.util.UUID;

public interface UserServiceClient {
    UserInfo getUserInfoById(UUID id);
}
