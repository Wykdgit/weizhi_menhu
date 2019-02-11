package com.wizz.demo.util;

import com.wizz.demo.Config.BusinessException;

public class ResultUtils {
    public static ResultData success(){
        ResultData data = new ResultData();
        data.setMsg("操作成功！");
        return data;
    }

    public static ResultData error(String msg) {
        throw new BusinessException(msg);
    }
}
