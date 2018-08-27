package io.ushi.bean;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class BeanUtilsExt {

    public static String[] getNullPropertyNames(Object object) {

        final BeanWrapper bean = new BeanWrapperImpl(object);
        return Stream.of(bean.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> bean.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
