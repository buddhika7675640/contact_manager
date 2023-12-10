package com.iterminal.ContactManager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ControllerException extends RuntimeException{
    private  static final long serialVersionUID = 1L;
    private String code;
    private String message;
}
