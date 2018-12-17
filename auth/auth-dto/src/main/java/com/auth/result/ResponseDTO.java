package com.auth.result;



/**
 * Resource返回结果
 */
public class ResponseDTO<T> {
    private T data;

    private String code;

    private String message;

    public ResponseDTO(){

    }
    public ResponseDTO(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public static ResponseDTO ok(String code,String message) {
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.code = code;
        responseDTO.message = message;
        return responseDTO;
    }
    public static <T>ResponseDTO ok(T data,String code,String message) {
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.data=data;
        responseDTO.code = code;
        responseDTO.message = message;
        return responseDTO;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
