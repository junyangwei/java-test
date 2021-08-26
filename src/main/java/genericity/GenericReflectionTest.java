package genericity;

import java.lang.reflect.*;
import java.util.*;

/**
 * 泛型程序设计 —— 反射
 * @author junyangwei
 * @date 2021-08-26
 */
public class GenericReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            try (Scanner in = new Scanner(System.in)) {
                System.out.println("进入类名(java.util.ArrayList): ");
                name = in.next();
            }
        }

        try {
            // 为类和公共方法打印泛型信息
            Class<?> cl = Class.forName(name);
            // 打印类名
            printClass(cl);
            // 打印方法信息
            for (Method m : cl.getDeclaredMethods()) {
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印类信息，参考结果如下：
     *  class java.util.ArrayList<E> extends java.util.AbstractList<E> implements java.util.List<E>, java.util.RandomAccess, java.lang.Cloneable, java.io.Serializable
     *  class java.lang.String extends java.lang.Object implements java.io.Serializable, java.lang.Comparable<java.lang.String>, java.lang.CharSequence
     * @param cl
     */
    public static void printClass(Class<?> cl) {
        // 打印类名/接口名：参考
        System.out.print(cl);
        // 打印泛型类型参数，以<开始，通过逗号","间隔，以>结束
        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);

        // 获取继承的父类Type信息，若存在则打印
        Type sc = cl.getGenericSuperclass();
        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }
        // 打印实现的接口信息，以implements开始，通过逗号","间隔
        printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
        // 换行
        System.out.println();
    }

    /**
     * 打印方法信息，参考结果如下：
     *  public boolean add(E)
     *  public E remove(int)
     *  public boolean equals(java.lang.Object)
     *  public java.lang.String toString()
     * @param m
     */
    public static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
        printTypes(m.getTypeParameters(), "<", ", ", ">", true);

        printType(m.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");

    }

    public static void printTypes(Type[] types, String pre, String sep, String suf,
                                  boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[] { Object.class })) {
            return;
        }
        if (types.length > 0) {
            System.out.print(pre);
        }
        for (int i = 0; i < types.length; i++) {
            if (i > 0) {
                System.out.print(sep);
            }
            printType(types[i], isDefinition);
        }
        if (types.length > 0) {
            System.out.print(suf);
        }
    }

    /**
     * 打印各种Type信息
     * @param type
     * @param isDefinition
     */
    public static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class) {
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        } else if (type instanceof  TypeVariable) {
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if (isDefinition) {
                printTypes(t.getBounds(), " extends ", " & ", "", false);
            }
        } else if (type instanceof WildcardType) {
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(t.getLowerBounds(), " super ", " & ", "", false);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }
    }
}
