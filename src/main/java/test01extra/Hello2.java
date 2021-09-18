/**
 * 用于为 test01 中的 JvmAppClassLoaderAddURL 测试加载指定路径下的类
 * 注意：
 *  - 这里我故意将包名去掉，通过 test01extra.Hello2 无法直接访问
 *  - 必须编码Hello2，在相应的文件夹中需要它的.class字节码文件，加载需要字节码文件
 *  - 若缺少.class文件，会报：ClassNotFoundException异常
 * @author junyangwei
 * @date 2021-09-17
 */
public class Hello2 {
    static {
        System.out.println("Hello2 Class Initialized!");
    }
}
