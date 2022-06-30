package com.jsamuel.study.generic.updown;

import java.util.Iterator;
import java.util.List;

public class UpAndDown {

    // 类型参数E的范围是 <E extends Comparable<? super E>>
    // 1. 需要进行比较，所以E需要是可比较的类，所以需要E extends Comparable<>
    // 2. Comparable<? super E>，要对E进行比较，即E的消费者，所以需要用super
    // 3. 参数 List<? extends E>，表示要操作的数据是E的子类的列表，指定上限，这样容器才够大
    public <E extends Comparable<? super E>> E max(List<? extends E> e1) {
        if (e1 == null) {
            return null;
        }

        // 迭代器返回的元素，属于E的某个子类型
        Iterator<? extends E> iterator = e1.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }

        return result;
    }
}
