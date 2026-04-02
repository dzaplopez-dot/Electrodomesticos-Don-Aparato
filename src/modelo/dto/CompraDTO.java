package modelo.dto;

public class CompraDTO {

	private String documentoCliente;
	private double totalSinDescuento;
	private double descuentoPorcentaje;
	private double totalConDescuento;
	private String mensajeDescuento;

	private int idCompra;

	public CompraDTO() {
	}

	public String getDocumentoCliente() {
		return documentoCliente;
	}

	public void setDocumentoCliente(String v) {
		this.documentoCliente = v;
	}

	public double getTotalSinDescuento() {
		return totalSinDescuento;
	}

	public void setTotalSinDescuento(double v) {
		this.totalSinDescuento = v;
	}

	public double getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(double v) {
		this.descuentoPorcentaje = v;
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

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int v) {
		this.idCompra = v;
	}
}