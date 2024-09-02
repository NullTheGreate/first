package com.first.first.thread;

import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

public class ThreadLogic implements Runnable {
    
    private List<Integer> list;

    private List<String> strList;

    private MemoryMXBean mBean;

    private static final long MEGABYTE = 1024L * 1024L;

    public ThreadLogic(List<Integer> list, MemoryMXBean memoryBean) {
        this.list = list;
        this.mBean = memoryBean;
    }

    @Override
    public void run() {

        // list.parallelStream().forEach(val -> {
        //     System.out.println(val * 123);
        //     System.out.println(val + " " + Thread.currentThread().getName());
        // });
        strList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getPriority());
            for (int x = 0; x < 1000; x++) {
                strList.add(x + " - " + x + " - " + Thread.currentThread().getName());
            }
            pollMemory(this.mBean);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void pollMemory(MemoryMXBean memoryBean) {
        memoryBean.setVerbose(true);
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();
        
        long usedHeapMemoryInMB = heapMemoryUsage.getUsed() / MEGABYTE;
        long maxHeapMemoryInMB = heapMemoryUsage.getMax() / MEGABYTE;
        
        long usedNonHeapMemoryInMB = nonHeapMemoryUsage.getUsed() / MEGABYTE;
        long maxNonHeapMemoryInMB = nonHeapMemoryUsage.getMax() / MEGABYTE;
        
        System.out.println("Memory Usage:");
        System.out.println("Heap Memory: " + usedHeapMemoryInMB + "MB / " + maxHeapMemoryInMB + "MB");
        System.out.println("Non-Heap Memory: " + usedNonHeapMemoryInMB + "MB / " + maxNonHeapMemoryInMB + "MB");
        System.out.println("--------------------");

        // MemoryPoolMXBean poolMXBean = memoryBean.getMemoryPoolMXBeans()[0];
    }
    
}
