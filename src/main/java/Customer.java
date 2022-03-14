public class Customer implements Runnable{
    int id;
    int arrival_time;
    int service_time;

    public Customer(int id, int arrival_time,int service_time) {
        this.id=id;
        this.arrival_time=arrival_time;
        this.service_time=service_time;
    }


    @Override
    public void run() {

    }

    public void print(){
        System.out.println(this.id +" "+this.arrival_time+" "+this.service_time);
    }
}
