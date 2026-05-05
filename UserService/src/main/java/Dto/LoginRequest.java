package Dto;


import lombok.Data;

import java.io.Serializable;


@Data
public class LoginRequest implements Serializable {

    private Long id;
    private String username;
    private String password;
}
