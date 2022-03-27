package DataModels;
import java.util.ArrayList;


public class Scheduler{
    private ArrayList<MyQueues> customerArrayList;
    private int nrCustomers;
    private int nrQs;
    public MasterPlan masterPlan;

    public Scheduler(int nrCustomers,int nrQs){
        this.nrCustomers = nrCustomers;
        this.nrQs = nrQs;
        this.customerArrayList = new ArrayList<>(nrQs);
        this.masterPlan = new MasterPlan();
        startThread(nrCustomers);
    }

    private void startThread(int nrCustomers) {
        for (int c = 0; c < this.nrQs; c++) {
            MyQueues queue = new MyQueues(nrCustomers);
            this.customerArrayList.add(queue);
            Thread tQ = new Thread(queue);
            tQ.start();
        }
    }

    public void sendCustomer(Customer cus){
        masterPlan.addCustomer2List(this.customerArrayList,cus);
    }

    public boolean qClosed(){
        int close=1;
        for(MyQueues q:this.customerArrayList){
            if (q.isOpen()) {
                close = 0;
                break;
            }
        }
        return close == 1;
    }

    public int getNrCustomers() {
        return nrCustomers;
    }

    public int getNrQs() {
        return nrQs;
    }

    public MasterPlan getMasterPlan() {
        return masterPlan;
    }

    public ArrayList<MyQueues> getCustomerArrayList() {
        return customerArrayList;
    }

    public String toString() {
        StringBuilder queue = new StringBuilder();
        for (MyQueues queues : this.customerArrayList) {
            queue.append("Queue ").append(queues.getQueueID()).append(": ").append(queues.toString()).append("\n");
        }
        return queue.toString();
    }
}
