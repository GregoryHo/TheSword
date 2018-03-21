package com.ns.greg.thesword_compiler;

import com.squareup.javapoet.ClassName;

/**
 * @author gregho
 * @since 2018/3/14
 */

class Util {

  private Util() {
    throw new AssertionError("No instance.");
  }

  static ClassName getClassNameInGson(String className) {
    return getClassName("com.google.gson.annotations", className);
  }

  static ClassName getListClassName() {
    return getClassName("java.util", "List");
  }

  static ClassName getClassNameInExternal(String className) {
    return getClassName("com.edimax.lifeknife_annotations.external", className);
  }

  private static ClassName getClassName(String packageName, String className) {
    return ClassName.get(packageName, className);
  }
}
