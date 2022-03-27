package DataModels;

import java.util.Random;

public class Customer implements Comparable<Customer>{
    private int id;
    private static int one=1;
    private int arrival_time;
    private int service_time;

    public Customer(int minArriv,int maxArriv,int minSer,int maxSer) {
        this.setId(Customer.getOne());
        Customer.setOne(Customer.getOne()+1);
        randomArrivSerTime(minArriv, maxArriv, minSer, maxSer);
    }

    private void randomArrivSerTime(int minArriv, int maxArriv, int minSer, int maxSer) {
        Random rand=new Random();
        this.setArrival_time(rand.nextInt((maxArriv - minArriv)+1)+ minArriv);
        this.setService_time(rand.nextInt((maxSer - minSer)+1)+ minSer);
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public void setService_time(int service_time) {
        this.service_time = service_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getOne() {
        return one;
    }

    public int getId() {
        return id;
    }

    public static void setOne(int one) {
        Customer.one = one;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public int getService_time() {
        return service_time;
    }

    @Override
    public String toString() {
        return " " +
                "(" + id +
                "," + arrival_time +
                "," + service_time +
                ")";
    }

    @Override
    public int compareTo(Customer cus) {
        return Integer.compare(this.arrival_time,cus.arrival_time);
    }
}
