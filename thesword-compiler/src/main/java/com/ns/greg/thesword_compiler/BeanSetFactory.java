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

  private BeanSetFactory() {
    throw new AssertionError("No instance.");
  }

  @Nullable static BaseFactory getFactory(Annotation annotation) {
    if (annotation instanceof Info) {
      return new InfoFactory(annotation);
    } else if (annotation instanceof Status) {
      return new StatusFactory(annotation);
    }

    return null;
  }
}
