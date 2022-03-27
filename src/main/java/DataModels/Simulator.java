package DataModels;

import DataModels.Customer;
import DataModels.MyQueues;
import DataModels.Scheduler;
import View.View;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Simulator implements Runnable {
    public int simInter;
    public int nrCustomers;
    public int numberQs;
    public int timeMinArr;
    public int timeMaxArr;
    public int timeMinServ;
    public int timeMaxServ;
    public double averageTimeW = 0;
    public double averageTimeS;
    public int peakH = 0;
    public int g = 0;
    private final Scheduler scheduler;
    private final ArrayList<Customer> customerArrayList;

    public Simulator(int simInter, int nrCustomers, int numberQs, int timeMinArr, int timeMaxArr, int timeMinServ, int timeMaxServ) {
        this.simInter = simInter;
        this.nrCustomers = nrCustomers;
        this.numberQs = numberQs;
        this.timeMinArr = timeMinArr;
        this.timeMaxArr = timeMaxArr;
        this.timeMinServ = timeMinServ;
        this.timeMaxServ = timeMaxServ;
        this.scheduler = new Scheduler(this.nrCustomers, this.numberQs);
        this.customerArrayList = new ArrayList<>();
        this.generateCustomers();
    }

    public void generateCustomers() {
        for (int i = 0; i < this.nrCustomers; i++) {
            Customer cus = new Customer(this.timeMinArr, this.timeMaxArr, this.timeMinServ, this.timeMaxServ);
            this.customerArrayList.add(cus);
        }
        Collections.sort(this.customerArrayList);
        avgServTime();
    }

    private void avgServTime() {
        double avg = 0;
        for (Customer cus : this.customerArrayList) {
            avg = avg + cus.getService_time();
        }
        this.averageTimeS = avg / this.nrCustomers;
    }

    @Override
    public void run() {
        FileWriter f = null;
        try {
            f = new FileWriter("outputTest2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int toph = mainLoop(f);
        MyQueues.stopThreads();
        afisare(f, toph);

    }

    private int mainLoop(FileWriter f) {
        int toph = 0;
        for(int cTime=0;cTime<=this.simInter;cTime++) {
            while (!this.customerArrayList.isEmpty() && this.customerArrayList.get(0).getArrival_time() == cTime) {
                this.scheduler.sendCustomer(this.customerArrayList.get(0));
                this.customerArrayList.remove(0);
            }
            int sizeq = 0;
            for (MyQueues q : scheduler.getCustomerArrayList()) {
                sizeq = sizeq + q.getCustomers().size();
                averageTimeW += q.getTime2Wait().doubleValue();
            }
            toph = toph + sizeq;
            if (sizeq > this.g) {
                this.g = sizeq;
                this.peakH = cTime;
            }
            View.setjRez(this.twoString(cTime));
            try {
                f.write(this.twoString(cTime));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (this.customerArrayList.isEmpty() && scheduler.qClosed()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return toph;
    }

    private void afisare(FileWriter f, int toph) {
        try {
            System.out.println(toph);
            f.write("Average waiting time: " + Math.round((averageTimeW / toph) * 100.0) / 100.0 + "\n");
            f.write("Average service time: " + this.averageTimeS + "\n");
            f.write("Peak hour: " + this.peakH + "\n");
            f.close();
            JOptionPane.showMessageDialog(null, "Average Service Time:" + this.averageTimeS + "\n" + "Peak hour:" + this.peakH+"\nAverage waiting time:"+ Math.round((averageTimeW / toph) * 100.0) / 100.0 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String twoString(int cTime) {
        StringBuilder afisare = new StringBuilder("Time: " + cTime + "\n");
        afisare.append("Waiting clients: ");
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        for(int identify=1;identify<=this.nrCustomers;identify++) {
            for (Customer cus : this.customerArrayList) {
                if (cus.getId() == identify) {
                    customerArrayList.add(cus);
                }
            }
        }
        for (Customer cus : customerArrayList) {
            afisare.append(cus.toString()).append(" ");
        }
        afisare.append("\n").append(scheduler.toString());
        return afisare.toString();
    }


}
