package io.ushi.rest;

import lombok.Data;

@Data
public class ErrorEntity {

    String field;

    String code;

    String message;
}
