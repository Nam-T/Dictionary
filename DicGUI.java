package diction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.management.StringValueExp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DicGUI extends JFrame {
    WriteToFile wtf ;

    private int width = 700;
    private int height = 600;
    private JTextField tfWordExplain;
    private JList<String> myList;
    private JTextField tfFind;
    private JButton BtnFind;
    public TreeMap tm = new TreeMap<String, Word>();
    DefaultListModel defaultListModel=new DefaultListModel();
    Scanner read = new Scanner(new FileInputStream("C:\\Users\\g1\\eclipse-workspace\\Dictionary\\src\\dic1\\loanword1.dat"),"UTF-8");

    public DicGUI() throws FileNotFoundException, IOException {
        Connect_RF.rf.fReader();
        setLayout(new GridLayout(1, 2, 5, 5));
        // add main panel
        add(createListPanel());
        add(createWordExplainPanel());
        // display
        setTitle("Từ điển Anh - Việt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private JPanel createListPanel() throws FileNotFoundException, IOException {

        JPanel panel = new JPanel(new BorderLayout());
        myList = new javax.swing.JList<>();
        tfFind = new javax.swing.JTextField();

        panel.setBorder(new EmptyBorder(0, 10, 10, 10));

        searchFilter(tfFind.getText());
        myList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        myList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myJListMouseClicked(evt);
            }
        });

        myList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                myJListKeyReleased(evt);
            }
        });

        //jScrollPane1.setViewportView(myList);
        tfFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtKeyReleased(evt);
            }
        });

        //set font
        myList.setFont(new java.awt.Font("Tahoma", 0, 18));
        tfFind.setFont(new java.awt.Font("Tahoma", 0, 18));

        panel.add(tfFind,BorderLayout.PAGE_START);
        panel.add(new JScrollPane(myList),
                BorderLayout.CENTER);
        return panel;
    }

    private JPanel createWordExplainPanel() {
        JPanel panel = new JPanel(new GridLayout(5,1,5,5));
        panel.add(createButtonFind());
        panel.add(createWordExplain());
        panel.add(createButtonDelete());
        panel.add(createButtonAdd());
        panel.add(createButtonRefresh());

        return panel;
    }
    //add panel

    private JPanel createWordExplain() {
        JPanel panel = new JPanel(new BorderLayout());
        tfWordExplain = new JTextField();
        tfWordExplain.setBackground(new java.awt.Color(255, 85, 85));
        tfWordExplain.setForeground(new java.awt.Color(1, 1, 1));
        tfWordExplain.setFont(new java.awt.Font("Tahoma", 0, 18));
        tfWordExplain.setSize(300,300);
        tfWordExplain.setEditable(false); // không cho phép nhập dữ liệu
        panel.add(tfWordExplain);
        return panel;
    }

    private JPanel createButtonFind(){
        JPanel panel = new JPanel();
        JButton btn = new JButton("Tìm kiếm nè");
        // add action
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String textt = tfFind.getText();
                if(tm.get(textt)==null){tfWordExplain.setText("Từ này không có trong từ điển");}
                else{
                    tfWordExplain.setText(String.valueOf(tm.get(textt)));}

            }
        });
        panel.add(btn);
        return panel;
    }

    private  JPanel createButtonRefresh(){
        JPanel panel = new JPanel();
        JButton btn = new JButton("Refresh");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner read1 = new Scanner(new FileInputStream("loanword1.dat"),"UTF-8");
                    String line;
                    while(read1.hasNextLine()) //khi chưa hết file
                    {
                        line = read1.nextLine(); //đọc 1 dòng
                        String[] temp;
                        if(!line.contains("#")) // nếu trong dòng không có kí tự #
                        {
                            temp = line.split("\t");
                            String key = temp[0].trim();
                            String val = temp[1].trim();
                            if(tm.containsKey(key.toLowerCase())==false){
                                tm.put(key.toLowerCase(),val.toLowerCase());
                                Word word = new Word(key.toLowerCase(),val.toLowerCase());
                                Connect_RF.rf.tm.put(key.toLowerCase(),word);
                            }
                        }
                    }
                } catch (IOException ex) {
                }

                searchFilter("");
            }
        });
        panel.add(btn);
        return panel;
    }

    private JPanel createButtonAdd(){
        JPanel panel = new JPanel();
        JButton btn = new JButton("Add từ nè");
        // add action
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                AddWord addWord = new AddWord();
                addWord.setVisible(true);

            }
        });
        panel.add(btn);
        return panel;
    }

    private JPanel createButtonDelete(){
        JPanel panel = new JPanel();
        JButton btn = new JButton("Delete từ nè");
        // add action
        btn.addActionListener(new ActionListener() {
            @Override


            public void actionPerformed(ActionEvent arg0){



                if(myList.isSelectionEmpty()==false){
                    int n= JOptionPane.showConfirmDialog(
                            panel,
                            "Bạn có chắc muốn xóa \" " + myList.getSelectedValue() + " \" khỏi từ điển ?",
                            "Delete",
                            JOptionPane.YES_NO_OPTION
                    );
                    if(n==JOptionPane.YES_OPTION){
                        tm.remove(myList.getSelectedValue());

                        Connect_RF.rf.tm.remove(myList.getSelectedValue());
                        try {
                            wtf = new WriteToFile("loanword1");
                            wtf.Write(Connect_RF.rf.tm.values());
                        } catch (IOException ex) {
                        }
                        searchFilter("");}
                }  }

        });

        panel.add(btn);
        return panel;
    }

    private void searchTxtKeyReleased(java.awt.event.KeyEvent evt) {
        searchFilter(tfFind.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            String textt = tfFind.getText();
            if(tm.get(textt)==null){tfWordExplain.setText("Từ này không có trong từ điển");}
            else{
                tfWordExplain.setText(String.valueOf(tm.get(textt)));}

        }
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            myList.requestFocus();
        }
    }

    private void myJListKeyReleased(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){

            if(myList.getSelectedIndex()>=0){
                changeWordTarget();
                tfFind.setText(myList.getSelectedValue());
                myList.setSelectedIndex(0);
                tfFind.requestFocus();
            }
        }

    }

    private void myJListMouseClicked(java.awt.event.MouseEvent evt) {
        changeWordTarget();
    }
    private void searchFilter(String searchTerm)
    {
        DefaultListModel filteredItems=new DefaultListModel();
        ArrayList stars=getStars();

        stars.stream().forEach((star) -> {
            String starName=star.toString().toLowerCase();
            if (starName.contains(searchTerm.toLowerCase())) {
                filteredItems.addElement(star);
            }
        });
        defaultListModel=filteredItems;
        myList.setModel(defaultListModel);

    }
    // create Jlist

    private ArrayList getStars(){
        ArrayList stars=new ArrayList();

        String line;
        while(read.hasNextLine()) //khi chưa hết file
        {
            line = read.nextLine(); //đọc 1 dòng
            String[] temp;
            if(!line.contains("#")) // nếu trong dòng không có kí tự #
            {
                temp = line.split("\t");
                String key = temp[0].trim();
                String val = temp[1].trim();
                tm.put(key.toLowerCase(),val.toLowerCase());
            }
        }
        Iterator<String> iterator=tm.keySet().iterator();
        while (iterator.hasNext()) {
            stars.add(iterator.next());
        }
        return stars;
    }

    private void bindData(){
        //foreach with functinal operation
        getStars().stream().forEach((star) -> {
            defaultListModel.addElement(star);
        });
        myList.setModel(defaultListModel);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void changeWordTarget(){
        tfWordExplain.setText(String.valueOf(tm.get(myList.getSelectedValue())));
    }
}

