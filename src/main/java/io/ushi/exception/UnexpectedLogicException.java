package io.ushi.exception;

import io.ushi.validation.UshiError;

/**
 * 自定义逻辑错误，比如数据不存在、数据重复等，同样是参数错误，稍区别与字段合法性校验
 */
public class UnexpectedLogicException extends RuntimeException {

    private String field;

    private UshiError error;

    public UnexpectedLogicException(String field, UshiError error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public UshiError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder("Logic Unexpected for field: ").append(field)
                .append(", with error: ").append(error);
        return sb.toString();
    }
}
