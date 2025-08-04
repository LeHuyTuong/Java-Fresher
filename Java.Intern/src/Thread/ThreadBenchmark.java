package src.Thread;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ThreadBenchmark {

    private static final String CSV_FILE = "thread_benchmark_results.csv";

    public static void main(String[] args) throws InterruptedException, IOException {
        try (FileWriter writer = new FileWriter(CSV_FILE)) {
            // Ghi header
            writer.write("Type,TaskType,ThreadCount,DurationMillis\n");

            System.out.println("📊 BẮT ĐẦU BENCHMARK & GHI CSV VÀO: " + CSV_FILE);

            System.out.println("\n🔄 IO-bound: 4000 threads sleep 100ms...");
            testPlatformThreads_io(writer);
            testVirtualThreads_io(writer);

            System.out.println("\n💻 CPU-bound: 100 threads tính Fibonacci(38)...");
            testPlatformThreads_cpu(writer);
            testVirtualThreads_cpu(writer);

            System.out.println("\n✅ ĐÃ GHI TOÀN BỘ KẾT QUẢ VÀO: " + CSV_FILE);
        }
    }

    private static void testPlatformThreads_io(FileWriter writer) throws InterruptedException, IOException {
        final int COUNT = 4000;
        CountDownLatch latch = new CountDownLatch(COUNT);
        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            Thread.ofPlatform().start(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        long duration = System.currentTimeMillis() - start;
        System.out.println("⏱️ Platform Thread (IO): " + duration + " ms");
        writer.write("Platform,IO," + COUNT + "," + duration + "\n");
    }

    private static void testVirtualThreads_io(FileWriter writer) throws InterruptedException, IOException {
        final int COUNT = 4000;
        CountDownLatch latch = new CountDownLatch(COUNT);
        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            Thread.ofVirtual().start(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        long duration = System.currentTimeMillis() - start;
        System.out.println("⏱️ Virtual Thread (IO): " + duration + " ms");
        writer.write("Virtual,IO," + COUNT + "," + duration + "\n");
    }

    private static void testPlatformThreads_cpu(FileWriter writer) throws InterruptedException, IOException {
        final int COUNT = 100;
        CountDownLatch latch = new CountDownLatch(COUNT);
        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            Thread.ofPlatform().start(() -> {
                fib(38);
                latch.countDown();
            });
        }

        latch.await();
        long duration = System.currentTimeMillis() - start;
        System.out.println("⏱️ Platform Thread (CPU): " + duration + " ms");
        writer.write("Platform,CPU," + COUNT + "," + duration + "\n");
    }

    private static void testVirtualThreads_cpu(FileWriter writer) throws InterruptedException, IOException {
        final int COUNT = 100;
        CountDownLatch latch = new CountDownLatch(COUNT);
        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            Thread.ofVirtual().start(() -> {
                fib(38);
                latch.countDown();
            });
        }

        latch.await();
        long duration = System.currentTimeMillis() - start;
        System.out.println("⏱️ Virtual Thread (CPU): " + duration + " ms");
        writer.write("Virtual,CPU," + COUNT + "," + duration + "\n");
    }

    // Hàm tính Fibonacci đệ quy (tốn CPU)
    private static long fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }
}
