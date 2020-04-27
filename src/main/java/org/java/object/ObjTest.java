package org.java.object;

/**
 * SoftReference
 * 系统内存充足 -> 不会回收
 * 系统内存不足 -> 会回收
 * 软引用通常用在对内存敏感的程序中，比如高速缓存就有用到软引用，内存够用的时候就保留，不够用就回收
 */
public class ObjTest {
    public static void main(String[] args) {
//        Object o1 = new Object();
//        SoftReference softReference = new SoftReference(o1);
//        o1 = null;
//        System.gc();
//        System.out.println(o1);
//        System.out.println(softReference.get());

//        Object o1 = new Object();
//        WeakReference weakReference = new WeakReference(o1);
//        o1 = null;
//        System.gc();
//        System.out.println(o1);//null        
//        System.out.println(weakReference.get());//null  

        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().maxMemory());

//        Object o1 = new Object();
//        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
//        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);
//        System.out.println(o1);//java.lang.Object@1540e19d       
//        System.out.println(phantomReference.get());//null        
//        System.out.println(referenceQueue.poll());
    }
}
