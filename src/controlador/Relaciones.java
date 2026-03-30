package controlador;

import modelo.Procesos;
import modelo.conexion.ConexionBD;
import modelo.dao.ClienteDAO;
import modelo.dao.CompraDAO;
import modelo.dao.DetalleCompraDAO;
import modelo.dao.ProductoCatalogoDAO;

import vista.VentanaConsultarLista;
import vista.VentanaPrincipal;
import vista.VentanaRegistrar;

public class Relaciones {

    public Relaciones() {

        // 1. Crear instancias
        VentanaPrincipal      vp  = new VentanaPrincipal();
        VentanaRegistrar      vr  = new VentanaRegistrar(vp, true);
        VentanaConsultarLista vl  = new VentanaConsultarLista(vp, true);
        Procesos              pr  = new Procesos();
        new ConexionBD();
        ClienteDAO            cd  = new ClienteDAO();
        ProductoCatalogoDAO   pcd = new ProductoCatalogoDAO();
        CompraDAO             cda = new CompraDAO();
        DetalleCompraDAO      dcd = new DetalleCompraDAO();
        Coordinador           co  = new Coordinador();

        // 2. Inyectar Coordinador en Vistas
        vp.setCoordinador(co);
        vr.setCoordinador(co);
        vl.setCoordinador(co);

        // 3. Inyectar todo en el Coordinador
        co.setVentanaPrincipal(vp);
        co.setVentanaRegistrar(vr);
        co.setVentanaConsultarLista(vl);
        co.setProcesos(pr);
        co.setClienteDAO(cd);
        co.setProductoCatalogoDAO(pcd);
        co.setCompraDAO(cda);
        co.setDetalleCompraDAO(dcd);

        // 4. Arrancar — siempre al final
        co.mostrarVentanaPrincipal();
    }
}