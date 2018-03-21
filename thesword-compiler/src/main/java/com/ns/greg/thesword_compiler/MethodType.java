package com.ns.greg.thesword_compiler;

import android.support.annotation.Nullable;

/**
 * @author gregho
 * @since 2018/3/12
 */

enum MethodType {

  GET(0),
  SET(1),
  GET_AND_SET(2);

  final int value;

  MethodType(int value) {
    this.value = value;
  }

  @Nullable static MethodType fromValue(int value) {
    for (MethodType type: values()) {
      if (type.value == value) {
        return type;
      }
    }

    return null;
  }
}
