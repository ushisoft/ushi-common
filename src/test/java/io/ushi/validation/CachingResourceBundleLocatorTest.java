package io.ushi.validation;

import io.ushi.validation.resourceloading.AbstractResourceBundleLocator;
import io.ushi.validation.resourceloading.CachingResourceBundleLocator;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class CachingResourceBundleLocatorTest {

    @Test
    void getResourceBundle() {
        AbstractResourceBundleLocator locator = new CachingResourceBundleLocator("io.ushi.validation.UshiMessages");
        ResourceBundle bundle = locator.getResourceBundle(new Locale("zh", "CN"));
        assertEquals("god", bundle.getString("test"));
    }
}