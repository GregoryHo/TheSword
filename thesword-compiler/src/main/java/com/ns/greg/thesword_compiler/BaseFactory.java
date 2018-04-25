package com.ns.greg.thesword_compiler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.annotation.Annotation;

/**
 * @author gregho
 * @since 2018/3/29
 */
abstract class BaseFactory<T extends Annotation> {

  private Annotation annotation;

  BaseFactory(Annotation annotation) {
    this.annotation = annotation;
  }

  @SuppressWarnings("unchecked") T getAnnotation() {
    return (T) annotation;
  }

  @NonNull abstract String[] getChildBeans();

  @Nullable abstract BeanSet parseBean(@NonNull String bean);
}
