package umu.tds.apps.vista;

import javax.swing.JPanel;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Usuario;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MejorarCuenta extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private ControladorAppMusic controlador;
	private JFrame ventana;
	private JLabel lblNewLabel_7;
	
	public MejorarCuenta(JFrame ventana) {
		
		controlador = ControladorAppMusic.getUnicaInstancia();
		this.ventana = ventana;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_4.setMaximumSize(new Dimension(20, 40));
		add(rigidArea_4);
		
		JLabel lblNewLabel = new JLabel("Mejorar cuenta AppMusic");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea);
		
		JLabel lblNewLabel_1 = new JLabel("Disfrute de una gran cantidad de beneficios al mejorar su cuenta, tales como:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("- Ver las canciones m\u00E1s reproducidas en AppMusic");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("- Ser capaz de exportar tus playlists");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("- \u00A1Y otros muchos m\u00E1s beneficios!");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_4);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setMaximumSize(new Dimension(20, 40));
		rigidArea_1.setMinimumSize(new Dimension(20, 40));
		rigidArea_1.setPreferredSize(new Dimension(20, 40));
		add(rigidArea_1);
		
		JLabel lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_5.setText("Descuento aplicado en su cuenta: " + controlador.getUsuarioActual().getDescuento().getValorDescuento()*100 +"%");
		lblNewLabel_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_5);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_2.setMaximumSize(new Dimension(20, 40));
		add(rigidArea_2);
		
		JLabel lblNewLabel_6 = new JLabel();
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel_6.setText("Precio total: " + (Usuario.getPreciobase() -(Usuario.getPreciobase() * controlador.getUsuarioActual().getDescuento().getValorDescuento())) + "€");
		lblNewLabel_6.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_6);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_3.setMaximumSize(new Dimension(20, 40));
		add(rigidArea_3);
		
		JButton btnNewButton = new JButton("Mejorar cuenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				controlador.becomePremium();
				btnNewButton.setEnabled(false);
				lblNewLabel_7.setVisible(true);
				repaint();
				revalidate();
				Principal.getInstancia().enablePremiumFeatures();
				ventana.repaint();
				ventana.revalidate();
				
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnNewButton);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_5.setMaximumSize(new Dimension(20, 40));
		add(rigidArea_5);
		
		lblNewLabel_7 = new JLabel("\u00A1Esta cuenta ya es Premium!");
		lblNewLabel_7.setForeground(new Color(34, 139, 34));
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_7.setVisible(false);
		add(lblNewLabel_7);
		
		if (controlador.getUsuarioActual().isPremium()) {
			btnNewButton.setEnabled(false);
			lblNewLabel_7.setVisible(true);
		}
		

	}

}
