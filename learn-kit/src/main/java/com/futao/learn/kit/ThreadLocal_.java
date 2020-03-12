package com.futao.learn.kit;

/**
 *
 * https://blog.csdn.net/tmr1016/article/details/100141446?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 * ThreadLocal为什么要用弱引用和内存泄露问题
 *
 * @author futao
 * Created on 2020/3/11.
 */
public class ThreadLocal_ {
    private static ThreadLocal<Long> x = new ThreadLocal<Long>() {
        /**
         * 会延迟到get()方法执行才进行初始化加载
         * @return
         */
        @Override
        protected Long initialValue() {
            System.out.println("init run...");
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        System.out.println("main:" + x.get());
        x.set(999L);
        System.out.println("main:" + x.get());
        //remove()之后下次get会重新触发init()方法的执行，可以通过日志看到
        x.remove();
        System.out.println("main:" + x.get());

        new Thread(() -> System.out.println("thread:" + x.get())).start();

    }
}
