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
import javax.persistence.Query;
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

/**
 *
 * @author edher
 */
@Stateless
@Path("/dolar/")
@Produces("application/json")
public class DolarFacadeREST extends AbstractFacade<Dolar> {

    @PersistenceContext(unitName = "SEKRESTPU")
    private EntityManager em;

    public DolarFacadeREST() {
        super(Dolar.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void addDolar(MultivaluedMap<String,String> parametros) throws ParseException {
        Dolar d = new Dolar();
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(parametros.getFirst("dia")); 
        d.setDia(date1);
        d.setVenta(Float.valueOf(parametros.getFirst("venta")));
        super.create(d);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Dolar entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Dolar find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    //GET con Fecha
    @GET
    @Path("/findFec/{fecha}")
    @Produces({MediaType.APPLICATION_JSON})
    public Dolar findFec(@PathParam("fecha") String fecha) {
        Dolar dolar = new Dolar();
        String consulta;
       
        try{//
             Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fecha);  
            dolar.setDia(date1);
            consulta = "FROM Dolar u WHERE u.dia = ?1";
            Query query= em.createQuery(consulta);
            query.setParameter(1,dolar.getDia());
            
            List<Dolar> lista = query.getResultList();
            if(!lista.isEmpty()){
                dolar = lista.get(0);
            }else{
                dolar=null;
            }
            
        }
        catch(ParseException e){
            try {
                dolar = null;
                throw e;
            } catch (ParseException ex) {
                Logger.getLogger(DolarFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dolar;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Dolar> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dolar> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
