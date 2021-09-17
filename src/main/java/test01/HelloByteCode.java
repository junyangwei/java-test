package test01;

/**
 * 一个简单的Hello类字节码查看啊
 *  - 使用`javac -g HelloByteCode.java`命令编译后
 *  - 再使用`javap -c -verbose HelloByteCode`查看字节码信息
 * @author junyangwei
 * @date 2021-09-17
 */
public class HelloByteCode {
    public static void main(String[] args) {
        HelloByteCode obj = new HelloByteCode();
    }
}
