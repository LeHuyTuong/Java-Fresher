package src.Thread;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {  // thu thap user nhat dinh
 // context -> ser1 -> ser2 -> ser n -> van su dung local
 // -> nhung van bi ro ri memory -> threadLocal duoc su dung trong tinh huong j
    private static ThreadLocal<Integer> totalMoneyInWallet = new ThreadLocal<>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private static int getMoney(int money) {
        System.out.println(Thread.currentThread().getName() + ": Customer gives " + money);
        int currentMoney = totalMoneyInWallet.get();
        totalMoneyInWallet.set(currentMoney + money);
        System.out.println(Thread.currentThread().getName() + ": Total money in wallet: " + totalMoneyInWallet.get()
                + " | TIME | " + LocalTime.now());
        return money;
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                getMoney(new Random().nextInt(100));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                getMoney(new Random().nextInt(100));
            });
        }

        threadPool.shutdown();
    }
}
