package io.ushi.validation.resourceloading;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CachingResourceBundleLocator extends AbstractResourceBundleLocator {

    private final ConcurrentMap<Locale, ResourceBundle> bundleCache = new ConcurrentHashMap<>();

    public CachingResourceBundleLocator(String bundleName) {
        super(bundleName);
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale) {
        ResourceBundle cachedResourceBundle = bundleCache.get(locale);
        if (cachedResourceBundle == null) {
            final ResourceBundle bundle = super.getResourceBundle(locale);
            if (bundle != null) {
                cachedResourceBundle = bundleCache.putIfAbsent(locale, bundle);
                if (cachedResourceBundle == null) {
                    return bundle;
                }
            }
        }
        return cachedResourceBundle;
    }
}
