package com.ns.greg.thesword_compiler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.ns.greg.thesword_annotations.Info;
import java.lang.annotation.Annotation;

/**
 * @author gregho
 * @since 25/04/2018
 */
public class InfoFactory extends BaseFactory<Info> {

  InfoFactory(Annotation annotation) {
    super(annotation);
  }

  @NonNull @Override String[] getChildBeans() {
    return getAnnotation().childBeans();
  }

  @Nullable @Override BeanSet parseBean(@NonNull String bean) {
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
}
