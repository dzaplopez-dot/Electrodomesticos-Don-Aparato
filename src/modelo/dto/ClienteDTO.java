package modelo.dto;

public class ClienteDTO {
	
	private String documento;
	private String nombre;
	private String apellido;
	private int edad;
	private String telefono;
	private String tipo;
	
public ClienteDTO() {
		
	}
	
	public ClienteDTO(String documento,String nombre, String apellido, int edad, String telefono, String tipo) {
		
		this.documento=documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad=edad;
		this.telefono=telefono;
		this.tipo=tipo;
		
	}
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String teléfono) {
		this.telefono = teléfono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// ── Campos de resultado — los llena Procesos ──────────────────────────────
	private double totalSinDescuento;
	private double descuentoPorcentaje;
	private double totalConDescuento;
	private String mensajeDescuento;

	// Getters y setters
	public double getTotalSinDescuento()             { return totalSinDescuento; }
	public void   setTotalSinDescuento(double v)     { this.totalSinDescuento = v; }

	public double getDescuentoPorcentaje()           { return descuentoPorcentaje; }
	public void   setDescuentoPorcentaje(double v)   { this.descuentoPorcentaje = v; }

	public double getTotalConDescuento()             { return totalConDescuento; }
	public void   setTotalConDescuento(double v)     { this.totalConDescuento = v; }

	public String getMensajeDescuento()              { return mensajeDescuento; }
	public void   setMensajeDescuento(String v)      { this.mensajeDescuento = v; }

	
	
}
