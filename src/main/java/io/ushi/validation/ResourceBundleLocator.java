package io.ushi.validation;

import java.util.Locale;
import java.util.ResourceBundle;

public interface ResourceBundleLocator {
    ResourceBundle getResourceBundle(Locale locale);
}
