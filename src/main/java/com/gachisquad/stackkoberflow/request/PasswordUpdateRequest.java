package com.gachisquad.stackkoberflow.request;

import lombok.ToString;

@ToString
public class PasswordUpdateRequest {
    public String oldPassword;
    public String newPassword;

}
