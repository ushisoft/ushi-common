package io.ushi.validation;

public enum CommonErrors implements UshiError {

    @Text("{io.ushi.validation.DataNotFound.message}")
    DataNotFound,

    @Text("{io.ushi.validation.Duplicated.message}")
    Duplicated;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        String message = null;
        try {
            message = getClass().getField(name()).getAnnotation(Text.class).value();
        } catch (NoSuchFieldException e) {
            // do nothing
        }
        return message;
    }
}
