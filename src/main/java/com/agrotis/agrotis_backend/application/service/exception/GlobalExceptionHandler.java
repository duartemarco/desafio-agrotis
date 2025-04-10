package com.agrotis.agrotis_backend.application.service.exception;

import com.agrotis.agrotis_backend.application.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler   {

    @ResponseBody
    @ResponseStatus
    @ExceptionHandler
    public ErrorDTO handleLaboratorioNotFound(LaboratorioNotFoundException laboratorioNotFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Laboratório não encontrado");
        errorDTO.setTimestamp(new Date());
        return errorDTO;
    }


}
