package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import controlador.Coordinador;
import modelo.dto.ClienteDTO;
import modelo.dto.CompraDTO;
import modelo.dto.ProductoCatalogoDTO;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class VentanaRegistrar extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEdad;
	private JTextField txtTelefono;
	private JTextField txtTipo;

	
	private JButton btnBuscar;
	private JButton btnComprar;
	private JButton btnMostrarDatos;
	private JButton btnLimpiar;

	
	private JTable tblCatalogo;
	private DefaultTableModel modeloTabla;

	
	private ArrayList<ProductoCatalogoDTO> catalogoActual = new ArrayList<>();

	
	private JLabel lblCliente;
	private JLabel lblAfiliacion;
	private JLabel lblDescuento;
	private JLabel lblPrecioReal;
	
	
	private JTextArea txtDetalle;

	
	private Coordinador miCoordinador;

	
	public VentanaRegistrar(VentanaPrincipal padre, boolean modal) {
		super(padre, modal);
		setTitle("Registrar Compra — DON APARATO");
		setBounds(100, 100, 580, 730);
		setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		
		JPanel pnlCliente = new JPanel();
		pnlCliente.setBorder(new TitledBorder("Datos del cliente"));
		pnlCliente.setLayout(null);
		pnlCliente.setBounds(10, 10, 545, 175);
		contentPane.add(pnlCliente);

		JLabel lblDoc = new JLabel("Documento:");
		lblDoc.setBounds(10, 25, 90, 25);
		pnlCliente.add(lblDoc);
		txtDocumento = new JTextField();
		txtDocumento.setBounds(105, 25, 140, 25);
		pnlCliente.add(txtDocumento);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(255, 25, 80, 25);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setOpaque(true);
		btnBuscar.addActionListener(this);
		pnlCliente.add(btnBuscar);

		JLabel lblNom = new JLabel("Nombre:");
		lblNom.setBounds(10, 60, 90, 25);
		pnlCliente.add(lblNom);
		txtNombre = new JTextField();
		txtNombre.setBounds(105, 60, 140, 25);
		pnlCliente.add(txtNombre);

		JLabel lblApe = new JLabel("Apellido:");
		lblApe.setBounds(265, 60, 80, 25);
		pnlCliente.add(lblApe);
		txtApellido = new JTextField();
		txtApellido.setBounds(350, 60, 140, 25);
		pnlCliente.add(txtApellido);

		JLabel lblEd = new JLabel("Edad:");
		lblEd.setBounds(10, 100, 90, 25);
		pnlCliente.add(lblEd);
		txtEdad = new JTextField();
		txtEdad.setBounds(105, 100, 60, 25);
		pnlCliente.add(txtEdad);

		JLabel lblTel = new JLabel("Telefono:");
		lblTel.setBounds(190, 100, 80, 25);
		pnlCliente.add(lblTel);
		txtTelefono = new JTextField();
		txtTelefono.setBounds(275, 100, 130, 25);
		pnlCliente.add(txtTelefono);

		JLabel lblTip = new JLabel("Tipo (A/B/C):");
		lblTip.setBounds(10, 140, 95, 25);
		pnlCliente.add(lblTip);
		txtTipo = new JTextField();
		txtTipo.setBounds(110, 140, 50, 25);
		pnlCliente.add(txtTipo);

		JLabel lblTipoHint = new JLabel("(vacio = sin tipo)");
		lblTipoHint.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblTipoHint.setForeground(Color.GRAY);
		lblTipoHint.setBounds(170, 140, 130, 25);
		pnlCliente.add(lblTipoHint);

		
		JPanel pnlCatalogo = new JPanel();
		pnlCatalogo.setBorder(new TitledBorder("Catalogo — ingresa la cantidad deseada"));
		pnlCatalogo.setLayout(null);
		pnlCatalogo.setBounds(10, 198, 545, 210);
		contentPane.add(pnlCatalogo);

		modeloTabla = new DefaultTableModel(new String[] { "Producto", "Precio unitario", "Stock", "Cantidad" }, 0) {
			@Override
			public boolean isCellEditable(int fila, int col) {
				return col == 3;
			}
		};

		tblCatalogo = new JTable(modeloTabla);
		tblCatalogo.getColumnModel().getColumn(0).setPreferredWidth(200);
		tblCatalogo.getColumnModel().getColumn(1).setPreferredWidth(110);
		tblCatalogo.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblCatalogo.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblCatalogo.setRowHeight(24);
		tblCatalogo.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollTabla = new JScrollPane(tblCatalogo);
		scrollTabla.setBounds(10, 20, 525, 175);
		pnlCatalogo.add(scrollTabla);

		
		btnComprar = new JButton("Realizar Compra");
		btnComprar.setBounds(10, 420, 155, 32);
		btnComprar.setForeground(Color.BLACK);
		btnComprar.setOpaque(true);
		btnComprar.addActionListener(this);
		contentPane.add(btnComprar);

		btnMostrarDatos = new JButton("Mostrar Datos");
		btnMostrarDatos.setBounds(180, 420, 155, 32);
		btnMostrarDatos.setForeground(Color.BLACK);
		btnMostrarDatos.setOpaque(true);
		btnMostrarDatos.addActionListener(this);
		contentPane.add(btnMostrarDatos);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(350, 420, 155, 32);
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setOpaque(true);
		btnLimpiar.addActionListener(this);
		contentPane.add(btnLimpiar);

		
		JPanel pnlResultado = new JPanel();
		pnlResultado.setBorder(new TitledBorder("Resultado de la compra"));
		pnlResultado.setLayout(null);
		pnlResultado.setBounds(10, 465, 545, 220);
		contentPane.add(pnlResultado);

		lblCliente = new JLabel("");
		lblCliente.setBounds(10, 22, 520, 25);
		pnlResultado.add(lblCliente);

		lblAfiliacion = new JLabel("");
		lblAfiliacion.setBounds(10, 50, 520, 25);
		lblAfiliacion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pnlResultado.add(lblAfiliacion);

	
		txtDetalle = new JTextArea();
		txtDetalle.setEditable(false);
		txtDetalle.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtDetalle.setBackground(pnlResultado.getBackground());
		txtDetalle.setLineWrap(true);
		txtDetalle.setWrapStyleWord(true);

		JScrollPane scrollDetalle = new JScrollPane(txtDetalle);
		scrollDetalle.setBounds(10, 80, 520, 80);
		scrollDetalle.setBorder(null);
		scrollDetalle.setVerticalScrollBarPolicy(
		    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pnlResultado.add(scrollDetalle);

		lblDescuento = new JLabel("");
		lblDescuento.setBounds(10, 165, 520, 22);
		pnlResultado.add(lblDescuento);

		lblPrecioReal = new JLabel("");
		lblPrecioReal.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPrecioReal.setBounds(10, 190, 520, 25);
		pnlResultado.add(lblPrecioReal);
		
		
		modeloTabla.addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {
		        if (e.getColumn() == 3) { 
		            int fila = e.getFirstRow();
		            if (fila < 0 || fila >= modeloTabla.getRowCount()) return;
		            Object valor = modeloTabla.getValueAt(fila, 3);
		            int cantidad = 0;
		            try { cantidad = Integer.parseInt(valor.toString().trim()); }
		            catch (NumberFormatException ex) { cantidad = 0; }

		            if (cantidad > 0) {
		                
		                tblCatalogo.setRowSelectionInterval(fila, fila);
		                colorearFila(fila, new Color(198, 239, 206));
		            } else {
		                
		                tblCatalogo.clearSelection();
		                colorearFila(fila, null);
		            }
		        }
		    }
		});
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar)
			buscarCliente();
		else if (e.getSource() == btnComprar)
			enviarCarritoAlCoordinador();
		else if (e.getSource() == btnMostrarDatos)
			enviarDatosAlCoordinador();
		else if (e.getSource() == btnLimpiar)
			limpiar();
	}

	
	private void buscarCliente() {
		String doc = txtDocumento.getText().trim();
		if (doc.isBlank()) {
			lblCliente.setForeground(Color.RED);
			lblCliente.setText("Ingresa el documento primero.");
			return;
		}
		miCoordinador.buscarCliente(doc);
	}

	private void enviarCarritoAlCoordinador() {
		try {
			
			ClienteDTO cliente = new ClienteDTO();
			cliente.setDocumento(txtDocumento.getText().trim());
			cliente.setNombre(txtNombre.getText().trim());
			cliente.setApellido(txtApellido.getText().trim());
			cliente.setEdad(Integer.parseInt(txtEdad.getText().trim()));
			cliente.setTelefono(txtTelefono.getText().trim());
			cliente.setTipo(txtTipo.getText().trim());

			if (cliente.getDocumento().isBlank()) {
				lblCliente.setForeground(Color.RED);
				lblCliente.setText("Ingresa el documento del cliente.");
				return;
			}

			
			ArrayList<ProductoCatalogoDTO> carrito = new ArrayList<>();

			for (int i = 0; i < modeloTabla.getRowCount(); i++) {
				Object valorCelda = modeloTabla.getValueAt(i, 3);
				int cantidad = 0;

				try {
					cantidad = Integer.parseInt(valorCelda.toString().trim());
				} catch (NumberFormatException ex) {
					
					cantidad = 0;
				}

				if (cantidad > 0) {
					
					int stockDisponible = catalogoActual.get(i).getStock();
					if (cantidad > stockDisponible) {
						lblCliente.setForeground(Color.RED);
						lblCliente.setText("La cantidad de '" + catalogoActual.get(i).getNombre()
								+ "' supera el stock disponible (" + stockDisponible + ").");
						return;
					}

					ProductoCatalogoDTO prod = new ProductoCatalogoDTO();
					prod.setId(catalogoActual.get(i).getId());
					prod.setNombre(catalogoActual.get(i).getNombre());
					prod.setPrecioUnitario(catalogoActual.get(i).getPrecioUnitario());
					prod.setStock(catalogoActual.get(i).getStock());
					prod.setCantidadSeleccionada(cantidad);
					carrito.add(prod);
				}
			}

			
			miCoordinador.realizarCompraCarrito(cliente, carrito);

		} catch (NumberFormatException ex) {
			lblCliente.setForeground(Color.RED);
			lblCliente.setText("Edad debe ser un numero valido.");
		}
	}

	private void enviarDatosAlCoordinador() {
		ClienteDTO c = new ClienteDTO();
		c.setDocumento(txtDocumento.getText().trim());
		c.setNombre(txtNombre.getText().trim());
		c.setApellido(txtApellido.getText().trim());
		c.setTelefono(txtTelefono.getText().trim());
		c.setTipo(txtTipo.getText().trim());
		miCoordinador.mostrarDatosUsuario(c);
	}

	
	public void cargarCatalogo() {
		catalogoActual = miCoordinador.consultarCatalogo();
		modeloTabla.setRowCount(0);
		for (ProductoCatalogoDTO p : catalogoActual) {
			modeloTabla.addRow(
					new Object[] { p.getNombre(), String.format("$%,.0f", p.getPrecioUnitario()), p.getStock(), 0 });
		}
	}

	public void mostrarResultadoCarrito(
	        ClienteDTO cliente,
	        CompraDTO compra,
	        ArrayList<ProductoCatalogoDTO> carrito) {

	    
	    lblCliente.setForeground(Color.BLACK);
	    lblCliente.setText("Cliente: " + cliente.getNombre()
	        + " " + cliente.getApellido()
	        + "  |  Doc: " + cliente.getDocumento());

	    lblAfiliacion.setText("Afiliacion: " +
	        (cliente.getTipo().isBlank() ? "Sin tipo"
	         : "Tipo " + cliente.getTipo().toUpperCase()));

	
	    StringBuilder detalle = new StringBuilder();
	    detalle.append("Productos comprados:\n");
	    for (ProductoCatalogoDTO prod : carrito) {
	        detalle.append(String.format("  %-30s x%-3d  $%,.2f%n",
	            prod.getNombre(),
	            prod.getCantidadSeleccionada(),
	            prod.getSubtotal()));
	    }
	    txtDetalle.setText(detalle.toString());
	    txtDetalle.setCaretPosition(0); 

	    
	    lblDescuento.setForeground(
	        compra.getDescuentoPorcentaje() > 0
	        ? new Color(0, 130, 60) : Color.GRAY);
	    lblDescuento.setText("Descuento: " + compra.getMensajeDescuento()
	        + "  |  Sin descuento: $"
	        + String.format("%,.2f", compra.getTotalSinDescuento()));

	    lblPrecioReal.setForeground(new Color(0, 80, 180));
	    lblPrecioReal.setText("TOTAL A PAGAR: $"
	        + String.format("%,.2f", compra.getTotalConDescuento()));

	    
	    tblCatalogo.clearSelection();
	    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	        modeloTabla.setValueAt(0, i, 3);
	    }
	    tblCatalogo.repaint();
	}

	public void rellenarDatosCliente(ClienteDTO c) {
		txtNombre.setText(c.getNombre());
		txtApellido.setText(c.getApellido());
		txtEdad.setText(String.valueOf(c.getEdad()));
		txtTelefono.setText(c.getTelefono());
		txtTipo.setText(c.getTipo());
	}

	public void mostrarDatosUsuario(ClienteDTO c) {
	    lblCliente.setForeground(Color.BLACK);
	    lblCliente.setText("Nombre: " + c.getNombre() + " " + c.getApellido()
	        + "  |  Doc: " + c.getDocumento()
	        + "  |  Tipo: " + (c.getTipo().isBlank() ? "Sin tipo"
	                            : c.getTipo().toUpperCase()));

	    
	    StringBuilder productos = new StringBuilder();
	    int totalProductos = 0;
	    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	        int cantidad = 0;
	        try {
	            cantidad = Integer.parseInt(
	                modeloTabla.getValueAt(i, 3).toString().trim());
	        } catch (NumberFormatException ex) { cantidad = 0; }

	        if (cantidad > 0) {
	            if (productos.length() > 0) productos.append(" | ");
	            productos.append(modeloTabla.getValueAt(i, 0))
	                     .append(" x").append(cantidad);
	            totalProductos++;
	        }
	    }

	    if (totalProductos > 0) {
	        lblAfiliacion.setForeground(new Color(0, 80, 180));
	        lblAfiliacion.setText("Productos seleccionados: " + productos);
	        txtDetalle.setText("");
	    } else {
	        lblAfiliacion.setForeground(Color.GRAY);
	        lblAfiliacion.setText("No hay productos seleccionados en el carrito.");
	        txtDetalle.setText("");
	    }
	}

	public void mostrarClienteNoEncontrado() {
		lblCliente.setForeground(Color.RED);
		lblCliente.setText("Cliente no encontrado: " + txtDocumento.getText().trim());
	}

	public void mostrarErrorCamposVacios() {
		lblCliente.setForeground(Color.RED);
		lblCliente.setText("Los campos del cliente estan vacios.");
	}

	public void mostrarErrorCarritoVacio() {
		lblCliente.setForeground(Color.RED);
		lblCliente.setText("Selecciona al menos un producto con cantidad mayor a 0.");
	}

	public void mostrarErrorGuardado() {
		lblCliente.setForeground(Color.RED);
		lblCliente.setText("Error al guardar la compra. Intenta de nuevo.");
	}

	public void limpiar() {

		txtDocumento.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEdad.setText("");
		txtTelefono.setText("");
		txtTipo.setText("");

		lblCliente.setText("");
		lblAfiliacion.setText("");
		txtDetalle.setText("");
		lblDescuento.setText("");
		lblPrecioReal.setText("");

		for (int i = 0; i < modeloTabla.getRowCount(); i++) {
			modeloTabla.setValueAt(0, i, 3);
		}

		txtDocumento.requestFocus();
	}

	public void setCoordinador(Coordinador c) {
		this.miCoordinador = c;
	}

	public void actualizarStockEnTabla(ArrayList<ProductoCatalogoDTO> carrito) {
		for (ProductoCatalogoDTO prod : carrito) {
			for (int i = 0; i < catalogoActual.size(); i++) {
				if (catalogoActual.get(i).getId() == prod.getId()) {
					int stockActual = catalogoActual.get(i).getStock();
					int nuevoStock = stockActual - prod.getCantidadSeleccionada();
					catalogoActual.get(i).setStock(nuevoStock);

					if (nuevoStock <= 0) {
						
						modeloTabla.removeRow(i);
						catalogoActual.remove(i);
					} else {
						
						modeloTabla.setValueAt(nuevoStock, i, 2);
						modeloTabla.setValueAt(0, i, 3); // resetea cantidad
					}
					break;
				}
			}
		}
	}
	
	private void colorearFila(int fila, Color color) {
	    tblCatalogo.setDefaultRenderer(Object.class,
	        new javax.swing.table.DefaultTableCellRenderer() {
	            @Override
	            public java.awt.Component getTableCellRendererComponent(
	                    JTable table, Object value, boolean isSelected,
	                    boolean hasFocus, int row, int column) {
	                super.getTableCellRendererComponent(
	                    table, value, isSelected, hasFocus, row, column);
	                Object cant = modeloTabla.getValueAt(row, 3);
	                int q = 0;
	                try { q = Integer.parseInt(cant.toString().trim()); }
	                catch (NumberFormatException ex) { q = 0; }
	                if (!isSelected) {
	                    setBackground(q > 0 ? new Color(198, 239, 206) : Color.WHITE);
	                    setForeground(Color.BLACK);
	                } else {
	                    setBackground(new Color(0, 102, 204));
	                    setForeground(Color.WHITE);
	                }
	                return this;
	            }
	        });
	    tblCatalogo.repaint();
	}
}