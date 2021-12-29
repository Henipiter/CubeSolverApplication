package DTOs;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CounterTest {

    @Test
    void testCounterAndReset() {
        Counter.reset();
        Assertions.assertEquals(1, Counter.getAndIncrement());
        Assertions.assertEquals(2, Counter.getAndIncrement());
        Assertions.assertEquals(3, Counter.getAndIncrement());

        Counter.reset();
        Assertions.assertEquals(1, Counter.getAndIncrement());
    }
}