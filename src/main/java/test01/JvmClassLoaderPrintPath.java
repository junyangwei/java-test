package test01;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * 打印 JVM 类加载器的路径
 * @author junyangwei
 * @date 2021-09-17
 */
public class JvmClassLoaderPrintPath {

    public static void main(String[] args) {

        /*
            启动类加载器
            - 通过 Open JDK 或 Oracle JDK 中的一个静态方法拿到它的ClassPath
            - 从而拿到它内部加载的所有的这些路径
            - 最后就可以再把这些路径打印出来了
            注意：仅适用于我们常见的 Open JDK 和 Oracle JDK
         */
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL url : urls) {
            System.out.println(" ===> " + url.toExternalForm());
        }

        /*
            应用类加载器
            - 就是加载我们当前自己写的这个类的加载器
            - 可以通过类的名字.class.getClassLoader获得
         */
        printClassLoader("应用类加载器", JvmClassLoaderPrintPath.class.getClassLoader());

        /*
            扩展类加载器
            - 按照层级的逻辑关系，应用类加载器的父级加载器，就是扩展类加载器
            - 可以通过类的名字.class.getClassLoader().getParent()获得
         */
        printClassLoader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());
    }

    private  static void printClassLoader(String name, ClassLoader classLoader) {
        System.out.println();
        if (null != classLoader) {
            System.out.println(name + " ClassLoader -> " + classLoader.toString());
            printURLForClassLoader(classLoader);
        }
    }

    /*
       URLClassLoader 是 应用类加载器 和 扩展类加载器 的委托父级加载器
       - 可通过反射的方式，把它们作为URLClassLoader
       - 拿到URLClassLoader里面的一个字段 ucp
       - 再拿到 ucp 字段里隐藏的一个所有加载路径的列表 paths 字段
       - 之后将 paths 字段作为List的对象打印出来
       - 最终达到将 应用类加载器 和 扩展类加载器 内部加载的路径细节全部打印出来的目的
    */

    private static void printURLForClassLoader(ClassLoader classLoader) {
        Object ucp = insightField(classLoader, "ucp");
        Object path = insightField(ucp, "path");
        List paths = (List) path;
        for (Object p : paths) {
            System.out.println(" ===> " + p.toString());
        }
    }

    private static Object insightField(Object obj, String fName) {
        try {
            Field f = null;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
