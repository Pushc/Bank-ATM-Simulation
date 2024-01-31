package bank.atm.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//package bank.atm.simulation;
public class mini extends JFrame implements ActionListener{
    JButton button;
    String pin;
    mini(String pin){
        this.pin=pin;

        getContentPane().setBackground(new Color(255,204,204));
        setSize(500,600);
        setLocation(30,30);
        setLayout(null);

        JLabel label1=new JLabel();
        label1.setBounds(20,60,400,400);
        add(label1);

        JLabel label2 = new JLabel("Pushkar's Finance");
        label2.setFont(new Font("System", Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,40,300,50);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,430,300,50);
        add(label4);

        try{
            Con c=new Con();
            ResultSet rs=c.statement.executeQuery("select * from login where pin = '"+pin+"' ");
            while(rs.next()){
                label3.setText("Card Number: "+rs.getString("card_number").substring(0,4)+"XXXXXXXX"+rs.getString("card_number").substring(12));

            }
        }catch(Exception E){
            E.printStackTrace();
        }

        try{
            int balance=0;
            Con c=new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){
                label1.setText(label1.getText() + "<html>"+resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+ "<br><br><html>");
                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
        }
            label4.setText("Your Total Balance is Rs "+balance);
        }catch(Exception e){
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
