package genericity;

import common.Dog;
import common.GoldenDog;

/**
 * 泛型程序设计 —— 通配符
 * @author junyangwei
 * @date 2021-08-26
 */
public class PairTest3 {
    public static void main(String[] args) {
        GoldenDog dog1 = new GoldenDog("JY", 3);
        GoldenDog dog2 = new GoldenDog("Sally", 5);
        GoldenDog dog3 = new GoldenDog("James", 2);
        Pair<GoldenDog> dogs = new Pair<>(dog1, dog2);
        printAge(dogs);

        GoldenDog[] dogs2 = { dog1, dog2, dog3 };

        Pair<Dog> result = new Pair<>();
        minMaxAge(dogs2, result);
        System.out.println("minMaxAge - first: " + result.getFirst());
        System.out.println("minMaxAge - second: " + result.getSecond());

        maxMinAge(dogs2, result);
        System.out.println("maxMinAge - first: " + result.getFirst());
        System.out.println("maxMinAge - second: " + result.getSecond());
    }

    /**
     * 带有超类型限定的通配符可以向泛型对象写入
     * @param dogs
     */
    public static void printAge(Pair<? extends Dog> dogs) {
        Dog first = dogs.getFirst();
        Dog second = dogs.getSecond();
        System.out.println("The age of the dogs are " + first.getAge() + " and " + second.getAge());
    }

    /**
     * 带有子类型限定的通配符可以从泛型对象读取
     * @param d
     * @param result
     */
    public static void minMaxAge(GoldenDog[] d, Pair<? super GoldenDog> result) {
        if (d.length == 0) return;
        GoldenDog min = d[0];
        GoldenDog max = d[0];

        for (int i = 0; i < d.length; i++) {
            if (min.getAge() > d[i].getAge()) min = d[i];
            if (max.getAge() < d[i].getAge()) max = d[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxMinAge(GoldenDog[] d, Pair<? super GoldenDog> result) {
        minMaxAge(d, result);
        PairAlg.swapHelper(result);
    }
}

