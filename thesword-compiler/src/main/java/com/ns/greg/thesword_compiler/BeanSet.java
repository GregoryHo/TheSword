package com.ns.greg.thesword_compiler;

import com.squareup.javapoet.TypeName;
import java.lang.reflect.Type;

/**
 * @author gregho
 * @since 2018/3/12
 */

class BeanSet {

  private String fieldName;
  private TypeName fieldTypeName;
  private MethodType methodType;

  BeanSet(String fieldName, Type fieldType, MethodType methodType) {
    this(fieldName, TypeName.get(fieldType), methodType);
  }

  BeanSet(String fieldName, TypeName fieldTypeName, MethodType methodType) {
    this.fieldName = fieldName;
    this.fieldTypeName = fieldTypeName;
    this.methodType = methodType;
  }

  String getFieldName() {
    return fieldName;
  }

  TypeName getFieldTypeName() {
    return fieldTypeName;
  }

  String methodSet() {
    if (methodType == MethodType.SET || methodType == MethodType.GET_AND_SET) {
      return "set" + BeanSet.capitalize(fieldName);
    }

    return null;
  }

  String methodGet() {
    if (methodType == MethodType.GET || methodType == MethodType.GET_AND_SET) {
      return "get" + BeanSet.capitalize(fieldName);
    }

    return null;
  }

  private static String capitalize(String str) {
    if (str != null && str.length() != 0) {
      char[] chars = str.toCharArray();
      chars[0] = Character.toUpperCase(chars[0]);
      return new String(chars);
    } else {
      return str;
    }
  }
}
