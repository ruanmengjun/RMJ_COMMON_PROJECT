package service.entity;

import java.io.Serializable;

/**
 * 返回的数据
 * （只需要返回成功、失败状态（猜测））
 */
public class ResponseBean implements Serializable {
    public static final int SUCCESS = 0; //成功
    private int code;  // 返回码
    private String msg;  // 消息内容

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
