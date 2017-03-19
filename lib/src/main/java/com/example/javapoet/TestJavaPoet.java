package com.example.javapoet;


import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.Collections;
import java.util.logging.LogRecord;

import javax.lang.model.element.Modifier;

/**
 * Intro:
 * Author: zhangxutong
 * E-mail: mcxtzhang@163.com
 * Home Page: http://blog.csdn.net/zxt0601
 * Created:   2017/1/18.
 * History:
 */

public class TestJavaPoet {
    public static void main(String[] args) throws Exception {
/*        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(whatsMyName("slimShady"))
                .addMethod(whatsMyName("eminem"))
                .addMethod(whatsMyName("marshallMathers"))
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                .build();

        javaFile.writeTo(System.out);*/


//normal class
/*        ClassName hoverboard = ClassName.get("com.mattel", "Hoverboard");
//list
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
//类型变量type var
        TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);

        ClassName namedBoards = ClassName.get("com.mattel", "Hoverboard", "Boards");

        MethodSpec beyond = MethodSpec.methodBuilder("beyond")
                .returns(listOfHoverboards)
                .addStatement("$T result = new $T<>()", listOfHoverboards, arrayList)
                .addStatement("result.add($T.createNimbus(2000))", hoverboard)
                .addStatement("result.add($T.createNimbus(\"2001\"))", hoverboard)
                .addStatement("result.add($T.createNimbus($T.THUNDERBOLT))", hoverboard, namedBoards)
                .addStatement("$T.selectionSort(result)", Collections.class)
                .addStatement("return result.isEmpty() ? $T.emptyList() : result", Collections.class)
                .build();

        TypeSpec hello = TypeSpec.classBuilder("HelloWorld")
                .addMethod(beyond)
                .build();

        JavaFile.builder("com.example.helloworld", hello)
                .addStaticImport(hoverboard, "createNimbus")
                .addStaticImport(namedBoards, "*")
                .addStaticImport(Collections.class, "*")
                .build()
                .writeTo(System.out);*/



/*
        MethodSpec flux = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "greeting")
                .addStatement("this.$N = $N", "greeting", "greeting")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)
                .addField(String.class, "greeting", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(flux)
                .build();
*/
/*        TypeSpec comparator = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(Comparator.class, String.class))
                .addMethod(MethodSpec.methodBuilder("compare")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(String.class, "a")
                        .addParameter(String.class, "b")
                        .returns(int.class)
                        .addStatement("return a.length() - b.length()")
                        .build())
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addMethod(MethodSpec.methodBuilder("sortByLength")
                        .addParameter(ParameterizedTypeName.get(List.class, String.class), "strings")
                        .addStatement("$T.selectionSort($N, $L)", Collections.class, "strings", comparator)
                        .build())
                .build();*/

        MethodSpec logRecord = MethodSpec.methodBuilder("recordEvent")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addAnnotation(AnnotationSpec.builder(HeaderList.class)
                        .addMember("value", "$L", AnnotationSpec.builder(Header.class)
                                .addMember("name", "$S", "Accept")
                                .addMember("value", "$S", "application/json; charset=utf-8")
                                .build())
                        .addMember("value", "$L", AnnotationSpec.builder(Header.class)
                                .addMember("name", "$S", "User-Agent")
                                .addMember("value", "$S", "Square Cash")
                                .build())
                        .addMember("name", "test")
                        .build())
                .addParameter(LogRecord.class, "logRecord")
                .returns(LogReceipt.class)
                .build();


        //inner class
        ClassName className = ClassName.bestGuess("HelloWorld");
        FieldSpec INSTANCE = FieldSpec.builder(className, "INSTANCE")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .initializer("new $T()", className)
                .build();

        TypeSpec innerClass = TypeSpec.classBuilder("InnerClass")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .addField(INSTANCE)
                .build();

        //getInstance method
        MethodSpec methodGetInstance = MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(className)
                .addStatement("return $N.INSTANCE", innerClass)
                .build();


        ClassName contextClass = ClassName.get("android.content", "Context");
        ClassName intentClass = ClassName.get("android.content", "Intent");
        ClassName conponentNameClass = ClassName.get("android.content", "ComponentName");
        MethodSpec jump = MethodSpec.methodBuilder("jump")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "context")
                .addParameter(String.class, "where")
                .addStatement("String clsFullName = routerMap.get(where)")
                .beginControlFlow("if (TextUtils.isEmpty(clsFullName))")
                // TODO: 2017/1/19 log statement
                .endControlFlow()
                .beginControlFlow("else")
                .addStatement("$T intent = new $T()", intentClass, intentClass)
                .addStatement("intent.setComponent(new $T(context.getPackageName(), clsFullName))", conponentNameClass)
                .addStatement("context.startActivity(intent)")
                // TODO: 2017/1/19 log statement
                .addStatement("String clsFullName = routerMap.get(where)")
                .endControlFlow()
                .build();


        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)
                //.addMethod(logRecord)
                .addMethod(methodGetInstance)
                .addMethod(jump)
                .addType(innerClass)
                .build();


        JavaFile.builder("com.example.helloworld", helloWorld)
                .build()
                .writeTo(System.out);

        //importStaticReadmeExample();

    }


    public static void importStaticReadmeExample() {
        ClassName hoverboard = ClassName.get("com.mattel", "Hoverboard");
        ClassName namedBoards = ClassName.get("com.mattel", "Hoverboard", "Boards");
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);
        MethodSpec beyond = MethodSpec.methodBuilder("beyond")
                .returns(listOfHoverboards)
                .addStatement("$T result = new $T<>()", listOfHoverboards, arrayList)
                .addStatement("result.add($T.createNimbus(2000))", hoverboard)
                .addStatement("result.add($T.createNimbus(\"2001\"))", hoverboard)
                .addStatement("result.add($T.createNimbus($T.THUNDERBOLT))", hoverboard, namedBoards)
                .addStatement("$T.selectionSort(result)", Collections.class)
                .addStatement("return result.isEmpty() ? $T.emptyList() : result", Collections.class)
                .build();
        TypeSpec hello = TypeSpec.classBuilder("HelloWorld")
                .addMethod(beyond)
                .build();
        JavaFile example = JavaFile.builder("com.example.helloworld", hello)
                .addStaticImport(hoverboard, "createNimbus")
                .addStaticImport(namedBoards, "*")
                .addStaticImport(Collections.class, "*")
                .build();
        if ((example.toString()).equals(""
                + "package com.example.helloworld;\n"
                + "\n"
                + "import static com.mattel.Hoverboard.Boards.*;\n"
                + "import static com.mattel.Hoverboard.createNimbus;\n"
                + "import static java.util.Collections.*;\n"
                + "\n"
                + "import com.mattel.Hoverboard;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "\n"
                + "class HelloWorld {\n"
                + "  List<Hoverboard> beyond() {\n"
                + "    List<Hoverboard> result = new ArrayList<>();\n"
                + "    result.add(createNimbus(2000));\n"
                + "    result.add(createNimbus(\"2001\"));\n"
                + "    result.add(createNimbus(THUNDERBOLT));\n"
                + "    selectionSort(result);\n"
                + "    return result.isEmpty() ? emptyList() : result;\n"
                + "  }\n"
                + "}\n")) {
            System.out.println("checks passed ");
        } else {
            System.out.println("checks failed");
        }
        ;
    }

    private static MethodSpec whatsMyName(String name) {
        return MethodSpec.methodBuilder(name)
                .returns(String.class)
                .addStatement("return $S", name)
                .build();
    }
}
