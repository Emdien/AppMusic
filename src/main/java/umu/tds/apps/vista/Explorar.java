package umu.tds.apps.vista;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Explorar extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Explorar() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 30));
		panel.setMinimumSize(new Dimension(10, 30));
		panel.setMaximumSize(new Dimension(975, 40));
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Interprete");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(15);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(15);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estilo");
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_2);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
			}
		});
		btnNewButton.setPreferredSize(new Dimension(100, 26));
		btnNewButton.setMinimumSize(new Dimension(100, 26));
		btnNewButton.setMaximumSize(new Dimension(100, 26));
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(975, 550));
		add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 500));
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		table.setPreferredSize(new Dimension(400, 477));
		scrollPane.setViewportView(table);

	}
	


}
