package DTOs;

public class Counter {

    private static int value;

    public static void reset(){
        value = 1;
    }

    public static int getAndIncrement(){
        return value++;
    }
}
