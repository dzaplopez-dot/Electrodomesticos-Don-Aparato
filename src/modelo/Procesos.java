package modelo;

import java.util.ArrayList;
import modelo.dto.ClienteDTO;
import modelo.dto.CompraDTO;
import modelo.dto.ProductoCatalogoDTO;
import modelo.dto.ProductoDTO;

public class Procesos {

	public void calcularCompra(ClienteDTO cliente, ProductoDTO producto) {

		String tipo = cliente.getTipo().trim().toUpperCase();
		double descuento;
		String mensaje;

		if (tipo.equals("A")) {
			descuento = 0.40;
			mensaje = "Descuento del 40% (Tipo A)";
		} else if (tipo.equals("B")) {
			descuento = 0.20;
			mensaje = "Descuento del 20% (Tipo B)";
		} else if (tipo.equals("C")) {
			descuento = 0.10;
			mensaje = "Descuento del 10% (Tipo C)";
		} else {
			descuento = 0.0;
			mensaje = "No se le realiza descuento";
		}

		double totalSin = producto.getPrecioUnitario() * producto.getCantidad();
		double valorDescuento = totalSin * descuento;
		double totalCon = totalSin - valorDescuento;

		producto.setDescuentoPorcentaje(descuento);
		producto.setMensajeDescuento(mensaje);
		producto.setTotalSinDescuento(totalSin);
		producto.setTotalConDescuento(totalCon);
	}

	public void calcularCarrito(ClienteDTO cliente, ArrayList<ProductoCatalogoDTO> carrito, CompraDTO compra) {

		String tipo = cliente.getTipo().trim().toUpperCase();
		double descuento;
		String mensaje;

		if (tipo.equals("A")) {
			descuento = 0.40;
			mensaje = "Descuento del 40% (Tipo A)";
		} else if (tipo.equals("B")) {
			descuento = 0.20;
			mensaje = "Descuento del 20% (Tipo B)";
		} else if (tipo.equals("C")) {
			descuento = 0.10;
			mensaje = "Descuento del 10% (Tipo C)";
		} else {
			descuento = 0.0;
			mensaje = "No se le realiza descuento";
		}

		double totalSin = 0;
		for (ProductoCatalogoDTO prod : carrito) {
			double subtotal = prod.getPrecioUnitario() * prod.getCantidadSeleccionada();
			prod.setSubtotal(subtotal);
			totalSin = subtotal + totalSin;
		}

		double totalCon = totalSin * (1 - descuento);
		compra.setDocumentoCliente(cliente.getDocumento());
		compra.setTotalSinDescuento(totalSin);
		compra.setDescuentoPorcentaje(descuento);
		compra.setTotalConDescuento(totalCon);
		compra.setMensajeDescuento(mensaje);
	}

}

