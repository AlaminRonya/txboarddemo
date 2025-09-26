package com.sdlcpro.txdemo.presenter.common;

import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.common.PaginationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface GetPaginationApi<Resp> {
    ResponseEntity<PaginationResponse<Resp>> findByPagination(@ModelAttribute PageableDto param);
}
