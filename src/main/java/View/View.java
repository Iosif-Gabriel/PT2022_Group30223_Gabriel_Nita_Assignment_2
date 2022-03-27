package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JLabel numberClients;
    private JTextField textNumberClients;
    private JLabel numberQ;
    private JTextField textNumberQ;
    private JLabel simInter;
    private JTextField textSimInt;
    private JLabel minArrivTime;
    private JTextField textMinArivt;
    private JLabel maxArrivTime;
    private JTextField textMaxArivT;
    private JLabel labelMinSerTime;
    private JTextField textMinSerTime;
    private JLabel labelmaxSerTime;
    private JTextField textMaxSerTime;
    private JButton startSimButton;
    private JButton resetSimButton;
    private static JTextArea jRez;

    public View(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(650,360);
        setPreferredSize (new Dimension(624, 335));
        setLayout (null);
        this.setTitle("Queues Simulator1");

        numberClients = new JLabel ("Enter the number of clients:");
        textNumberClients = new JTextField (5);
        numberQ = new JLabel ("Enter number of queues:");
        textNumberQ = new JTextField (5);
        simInter = new JLabel ("Enter simulation interval:");
        textSimInt = new JTextField (5);
        minArrivTime = new JLabel ("Enter minumum arrival time:");
        textMinArivt = new JTextField (5);
        maxArrivTime = new JLabel ("Enter maximum arrival time:");
        textMaxArivT = new JTextField (5);
        labelMinSerTime = new JLabel ("Enter minumum service time:");
        textMinSerTime = new JTextField (5);
        labelmaxSerTime = new JLabel ("Enter maximum service time:");
        textMaxSerTime = new JTextField (5);
        startSimButton = new JButton ("StartSimulation");
        jRez = new JTextArea (20, 20);
        JScrollPane jScrollPane=new JScrollPane(jRez);
        jRez.setEnabled (false);
        resetSimButton=new JButton("Reset Simulation");


        this.add (numberClients);
        this.add (textNumberClients);
        this.add (numberQ);
        this.add (textNumberQ);
        this.add (simInter);
        this.add (textSimInt);
        this.add (minArrivTime);
        this.add (textMinArivt);
        this.add (maxArrivTime);
        this.add (textMaxArivT);
        this.add (labelMinSerTime);
        this.add (textMinSerTime);
        this.add (labelmaxSerTime);
        this.add (textMaxSerTime);
        this.add (startSimButton);
        this.add (resetSimButton);
        this.add(jScrollPane);


        numberClients.setBounds (50, 0, 160, 25);
        textNumberClients.setBounds (210, 0, 100, 25);
        numberQ.setBounds (380, -5, 165, 35);
        textNumberQ.setBounds (525, 0, 100, 25);
        simInter.setBounds (160, 95, 145, 30);
        textSimInt.setBounds (310, 100, 100, 25);
        minArrivTime.setBounds (45, 25, 165, 25);
        textMinArivt.setBounds (210, 25, 100, 25);
        maxArrivTime.setBounds (45, 45, 165, 30);
        textMaxArivT.setBounds (210, 50, 100, 25);
        labelMinSerTime.setBounds (355, 25, 175, 25);
        textMinSerTime.setBounds (525, 25, 100, 25);
        labelmaxSerTime.setBounds (355, 50, 170, 25);
        textMaxSerTime.setBounds (525, 50, 100, 25);
        startSimButton.setBounds (5, 185, 135, 55);
        resetSimButton.setBounds(5,100,135,55);
        jRez.setBounds (150, 135, 420, 180);
        jScrollPane.setBounds(150,135,420,180);
    }

    public JButton getResetSimButton() {
        return resetSimButton;
    }

    public void addButtonListener(ActionListener ac){
        this.startSimButton.addActionListener(ac);
   }

   public void resetButton(ActionListener ac){
        this.resetSimButton.addActionListener(ac);
   }

    public JTextField getTextNumberClients() {
        return textNumberClients;
    }


    public JTextField getTextNumberQ() {
        return textNumberQ;
    }

    public JTextField getTextSimInt() {
        return textSimInt;
    }


    public JTextField getTextMinArivt() {
        return textMinArivt;
    }


    public JTextField getTextMaxArivT() {
        return textMaxArivT;
    }

    public JTextField getTextMinSerTime() {
        return textMinSerTime;
    }


    public JTextField getTextMaxSerTime() {
        return textMaxSerTime;
    }

    public JButton getStartSimButton() {
        return startSimButton;
    }

    public JTextArea getjRez() {
        return jRez;
    }

    public static void setjRez(String resz) {
        jRez.setText(jRez.getText()+resz);
    }
}
