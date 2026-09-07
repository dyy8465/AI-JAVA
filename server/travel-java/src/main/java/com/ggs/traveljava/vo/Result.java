package com.ggs.traveljava.vo;

import lombok.Data;

@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;
    private String error;
    private String Response;



//    定义泛型方法，用于创建成功的结果对象
    public static <T> Result<T> ok(){
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("成功");
//        result.(true);
        return result;
    }
    public static <T> Result<T> ok(T data){
        Result<T> result = ok();
        result.setData(data);
        return result;
    }
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMsg("失败");
        return result;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> result = fail();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(401);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(String error, String rawResponse) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setError(error);
        result.setResponse(rawResponse);
        return result;
    }
}
