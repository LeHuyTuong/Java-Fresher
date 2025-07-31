package Thread;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {

    private int totalMoneyInWallet = 0;

    //nhung van chia se 1 du lieu chung, vi tien cua sieu thi
    // gio lam sao de co the chia ra , moi quay thu ngan se co 1 vi tien rieng, cuoi ngay cac thu ngan se tong ket lai thanh 1 vi chung -> thread local
    private synchronized Integer getMoney(int money) {
        System.out.println(Thread.currentThread().getName() + ": Khách hàng đưa " + money);
        totalMoneyInWallet += money;
        System.out.println(Thread.currentThread().getName() + ": Tổng tiền trong ví = "
                + totalMoneyInWallet + " | Time: " + LocalTime.now());
        return money;
    }

    public void runSimulation() {

//        ExecutorService threadPool = Executors.newFixedThreadPool(10);  // create one thread pool have 10 thread instance
        // tai tao va su dung lai , giam lang phi tai nguyen cpu , han che tao them
//        for (int i = 0; i < 100; i++) {   // 100 customer
//            threadPool.submit(() -> {
//                        getMoney(new Random().nextInt(100));
//                        try {
//                            Thread.sleep(2 * 1000);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                        getMoney(new Random().nextInt(100));
//                    }
//            );
//        }

        Object bow = new Object();
        Object arrow = new Object();

        ExecutorService archery = Executors.newFixedThreadPool(2);

        //Cr7 got arrow -> wait arrow from M10
        // Deadlock xay ra khi, Cr7 dang co mui ten nhung ko co cung, trong khi do M10 co cung nhung ko co mui ten
        // 2 luong thread nay tao ra moi cong dung khac nhau , nen tao ra 2 thang doi nhau lam xong -> ko co kq tra ve -> deadlock
        archery.submit(() -> {
            synchronized (bow) { // CR7 got bow
                System.out.println("Cr7: got bow -> wait arrow from M10");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                synchronized (arrow) {
                    System.out.println("CR7: OK --> archery");
                }
            }
        });
        //Cr7 chua mo khoa xong

        // den luot M10 ban thi ko co
        //M10 get arrow -> wait arrow from Cr7

        archery.submit(() -> {
            synchronized (arrow) {
                System.out.println("M10: got bow -> wait arrow from Cr7");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                synchronized (bow) {
                    System.out.println("M10: OK --> archery");
                }
            }
        });
        System.out.println("Starting executtor....");
        archery.shutdown();
    }

    public void runSimulationThreadWithTryLock() {
        Lock bow = new ReentrantLock();
        Lock arrow = new ReentrantLock();

        ExecutorService archery = Executors.newFixedThreadPool(2);

        archery.submit(() -> {
            try {
                if (bow.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("CR7: got bow, waiting for arrow...");
                        Thread.sleep(100); // giả lập delay
                        if (arrow.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("CR7: OK --> archery");
                            } finally {
                                arrow.unlock();
                            }
                        } else {
                            System.out.println("CR7: Timeout waiting for arrow! -> abort mission.");
                        }
                    } finally {
                        bow.unlock();
                    }
                } else {
                    System.out.println("CR7: Failed to get bow.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        archery.submit(() -> {
            try {
                if (arrow.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("M10: got arrow, waiting for bow...");
                        Thread.sleep(100);
                        if (bow.tryLock(1, TimeUnit.SECONDS)) { // sau 1 giay doi ma ko thay thi thoi
                            try {
                                System.out.println("M10: OK --> archery");  
                            } finally {
                                bow.unlock();
                            }
                        } else {
                            System.out.println("M10: Timeout waiting for bow! -> abort mission."); // thi thôi luôn
                        }
                    } finally {
                        arrow.unlock();
                    }
                } else {
                    System.out.println("M10: Failed to get arrow.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.println("Starting executor...");
        archery.shutdown();
    }

    public static void main(String[] args) {
        //CashierSystem system = new CashierSystem();
       // system.runSimulation();
       ThreadPool thread = new ThreadPool();
//        thread.runSimulation();
        thread.runSimulationThreadWithTryLock();
    }
}
