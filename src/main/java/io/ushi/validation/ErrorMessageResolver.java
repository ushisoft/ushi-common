package io.ushi.validation;

import io.ushi.rest.ErrorEntity;

public interface ErrorMessageResolver {

    String resolve(String message);

    ErrorEntity resolve(String field, UshiError error);
}
