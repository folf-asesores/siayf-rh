/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 29/12/2016 13:59:36
 */
@ManagedBean(name = "activacionQuincena")
@ViewScoped
public class ActivacionQuincenaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3985551155276750524L;
    @Inject
    private Suplencia suplencia;

    private QuincenaActivaDTO quincenaActiva;
    private int numeroQuincena;
    private int idUsuario;
    private int ejercicioFiscal;

    @PostConstruct
    public void inicio() {

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        setQuincenaActiva(suplencia.obtenerQuincenaActiva());

        setIdUsuario(usuario.getIdUsuario());

    }

    public void activarQuincena() {
        try {
            suplencia.activarQuincenaSuplencia(numeroQuincena, ejercicioFiscal,
                    idUsuario);
            inicio();
            JSFUtils.infoMessage("", "¡Se ha activado la quincena con éxito!");
            setNumeroQuincena(0);

        } catch (ValidacionException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public QuincenaActivaDTO getQuincenaActiva() {
        return quincenaActiva;
    }

    public void setQuincenaActiva(QuincenaActivaDTO quincenaActiva) {
        this.quincenaActiva = quincenaActiva;
    }

    public int getNumeroQuincena() {
        return numeroQuincena;
    }

    public void setNumeroQuincena(int numeroQuincena) {
        this.numeroQuincena = numeroQuincena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(int ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

}
