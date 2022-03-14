import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Customer> q= new ArrayBlockingQueue<>(100);
        Cons c=new Cons(q);
        Prod p=new Prod(q,10);
        new Thread(p).start();
        new Thread(c).start();
    }
}
