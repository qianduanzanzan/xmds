package com.atxiaoming.utils;

import com.atxiaoming.entity.ResultEntity;

public class resultUtil {
    /**
     * @param object
     * @return
     * 成功时调用的方法
     */
    public static ResultEntity success(Object object){

        ResultEntity result = new ResultEntity();
        result.setCode("success");
        result.setMsg("成功");
        result.setData(object);
        return result;

    }

    /**
     * @return
     * 没有数据时返回null
     */
    public static ResultEntity success() {
        return success(null);
    }

    /**
     * @param message
     * @return
     * 失败时调用的方法
     */
    public static ResultEntity Error(String message){

        ResultEntity result = new ResultEntity();
        result.setCode("failed");
        result.setMsg(message);
        return result;
    }
}
