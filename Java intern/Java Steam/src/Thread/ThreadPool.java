package Thread;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        ExecutorService threadPool = Executors.newFixedThreadPool(10);  // create one thread pool have 10 thread instance
        // tai tao va su dung lai , giam lang phi tai nguyen cpu , han che tao them
        for (int i = 0; i < 100; i++) {   // 100 customer
            threadPool.submit(() -> {
                        getMoney(new Random().nextInt(100));
                        try {
                            Thread.sleep(2 * 1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        getMoney(new Random().nextInt(100));
                    }
            );
        }

//        Thread cashier1 = new Thread(() -> {
//            getMoney(new Random().nextInt(100));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            getMoney(new Random().nextInt(100));
//
//        }, "cashier1");
//
//        Thread cashier2 = new Thread(() -> {
//            getMoney(new Random().nextInt(100));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            getMoney(new Random().nextInt(100));
//
//        }, "cashier2");
//        cashier1.start();
//        cashier2.start();
    }

    public static void main(String[] args) {
        CashierSystem system = new CashierSystem();
        system.runSimulation();
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
