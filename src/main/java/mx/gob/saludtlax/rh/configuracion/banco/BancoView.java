/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 03/06/2016 14:30:13
 */
public class BancoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6851486859466454478L;

    private List<BancoDTO> listaBanco = new ArrayList<>();

    private BancoDTO bancoDTO = new BancoDTO();

    private String accionBanco = "Registrar";

    private Integer idBanco;

    private Boolean dialogEliminarBanco = Boolean.FALSE;

    /**
     * @return the listaBanco
     */
    public List<BancoDTO> getListaBanco() {
        return listaBanco;
    }

    /**
     * @param listaBanco
     *            the listaBanco to set
     */
    public void setListaBanco(List<BancoDTO> listaBanco) {
        this.listaBanco = listaBanco;
    }

    /**
     * @return the bancoDTO
     */
    public BancoDTO getBancoDTO() {
        return bancoDTO;
    }

    /**
     * @param bancoDTO
     *            the bancoDTO to set
     */
    public void setBancoDTO(BancoDTO bancoDTO) {
        this.bancoDTO = bancoDTO;
    }

    /**
     * @return the accionBanco
     */
    public String getAccionBanco() {
        return accionBanco;
    }

    /**
     * @param accionBanco
     *            the accionBanco to set
     */
    public void setAccionBanco(String accionBanco) {
        this.accionBanco = accionBanco;
    }

    /**
     * @return the idBanco
     */
    public Integer getIdBanco() {
        return idBanco;
    }

    /**
     * @param idBanco
     *            the idBanco to set
     */
    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    /**
     * @return the dialogEliminarBanco
     */
    public Boolean getDialogEliminarBanco() {
        return dialogEliminarBanco;
    }

    /**
     * @param dialogEliminarBanco
     *            the dialogEliminarBanco to set
     */
    public void setDialogEliminarBanco(Boolean dialogEliminarBanco) {
        this.dialogEliminarBanco = dialogEliminarBanco;
    }

}
