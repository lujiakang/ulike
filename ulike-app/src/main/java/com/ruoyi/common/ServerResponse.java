package com.ruoyi.common;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * 统一响应结果封装类
 *
 * @author ruoyi
 */
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 成功响应（无数据）
     */
    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(0);
    }

    /**
     * 成功响应（带数据）
     */
    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(0, data);
    }

    /**
     * 成功响应（带消息）
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(0, msg);
    }

    /**
     * 成功响应（带消息和数据）
     */
    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(0, msg, data);
    }

    /**
     * 失败响应（默认错误消息）
     */
    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(1, "操作失败");
    }

    /**
     * 失败响应（自定义错误消息）
     */
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(1, errorMessage);
    }

    /**
     * 失败响应（自定义状态码和消息）
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return this.status == 0;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
