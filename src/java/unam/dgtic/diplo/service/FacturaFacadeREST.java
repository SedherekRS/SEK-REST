/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.dgtic.diplo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import unam.dgtic.diplo.entity.Dolar;
import unam.dgtic.diplo.entity.Empresa;
import unam.dgtic.diplo.entity.Factura;

/**
 *
 * @author edher
 */
@Stateless
@Path("/factura/")
@Produces("application/json")
public class FacturaFacadeREST extends AbstractFacade<Factura> {

    @PersistenceContext(unitName = "SEKRESTPU")
    private EntityManager em;

    public FacturaFacadeREST() {
        super(Factura.class);
    }

    @POST
    @Path("/new/")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void create(MultivaluedMap<String,String> parametros) {
        System.out.println("++++++++++Create REST++++++++++");
        Factura factura = new Factura();
        //
        factura.setNumeroFactura(parametros.getFirst("numeroFactura"));
        factura.setMonto(Float.parseFloat(parametros.getFirst("monto")));
        factura.setMoneda(parametros.getFirst("moneda"));
         Date date1=null;  
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(parametros.getFirst("fechaFactura"));
        } catch (ParseException ex) {
            Logger.getLogger(FacturaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        factura.setFechaFactura(date1);
        factura.setRutaFacturaPdf(parametros.getFirst("rutaFacturaPdf"));
        factura.setRutaFacturaXml(parametros.getFirst("rutaFacturaXml"));
        Empresa empresaIn= new Empresa();
        empresaIn.setId(Integer.parseInt(parametros.getFirst("idEmpresa")));
        Dolar dolarIn = new Dolar();
        dolarIn.setId(Integer.parseInt(parametros.getFirst("idDolar")));
        factura.setIdEmpresa(empresaIn);
        factura.setIdDolar(dolarIn);
        factura.setEstatus("1");
        
        //
        super.create(factura);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void edit(@PathParam("id") Integer id, Factura entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Factura find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Factura> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Factura> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
