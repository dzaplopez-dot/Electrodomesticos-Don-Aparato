package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Coordinador miCoordinador;
	private JButton btnRegistrar;
	private JButton btnVerLista;

	public VentanaPrincipal() {
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setBounds(100, 100, 450, 300);
		  JPanel contentPane = new JPanel();
		  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		  setContentPane(contentPane);
		  contentPane.setLayout(null);

		  JLabel lblTitulo = new JLabel("Tienda Don Aparato");
		  lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		  lblTitulo.setBounds(150, 10, 160, 30);
		  contentPane.add(lblTitulo);

		  JLabel lblSubtitulo = new JLabel("Seleccione una opcion");
		  lblSubtitulo.setBounds(145, 39, 157, 26);
		  contentPane.add(lblSubtitulo);

		  btnRegistrar = new JButton("Registrar compra");
		  btnRegistrar.setBounds(145, 98, 151, 29);
		  contentPane.add(btnRegistrar);

		  btnVerLista = new JButton("Ver todas las compras");
		  btnVerLista.setBounds(128, 140, 184, 29);
		  contentPane.add(btnVerLista);

		  btnRegistrar.addActionListener(this);
		  btnVerLista.addActionListener(this);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar)
			miCoordinador.mostrarVentanaRegistrar();
		else if (e.getSource() == btnVerLista)
			miCoordinador.mostrarVentanaConsultarLista();
	}

	public void setCoordinador(Coordinador c) {
		this.miCoordinador = c;
	}
}
