
package mx.gob.saludtlax.rh.importarconstancia;

import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.nomina.importarconstancia.ConstanciaSueldoDTO;
import mx.gob.saludtlax.rh.nomina.importarconstancia.ImportarConstanciaSueldoEJB;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "constanciaSueldo")
@ViewScoped
public class ConstaciaSueldoController {

    private ConstanciaSueldoDTO constancia = new ConstanciaSueldoDTO();
    private UploadedFile uploadFile;
    private boolean mostrarSubirArchivo = true;
    private ConstanciaSueldoView constanciaSueldoView;
    private Integer ejercicio;
    private Date fecha;

    @Inject
    ImportarConstanciaSueldoEJB importarConstanciaEJB;

    @PostConstruct
    public void init() {
        constanciaSueldoView = new ConstanciaSueldoView();
    }

    public void registrarDatos() {
        ejercicio = constancia.getEjercicioFiscal();
        fecha = constancia.getFechaDeclaracion();
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        uploadFile = event.getFile();
        mostrarSubirArchivo = false;
        String valores = new String(uploadFile.getContents(), "UTF-8");
        StringTokenizer st = new StringTokenizer(valores, "\n");
        while (st.hasMoreTokens()) {
            String dato = st.nextToken();
            procesarArchivo(dato);
        }
        constancia.setEjercicioFiscal(null);
        constancia.setFechaDeclaracion(null);
        updatePage();
        JSFUtils.infoMessage(
                "En este momento ha terminado de subir el archivo y se empieza a procesar",
                "En este momento ha terminado de subir el archivo y se empieza a procesar");
    }

    public void procesarArchivo(String dato) {
        String newArray = "";
        for (int i = 0; i < dato.length(); i++) {
            newArray += dato.charAt(i);
            if (String.valueOf(dato.charAt(i)).equals("|")) {
                if (String.valueOf(dato.charAt(i + 1)).equals("|")) {
                    newArray += "| |";
                }
            }
        }

        StringTokenizer string = new StringTokenizer(newArray, "|");

        Integer datos = string.countTokens();
        String[] array = new String[datos];
        int i = 0;
        while (string.hasMoreTokens()) {
            String s = string.nextToken();
            array[i] = s;
            i++;
        }
        ConstanciaSueldoDTO constanciaSueldoDTO = new ConstanciaSueldoDTO();
        constanciaSueldoDTO.setEjercicioFiscal(ejercicio);
        constanciaSueldoDTO.setFechaDeclaracion(fecha);
        importarConstanciaEJB.guardarConstancia(array, constanciaSueldoDTO);

    }

    public void mostrarTablaFinal() {
        mostrarSubirArchivo = true;
    }

    public void updatePage() throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext
                .getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();

        response.sendRedirect(request.getContextPath()
                + "/contenido/nomina/importarConstancia.xhtml");
    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public boolean isMostrarSubirArchivo() {
        return mostrarSubirArchivo;
    }

    public void setMostrarSubirArchivo(boolean mostrarSubirArchivo) {
        this.mostrarSubirArchivo = mostrarSubirArchivo;
    }

    public ConstanciaSueldoView getConstanciaSueldoView() {
        return constanciaSueldoView;
    }

    public void setConstanciaSueldoView(
            ConstanciaSueldoView constanciaSueldoView) {
        this.constanciaSueldoView = constanciaSueldoView;
    }

    public ConstanciaSueldoDTO getConstancia() {
        return constancia;
    }

    public void setConstancia(ConstanciaSueldoDTO constancia) {
        this.constancia = constancia;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}