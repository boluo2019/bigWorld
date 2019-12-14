import java.util.concurrent.atomic.AtomicInteger;

public class LockTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
