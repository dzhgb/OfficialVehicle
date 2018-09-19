package cn.projectivity.handler;

public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 500;

    public Integer code;
    public String message;
    public String url;
    public T data;

}
