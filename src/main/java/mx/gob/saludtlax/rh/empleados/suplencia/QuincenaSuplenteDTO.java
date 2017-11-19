/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 17/01/2017 20:48:32
 */
public class QuincenaSuplenteDTO {
    private Integer idQuincena;
    private Integer idSuplente;
    private String suplente;
    private int numeroQuincena;
    private int ejercicioFiscal;
    private String estatus;

    public QuincenaSuplenteDTO(Integer idQuincena, String suplente,
            int numeroQuincena, int ejercicioFiscal, Integer idSuplente,
            String estatus) {

        this.idQuincena = idQuincena;
        this.suplente = suplente;
        this.numeroQuincena = numeroQuincena;
        this.ejercicioFiscal = ejercicioFiscal;
        this.idSuplente = idSuplente;
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public QuincenaSuplenteDTO() {

    }

    public Integer getIdSuplente() {
        return idSuplente;
    }

    public void setIdSuplente(Integer idSuplente) {
        this.idSuplente = idSuplente;
    }

    public Integer getIdQuincena() {
        return idQuincena;
    }

    public void setIdQuincena(Integer idQuincena) {
        this.idQuincena = idQuincena;
    }

    public String getSuplente() {
        return suplente;
    }

    public void setSuplente(String suplente) {
        this.suplente = suplente;
    }

    public int getNumeroQuincena() {
        return numeroQuincena;
    }

    public void setNumeroQuincena(int numeroQuincena) {
        this.numeroQuincena = numeroQuincena;
    }

    public int getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(int ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

}
