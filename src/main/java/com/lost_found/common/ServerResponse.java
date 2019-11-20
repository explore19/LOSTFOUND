package com.lost_found.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lost_found.pojo.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description= "返回响应数据")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

    @ApiModelProperty(value = "返回的状态码")
    private int status;
    @ApiModelProperty(value = "返回的信息")
    private String msg;
    @ApiModelProperty(value = "返回的数据")
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status,String message) {
        this.status = status;
        this.msg = message;
    }

    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String message, T data) {
        this.status = status;
        this.msg = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String message) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String message,T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDescription());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int status, String errorMessage){
        return new ServerResponse<T>(status,errorMessage);
    }

    public static <T> ServerResponse<T> needLogin(){
        return new ServerResponse<T>(ResponseCode.NEED_LOGIN.getCode(),"请登录后重试");
    }
}
