package cn.hiczy.vo;

import lombok.Data;

/**
 * 公用返回类
 * @author saco
 */

@Data
public class CommonResult<T> {

    private final static Integer SUCCESS_CODE = 200;
    private final static Integer FAILURE_CODE = -1;

    private Integer code;

    private String message;

    private T data;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = SUCCESS_CODE;
        result.message = "";
        result.data = data;
        return result;
    }


    public static  CommonResult failure(String message) {
        CommonResult result = new CommonResult<>();
        result.code = FAILURE_CODE;
        result.message = message;
        result.data = null;
        return result;
    }

    public static CommonResult failure(Integer code,String message) {
        CommonResult result = new CommonResult<>();
        result.code = code;
        result.message = message;
        result.data = null;
        return result;
    }


}
