package com.codewithrahul.blog.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private String about;
}
