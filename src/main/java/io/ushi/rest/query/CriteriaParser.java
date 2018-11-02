package io.ushi.rest.query;

import io.ushi.rest.QueryEntity;

import java.io.IOException;

public interface CriteriaParser {

    QueryEntity parse(String json) throws IOException;
}
