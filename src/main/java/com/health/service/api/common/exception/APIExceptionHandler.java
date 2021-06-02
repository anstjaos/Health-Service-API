package com.health.service.api.common.exception;

import com.health.service.api.common.model.APIResponse;
import com.health.service.api.common.model.APIResponseHeader;
import com.health.service.api.common.model.command.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {

    @ExceptionHandler({HealthServiceException.class})
    @ResponseBody
    public APIResponse handleCustomException(HealthServiceException e) {
        return createResponse(e.getResultCode(), e.getMessage());
    }

    private APIResponse createResponse(ResultCode resultCode, String message) {
        return new APIResponse(APIResponseHeader.create(false, resultCode.getCode(), message), null);
    }
}
