package io.ushi.rest.query;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CriteriaTest {

    @Test
    void parse() {

        Criteria result = Criteria.field("name").expression("zhouleibo").build();
        assertEquals(Criteria.Operation.equal, result.getOperation());

        result = Criteria.field("name").expression("[1, 2]").build();
        assertEquals(Criteria.Operation.between, result.getOperation());
        assertEquals("1", result.getValue());

    }

}