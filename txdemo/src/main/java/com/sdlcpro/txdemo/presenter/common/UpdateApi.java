package com.sdlcpro.txdemo.presenter.common;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdateApi<Id, Req, Resp>{
    ResponseEntity<ApiResponseDto<Resp>> update(@PathVariable("id") Id id, @RequestBody Req param);
}