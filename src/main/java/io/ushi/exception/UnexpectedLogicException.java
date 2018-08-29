package io.ushi.exception;

import io.ushi.rest.ErrorEntity;

/**
 * 自定义逻辑错误，比如数据不存在、数据重复等，同样是参数错误，稍区别与字段合法性校验
 */
public class UnexpectedLogicException extends RuntimeException {

    private final ErrorEntity errorEntity;

    public UnexpectedLogicException(ErrorEntity errorEntity) {
        this.errorEntity = errorEntity;
    }

    public ErrorEntity getErrorEntity() {
        return this.errorEntity;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder("Logic Unexpected for field: ")
                .append(errorEntity.getField())
                .append(", with error: ").append(errorEntity.getCode());
        return sb.toString();
    }
}
