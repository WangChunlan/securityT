package com.security.newcode;

import com.security.validateCode.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 刘邦龙
 * @date 2018-08-16 14:46
 */
public interface ValidateCodeKeeper {
    /**
     * 保存到session
     * @param request
     * @param SESSION_KEY_PREFIX
     * @param validateCode
     */
    void saveValidateCode(ServletWebRequest request, String SESSION_KEY_PREFIX, ValidateCode validateCode);

    ValidateCode getValidateCode(ServletWebRequest request, String SESSION_KEY_PREFIX);

    void removeValidateCode(ServletWebRequest request, String SESSION_KEY_PREFIX);
}
