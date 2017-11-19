
package mx.gob.saludtlax.rh.siif;

public class PuestosGeneralesDTO {

    private Integer idPuestoGeneral;
    private String codigo;
    private String puesto;
    private String idTipoTabulador;

    //  < < < Getters & Setters > > >
    public Integer getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    public void setIdPuestoGeneral(Integer idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getIdTipoTabulador() {
        return idTipoTabulador;
    }

    public void setIdTipoTabulador(String idTipoTabulador) {
        this.idTipoTabulador = idTipoTabulador;
    }

}