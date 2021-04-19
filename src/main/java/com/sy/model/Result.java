package com.sy.model;

import com.github.pagehelper.PageInfo;

/**
 * @ClassName Result
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 10:22
 * @Version 1.0
 */
public class Result {

    private Integer code;
    private String msg;
    private Object data;
    private PageInfo pageInfo;
    private Long count;//总记录数

    public final static Integer CODE_SUCCESS = 0;
    public final static Integer CODE_ERROR = 1;
    public final static String MSG_SUCCESS = "success";
    public final static String MSG_ERROR = "error";

    public Result(){
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
    }

    public void respOK(){
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
    }
    public void respError(){
        this.code = CODE_ERROR;
        this.msg = MSG_ERROR;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {

        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Long getCount() {

        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
