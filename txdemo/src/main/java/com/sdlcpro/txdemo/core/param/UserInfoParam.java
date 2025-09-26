package com.sdlcpro.txdemo.core.param;


public record UserInfoParam(
        String firstName,String lastName, String email, String phoneNumber, String address
) {
}
