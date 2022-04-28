package com.example.apt_processor;

import com.example.apt_annotation.FundSubView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author: hepan
 * @date: 2022/4/25
 * @desc:
 */


@AutoService(Processor.class)
public class FundSubProcessor extends AbstractProcessor {


    private Elements elements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elements = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(FundSubView.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        Set<? extends Element> temp = roundEnv.getElementsAnnotatedWith(FundSubView.class);

        Map<String, String> param = new HashMap<>();
        for (Element element : temp) {
            TypeElement ve = (TypeElement) element;
            FundSubView bindAnnotation = ve.getAnnotation(FundSubView.class);
            String type = bindAnnotation.type();
            String className = ve.getQualifiedName().toString();

            // 不同 View 具有相同 type
            if (param.containsKey(type)) {
                String exist = param.get(type);
                if (exist != null && !exist.equals(className)) {
                    String msg = String.format("%s and %s have the same type", exist, className);
                    throw new IllegalStateException(msg);
                }
            }
            param.put(type, className);
        }

        // 这里取巧 superClass 实现.
        ClassName superClass = ClassName.get(elements.getTypeElement("com.example.commonui.CardFactory"));

        // 默认生成在 app -> build -> generated 目录下
        TypeSpec typeSpec = TypeSpec.classBuilder("FundCard_AutoGen")
                .superclass(superClass)
                .addMethod(getMethod(param))
                .addModifiers(Modifier.PUBLIC)
                .build();

        JavaFile jf = JavaFile.builder("com.example.commonui", typeSpec)
                .build();
        try {
            jf.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private MethodSpec getMethod(Map<String, String> param) {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Void.TYPE);

        param.keySet().forEach(new Consumer<String>() {
            @Override
            public void accept(String key) {
//                methodBuilder.addStatement("mSubViewClassMap.put($S, $T.class)",key,ClassName.bestGuess(param.get(key)));
                methodBuilder.addStatement("register($S, $T.class)", key, ClassName.bestGuess(param.get(key)));
            }
        });

//        methodBuilder.addStatement("$T.out.println($S)",System.class,"hepan init");

        return methodBuilder.build();
    }

}
