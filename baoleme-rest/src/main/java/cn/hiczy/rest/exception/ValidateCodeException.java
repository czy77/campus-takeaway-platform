package cn.hiczy.rest.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author saco
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String message) {
        super(message);
    }


}
