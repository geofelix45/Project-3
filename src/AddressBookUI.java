package com.company;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * It includes UI components
 * to show the GUI.
 *
 * @version 1.0
 *
 */
public class AddressBookUI {

    // Attributes..
    private JFrame frameUI;
    private JPanel dataPanel, buttonsPanel;
    private JList<String> dataList;
    private JScrollPane dataScrollPane;
    private DefaultListModel<String> listModel;
    private JButton additionBTN, removalBTN, findBTN, quitBTN;

    /**
     * Creating & Displaying the UI
     */
    public AddressBookUI() {

        frameUI = new JFrame("Address Book");
        frameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameUI.setSize(700, 600);
        frameUI.setLocationRelativeTo(null);
        frameUI.setResizable(false);

        addUIComponents();
        frameUI.setVisible(true);

    }

    /**
     * Adding UI Components into the frame.
     */
    private void addUIComponents() {

        dataPanel = new JPanel();
        dataPanel.setBackground(Color.WHITE);
        dataPanel.setLayout(new BorderLayout(0, 0));
        frameUI.setContentPane(dataPanel);

        buttonsPanel = new JPanel();
        dataPanel.add(buttonsPanel, BorderLayout.NORTH);

        additionBTN = new JButton("Add New");
        additionBTN.setBackground(Color.WHITE);
        buttonsPanel.add(additionBTN);

        removalBTN = new JButton("Remove");
        removalBTN.setBackground(Color.WHITE);
        buttonsPanel.add(removalBTN);

        quitBTN = new JButton("Quit");
        quitBTN.setBackground(Color.WHITE);
        buttonsPanel.add(quitBTN);

        dataScrollPane = new JScrollPane();
        dataPanel.add(dataScrollPane, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        dataList = new JList<>(listModel);
        dataScrollPane.setViewportView(dataList);

    }

    /**
     * Updating the Address Entries into the
     * UI frame.
     */
    public void updateAddressEntries(List<AddressEntry> entries) {

        this.listModel.clear();
        for(AddressEntry entry: entries) {
            listModel.addElement(entry.toString());
        }

    }

}
