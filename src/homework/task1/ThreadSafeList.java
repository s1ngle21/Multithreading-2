package homework.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeList<T> {
    private T obj;
    private List<T> list;

    public ThreadSafeList(List<T> threadSafeList) {
        this.list = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void add(T obj) {
        this.list.add(obj);
    }

    public synchronized void remove(T obj) {
        this.list.remove(obj);
    }

    public synchronized T get(int index) {
        return this.list.get(index);
    }
}
