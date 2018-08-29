package io.ushi.validation;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class CachingResourceBundleLocatorTest {

    @Test
    void getResourceBundle() {
        CachingResourceBundleLocator locator = new CachingResourceBundleLocator();
        ResourceBundle bundle = locator.getResourceBundle(new Locale("zh", "CN"));
        assertEquals("god", bundle.getString("test"));
    }
}