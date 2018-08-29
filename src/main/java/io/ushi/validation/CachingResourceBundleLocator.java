package io.ushi.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CachingResourceBundleLocator {

    private static final Logger logger = LoggerFactory.getLogger(CachingResourceBundleLocator.class);

    private static final String USHI_MESSAGE_RESOURCE_BASE_NAME = "io.ushi.validation.UshiMessages";

    private final ConcurrentMap<Locale, ResourceBundle> bundleCache = new ConcurrentHashMap<>();

    private final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    public ResourceBundle getResourceBundle(Locale locale) {

        ResourceBundle cachedResourceBundle = bundleCache.get(locale);
        if (cachedResourceBundle == null) {
            final ResourceBundle bundle = loadBundle(locale);
            if (bundle != null) {
                cachedResourceBundle = bundleCache.putIfAbsent(locale, bundle);
                if (cachedResourceBundle == null) {
                    return bundle;
                }
            }
        }
        return cachedResourceBundle;
    }

    public ResourceBundle loadBundle(Locale locale) {
        logger.debug("load properties with local: {}", locale);
        return ResourceBundle.getBundle(USHI_MESSAGE_RESOURCE_BASE_NAME, locale, classLoader);
    }
}
