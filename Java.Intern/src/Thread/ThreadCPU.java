package src.Thread;

public class ThreadCPU {

    static int core = 10;

    public static void main(String[] args) {
        for(int i = 0 ; i < core  ; i++){
            new Thread(() -> {
                while(true){

                }
            }).start();
        }
        while (true){

        }
    }
}
