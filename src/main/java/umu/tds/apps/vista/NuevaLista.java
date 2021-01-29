package umu.tds.apps.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class NuevaLista extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTable table_1;
	private JTextField textField_3;
	
	public NuevaLista() {

setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(975, 40));
		panel_2.setMinimumSize(new Dimension(975, 40));
		panel_2.setMaximumSize(new Dimension(975, 40));
		add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre de playlist");
		panel_2.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_2.add(textField_3);
		textField_3.setColumns(15);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_5);
		
		JButton btnNewButton_5 = new JButton("Crear");
		panel_2.add(btnNewButton_5);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_6);
		
		JButton btnNewButton_6 = new JButton("Eliminar");
		panel_2.add(btnNewButton_6);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 40));
		panel.setMinimumSize(new Dimension(10, 40));
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
		panel_1.setMaximumSize(new Dimension(975, 470));
		add(panel_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Canciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setToolTipText("");
		scrollPane.setPreferredSize(new Dimension(400, 390));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		table.setPreferredSize(new Dimension(400, 367));
		scrollPane.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JButton btnNewButton_3 = new JButton("> >");
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(btnNewButton_3);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panel_4.add(rigidArea_4);
		
		JButton btnNewButton_4 = new JButton("< <");
		btnNewButton_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(btnNewButton_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_6.add(scrollPane_1);
		scrollPane_1.setPreferredSize(new Dimension(400, 390));
		
		table_1 = new JTable();
		table_1.setPreferredSize(new Dimension(400, 367));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(76);
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 40));
		panel_3.setMinimumSize(new Dimension(10, 40));
		add(panel_3);
		
		JButton btnNewButton_1 = new JButton("Aceptar");
		panel_3.add(btnNewButton_1);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_3);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		panel_3.add(btnNewButton_2);
	}

}
