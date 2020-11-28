package com.wxw.common.distributed_lock;

import com.wxw.service.OrderService;
import com.wxw.service.impl.OrderServiceImpl;
import com.wxw.service.impl.OrderServiceImplWithLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 模拟分布式集群 并发测试
 * @author: wxw
 * @date: 2020-11-28-16:41
 */
public class ConcurrentTestDistributeDemo {

    public static void main(String[] args) {
        // 并发数
        int currency = 20;
        CyclicBarrier cb = new CyclicBarrier(currency);   // 循环屏障
        CountDownLatch cdl = new CountDownLatch(currency); // 倒计数锁存器

        OrderService orderService = new OrderServiceImpl();
//        OrderService orderServiceLock = new OrderServiceImplWithLock();

        // 多线程模拟高并发
        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //模拟分布式集群场景
                    OrderService orderServiceLock = new OrderServiceImplWithLock();
                    System.out.println(Thread.currentThread().getName() + "：我准备好");
                    // 等待一起出发
                    try {
                        // 方式一
                        cb.await();
                        // 方式二
//                        cdl.countDown();
//                        cdl.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    // 调用创建订单服务
//                    orderService.createOrder();
                    orderServiceLock.createOrder();
                }
            }).start();
        }
    }
}
