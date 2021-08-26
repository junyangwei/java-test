package genericity;

/**
 * @author junyangwei
 * @date 2021-08-26
 */
public class PairAlg {
    public static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    /**
     * 通配符?不是变量，在代码中不能使用它作为一种类型
     * @param p
     */
    public static void swap(Pair<?> p) {
        // 调用辅助方法
        swapHelper(p);
    }

    /**
     * 解决方法是写一个辅助方法，通过swapHelper方法的参数T捕获通配符
     * @param p
     * @param <T>
     */
    public static <T> void swapHelper(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}
