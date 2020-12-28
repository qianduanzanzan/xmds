package com.atxiaoming.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端出错"),

    ACCPWD_NOT_EMPTY(50001,"账号或密码不能为空"),
    ACC_ERROR(50002,"账号错误"),
    PWD_ERROR(50003,"密码错误");

    private final Integer code;
    private final String msg;
}
