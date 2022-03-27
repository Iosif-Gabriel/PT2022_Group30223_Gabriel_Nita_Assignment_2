package Controller;

import DataModels.Customer;
import DataModels.MyQueues;
import DataModels.Simulator;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerQueues {
    private View inter;

    public ControllerQueues(View inter) {
        this.inter = inter;
        inter.addButtonListener(new StartSimListener());
        inter.resetButton(new ResetSimListener());
    }

    class StartSimListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Thread th;
            Simulator sim;
            if (e.getSource() == inter.getStartSimButton()) {
                int simInter = Integer.parseInt(inter.getTextSimInt().getText());
                int nrcustomers = Integer.parseInt(inter.getTextNumberClients().getText());
                int minArrivT = Integer.parseInt(inter.getTextMinArivt().getText());
                int maxArrivT = Integer.parseInt(inter.getTextMaxArivT().getText());
                int nrQs = Integer.parseInt(inter.getTextNumberQ().getText());
                int minSerT = Integer.parseInt(inter.getTextMinSerTime().getText());
                int maxSerT = Integer.parseInt(inter.getTextMaxSerTime().getText());
                sim = new Simulator(simInter, nrcustomers, nrQs, minArrivT, maxArrivT, minSerT, maxSerT);
                th = new Thread(sim);
                th.start();
            }
        }
    }

    class ResetSimListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == inter.getResetSimButton()) {
                inter.getjRez().setText("");
                inter.getTextSimInt().setText("");
                inter.getTextNumberClients().setText("");
                inter.getTextMinArivt().setText("");
                inter.getTextMaxArivT().setText("");
                inter.getTextNumberQ().setText("");
                inter.getTextMinSerTime().setText("");
                inter.getTextMaxSerTime().setText("");
                MyQueues.setOne(1);
                Customer.setOne(1);
            }
        }
    }
}
