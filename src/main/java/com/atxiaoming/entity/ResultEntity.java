package com.atxiaoming.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultEntity<T>  implements Serializable {
    private String code;
    private String msg;
    private T data;
}
