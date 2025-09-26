package com.sdlcpro.txdemo.presenter.common;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface GetApi<Req, Resp>{
    ResponseEntity<ApiResponseDto<Resp>> findById(@PathVariable("id") Req id);
}