package framePack.experimentPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTask extends JFrame implements ActionListener {
    private JLabel label = new JLabel();
    private JLabel err = new JLabel();
    public void error(String str){
        err.setText("Error: "+str);
        err.setForeground(Color.red);
        err.setBounds(0, 50, 414, 100);
        err.setFont(new Font("Arial", Font.PLAIN, 15));
        err.setVisible(true);
    }

    public frameTask() {

        setTitle("Calculator");
        setBounds(480, 200, 414,402);
        setLayout(null);

        JPanel btnPanel = new JPanel();
        btnPanel.setBounds(0, 165, 400, 250);
        btnPanel.setLayout(new GridLayout(5, 3));
        btnPanel.setVisible(true);

        add(btnPanel);

        String[] buttons = new String[]{
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                ".", "0", "=", "+"};



        for (int i = 0; i < buttons.length; i++) {

            JButton button = new JButton(buttons[i]);
            button.setFont(new Font("Arial", Font.PLAIN, 45));

            button.setBackground(Color.white);

            if(i==3 || i==7 || i==11 || i==12 || i==15){
                button.setBackground(Color.lightGray);
            }

            if(i==14){
                button.setBackground(Color.blue);
                button.setForeground(Color.white);
            }


            button.addActionListener(this);
            btnPanel.add(button);

        }

        label.setBounds(0, 0, 414, 100);
        label.setFont(new Font("Arial", Font.PLAIN, 45));

        add(label);
        add(err);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String[] labelArr = label.getText().split("[+*/-]");

        for (int i = 0; i < labelArr.length; i++) {
            if(labelArr[i].length() == 10){
                label.setText(label.getText());
                error("Number can not be bigger than 10 characters");
                break;
            }if(labelArr[i].indexOf('0') == 0
                    || labelArr[i].indexOf('+') == 0
                    || labelArr[i].indexOf('-') == 0
                    || labelArr[i].indexOf('*') == 0
                    || labelArr[i].indexOf('/') == 0){
                error("Expression can not start with"+" << "+label.getText().charAt(0)+" >> symbol");
            }
        }
        switch (e.getActionCommand()) {
            case "0":
                label.setText(label.getText() + 0);
                break;

            case "1":
                label.setText(label.getText() + 1);
                break;

            case "2":
                label.setText(label.getText() + 2);
                break;

            case "3":
                label.setText(label.getText() + 3);
                break;

            case "4":
                label.setText(label.getText() + 4);
                break;

            case "5":
                label.setText(label.getText() + 5);
                break;

            case "6":
                label.setText(label.getText() + 6);
                break;

            case "7":
                label.setText(label.getText() + 7);
                break;

            case "8":
                label.setText(label.getText() + 8);
                break;

            case "9":
                label.setText(label.getText() + 9);
                break;

            case "+":
                label.setText(label.getText() + '+');
                break;

            case "-":
                label.setText(label.getText() + '-');
                break;

            case "*":
                label.setText(label.getText() + '*');
                break;

            case "/":
                label.setText(label.getText() + '/');
                break;

            case ".":
                label.setText(label.getText() + '.');
                break;

            case "=":

        }
    }
}
