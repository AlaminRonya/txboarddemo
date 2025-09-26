package com.sdlcpro.txdemo.core.service.impl;

import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.core.dto.UserInfoDto;
import com.sdlcpro.txdemo.core.mapper.ResponseUserInfoMapper;
import com.sdlcpro.txdemo.core.param.UserInfoParam;
import com.sdlcpro.txdemo.core.service.UserInfoService;
import com.sdlcpro.txdemo.core.service.client.UserServiceClient;
import com.sdlcpro.txdemo.data.entity.UserInfo;
import com.sdlcpro.txdemo.data.repository.UserInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserInfoServiceImpl extends AbstractBaseService<UserInfo, UUID> implements UserInfoService, UserServiceClient {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserInfoRepository userInfoRepository;

    private final ResponseUserInfoMapper responseUserInfoMapper;

    protected UserInfoServiceImpl(JpaRepository<UserInfo, UUID> repository, UserInfoRepository userInfoRepository, ResponseUserInfoMapper responseUserInfoMapper) {
        super(repository);
        this.userInfoRepository = userInfoRepository;
        this.responseUserInfoMapper = responseUserInfoMapper;
    }

    @Override
    public UserInfoDto saveUserInfo(UserInfoParam param) {

        UserInfo userInfo = UserInfo.builder()
                .firstName(param.firstName())
                .lastName(param.lastName())
                .email(param.email())
                .phoneNumber(param.phoneNumber())
                .build();

        return responseUserInfoMapper.apply(super.save(userInfo));
    }

    @Override
    public List<UserInfoDto> getUserInfos() {
        return super.findAll().stream().map(responseUserInfoMapper).toList();
    }

    @Override
    public Page<UserInfoDto> getUserInfos(PageableDto pageableDto) {
        return super.findAll(pageableDto.getPageable()).map(responseUserInfoMapper);
    }

    @Override
    public UserInfoDto findUserInfoById(UUID id) {
        return responseUserInfoMapper.apply(userInfo(id));
    }

    @Override
    public void deleteUserInfoById(UUID id) {
        try {
            super.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserInfo getUserInfoById(UUID id) {
        return userInfo(id);
    }

    private UserInfo userInfo(UUID id){

        if (id == null){
            throw new IllegalArgumentException(i18n("x0.is.required","id"));
        }

        return super.findById(id).orElseThrow(()-> new EntityNotFoundException(i18n("x0.not.found", "user.info")));
    }

}
