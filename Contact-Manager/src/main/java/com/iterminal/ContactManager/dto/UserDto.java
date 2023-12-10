package com.iterminal.ContactManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private long id;
    private String name;
    private String email;
    private String mobile;
    private String company;
    private String title;
    private String group;
    private String photo;
}
