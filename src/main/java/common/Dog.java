package common;

import java.time.LocalDate;

/**
 * 定义一个类 ———— 狗
 * @author junyangwei
 * @date 2021-08-26
 */
public class Dog {
    /**
     * 描述：狗的颜色（类的域）
     * 修饰符：private修饰，表示私有，只有Dog类的实例（即构造的对象）才能够访问（封装、对外隐藏）
     */
    private String color;
    /**
     * 狗主人的名字
     */
    private String masterName;
    /**
     * 狗的年龄
     */
    private Integer age;
    /**
     * 初始化的时间
     */
    private LocalDate createTime;
    /**
     * 最近的更新时间
     */
    private LocalDate updateTime;

    // 初始化块，在创建Dog类的实例的时候会执行（建议将初始化块放在域定义之后）
    {
        age = 1;
    }

    /**
     * 狗的ID【静态域】
     */
    public static int id = 0;
    /**
     * 狗有四只脚【静态常量】（静态常量一般全大写，通过下划线_分割每个字符）
     */
    public static final int FEET_NUM = 4;

    // 静态初始化块，在第一次加载类时会触发
    static {
        id = 1;
    }

    /**
     * 构造方法，名称与类名相同，使用new Dog()时调用的就是这个方法
     */
    public Dog() {
    }


    /**
     * (带参)构造方法，名称也与类名相同，使用new Dog("gold", "JY", 2)时调用的就是这个方法
     * 重载相关的描述：这里构造器Dog其实使用了Java重载的特性，才能有多个相同名称，但不同参数(类型)组合的构造器
     */
    public Dog(String color, String masterName, Integer age) {
        this.color = color;
        this.masterName = masterName;
        this.age = age;
        this.createTime = LocalDate.now();
        // 直接将createTime的引用赋值给updateTime，减少重复构建对象
        this.updateTime = this.createTime;
    }

    /**
     * 狗的id是否大于0【静态方法】（只能够访问静态域或静态常量）
     */
    public static boolean dogIdBiggerThanZero() {
        // 访问非静态域/非静态常量，无法执行
        // String masterName = this.masterName;

        // 但可以访问静态常量和静态域
        int feetNum = FEET_NUM;
        return id > 0;
    }

    /**
     * 定义类的方法，用于Dog类的实例（即构造的对象）获取内部数据或修改内部数据
     * 如：getColor方法用于获取颜色（访问器方法），setColor用于修改颜色（更改器方法）
     * 其中setColor后面括号包含的color即为显示参数，方法内的this.color即为隐士参数
     */

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMasterName() {
        return this.masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[color=" + this.color
                + ",masterName=" + this.masterName
                + ",age=" + this.age
                + "]";
    }
}
