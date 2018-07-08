package com.example.demo.controller

import com.example.demo.components.ApiDomain
import com.example.demo.components.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import com.fasterxml.jackson.annotation.*


@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@RequestMapping(value = ApiDomain.HOST_PATH)
abstract class BaseController {
    @ExceptionHandler
    @ResponseBody
    fun handleServiceException(req:HttpServletRequest , response:HttpServletResponse , e:Exception ): ErrorResponse
    {
        var status = when(e){
            is AccessDeniedException -> HttpStatus.FORBIDDEN.value()
            is org.springframework.web.bind.MissingServletRequestParameterException -> 400
            is org.springframework.web.HttpRequestMethodNotSupportedException -> 405
            is java.lang.IllegalArgumentException -> 400
            is org.springframework.http.converter.HttpMessageNotReadableException -> 4002
            is DataCanNotNullException -> 400
            is DataNOTFOUNDException -> 404
            else -> HttpStatus.OK.value()
        }

        if(status != 200){
            var msg = e.message?:e.getLocalizedMessage()
            if(status == 4002){
                msg =   "json data error"
                status = 400
            }
            val error = ErrorResponse(status,msg)
            response.setStatus(status)
            return error
        }
        else throw e
    }

}
