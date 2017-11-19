
package mx.gob.saludtlax.rh.siif;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ConsultaDatosEJB implements DatosPersonales, Serializable {

    private static final long serialVersionUID = 648837261243804947L;

    @Inject
    private ConsultaDatosService consultaDatosService;

    @Override
    public DatosPersonalesDTO obtenerConsultaPorRfc(String rfc) {
        DatosPersonalesDTO dto = consultaDatosService.obtenerDatosPersonal(rfc);
        return dto;
    }

    @Override
    public List<DatosPersonalesDTO> obtenerListaConsultaPorRfc() {
        List<DatosPersonalesDTO> dto = consultaDatosService
                .obtenerListaDatosPersonal();
        return dto;
    }

    @Override
    public void eliminarDatosPersonales(
            DatosPersonalesDTO DatosPersonalesSelect) {
        consultaDatosService.eliminarDatosPersonales(DatosPersonalesSelect);

    }

    @Override
    public DatosPersonalesDTO obtenerDatosPersonales(
            DatosPersonalesDTO DatosPersonalesSelect) {
        DatosPersonalesDTO dto = consultaDatosService
                .obtenerDatosPersonalesPorId(
                        DatosPersonalesSelect.getIdDatoPersonal());
        return dto;
    }

    @Override
    public void crearDatosPersonales(DatosPersonalesDTO DatosPersonales) {
        consultaDatosService.crearDatosPersonales(DatosPersonales);

    }

    @Override
    public void actualizarDatosPersonales(DatosPersonalesDTO DatosPersonales) {
        consultaDatosService.actualizarDatosPersonales(DatosPersonales);

    }

    @Override
    public DatosPersonalesDTO nuevoDatosPersonales() {
        return consultaDatosService.nuevosDatosPersonales();
    }

    @Override
    public List<DatosPersonalesDTO> obtenerlistaDatosPersonalesPorCriterio(
            String rfc) {
        List<DatosPersonalesDTO> list = consultaDatosService
                .obtenerListaDatosPersonalesPorCriterio(rfc);
        return list;

    }

    @Override
    public Boolean verificaIdDatoPersonal(int idDatoPersonal) {
        int existe = 0;
        existe = consultaDatosService
                .verificaDatosPersonalesPorId(idDatoPersonal);
        if (existe == 1) {
            return true;
        } else {
            return false;
        }
    }

}