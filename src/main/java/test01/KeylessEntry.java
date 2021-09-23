package test01;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个简单的内存泄露实例
 * @author junyangwei
 * @date 2021-09-23
 */
public class KeylessEntry {
    static class Key {
        Integer id;
        Key(Integer id) {
            this.id = id;
        }
        @Override
        public int hashCode() {
            return id.hashCode();
        }

        /**
         * 重写equals方法，保证id相等就是相同的对象
         *  - 如果去除这个方法，那么执行main方法报：OutOfMemoryError: GC overhead limit exceeded
         *  - 因为没有重写 equals 方法，在HashMap的桶中比较时，会调用Object.equals判断为不同
         * @param o
         */
        @Override
        public boolean equals(Object o) {
            boolean response = false;
            if (o instanceof Key) {
                response = (((Key) o).id).equals(this.id);
            }
            return response;
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        while (true) {
            for (int i = 0; i < 10000; i++) {
                if (!m.containsKey(new Key(i))) {
                    m.put(new Key(i), "Number:" + i);
                }
            }
            System.out.println("m.size()=" + m.size());
        }
    }
}
