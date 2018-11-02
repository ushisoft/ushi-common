package io.ushi.rest.query;

import java.util.List;

public class Condition {

    public enum BooleanOperator {
        AND,
        OR
    }

    BooleanOperator operator;

    List<Criteria> criterias;
}
