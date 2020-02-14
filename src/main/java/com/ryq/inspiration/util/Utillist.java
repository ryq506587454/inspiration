package com.ryq.inspiration.util;

import lombok.Data;

import java.io.Serializable;
/*
 * 工具类
 * 泛型类
 */

@SuppressWarnings("serial")
@Data
public class Utillist<T> implements Serializable {
    private String msg; // 提示信息
    private int code; // 报错代码
    private T list; // 内容

    private Utillist(String msg, T data, int code) {
        this.msg = msg;
        this.code = code;
        this.list = data;
    }

    public static <T> Utillist<T> CreatUtillist(String msg, T data, int code) {
        return new Utillist<T>(msg, data, code);
    }

}
