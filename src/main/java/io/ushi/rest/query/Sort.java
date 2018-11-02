package io.ushi.rest.query;

import lombok.Data;

@Data
public class Sort {

    public enum Order {
        ASC,
        DESC
    }

    String field;

    Order order = Order.ASC;
}
