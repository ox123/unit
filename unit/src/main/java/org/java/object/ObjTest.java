package org.java.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SoftReference
 * 系统内存充足 -> 不会回收
 * 系统内存不足 -> 会回收
 * 软引用通常用在对内存敏感的程序中，比如高速缓存就有用到软引用，内存够用的时候就保留，不够用就回收
 */
public class ObjTest {
    private List<Integer> nums;

    //precondition: nums.size() > 0
//nums contains Integer objects
    public void numQuest() {
        int arr[] = {0, 0, 4, 2, 5, 0, 3, 0};
        nums = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nums.add(arr[i]);
        }
        int k = 0;
        Integer zero = new Integer(0);
        while (k < nums.size()) {
            if (nums.get(k).equals(zero))
                nums.remove(k);
            k++;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        new ObjTest().numQuest();
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
