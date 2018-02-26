package cn.com.myproject.util;


import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/6/27.
 */
public class Message<T> implements Serializable {
    /**
     * 请求结果，0为失败，1为成功
     */
    private int result;


    /**
     * 请求结果信息
     */
    private String message;


    /**
     * 请求的返回数据对象，也将被转为json格式
     */
    private T data;

    public Message() {

    }

    public Message(int result,String message) {
        this.result = result;
        this.message = message;
    }

    public Message(int result,String message,T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
