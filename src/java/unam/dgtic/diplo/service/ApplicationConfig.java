/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.dgtic.diplo.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author edher
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(unam.dgtic.diplo.service.DolarFacadeREST.class);
        resources.add(unam.dgtic.diplo.service.EmpresaFacadeREST.class);
        resources.add(unam.dgtic.diplo.service.FacturaFacadeREST.class);
    }
    
}
