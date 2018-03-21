package com.ns.greg.thesword_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gregho
 * @since 2018/3/21
 */

@Retention(RetentionPolicy.CLASS) @Target(ElementType.TYPE) public @interface Status {

  String[] childBeans() default {
      "state", "time", "job"
  };
}
