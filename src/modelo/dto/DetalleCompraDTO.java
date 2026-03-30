package modelo.dto;

public class DetalleCompraDTO {

    private int    idCompra;
    private int    idProducto;
    private String nombreProducto;
    private int    cantidad;
    private double subtotal;

    public DetalleCompraDTO() {}

    public int    getIdCompra()                  { return idCompra; }
    public void   setIdCompra(int v)             { this.idCompra = v; }

    public int    getIdProducto()                { return idProducto; }
    public void   setIdProducto(int v)           { this.idProducto = v; }

    public String getNombreProducto()            { return nombreProducto; }
    public void   setNombreProducto(String v)    { this.nombreProducto = v; }

    public int    getCantidad()                  { return cantidad; }
    public void   setCantidad(int v)             { this.cantidad = v; }

    public double getSubtotal()                  { return subtotal; }
    public void   setSubtotal(double v)          { this.subtotal = v; }
}


