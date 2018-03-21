package com.ns.greg.thesword_compiler;

import com.google.auto.service.AutoService;
import com.ns.greg.thesword_annotations.Info;
import com.ns.greg.thesword_annotations.Status;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author gregho
 * @since 2018/3/12
 */

@AutoService(Processor.class) public class DrawProcessor extends AbstractProcessor {

  private static final String DRAW = "Draw";

  private Elements elementUtils;
  private Filer filter;

  @Override public synchronized void init(ProcessingEnvironment processingEnvironment) {
    super.init(processingEnvironment);
    elementUtils = processingEnvironment.getElementUtils();
    filter = processingEnvironment.getFiler();
  }

  @Override public Set<String> getSupportedAnnotationTypes() {
    Set<String> types = new LinkedHashSet<>();
    for (Class<? extends Annotation> annotation : getSupportedAnnotationClasses()) {
      types.add(annotation.getCanonicalName());
    }

    return types;
  }

  private Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
    Set<Class<? extends Annotation>> annotationClasses = new LinkedHashSet<>();
    annotationClasses.add(Info.class);
    annotationClasses.add(Status.class);
    return annotationClasses;
  }

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
    // Process each element.
    for (Class<? extends Annotation> annotationClass : getSupportedAnnotationClasses()) {
      processElement(roundEnvironment, annotationClass);
    }

    return false;
  }

  private void processElement(RoundEnvironment roundEnvironment,
      Class<? extends Annotation> annotationClass) {
    for (Element element : roundEnvironment.getElementsAnnotatedWith(annotationClass)) {
      if (element.getKind() != ElementKind.CLASS) {
        // ignored the type which doesn't match to class
        continue;
      }

      TypeElement typeElement = (TypeElement) element;
      Annotation annotation = typeElement.getAnnotation(annotationClass);
      String[] beans = BeanSetFactory.getChildBeans(annotation);
      if (beans == null || beans.length == 0) {
        // if beans is null or empty
        continue;
      }

      BeanSetFactory beanSetFactory = BeanSetFactory.getFactory(annotation);
      String className = typeElement.getSimpleName().toString();
      String packageName = elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
      // Class
      TypeSpec.Builder classBuilder =
          TypeSpec.classBuilder(className + "_" + annotationClass.getSimpleName() + DRAW)
              .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
      for (String bean : beans) {
        BeanSet beanSet = beanSetFactory.parseBean(bean);
        if (beanSet == null) {
          // if the bean isn't able to parsed
          continue;
        }

        // Filed
        String fieldName = beanSet.getFieldName();
        TypeName fieldTypeName = beanSet.getFieldTypeName();
        classBuilder.addField(FieldSpec.builder(fieldTypeName, fieldName, Modifier.PRIVATE)
            .addAnnotation(AnnotationSpec.builder(Util.getClassNameInGson("Expose")).build())
            .addAnnotation(AnnotationSpec.builder(Util.getClassNameInGson("SerializedName"))
                .addMember("value", "$S", bean)
                .build())
            .build());
        // Method [GET]
        String methodGet = beanSet.methodGet();
        if (methodGet != null) {
          classBuilder.addMethod(MethodSpec.methodBuilder(methodGet)
              .addModifiers(Modifier.PUBLIC)
              .returns(fieldTypeName)
              .addStatement("return $N", fieldName)
              .build());
        }
        // Method [SET]
        String methodSet = beanSet.methodSet();
        if (methodSet != null) {
          classBuilder.addMethod(MethodSpec.methodBuilder(methodSet)
              .addModifiers(Modifier.PUBLIC)
              .returns(void.class)
              .addParameter(fieldTypeName, fieldName)
              .addStatement("this.$N = $N", fieldName, fieldName)
              .build());
        }
      }

      // Write to file
      try {
        JavaFile.builder(packageName, classBuilder.build()).build().writeTo(filter);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_7;
  }
}
