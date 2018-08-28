package com.security.validateCode;

import com.security.utils.SecurityConstants;

/**
 * @ClassName ValidateCodeType
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/21 9:11
 * @Version 1.0
 **/
public enum ValidateCodeType {
    SMS {
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }

        public String getSessionKey(){
            return SecurityConstants.DEFAULT_SESSION_KEY_FOR_CODE_SMS;
        }
    },
    IMAGE {
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }

        public String getSessionKey(){
            return SecurityConstants.DEFAULT_SESSION_KEY_FOR_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数名字
     * @return
     */
    public abstract String getParamNameOnValidate();

    public abstract String getSessionKey();


}
