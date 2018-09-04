package io.ushi.rest;

import lombok.Data;

@Data
public class ErrorEntity {

    String field;

    String code;

    String message;

    private ErrorEntity() {}

    public static RequiredField field(String field) {
        return new ErrorEntity.Builder(field);
    }

    public interface RequiredField {
        RequiredCode error(String error);
    }

    public interface RequiredCode {
        OptionalMessage message(String message);
        ErrorEntity build();
    }

    public interface OptionalMessage {
        ErrorEntity build();
    }

    private static class Builder implements RequiredField, RequiredCode, OptionalMessage {

        private final ErrorEntity instance = new ErrorEntity();

        public Builder(String field) {
            instance.field = field;
        }

        @Override
        public RequiredCode error(String error) {
            instance.code = error;
            return this;
        }

        @Override
        public OptionalMessage message(String message) {
            instance.message = message;
            return this;
        }

        @Override
        public ErrorEntity build() {
            return instance;
        }
    }
}
