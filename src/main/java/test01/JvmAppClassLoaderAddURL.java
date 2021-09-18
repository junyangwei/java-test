package test01;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 添加引用类 —— 使用反射调用 addUrl 方法添加 Jar 或 路径
 * @author junyangwei
 * @date 2021-09-17
 */
public class JvmAppClassLoaderAddURL {

    public static void main(String[] args) {
        // 需要添加的详细路径（注意：我已通过...省略本机的详细路径，调用时需要修改）
        String addPath = "file:/Users/.../java-test/src/main/java/test01extra/";

        // 获取当前自己编写的类的加载器（即应用类加载器），并将其强制转换成委托的父级加载器URLClassLoader
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();

        try {
            // 通过反射，获取 (委托的父级加载器) 内部隐式的方法"addURL"
            Method addUrl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);

            // 将这个方法设置为可见
            addUrl.setAccessible(true);

            // 初始化指定的路径
            URL url = new URL(addPath);

            // 调用"addURL"这个方法，加载这个路径
            addUrl.invoke(urlClassLoader, url);

            // 效果与Class.forName("test01extra.Hello2").newInstance()一样
            Class.forName("Hello2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
