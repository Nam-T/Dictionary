package diction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class AddWord extends JFrame {
    JLabel target, explain;
    JButton btnOk;
    JTextField tfTarget,tfExplain;
    String w_target1;
    String w_explain1;
    WriteToFile wtf;
    public AddWord(){
        setTitle("Nhập dữ liệu cần thêm");
        doAddWord();

    }
    public void doAddWord(){
        target = new JLabel("Tiếng Anh: ");
        explain = new JLabel("Tiếng Việt: ");
        btnOk = new JButton("Đồng ý");
        tfTarget = new JTextField();
        tfExplain = new JTextField();

        tfTarget.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTargetKeyReleased(evt);
            }
        });

        setLayout(new GridLayout(5,2,5,5));
        add(target);
        add(tfTarget);
        add(explain);
        add(tfExplain);
        add(btnOk);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                w_target1=tfTarget.getText();
                w_explain1=tfExplain.getText();
                Word word = new Word(w_target1.toLowerCase(),w_explain1.toLowerCase());
                if((w_target1.compareTo("")==0)||(w_target1.compareTo("")==0)){
                    JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập đủ thông tin");
                }else{
                    try {
                        Connect_RF.rf.fReader();
                        if(Connect_RF.rf.tm.containsKey(w_target1)){
                            JOptionPane.showMessageDialog(rootPane, "Từ này đã có trong từ điển");
                        }else {
                            Connect_RF.rf.tm.put(w_target1,word);
                            wtf = new WriteToFile("loanword1");
                            wtf.Write(Connect_RF.rf.tm.values());
                            setVisible(false);
                        }
                    } catch (IOException ex) {
                    }
                }

            }
        });
        setSize(300, 200);
        setLocationRelativeTo(null);
    }
    private void tfTargetKeyReleased(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_TAB){
            tfExplain.requestFocus();
        }

    }
}
