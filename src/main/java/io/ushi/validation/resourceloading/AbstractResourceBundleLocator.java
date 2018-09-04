package io.ushi.validation.resourceloading;

import io.ushi.validation.ResourceBundleLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractResourceBundleLocator implements ResourceBundleLocator {

    private static final Logger logger = LoggerFactory.getLogger(AbstractResourceBundleLocator.class);

    private final String bundleName;
    private final ClassLoader classLoader;

    private final ConcurrentMap<Locale, ResourceBundle> bundleCache = new ConcurrentHashMap<>();

    public AbstractResourceBundleLocator(String bundleName) {
        this(bundleName, null);
    }

    public AbstractResourceBundleLocator(String bundleName, ClassLoader classLoader) {
        Assert.notNull(bundleName, "bundle name can not be null.");
        this.bundleName = bundleName;
        this.classLoader = classLoader;
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale) {

        ResourceBundle rb = null;

        if (classLoader != null) {
            rb = loadBundle(classLoader, locale, bundleName + " not found by user-provided classloader");
        }

        if (rb == null) {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            if (classLoader != null) {
                rb = loadBundle(classLoader, locale, bundleName + " not found by default classloader");
            }
        }

        return rb;
    }

    private ResourceBundle loadBundle(ClassLoader classLoader, Locale locale, String message) {
        ResourceBundle rb = null;
        try {
            rb = ResourceBundle.getBundle(
                    bundleName,
                    locale,
                    classLoader
            );
        } catch (MissingResourceException e) {
            logger.trace(message, e);
        }
        return rb;
    }
}
