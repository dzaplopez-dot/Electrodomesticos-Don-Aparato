package modelo;

import java.util.ArrayList;
import modelo.dto.ClienteDTO;
import modelo.dto.CompraDTO;
import modelo.dto.ProductoCatalogoDTO;
import modelo.dto.ProductoDTO;

public class Procesos {

    // ── Método anterior — se mantiene por compatibilidad ──────────────────────
    public void calcularCompra(ClienteDTO cliente, ProductoDTO producto) {

        String tipo = cliente.getTipo().trim().toUpperCase();
        double descuento;
        String mensaje;

        if (tipo.equals("A"))      { descuento = 0.40; mensaje = "Descuento del 40% (Tipo A)"; }
        else if (tipo.equals("B")) { descuento = 0.20; mensaje = "Descuento del 20% (Tipo B)"; }
        else if (tipo.equals("C")) { descuento = 0.10; mensaje = "Descuento del 10% (Tipo C)"; }
        else                       { descuento = 0.0;  mensaje = "No se le realiza descuento"; }

        double totalSin = producto.getPrecioUnitario() * producto.getCantidad();
        double valorDescuento = totalSin * descuento;
        double totalCon = totalSin - valorDescuento;

        producto.setDescuentoPorcentaje(descuento);
        producto.setMensajeDescuento(mensaje);
        producto.setTotalSinDescuento(totalSin);
        producto.setTotalConDescuento(totalCon);
    }

    public void calcularCarrito(
            ClienteDTO cliente,
            ArrayList<ProductoCatalogoDTO> carrito,
            CompraDTO compra) {

        // 1. Determinar descuento según tipo del cliente
        String tipo = cliente.getTipo().trim().toUpperCase();
        double descuento;
        String mensaje;

        if (tipo.equals("A"))      { descuento = 0.40; mensaje = "Descuento del 40% (Tipo A)"; }
        else if (tipo.equals("B")) { descuento = 0.20; mensaje = "Descuento del 20% (Tipo B)"; }
        else if (tipo.equals("C")) { descuento = 0.10; mensaje = "Descuento del 10% (Tipo C)"; }
        else                       { descuento = 0.0;  mensaje = "No se le realiza descuento"; }

        // 2. Calcular subtotal de cada producto y sumar el total
        double totalSin = 0;
        for (ProductoCatalogoDTO prod : carrito) {
            double subtotal = prod.getPrecioUnitario()
                            * prod.getCantidadSeleccionada();
            prod.setSubtotal(subtotal);
            totalSin += subtotal;
        }

        // 3. Aplicar descuento
        double totalCon = totalSin * (1 - descuento);

        // 4. Escribir resultados en CompraDTO
        compra.setDocumentoCliente   (cliente.getDocumento());
        compra.setTotalSinDescuento  (totalSin);
        compra.setDescuentoPorcentaje(descuento);
        compra.setTotalConDescuento  (totalCon);
        compra.setMensajeDescuento   (mensaje);
    }

    public double obtenerDescuento(String tipo) {
        switch (tipo.trim().toUpperCase()) {
            case "A": return 0.40;
            case "B": return 0.20;
            case "C": return 0.10;
            default:  return 0.0;
        }
    }
}
