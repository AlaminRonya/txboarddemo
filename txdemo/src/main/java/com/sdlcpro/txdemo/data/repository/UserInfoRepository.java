package com.sdlcpro.txdemo.data.repository;

import com.sdlcpro.txdemo.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
}