package io.ushi.validation;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DefaultErrorMessageResolverTest {

    @Test
    void render() {
        DefaultErrorMessageResolver messageResolver = new DefaultErrorMessageResolver();
        messageResolver.setLocale(new Locale("zh", "CN"));
        String withPattern = messageResolver.resolve("{io.ushi.validation.Duplicated.message}");
        assertEquals("数据重复。", withPattern);
        String withoutPattern = messageResolver.resolve("io.ushi.validation.Duplicated.message");
        assertEquals("io.ushi.validation.Duplicated.message", withoutPattern);
    }

}