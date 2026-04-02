package controlador;

import java.util.ArrayList;
import modelo.Procesos;
import modelo.dao.ClienteDAO;
import modelo.dao.CompraDAO;
import modelo.dao.DetalleCompraDAO;
import modelo.dao.ProductoCatalogoDAO;
import modelo.dto.ClienteDTO;
import modelo.dto.CompraDTO;
import modelo.dto.DetalleCompraDTO;
import modelo.dto.ProductoCatalogoDTO;
import vista.VentanaConsultarLista;
import vista.VentanaPrincipal;
import vista.VentanaRegistrar;

public class Coordinador {

	
	private VentanaPrincipal miVentanaPrincipal;
	private VentanaConsultarLista miVentanaConsultarLista;
	private VentanaRegistrar miVentanaRegistrar;
	private Procesos misProcesos;
	private ClienteDAO miClienteDAO;
	private ProductoCatalogoDAO miProductoCatalogoDAO;
	private CompraDAO miCompraDAO;
	private DetalleCompraDAO miDetalleCompraDAO;

	
	public void setVentanaPrincipal(VentanaPrincipal v) {
		this.miVentanaPrincipal = v;
	}

	public void setVentanaConsultarLista(VentanaConsultarLista v) {
		this.miVentanaConsultarLista = v;
	}

	public void setVentanaRegistrar(VentanaRegistrar v) {
		this.miVentanaRegistrar = v;
	}

	public void setProcesos(Procesos p) {
		this.misProcesos = p;
	}

	public void setClienteDAO(ClienteDAO dao) {
		this.miClienteDAO = dao;
	}

	public void setProductoCatalogoDAO(ProductoCatalogoDAO dao) {
		this.miProductoCatalogoDAO = dao;
	}

	public void setCompraDAO(CompraDAO dao) {
		this.miCompraDAO = dao;
	}

	public void setDetalleCompraDAO(DetalleCompraDAO dao) {
		this.miDetalleCompraDAO = dao;
	}


	public void mostrarVentanaPrincipal() {
		miVentanaPrincipal.setVisible(true);
	}

	public void mostrarVentanaRegistrar() {
		miVentanaRegistrar.limpiar();
		miVentanaRegistrar.cargarCatalogo(); 
		miVentanaRegistrar.setVisible(true);
	}

	public void mostrarVentanaConsultarLista() {
		miVentanaConsultarLista.cargarLista();
		miVentanaConsultarLista.setVisible(true);
	}

	
	public void buscarCliente(String documento) {
		ClienteDTO c = miClienteDAO.consultarPorDocumento(documento);
		if (c != null) {
			miVentanaRegistrar.rellenarDatosCliente(c);
		} else {
			miVentanaRegistrar.mostrarClienteNoEncontrado();
		}
	}

	public void realizarCompraCarrito(ClienteDTO cliente, ArrayList<ProductoCatalogoDTO> carrito) {

		
		if (carrito.isEmpty()) {
			miVentanaRegistrar.mostrarErrorCarritoVacio();
			return;
		}

		
		CompraDTO compra = new CompraDTO();
		misProcesos.calcularCarrito(cliente, carrito, compra);

		
		miClienteDAO.registrarCliente(cliente);

		
		int idCompra = miCompraDAO.registrarCompra(compra);
		if (idCompra == -1) {
			miVentanaRegistrar.mostrarErrorGuardado();
			return;
		}
		compra.setIdCompra(idCompra);

		
		for (ProductoCatalogoDTO prod : carrito) {
			DetalleCompraDTO detalle = new DetalleCompraDTO();
			detalle.setIdCompra(idCompra);
			detalle.setIdProducto(prod.getId());
			detalle.setCantidad(prod.getCantidadSeleccionada());
			detalle.setSubtotal(prod.getSubtotal());
			miDetalleCompraDAO.registrarDetalle(detalle);

			
			miProductoCatalogoDAO.actualizarStock(prod.getId(), prod.getCantidadSeleccionada());
		}

		
		miVentanaRegistrar.mostrarResultadoCarrito(cliente, compra, carrito);
		miVentanaRegistrar.actualizarStockEnTabla(carrito);
	}

	
	public void mostrarDatosUsuario(ClienteDTO cliente) {
		boolean vacios = cliente.getNombre().isBlank() || cliente.getApellido().isBlank()
				|| cliente.getDocumento().isBlank();
		if (vacios)
			miVentanaRegistrar.mostrarErrorCamposVacios();
		else
			miVentanaRegistrar.mostrarDatosUsuario(cliente);
	}

	
	public ArrayList<ProductoCatalogoDTO> consultarCatalogo() {
		return miProductoCatalogoDAO.consultarTodos();
	}

	
	public ArrayList<ClienteDTO> consultarClientes() {
		return miClienteDAO.consultarLista();
	}

	public ArrayList<CompraDTO> consultarComprasPorCliente(String documento) {
		return miCompraDAO.consultarPorCliente(documento);
	}

	public ArrayList<DetalleCompraDTO> consultarDetallePorCompra(int idCompra) {
		return miDetalleCompraDAO.consultarPorCompra(idCompra);
	}
}
