package src.Thread;


public class VirtualThread {
    public static void main(String[] args) {
        //int count = testPlatformThreads();
        int count = testVirtualThreads();

        System.out.println("Tong so thread VM da tao " + count);
    }
    private static int testPlatformThreads(){
        int count = 0;
        try{
            while(true){
                long begin = System.currentTimeMillis();
                Thread thread = Thread.ofPlatform().unstarted( () -> {
                    try{
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                thread.start();
                count++;
                System.out.println("Traditional threads count: " + count
                                    +", time: " + (System.currentTimeMillis() - begin)  + "ms");
            }
        }catch (Throwable e){
            System.out.println("Reached the limit of traditional threads");
            e.printStackTrace();
        }
        return count;
    }
    private static int testVirtualThreads() {
        int count = 0;
        try {
            while (true) {
                long begin = System.currentTimeMillis();
                Thread thread = Thread.ofVirtual().unstarted(() -> {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                thread.start();
                count++;
                System.out.println("Virtual threads count: " + count +
                        ", time: " + (System.currentTimeMillis() - begin) + "ms");
            }
        } catch (Throwable e) {
            System.out.println("Reached the limit of virtual threads");
            e.printStackTrace();
        }
        return count;
    }

}

