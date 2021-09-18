package test01;

import java.util.Base64;

/**
 * 自定义类加载器（继承了ClassLoader）
 *  - 不通过常规的.class文件来加载类
 *  - 通过.class文件经过base64的加密值，来直接加载类
 * @author junyangwei
 * @date 2021-09-17
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        // 通过 findClass 方法加载 Hello 类，并通过 newInstance 方法进行初始化
        new HelloClassLoader().findClass("test01.Hello").newInstance();
    }

    /**
     * 加载Hello类
     * @param name 类所在包.类名
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 命令行模式下，通过`base64 Hello.class`命令获取的文件base64加密值
        String helloBase64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAGEhlbGxvIENsYXNzIEluaXRpYWxpemVkIQcAGQwAGgAbAQAMdGVzdDAxL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAdAAEAAQAAAAUqtwABsQAAAAEACgAAAAYAAQAAAAgACAALAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAACgAIAAsAAQAMAAAAAgAN";

        // 获取base64解密后的Byte数组
        byte[] bytes = decode(helloBase64);

        // 调用ClassLoader自带的defineClass（相当于默认通过JVM内部的ClassLoader机制加载Hello.class文件）
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 通过JDK自带的Base64解码方式进行解码
     * @param base64 经过base64加密的值
     * @return 解密后的Byte数组（等同于 Hello.class）
     */
    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
