package modelo.dto;

public class ProductoCatalogoDTO {

	private int id;
	private String nombre;
	private double precioUnitario;
	private int stock;

	private int cantidadSeleccionada;

	private double subtotal;

	public ProductoCatalogoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int v) {
		this.id = v;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String v) {
		this.nombre = v;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double v) {
		this.precioUnitario = v;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int v) {
		this.stock = v;
	}

	public int getCantidadSeleccionada() {
		return cantidadSeleccionada;
	}

	public void setCantidadSeleccionada(int v) {
		this.cantidadSeleccionada = v;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double v) {
		this.subtotal = v;
	}
}
