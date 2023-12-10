package com.iterminal.ContactManager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserException extends RuntimeException{
    private String code;
    private String message;
    private  static final long serialVersionUID = 1L;
}

