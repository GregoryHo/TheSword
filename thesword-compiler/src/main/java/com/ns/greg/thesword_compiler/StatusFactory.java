package com.ns.greg.thesword_compiler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.ns.greg.thesword_annotations.Status;
import java.lang.annotation.Annotation;

/**
 * @author gregho
 * @since 25/04/2018
 */
public class StatusFactory extends BaseFactory<Status> {

  StatusFactory(Annotation annotation) {
    super(annotation);
  }

  @NonNull @Override String[] getChildBeans() {
    return getAnnotation().childBeans();
  }

  @Nullable @Override BeanSet parseBean(@NonNull String bean) {
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
