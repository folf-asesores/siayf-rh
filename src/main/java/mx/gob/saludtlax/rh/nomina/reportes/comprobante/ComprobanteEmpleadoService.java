/*
 * ComprobanteEmpleadoService.java
 * Creado el 22/Nov/2016 4:25:46 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.persistencia.espejo.ComprobanteEmpleadoRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ComprobanteEmpleadoService implements Serializable {

    private static final long serialVersionUID = 75388205920079865L;

    @Inject private ComprobanteEmpleadoRepository comprobateEmpleadoRepository;

    protected byte[] generarReporte(Integer idProductoNomina) {
        List<ComprobanteEmpleadoPOJO> datosBrutos = comprobateEmpleadoRepository.obtenerDatos(idProductoNomina);
        List<ComprobanteEmpleadoDTO> datosTratados = convertir(datosBrutos);
        ComprobanteEmpleadoRep per = new ComprobanteEmpleadoRep();

        return per.obtenerArchivo(datosTratados);
    }

    private List<ComprobanteEmpleadoDTO> convertir(List<ComprobanteEmpleadoPOJO> datosBrutos) {
        Map<String, List<Integer>> iteradores = new HashMap<>();

        for (int i = 0; i < datosBrutos.size(); i++) {
            ComprobanteEmpleadoPOJO datoBruto = datosBrutos.get(i);

            if(!iteradores.containsKey(datoBruto.getFiliacion())) {
                List<Integer> indices = new ArrayList<>();
                indices.add(i);
                iteradores.put(datoBruto.getFiliacion(), indices);
            } else {
                List<Integer> indices = iteradores.get(datoBruto.getFiliacion());
                indices.add(i);
            }
        }

        List<ComprobanteEmpleadoDTO> datosTratados = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> iterador : iteradores.entrySet()) {
            ComprobanteEmpleadoDTO datoTratado = new ComprobanteEmpleadoDTO();
            List<Integer> indices = iterador.getValue();
            List<ConceptoComprobanteDTO> conceptos = new ArrayList<>();

            for (int i = 0; i < indices.size(); i++) {
                Integer indice = indices.get(i);
                ComprobanteEmpleadoPOJO datoBruto = datosBrutos.get(indice);

                if (i == 0) {
                    datoTratado.setNombre(datoBruto.getNombre());
                    datoTratado.setFiliacion(datoBruto.getFiliacion());
                    datoTratado.setFechaPago(datoBruto.getFechaPago());
                    datoTratado.setClaveCentroResponsabilidad(datoBruto.getClaveCentroResposabilidad());
                    datoTratado.setInicioPeriodo(datoBruto.getInicioPeriodo());
                    datoTratado.setFinPeriodo(datoBruto.getFinPeriodo());
                    datoTratado.setPercepciones(datoBruto.getPercepciones());
                    datoTratado.setDeducciones(datoBruto.getDeducciones());
                    datoTratado.setNeto(datoBruto.getNeto());
                    datoTratado.setConceptos(conceptos);

                    conceptos.add(new ConceptoComprobanteDTO(Short.valueOf(datoBruto.getClave() == null ? "0" : datoBruto.getClave().trim()), datoBruto.getImporte()));
                } else {
                    conceptos.add(new ConceptoComprobanteDTO(Short.valueOf(datoBruto.getClave() == null ? "0" : datoBruto.getClave().trim()), datoBruto.getImporte()));
                }
            }

            Collections.sort(conceptos);
            datosTratados.add(datoTratado);
        }

        Collections.sort(datosTratados);
        return datosTratados;
    }

}
