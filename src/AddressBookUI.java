package address.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import address.AddressBook;
import address.data.AddressEntry;

/**
 * It includes UI components
 * to show the GUI.
 * 
 * @version 1.0
 * 
 */
public class AddressBookUI implements ActionListener {

	// Attributes..
	private JFrame frameUI;
	private JPanel dataPanel, buttonsPanel;
	private JList<String> dataList;
	private JScrollPane dataScrollPane;
	private DefaultListModel<String> listModel;
	private JButton additionBTN, removalBTN, findBTN, quitBTN;
	private AddressBook book;
	
	/**
	 * Creating and Displaying the UI
	 * 
	 * @param book book
	 */
	public AddressBookUI(AddressBook book) {
	
		frameUI = new JFrame("Address Book");
		frameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameUI.setSize(700, 600);
		frameUI.setLocationRelativeTo(null);
		frameUI.setResizable(false);
		
		this.book = book;
		
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
		additionBTN.addActionListener(this);
		buttonsPanel.add(additionBTN);
		
		removalBTN = new JButton("Remove");
		removalBTN.setBackground(Color.WHITE);
		removalBTN.addActionListener(this);
		buttonsPanel.add(removalBTN);
		
		quitBTN = new JButton("Quit");
		quitBTN.setBackground(Color.WHITE);
		quitBTN.addActionListener(this);
		
		findBTN = new JButton("Find");
		findBTN.setBackground(Color.WHITE);
		findBTN.addActionListener(this);
		buttonsPanel.add(findBTN);
		buttonsPanel.add(quitBTN);
		
		dataScrollPane = new JScrollPane();
		dataPanel.add(dataScrollPane, BorderLayout.CENTER);
		
		listModel = new DefaultListModel<>();
		dataList = new JList<>(listModel);
		dataScrollPane.setViewportView(dataList);
		this.updateAddressEntries();
		
	}
	
	/**
	 * Updating the Address Entries into the
	 * UI frame.
	 */
	public void updateAddressEntries() {
		
		this.listModel.clear();
		for(AddressEntry entry: book.list()) {
			listModel.addElement(entry.toString());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Adding..
		if(e.getSource() == additionBTN) {
			
			String firstName = JOptionPane.showInputDialog("Enter First Name: ");
			String lastName = JOptionPane.showInputDialog("Enter Last Name: ");
			String street = JOptionPane.showInputDialog("Enter Street: ");
			String city = JOptionPane.showInputDialog("Enter City: ");
			String state = JOptionPane.showInputDialog("Enter State: ");
			int zip = 0;
			while(true) {
				try {
					zip = Integer.parseInt(JOptionPane.showInputDialog("Enter Zip Code: "));
					break;
				} catch(Exception ex) {}
			}
			String phone = JOptionPane.showInputDialog("Enter Phone: ");
			String email = JOptionPane.showInputDialog("Enter Email: ");
			AddressEntry entry = new AddressEntry(firstName, lastName, street, city, state,
					zip, phone, email);
			book.add(entry);
			this.updateAddressEntries();
			JOptionPane.showMessageDialog(null, "Address is added.");
			
		} else if(e.getSource() == removalBTN) {
			
			int index = dataList.getSelectedIndex();
			if(index != -1) {
				listModel.remove(index);
				book.remove(book.list().get(index));
				this.updateAddressEntries();
				JOptionPane.showMessageDialog(null, "Address is removed.");
			} else {
				JOptionPane.showMessageDialog(null, "Select 1 row from the list.");
			}
			
		} else if(e.getSource() == findBTN) {

			String find = JOptionPane.showInputDialog("Enter Last Name: ");
			List<AddressEntry> entries = book.find(find);
			String line = "";
			for(int i = 0; i < entries.size(); i++) {
				line += (i+1)+":"+entries.get(i)+"\n";
			}
			JOptionPane.showMessageDialog(null, "The following "+entries.size()+" entries were found in the address book"
					+ " for a last name starting with \""+find+"\":\n"+line);
			
		} else if(e.getSource() == quitBTN) {
			System.exit(0);
		}
		
	}
	
}
