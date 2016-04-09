package com.Brendon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class ticketGUI  extends JFrame{
    private JPanel rootPanel;
    private JTextField ticketEntry;
    private JList ticketList;
    private JScrollPane openTickets;
    private JButton addToListButton;
    private JButton resolveSelectionButton;
    private JButton quitButton;

    private JComboBox severityLevel;
    private int sevSelect = 0;

    protected DefaultListModel listModel;
    Vector<Ticket> ticketData;
    private int ticketNum = 0; // ticket ID number


    public ticketGUI() {

        super("Ticket Manager");

        ticketData = File.getLakes();

        listModel = new DefaultListModel<String>();
        setPreferredSize(new Dimension(500,500));

        for (Ticket t : ticketData) { // This goes over the object and displays them as well as setting the new
                                    // ticket ID.

            ticketNum = t.getIdNum();
            listModel.addElement(t);


        }


        ticketList.setModel(listModel);
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        configureSeverity();
        addListeners();

        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


    }

    protected void configureSeverity() { // adds a drop down menu for severity levels.

        for (int x = 1; x <= 5; x++) {

            this.sevSelect = x;
            severityLevel.addItem(x);

        }

    }

    protected void addListeners() {

        /*
        Gets the typed message and displays it, also adds the jobs to the proper lists.
         */
        addToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ticket = ticketEntry.getText();
                ticket = ticket.trim();
                ticketNum += 1;


                int sevLevel =  (int) severityLevel.getSelectedItem();


                Ticket newTicket = new Ticket(ticketNum,ticket,sevLevel);

                listModel.addElement(newTicket);
                ticketEntry.setText("");
                ticketData.addElement(newTicket);


            }
        });


        resolveSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ticket deletion = (Ticket)ticketList.getSelectedValue();
                listModel.removeElement(deletion);
                ticketData.removeElement(deletion);

            }
        });

        quitButton.addActionListener(new ActionListener() { // calls the File class and writes information to the file
            @Override
            public void actionPerformed(ActionEvent e) {

                File.saveTicket(ticketData);
                System.exit(0);

            }
        });



    }

}
