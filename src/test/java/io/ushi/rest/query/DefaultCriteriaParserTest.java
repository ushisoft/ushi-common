package io.ushi.rest.query;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class DefaultCriteriaParserTest {

    @Test
    void parse() throws IOException {

        DefaultCriteriaParser parser = new DefaultCriteriaParser();

        String content = new String(Files.readAllBytes(Paths.get("src/test/resources/io/ushi/rest/query/test.json")));

        parser.parse(content);

    }
}