import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Cons implements Runnable{
    BlockingQueue<Customer> q= new ArrayBlockingQueue<>(100);
    public Cons(BlockingQueue q) {
        this.q=q;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            while(q!=null){
                Customer c=q.take();
                c.print();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
