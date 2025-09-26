package com.sdlcpro.txdemo.core.dto;


import java.util.UUID;

public record UserInfoDto(UUID id, String firstName,String lastName, String email, String phoneNumber, String address) {
}