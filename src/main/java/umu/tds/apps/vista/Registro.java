package umu.tds.apps.vista;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class Registro extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JPanel login_contents;
	private Login login_window;
	private JTextField nombre_textField;
	private JTextField apellidos_textField;
	private JTextField textField_2;
	private JTextField email_textField;
	private JTextField username_textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblNombreWarning;
	private JLabel lblApellidosWarning;
	private JLabel lblFechaWarning;
	private JLabel lblEmailWarning;
	private JLabel lblUsernameWarning;
	private JLabel lblPasswordWarning;
	private JLabel lblRepeatWarning;
	private JLabel lblAllFieldsWarning;
	private JLabel lblPasswordMatchWarning;
	private JLabel lblUserRegisteredWarning;
	
	public Registro(JPanel contents, Login window) {
		
		// Se guarda una referencia a la ventana de login para poder mandarle mensajes.
		login_window = window;
		
		// Los contenidos del panel de login se guardan aqui.
		login_contents = contents;
		
		
		setMinimumSize(new Dimension(10, 350));
		setMaximumSize(new Dimension(32767, 350));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 200));
		panel.setMaximumSize(new Dimension(32767, 200));
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 210, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		nombre_textField = new JTextField();
		GridBagConstraints gbc_nombre_textField = new GridBagConstraints();
		gbc_nombre_textField.insets = new Insets(0, 0, 5, 5);
		gbc_nombre_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombre_textField.gridx = 3;
		gbc_nombre_textField.gridy = 1;
		panel.add(nombre_textField, gbc_nombre_textField);
		nombre_textField.setColumns(10);
		
		lblNombreWarning = new JLabel("*");
		lblNombreWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblNombreWarning = new GridBagConstraints();
		gbc_lblNombreWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreWarning.gridx = 4;
		gbc_lblNombreWarning.gridy = 1;
		panel.add(lblNombreWarning, gbc_lblNombreWarning);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		apellidos_textField = new JTextField();
		GridBagConstraints gbc_apellidos_textField = new GridBagConstraints();
		gbc_apellidos_textField.insets = new Insets(0, 0, 5, 5);
		gbc_apellidos_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellidos_textField.gridx = 3;
		gbc_apellidos_textField.gridy = 2;
		panel.add(apellidos_textField, gbc_apellidos_textField);
		apellidos_textField.setColumns(10);
		
		lblApellidosWarning = new JLabel("*");
		lblApellidosWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblApellidosWarning = new GridBagConstraints();
		gbc_lblApellidosWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidosWarning.gridx = 4;
		gbc_lblApellidosWarning.gridy = 2;
		panel.add(lblApellidosWarning, gbc_lblApellidosWarning);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha nacimiento:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 3;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		lblFechaWarning = new JLabel("*");
		lblFechaWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblFechaWarning = new GridBagConstraints();
		gbc_lblFechaWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaWarning.gridx = 4;
		gbc_lblFechaWarning.gridy = 3;
		panel.add(lblFechaWarning, gbc_lblFechaWarning);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 4;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		email_textField = new JTextField();
		GridBagConstraints gbc_email_textField = new GridBagConstraints();
		gbc_email_textField.insets = new Insets(0, 0, 5, 5);
		gbc_email_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_email_textField.gridx = 3;
		gbc_email_textField.gridy = 4;
		panel.add(email_textField, gbc_email_textField);
		email_textField.setColumns(10);
		
		lblEmailWarning = new JLabel("*");
		lblEmailWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblEmailWarning = new GridBagConstraints();
		gbc_lblEmailWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailWarning.gridx = 4;
		gbc_lblEmailWarning.gridy = 4;
		panel.add(lblEmailWarning, gbc_lblEmailWarning);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre usuario:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 5;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		username_textField = new JTextField();
		GridBagConstraints gbc_username_textField = new GridBagConstraints();
		gbc_username_textField.insets = new Insets(0, 0, 5, 5);
		gbc_username_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_username_textField.gridx = 3;
		gbc_username_textField.gridy = 5;
		panel.add(username_textField, gbc_username_textField);
		username_textField.setColumns(10);
		
		lblUsernameWarning = new JLabel("*");
		lblUsernameWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblUsernameWarning = new GridBagConstraints();
		gbc_lblUsernameWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsernameWarning.gridx = 4;
		gbc_lblUsernameWarning.gridy = 5;
		panel.add(lblUsernameWarning, gbc_lblUsernameWarning);
		
		JLabel lblNewLabel_5 = new JLabel("Contrase\u00F1a:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 6;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 6;
		panel.add(passwordField, gbc_passwordField);
		
		lblPasswordWarning = new JLabel("*");
		lblPasswordWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblPasswordWarning = new GridBagConstraints();
		gbc_lblPasswordWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordWarning.gridx = 4;
		gbc_lblPasswordWarning.gridy = 6;
		panel.add(lblPasswordWarning, gbc_lblPasswordWarning);
		
		JLabel lblNewLabel_6 = new JLabel("Repetir contrase\u00F1a:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 7;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 0, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 7;
		panel.add(passwordField_1, gbc_passwordField_1);
		
		lblRepeatWarning = new JLabel("*");
		lblRepeatWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblRepeatWarning = new GridBagConstraints();
		gbc_lblRepeatWarning.insets = new Insets(0, 0, 0, 5);
		gbc_lblRepeatWarning.gridx = 4;
		gbc_lblRepeatWarning.gridy = 7;
		panel.add(lblRepeatWarning, gbc_lblRepeatWarning);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(32767, 30));
		panel_1.add(panel_2);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFields()) {
					
				}
			}
		});
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setPreferredSize(new Dimension(85, 20));
		panel_2.add(rigidArea_1);
		panel_2.add(btnNewButton);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(5, 20));
		panel_2.add(rigidArea);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		lblAllFieldsWarning = new JLabel("* Todos los campos han de ser rellenados");
		lblAllFieldsWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblAllFieldsWarning = new GridBagConstraints();
		gbc_lblAllFieldsWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllFieldsWarning.gridx = 4;
		gbc_lblAllFieldsWarning.gridy = 0;
		panel_3.add(lblAllFieldsWarning, gbc_lblAllFieldsWarning);
		
		lblPasswordMatchWarning = new JLabel("* Las dos claves han de coincidir");
		lblPasswordMatchWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblPasswordMatchWarning = new GridBagConstraints();
		gbc_lblPasswordMatchWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordMatchWarning.gridx = 4;
		gbc_lblPasswordMatchWarning.gridy = 1;
		panel_3.add(lblPasswordMatchWarning, gbc_lblPasswordMatchWarning);
		
		lblUserRegisteredWarning = new JLabel("* El usuario ya ha sido registrado");
		lblUserRegisteredWarning.setForeground(Color.RED);
		GridBagConstraints gbc_lblUserRegisteredWarning = new GridBagConstraints();
		gbc_lblUserRegisteredWarning.insets = new Insets(0, 0, 0, 5);
		gbc_lblUserRegisteredWarning.gridx = 4;
		gbc_lblUserRegisteredWarning.gridy = 2;
		panel_3.add(lblUserRegisteredWarning, gbc_lblUserRegisteredWarning);

	}
	
	private void ocultarWarnings() {
		lblNombreWarning.setVisible(false);
		lblApellidosWarning.setVisible(false);
		lblFechaWarning.setVisible(false);
		lblEmailWarning.setVisible(false);
		lblUsernameWarning.setVisible(false);
		lblPasswordWarning.setVisible(false);
		lblRepeatWarning.setVisible(false);
		lblAllFieldsWarning.setVisible(false);
		lblPasswordMatchWarning.setVisible(false);
		lblUserRegisteredWarning.setVisible(false);
	}

	private boolean checkFields() {
		ocultarWarnings();
		boolean ok = true;
		
		// Primero compruebo si se han introducido todos los datos
		
		if (nombre_textField.getText().trim().isEmpty()) {
			lblNombreWarning.setVisible(true); 
			ok=false;
		}
		
		if (apellidos_textField.getText().trim().isEmpty()) {
			lblApellidosWarning.setVisible(true); 
			ok=false;
		}
		
		if (email_textField.getText().trim().isEmpty()) {
			lblEmailWarning.setVisible(true); 
			ok=false;
		}
		
		if (username_textField.getText().trim().isEmpty()) {
			lblUsernameWarning.setVisible(true); 
			ok=false;
		}
		
		String password = new String(passwordField.getPassword());
		String password2 = new String(passwordField_1.getPassword());
		
		if (password.equals("")) {
			lblPasswordWarning.setVisible(true); 
			ok=false;
		}
		
		// Una vez comprobado, si se ha encontrado algun error
		// Entonces faltan campos por rellenar
		
		if (!ok) lblAllFieldsWarning.setVisible(true);
		
		// Compruebo que las contraseñas coincidan
		
		if (ok && !password.equals(password2)) {
			lblPasswordWarning.setVisible(true);
			lblRepeatWarning.setVisible(true);
			lblPasswordMatchWarning.setVisible(true);
			ok=false;
		}
		
		
		return false;
	}

}
