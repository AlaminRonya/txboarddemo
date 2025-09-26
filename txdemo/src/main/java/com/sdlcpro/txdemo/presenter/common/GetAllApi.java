package com.sdlcpro.txdemo.presenter.common;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GetAllApi<Resp>{
    ResponseEntity<ApiResponseDto<List<Resp>>> findAll();

}