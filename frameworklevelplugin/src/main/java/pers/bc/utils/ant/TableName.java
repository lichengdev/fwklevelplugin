package pers.bc.utils.ant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
// @Repeatable jdk8
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface TableName
{
   String value();
}
// 元注解：
// 元注解的作用就是负责注解其他注解。Java5.0定义了4个标准的meta-annotation类型，它们被用来提供对其它 annotation类型作说明。Java5.0定义的元注解：
// 　　1.@Target,
// 　　2.@Retention,
// 　　3.@Documented,
// 　　4.@Inherited
// 这些类型和它们所支持的类在java.lang.annotation包中可以找到。下面我们看一下每个元注解的作用和相应分参数的使用说明。
//
// @Target：
// 　　　@Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、
// 成员变量、枚举值）、方法参数和本地变量（如循 环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
// 作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
//
// 　　取值(ElementType)有：
//
// 1.@Target(ElementType.TYPE) —— 可以适用于任何类的元素
// 2.@Target(ElementType.FIELD) —— 只适用于字段或属性
// 3.@Target(ElementType.METHOD) —— 只适用于方法的注解
// 4.@Target(ElementType.PARAMETER) —— 只适用于方法的参数
// 5.@Target(ElementType.CONSTRUCTOR) —— 只适用于构造函数
// 6.@Target(ElementType.LOCAL_VARIABLE) —— 只适用于局部变量
// 7.@Target(ElementType.ANNOTATION_TYPE) —— 指明声明类型本身是一个注解类型
// 8.@Target(ElementType.PACKAGE) —— 只适用于包的注解
