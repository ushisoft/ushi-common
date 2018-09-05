package io.ushi.validation;

import io.ushi.rest.ErrorEntity;
import io.ushi.validation.resourceloading.CachingResourceBundleLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultErrorErrorMessageResolver implements ErrorMessageResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultErrorErrorMessageResolver.class);

    private static final String DEFAULT_MESSAGE_SOURCE_NAME = "io.ushi.validation.UshiMessages";

    private final ResourceBundleLocator defaultResourceBundleLocator = new CachingResourceBundleLocator(DEFAULT_MESSAGE_SOURCE_NAME);

    private Locale locale = Locale.getDefault();

    @Override
    public String resolve(String message) {
        ResourceBundle rb = defaultResourceBundleLocator.getResourceBundle(locale);
        if (rb == null) {
            logger.warn("message resource not found with locale {}", locale);
            return message;
        }

        Pattern p = Pattern.compile("^\\{(.*)\\}$");
        Matcher m = p.matcher(message);
        if (m.find()) {
            String formatted = rb.getString(m.group(1));
            if (!StringUtils.isEmpty(formatted)) {
                return formatted;
            }
        }

        return message;
    }

    @Override
    public ErrorEntity resolve(String field, UshiError error) {
        return ErrorEntity.field(field)
                .code(error.getCode())
                .message(resolve(error.getMessage()))
                .build();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
