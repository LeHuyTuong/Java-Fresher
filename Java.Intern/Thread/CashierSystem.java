package Thread;

import java.time.LocalTime;
import java.util.Random;

public class CashierSystem {
    private int totalMoneyInWallet = 0;

    // Synchronized để tránh race condition , tránh sử dụng 1 biến chung có thể sai
    // neu khong co synchronized thì sẽ xảy ra cùng lúc với nhau , có thể ra trường hợp cuối cùng bị sai
    private synchronized Integer getMoney(int money) {  // lock() các nguồn lại, chạy sen kẽ nhau,
        // nếu thu ngân nhiều , hàng ngàn người thì sẽ phải chờ thằng 1 , 2 ,3 tính xong thì bao giờ mới xong 1000 người
        System.out.println(Thread.currentThread().getName() + ": Khách hàng đưa " + money);
        totalMoneyInWallet += money;
        System.out.println(Thread.currentThread().getName() + ": Tổng tiền trong ví = "
                + totalMoneyInWallet + " | Time: " + LocalTime.now());
        return money;
    }

    public void runSimulation() {

        for(int i = 0 ; i < 100;i++){
            Thread cashier1 = new Thread(() -> {
                getMoney(new Random().nextInt(100)); // customer 1
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                getMoney(new Random().nextInt(100));// customer2

            }, "cashier"+i);
            cashier1.start();
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
    }
}
