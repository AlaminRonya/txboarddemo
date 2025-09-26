package com.sdlcpro.txdemo.presenter.common;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreateApi <Req, Resp>{
    ResponseEntity<ApiResponseDto<Resp>> save(@Valid @RequestBody Req param);
}