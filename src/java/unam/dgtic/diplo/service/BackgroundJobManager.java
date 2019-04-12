/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.dgtic.diplo.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import unam.dgtic.apps.Banxico;

/**
 *
 * @author edher
 */
@Singleton
@Startup

public class BackgroundJobManager implements Serializable{
    @EJB
    private DolarFacadeREST EJBDolar;
    final MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

@Schedule(hour="11", minute="12", second="0", persistent=false)
//@Schedule(hour="*", minute="*/1", second="0", persistent=false)para probar la tarea :)
public void someDailyJob() throws Exception {
        Banxico http = new Banxico();
        String resultado = http.sendPost();
        float r= http.procesarTexto(resultado);  
        java.util.Date fecha = new Date();
        System.out.println (r+"-"+format.format(fecha));
        form.add("dia", format.format(fecha));
        form.add("venta", Float.toString(r));
        EJBDolar.addDolar(form);
    }

}