
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

public class TerminoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -613447376403476643L;

    private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<>();
    private List<TerminoDetalleDTO> terminoDetalleDTO = new ArrayList<>();
    private List<TerminoDTO> terminoListaDTO = new ArrayList<>();
    private TerminoDTO terminoDTO = new TerminoDTO();
    private Integer idMovimiento;
    private String criterio;
    private byte[] bytes = null;
    private String comision;

    private boolean mostrarTermino = false;
    private boolean mostrarPrincipal = true;
    private boolean mostrarOpcionDescarga = false;

    public List<InfoEmpleadoDTO> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<TerminoDetalleDTO> getTerminoDetalleDTO() {
        return terminoDetalleDTO;
    }

    public void setTerminoDetalleDTO(List<TerminoDetalleDTO> terminoDetalleDTO) {
        this.terminoDetalleDTO = terminoDetalleDTO;
    }

    public List<TerminoDTO> getTerminoListaDTO() {
        return terminoListaDTO;
    }

    public void setTerminoListaDTO(List<TerminoDTO> terminoListaDTO) {
        this.terminoListaDTO = terminoListaDTO;
    }

    public TerminoDTO getTerminoDTO() {
        return terminoDTO;
    }

    public void setTerminoDTO(TerminoDTO terminoDTO) {
        this.terminoDTO = terminoDTO;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public boolean isMostrarTermino() {
        return mostrarTermino;
    }

    public void setMostrarTermino(boolean mostrarTermino) {
        this.mostrarTermino = mostrarTermino;
    }

    public boolean isMostrarPrincipal() {
        return mostrarPrincipal;
    }

    public void setMostrarPrincipal(boolean mostrarPrincipal) {
        this.mostrarPrincipal = mostrarPrincipal;
    }

    public boolean isMostrarOpcionDescarga() {
        return mostrarOpcionDescarga;
    }

    public void setMostrarOpcionDescarga(boolean mostrarOpcionDescarga) {
        this.mostrarOpcionDescarga = mostrarOpcionDescarga;
    }

}
