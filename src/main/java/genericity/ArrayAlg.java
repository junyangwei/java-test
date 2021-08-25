package genericity;

/**
 * 泛型程序设计 —— 数组处理类
 * @author junyangwei
 */
class ArrayAlg {
    /**
     * 获取字符串数组的按字母顺序的最小及最大值
     * @param a 一个字符串数组
     * @return 一个Pair对象，带有最小和最大值，或者为null(如果入参为null或数组为空)
     */
    static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min, max);
    }

    /**
     * 获取类型变量T数组对象的最小值和最大值
     * @param a 一个类型变量T的数组对象
     * @return 一个Pair对象，带有最小值和最大值，或者为null(如果入参为null或数组为空)
     */
    static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min, max);
    }
}

