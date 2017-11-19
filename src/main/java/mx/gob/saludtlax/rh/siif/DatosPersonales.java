
package mx.gob.saludtlax.rh.siif;

import java.util.List;

public interface DatosPersonales {

    public DatosPersonalesDTO obtenerConsultaPorRfc(String rfc);

    List<DatosPersonalesDTO> obtenerListaConsultaPorRfc();

    public void eliminarDatosPersonales(
            DatosPersonalesDTO DatosPersonalesSelect);

    DatosPersonalesDTO obtenerDatosPersonales(
            DatosPersonalesDTO DatosPersonalesSelect);

    public void crearDatosPersonales(DatosPersonalesDTO DatosPersonales);

    public void actualizarDatosPersonales(DatosPersonalesDTO DatosPersonales);

    DatosPersonalesDTO nuevoDatosPersonales();

    public List<DatosPersonalesDTO> obtenerlistaDatosPersonalesPorCriterio(
            String rfcCriterio);

    public Boolean verificaIdDatoPersonal(int idDatoPersonal);

}
