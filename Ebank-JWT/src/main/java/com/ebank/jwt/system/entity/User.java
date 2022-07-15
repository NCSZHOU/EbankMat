package com.ebank.jwt.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel(value = "User", description = "user information")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userName;

    private String password;

    private int enabled;
}
