package DataModels;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueues implements Runnable {

    private BlockingQueue<Customer> customers;
    private AtomicInteger time2Wait;
    private int queueID;
    private static int one = 1;
    private boolean open;
    private static boolean run = true;

    public MyQueues(int nrOfCustomers) {
        this.customers = new ArrayBlockingQueue<>(nrOfCustomers);
        this.time2Wait = new AtomicInteger();
        this.time2Wait.set(0);
        this.queueID = MyQueues.getOne();
        MyQueues.setOne(MyQueues.getOne() + 1);
        this.open = false;
    }

    @Override
    public void run() {
        while (run) {
            while (customers.peek() != null) {
                try {
                    int newtime = customers.peek().getService_time();
                    Thread.sleep(1000);
                    time2Wait.decrementAndGet();
                    newtime--;
                    assert customers.peek() != null;
                    customers.peek().setService_time(newtime);
                    if (newtime == 0) {
                        customers.poll();
                    }
                    if (customers.isEmpty()) {
                        time2Wait.set(0);
                        this.open = false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BlockingQueue<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer newCus) {
        this.open = true;
        this.customers.add(newCus);
        this.time2Wait.set(newCus.getService_time() + this.getTime2Wait().get());
    }

    public AtomicInteger getTime2Wait() {
        return time2Wait;
    }

    public void setTime2Wait(AtomicInteger time2Wait) {
        this.time2Wait = time2Wait;
    }

    public int getQueueID() {
        return queueID;
    }

    public void setQueueID(int queueID) {
        this.queueID = queueID;
    }

    public static int getOne() {
        return one;
    }

    public static void setOne(int one) {
        MyQueues.one = one;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public static void stopThreads() {
        run = false;
    }

    public String toString() {
        StringBuilder close = new StringBuilder();
        if (!this.open) {
            close = new StringBuilder("closed");
        } else {
            for (Customer cus : this.customers) {
                close.append(cus).append(" ");
            }
        }
        return close.toString();
    }

}
