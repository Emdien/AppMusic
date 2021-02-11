package umu.tds.apps.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import umu.tds.apps.controlador.ControladorAppMusic;

import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel content_panel_container;
	private Registro registro;
	private ControladorAppMusic controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		controlador = ControladorAppMusic.getUnicaInstancia();
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel header_spacing_panel = new JPanel();
		header_spacing_panel.setPreferredSize(new Dimension(10, 30));
		header_spacing_panel.setMinimumSize(new Dimension(10, 30));
		header_spacing_panel.setMaximumSize(new Dimension(32767, 50));
		frame.getContentPane().add(header_spacing_panel);
		
		JPanel header_panel = new JPanel();
		header_panel.setPreferredSize(new Dimension(10, 80));
		header_panel.setMinimumSize(new Dimension(32767, 100));
		header_panel.setMaximumSize(new Dimension(32767, 100));
		frame.getContentPane().add(header_panel);
		header_panel.setLayout(new BoxLayout(header_panel, BoxLayout.Y_AXIS));
		
		JLabel headerLabel = new JLabel("AppMusic");
		headerLabel.setFont(new Font("Dialog", Font.BOLD, 36));
		headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		header_panel.add(headerLabel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(20, 15));
		rigidArea.setMaximumSize(new Dimension(20, 15));
		header_panel.add(rigidArea);
		
		JSeparator separator = new JSeparator();
		separator.setMaximumSize(new Dimension(300, 32767));
		header_panel.add(separator);
		
		
		// Panel de contenidos de la ventana de login/registro
		
		registro = new Registro(this);
		registro.setVisible(false);
		frame.getContentPane().add(registro);
		
		
		content_panel_container = new JPanel();
		content_panel_container.setMaximumSize(new Dimension(32767, 350));
		frame.getContentPane().add(content_panel_container);
		content_panel_container.setLayout(new BoxLayout(content_panel_container, BoxLayout.Y_AXIS));
		
		JPanel fields_panel = new JPanel();
		fields_panel.setMaximumSize(new Dimension(32767, 100));
		content_panel_container.add(fields_panel);
		GridBagLayout gbl_fields_panel = new GridBagLayout();
		gbl_fields_panel.columnWidths = new int[]{0, 41, 97, 230, 0, 0, 0, 0};
		gbl_fields_panel.rowHeights = new int[]{35, 35, 0};
		gbl_fields_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_fields_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		fields_panel.setLayout(gbl_fields_panel);
		
		JLabel usernameLabel = new JLabel("Usuario:");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.anchor = GridBagConstraints.EAST;
		gbc_usernameLabel.gridx = 2;
		gbc_usernameLabel.gridy = 0;
		fields_panel.add(usernameLabel, gbc_usernameLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		fields_panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.insets = new Insets(0, 0, 0, 5);
		gbc_passwordLabel.gridx = 2;
		gbc_passwordLabel.gridy = 1;
		fields_panel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.ipady = 5;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 1;
		fields_panel.add(passwordField, gbc_passwordField);
		
		JPanel buttons_panel = new JPanel();
		content_panel_container.add(buttons_panel);
		
		JButton btnNewButton = new JButton("Iniciar sesi\u00F3n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = textField.getText().trim();
				String password = new String(passwordField.getPassword());
				boolean logged = controlador.login(usuario, password);
				
				if (logged) {
					Principal windowP = new Principal();
					frame.dispose();
					
				} else {
					JOptionPane.showMessageDialog(frame,
							"Nombre de usuario o contrase\u00F1a no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setPreferredSize(new Dimension(67, 20));
		buttons_panel.add(rigidArea_1);
		buttons_panel.add(btnNewButton);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_2.setPreferredSize(new Dimension(5, 20));
		buttons_panel.add(rigidArea_2);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeToRegister();
			}
		});
		buttons_panel.add(btnNewButton_1);
	}

	public void changeContents(int flag) {	// Flag = 0 no muestra nada (Cancelar) Flag = 1 registrado con exito.
		content_panel_container.setVisible(true);
		registro.setVisible(false);
		frame.setTitle("AppMusic Login");
		frame.revalidate();
		frame.repaint();
		
	}
	
	private void changeToRegister() {
		System.out.println("Cambiando a panel de registro");
		frame.setTitle("AppMusic Registro");
		registro.setVisible(true);
		content_panel_container.setVisible(false);
		frame.revalidate();
		frame.repaint();
	}

	public boolean registrar(String nombre, String apellidos, Date fecha, String email, String username, String password) {
		
		return controlador.registrarUsuario(nombre, apellidos, fecha, email, username, password);
	}
	
	

}
