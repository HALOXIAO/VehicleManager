package com.vehicle.business.common;

/**
 * @author HALOXIAO
 **/
public class ResultBean<T> {


    private static final long serialVersionUID = 2L;


    /**
     * 返回的信息(主要出错的时候使用)
     */
    private String msg;

    /**
     * 接口返回码, 0表示成功
     * 0   : 成功
     * >0 : 表示已知的异常(例如提示错误等, 需要调用地方单独处理)
     * <0 : 表示未知的异常(不需要单独处理, 调用方统一处理)
     */
    private int code;

    /**
     * 返回的数据
     */
    private T data;

    private ResultBean() {

    }

    public ResultBean(String msg, RESULT_BEAN_STATUS_CODE code) {
        super();
        this.msg = msg;
        this.code = code.getValue();
    }

}
