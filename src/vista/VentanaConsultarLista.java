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
import modelo.dto.DetalleCompraDTO;

public class VentanaConsultarLista extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    
    private JTable            tblCompras;
    private DefaultTableModel modeloCompras;

    
    private JTable            tblDetalle;
    private DefaultTableModel modeloDetalle;

    
    private JLabel lblResumen;

    
    private JButton btnVerDetalle;
    private JButton btnCerrar;

    
    private Coordinador miCoordinador;

    
    public VentanaConsultarLista(VentanaPrincipal padre, boolean modal) {
        super(padre, modal);
        setTitle("Historial de Compras — DON APARATO");
        setBounds(100, 100, 620, 580);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        
        JPanel pnlCompras = new JPanel();
        pnlCompras.setBorder(new TitledBorder("Compras registradas"));
        pnlCompras.setLayout(null);
        pnlCompras.setBounds(10, 10, 585, 210);
        contentPane.add(pnlCompras);

        modeloCompras = new DefaultTableModel(
            new String[]{"ID", "Cliente", "Descuento", "Total sin desc.", "Total a pagar"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        tblCompras = new JTable(modeloCompras);
        tblCompras.getColumnModel().getColumn(0).setPreferredWidth(35);
        tblCompras.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblCompras.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblCompras.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblCompras.getColumnModel().getColumn(4).setPreferredWidth(120);
        tblCompras.setRowHeight(24);
        tblCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCompras.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollCompras = new JScrollPane(tblCompras);
        scrollCompras.setBounds(10, 20, 565, 170);
        pnlCompras.add(scrollCompras);

   
        btnVerDetalle = new JButton("Ver detalle de compra seleccionada");
        btnVerDetalle.setBounds(10, 232, 280, 32);
        btnVerDetalle.setForeground(Color.BLACK);
        btnVerDetalle.setOpaque(true);
        contentPane.add(btnVerDetalle);
        btnVerDetalle.addActionListener(this);

        lblResumen = new JLabel("");
        lblResumen.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblResumen.setBounds(300, 232, 295, 32);
        contentPane.add(lblResumen);

        
        JPanel pnlDetalle = new JPanel();
        pnlDetalle.setBorder(new TitledBorder("Detalle de productos"));
        pnlDetalle.setLayout(null);
        pnlDetalle.setBounds(10, 275, 585, 210);
        contentPane.add(pnlDetalle);

        modeloDetalle = new DefaultTableModel(
            new String[]{"Producto", "Cantidad", "Subtotal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        tblDetalle = new JTable(modeloDetalle);
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(280);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblDetalle.setRowHeight(24);
        tblDetalle.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollDetalle = new JScrollPane(tblDetalle);
        scrollDetalle.setBounds(10, 20, 565, 170);
        pnlDetalle.add(scrollDetalle);

     
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(240, 498, 120, 32);
        btnCerrar.setForeground(Color.BLACK);
        btnCerrar.setOpaque(true);
        contentPane.add(btnCerrar);
        btnCerrar.addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerDetalle) verDetalleSeleccionado();
        else if (e.getSource() == btnCerrar) setVisible(false);
    }

    
    private void verDetalleSeleccionado() {
        int filaSeleccionada = tblCompras.getSelectedRow();
        if (filaSeleccionada == -1) {
            lblResumen.setForeground(java.awt.Color.RED);
            lblResumen.setText("Selecciona una compra primero.");
            return;
        }

        int idCompra = (int) modeloCompras.getValueAt(filaSeleccionada, 0);
        ArrayList<DetalleCompraDTO> detalle =
            miCoordinador.consultarDetallePorCompra(idCompra);

        modeloDetalle.setRowCount(0);
        for (DetalleCompraDTO d : detalle) {
            modeloDetalle.addRow(new Object[]{
                d.getNombreProducto(),
                d.getCantidad(),
                String.format("$%,.2f", d.getSubtotal())
            });
        }

        lblResumen.setForeground(new java.awt.Color(0, 80, 180));
        lblResumen.setText("Compra #" + idCompra
            + " — " + detalle.size() + " producto(s)");
    }

    public void cargarLista() {
        ArrayList<ClienteDTO> clientes = miCoordinador.consultarClientes();
        modeloCompras.setRowCount(0);
        modeloDetalle.setRowCount(0);
        lblResumen.setText("");

        for (ClienteDTO c : clientes) {
            ArrayList<CompraDTO> compras =
                miCoordinador.consultarComprasPorCliente(c.getDocumento());

            for (CompraDTO compra : compras) {
                modeloCompras.addRow(new Object[]{
                    compra.getIdCompra(),
                    c.getNombre() + " " + c.getApellido(),
                    compra.getMensajeDescuento(),
                    String.format("$%,.2f", compra.getTotalSinDescuento()),
                    String.format("$%,.2f", compra.getTotalConDescuento())
                });
            }
        }

        if (modeloCompras.getRowCount() == 0) {
            lblResumen.setForeground(java.awt.Color.GRAY);
            lblResumen.setText("No hay compras registradas.");
        }
    }


    public void setCoordinador(Coordinador c) {
        this.miCoordinador = c;
    }
}
