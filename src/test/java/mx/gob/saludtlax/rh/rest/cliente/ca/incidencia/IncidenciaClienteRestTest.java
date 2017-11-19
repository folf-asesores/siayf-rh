
package mx.gob.saludtlax.rh.rest.cliente.ca.incidencia;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaClienteRest;
import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaFormModel;
import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaModelView;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.InitTest;

import junit.framework.Assert;

@RunWith(Arquillian.class)
public class IncidenciaClienteRestTest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7205846196841979190L;

    @Deployment
    public static WebArchive createDeployment() {

        return InitTest.crearWar();
    }

    @Inject
    IncidenciaClienteRest incidenciaClienteRest;

    @Test
    public void buscarIncidenciaPorId() throws RESTClientException {

        IncidenciaModelView incidenciaModelView = incidenciaClienteRest.buscarIncidenciaPorId(2);
        Assert.assertNotNull(incidenciaModelView);

    }

    @Ignore
    @Test
    public void guardarIncidencia() throws RESTClientException {

        Integer idIncidencia = null;
        IncidenciaFormModel incidenciaFormModel = new IncidenciaFormModel();

        incidenciaFormModel.setDescripcion("Incidencia Prueba");
        incidenciaFormModel.setEsImagen(1);
        incidenciaFormModel.setImagenMarca(this.getClass().getClassLoader().getResourceAsStream("logowiki.png"));
        incidenciaFormModel.setMarcaReporte("img.png");

        idIncidencia = incidenciaClienteRest.guardarIncidencia(incidenciaFormModel);

        Assert.assertNotNull(idIncidencia);
    }

    @Ignore
    @Test
    public void actualizarIncidencia() throws RESTClientException {

        Integer idIncidencia = null;
        IncidenciaFormModel incidenciaFormModel = new IncidenciaFormModel();

        incidenciaFormModel.setIdIncidencia(22);
        incidenciaFormModel.setDescripcion("Incidencia Prueba Modificada");
        incidenciaFormModel.setEsImagen(1);
        incidenciaFormModel.setImagenMarca(this.getClass().getClassLoader().getResourceAsStream("logowiki.png"));
        incidenciaFormModel.setMarcaReporte("img.png");

        idIncidencia = incidenciaClienteRest.actualizarIncidencia(incidenciaFormModel);

        Assert.assertNotNull(idIncidencia);

    }

    public void listadoIncidencias() throws RESTClientException {
        List<IncidenciaModelView> listadoIncidenciaViewModel = null;

        listadoIncidenciaViewModel = incidenciaClienteRest.listadoIncidencias();

        Assert.assertNotNull(listadoIncidenciaViewModel);
    }

}
