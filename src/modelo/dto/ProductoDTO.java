package modelo.dto;

public class ProductoDTO {

	private String nombreProducto;
	private double precioUnitario;
	private int cantidad;
	private double descuentoPorcentaje;
	private double totalSinDescuento;
	private double totalConDescuento;
	private String mensajeDescuento;


	public ProductoDTO() {

	}

	public ProductoDTO(String nombreProducto, double precioUnitario, int cantidad) {

		this.nombreProducto = nombreProducto;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;

	}
	
	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	public double getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(double v) {
		this.descuentoPorcentaje = v;
	}

	public double getTotalSinDescuento() {
		return totalSinDescuento;
	}

	public void setTotalSinDescuento(double v) {
		this.totalSinDescuento = v;
	}

	public double getTotalConDescuento() {
		return totalConDescuento;
	}

	public void setTotalConDescuento(double v) {
		this.totalConDescuento = v;
	}

	public String getMensajeDescuento() {
		return mensajeDescuento;
	}

	public void setMensajeDescuento(String v) {
		this.mensajeDescuento = v;
	}
}