import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Prod implements Runnable{
    int n;
    BlockingQueue<Customer> q= new ArrayBlockingQueue<>(100);
    public Prod(BlockingQueue<Customer> q,int n) {
        this.n=n;
        this.q=q;
    }
    @Override
    public void run() {
       Random rand=new Random();
       for(int i=1;i<n;i++){
           Customer cus=new Customer(i,rand.nextInt(i,200),rand.nextInt(i,200));
           try {
               Thread.sleep(1000);
               this.q.put(cus);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }


    }
}
