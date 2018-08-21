package com.security.validateCode;

/**
 * @ClassName SimpleResonse
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/20 9:43
 * @Version 1.0
 **/
public class SimpleResonse {
    private Object content;

    public SimpleResonse(Object content) {
        this.content = content;
    }
    public SimpleResonse() {

    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
