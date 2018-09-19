package cn.projectivity.handler;

import cn.projectivity.handler.ErrorInfo;
import cn.projectivity.handler.JsonException;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * 自定义异常页面展示
 * Created by Skyline on 2017/5/4.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //采用spring-boot 默认的映射/error
    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     *
     * 捕获@RequestMapping注解的方法抛出的Exception异常并处理：
     * 若是ajax请求或请求端接受json数据则返回json信息；否则转发（forward）到默认的/error映射，error.html页面展示信息,因为是forward所以不用再经过拦截器处理。
     * 若是去掉该方法或者去掉注解@ExceptionHandler，则spring-boot对异常的处理：302重定向到到默认的错误异常处理映射/error,因为是302重定向，
     * 所以会经过拦截器处理。
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
        e.printStackTrace();
        String accept = req.getHeader("Accept");

        String requestType = req.getHeader("X-Requested-With");
        boolean ajax = (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) ? true : false;
        if(ajax || accept.contains("json")) {     //ajax请求或者请求端接受json数据
            ErrorInfo<String> r = new ErrorInfo<>();
            r.message = e.getMessage();
            r.code = ErrorInfo.ERROR;
            r.url = req.getRequestURL().toString();
            PrintWriter writer = res.getWriter();
            writer.write(new Gson().toJson(r));
            writer.flush();
            writer.close();
            return null;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", req.getRequestURL());
            mav.setViewName(DEFAULT_ERROR_VIEW);
            return mav;
        }
    }


    /**
     * 会优先处理JsonException异常
     * 返回json格式
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, JsonException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.message = e.getMessage();
        r.code = ErrorInfo.ERROR;
        r.url = req.getRequestURL().toString();
        return r;
    }


}