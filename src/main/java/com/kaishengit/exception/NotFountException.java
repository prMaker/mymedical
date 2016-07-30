package com.kaishengit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2016/7/30.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFountException extends RuntimeException {

}
