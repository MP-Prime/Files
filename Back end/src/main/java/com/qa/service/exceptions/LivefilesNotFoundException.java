package com.qa.service.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This particular file does not exist.")
public class LivefilesNotFoundException extends EntityNotFoundException {

}
