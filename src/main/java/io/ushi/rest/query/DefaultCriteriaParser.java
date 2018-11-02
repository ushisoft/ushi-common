package io.ushi.rest.query;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.ushi.rest.QueryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class DefaultCriteriaParser implements CriteriaParser {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCriteriaParser.class);

    @Override
    public QueryEntity parse(String json) throws IOException {

        // result
        QueryEntity result = new QueryEntity();

        // parse by jackson
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);
        // continue parsing the token till the end of input is reached
        while (!parser.isClosed()) {
            // get the token
            JsonToken token = parser.nextToken();
            // if its the last token then we are done
            if (token == null) {
                break;
            }
            // look for $select field
            if (JsonToken.FIELD_NAME.equals(token) && "$select".equals(parser.getCurrentName())) {
                // we are entering the $select now. The first token should be a string
                token = parser.nextToken();
                if (!JsonToken.VALUE_STRING.equals(token)) {
                    // bail out
                    logger.error("$select should be a string value.");
                    break;
                }
            }

        }


        return null;
    }

    // fields separated by comma
    private String[] parseSelectClause(String selectFields) {
        String[] result = StringUtils.tokenizeToStringArray(selectFields, ",");
        return StringUtils.removeDuplicateStrings(result);
    }
}
