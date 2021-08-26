package common;

import java.time.LocalDate;

/**
 * 定义一个类 ———— 金毛
 * @author junyangwei
 * @date 2021-08-26
 */
public class GoldenDog extends Dog {

    public GoldenDog(String masterName, Integer age) {
        LocalDate now = LocalDate.now();
        this.setColor("gold");
        this.setMasterName(masterName);
        this.setAge(age);
        this.setCreateTime(now);
        this.setUpdateTime(now);
    }
}
