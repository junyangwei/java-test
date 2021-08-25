package genericity;

/**
 * 泛型程序设计 —— 定义简单的泛型类
 * @author junyangwei
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        this.first = null;
        this.second = null;
    }
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }
    public T getSecond() {
        return this.second;
    }

    public void setFirst(T newValue) {
        this.first = newValue;
    }
    public void setSecond(T newValue) {
        this.second = newValue;
    }

}
