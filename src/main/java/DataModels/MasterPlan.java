package DataModels;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MasterPlan {
    public void addCustomer2List(ArrayList<MyQueues> c, Customer cus){
        AtomicInteger wait=c.get(0).getTime2Wait();
        int idQ=1;
        for(MyQueues queue:c){
            if(queue.getTime2Wait().get()<wait.get()){
                idQ=queue.getQueueID();
                wait=queue.getTime2Wait();
            }
        }
        c.get(idQ-1).addCustomer(cus);
    }
}
