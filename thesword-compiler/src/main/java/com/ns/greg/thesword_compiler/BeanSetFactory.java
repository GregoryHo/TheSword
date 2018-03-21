package com.ns.greg.thesword_compiler;

import android.support.annotation.Nullable;
import com.ns.greg.thesword_annotations.Info;
import com.ns.greg.thesword_annotations.Status;
import java.lang.annotation.Annotation;

/**
 * @author gregho
 * @since 2018/3/13
 */
class BeanSetFactory {

  private Annotation annotation;

  private BeanSetFactory(Annotation annotation) {
    this.annotation = annotation;
  }

  static BeanSetFactory getFactory(Annotation annotation) {
    return new BeanSetFactory(annotation);
  }

  static String[] getChildBeans(Annotation annotation) {
    if (annotation instanceof Info) {
      return ((Info) annotation).childBeans();
    } else if (annotation instanceof Status) {
      return ((Status) annotation).childBeans();
    }

    return null;
  }

  BeanSet parseBean(String bean) {
    if (annotation instanceof Info) {
      return parseInfoBean(bean);
    } else if (annotation instanceof Status) {
      return parseStatusBean(bean);
    }

    return null;
  }

  @Nullable private BeanSet parseInfoBean(String bean) {
    switch (bean) {
      case "name":
        return new BeanSet("userName", String.class, MethodType.GET_AND_SET);

      case "sex":
        return new BeanSet("gender", String.class, MethodType.GET_AND_SET);

      case "age":
        return new BeanSet("age", String.class, MethodType.GET_AND_SET);
    }

    return null;
  }

  @Nullable private BeanSet parseStatusBean(String bean) {
    switch (bean) {
      case "state":
        return new BeanSet("userState", String.class, MethodType.GET);

      case "job":
        return new BeanSet("job", String.class, MethodType.GET_AND_SET);

      case "time":
        return new BeanSet("time", String.class, MethodType.GET);
    }

    return null;
  }
}
