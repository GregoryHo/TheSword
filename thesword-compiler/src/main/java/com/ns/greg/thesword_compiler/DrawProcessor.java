package com.ns.greg.thesword_compiler;

import com.google.auto.service.AutoService;
import com.ns.greg.thesword_annotations.Draw;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * @author gregho
 * @since 2018/3/12
 */

@AutoService(Processor.class) public class DrawProcessor extends AbstractProcessor {

  @Override public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(Draw.class.getCanonicalName());
  }

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
    // Method
    MethodSpec draw = MethodSpec.methodBuilder("draw")
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .returns(void.class)
        .addParameter(String.class, "swordName")
        .addStatement("$T.out.println($S + $N)", System.class, "JavaPoet draw ", "swordName")
        .build();
    // Class
    TypeSpec drawSword = TypeSpec.classBuilder("DrawSword")
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addMethod(draw)
        .build();
    // File
    JavaFile javaFile = JavaFile.builder("com.thesword.drawsword", drawSword).build();
    try {
      javaFile.writeTo(processingEnv.getFiler());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return false;
  }

  @Override public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_7;
  }
}
