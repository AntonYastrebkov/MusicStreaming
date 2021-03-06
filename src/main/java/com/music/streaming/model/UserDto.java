package com.music.streaming.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
}
