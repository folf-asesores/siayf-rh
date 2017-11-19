
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.siif.DatosPersonalesDTO;
import mx.gob.saludtlax.rh.siif.SiifDatosLaboralesDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "reportarSiifSeguroPopular")
@SessionScoped
public class ReportarSiifSeguroPopularControlller {
    private static final Logger LOGGER = Logger
            .getLogger(ReportarSiifSeguroPopularControlller.class.getName());

    @Inject
    private ReportarSiifSeguroPopularEJB ejb;

    @Inject
    private EstructuraContratoTrailersService estructuraContratoTra;

    @Inject
    private DatoPersonalLaboralEJB datoPersonalLaboralEJB;

    private ReportarSiifSeguropopularView view;

    private UploadExcelFileAnexo uploadedFile;

    @PostConstruct
    public void init() {
        view = new ReportarSiifSeguropopularView();
    }

    public String cancelar() {
        view.panelPrincipal();
        return "/contenido/configuracion/reportarSiif.xhtml?faces-redirect=true";
    }

    public ReportarSiifSeguropopularView getView() {
        return view;
    }

    public String procesarPrenomina() {
        System.out.println("view.getDat():: " + view.getDatSP());

        boolean eval = true;
        if (view.getDatSP() == null
                || StringUtils.isEmpty(view.getDatSP().getFileName())) {
            JSFUtils.errorMessage("Archivo Requerido",
                    "El Archivo XLS/XLSX es requerido, El Archivo debe tener la extención *.xls / *.xlsx");
            eval = false;
        }

        if (eval) {
            System.out.println(
                    "view.getDat():: " + view.getDatSP().getFileName());

            LOGGER.info(view.getDatSP().getFileName());

            try {
                ejb.procesarNominaTheosToSIIF(view.getDatSP());
                JSFUtils.infoMessage(
                        "En este momento ha terminado de subir el archivo y se empieza a procesar",
                        "En este momento ha terminado de subir el archivo y se empieza a procesar");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /***
     * Import Excel
     *
     * @param event
     */
    public void uploadFileDeudores() {
        uploadedFile = new UploadExcelFileAnexo();
        uploadedFile.validate(view.getDatDeudores());
        settingDataDeudores(uploadedFile.getAnexoDTOs());
    }

    public void uploadFileSP() {
        uploadedFile = new UploadExcelFileAnexo();
        uploadedFile.validate(view.getDatSP());
        settingDataDAT(uploadedFile.getAnexoDTOs());
    }

    public void uploadFileDispersion() {
        uploadedFile = new UploadExcelFileAnexo();
        uploadedFile.validate(view.getInputDispersion());
        settingDataDispersion(uploadedFile.getAnexoDTOs());
    }

    /***
     * Colaborador del import excel
     *
     * @param data
     */
    // private void settingDataTRA(List<EstructuraDTO> data) {
    //
    // Iterator<EstructuraDTO> arrayIterator = data.iterator();
    // List<EstructuraContratosTrailersDTO> listaEstructura = new
    // ArrayList<EstructuraContratosTrailersDTO>();
    // try {
    // while (arrayIterator.hasNext()) {
    //
    // EstructuraDTO genericoDTO = arrayIterator.next();
    // EstructuraContratosTrailersDTO estructuraDTO = new
    // EstructuraContratosTrailersDTO();
    // estructuraDTO.setRfc(genericoDTO.getDato(0, String.class));
    // estructuraDTO.setNumEmp(genericoDTO.getDato(1, String.class));
    // estructuraDTO.setNumCheq(genericoDTO.getDato(2, String.class));
    // estructuraDTO.settConcep(genericoDTO.getDato(3, Integer.class));
    // estructuraDTO.setConcep(genericoDTO.getDato(4, String.class));
    // estructuraDTO.setImporte(genericoDTO.getDato(5, BigDecimal.class));
    // estructuraDTO.setAnio(genericoDTO.getDato(6, String.class));
    // estructuraDTO.setQna(genericoDTO.getDato(7, String.class));
    // estructuraDTO.setPtaAnt(genericoDTO.getDato(8, String.class));
    // estructuraDTO.setTotPagos(genericoDTO.getDato(9, String.class));
    // estructuraDTO.setPagoEfec(genericoDTO.getDato(10, String.class));
    // estructuraDTO.setNomProd(genericoDTO.getDato(11, String.class));
    // estructuraDTO.setNumControl(genericoDTO.getDato(12, Integer.class));
    // listaEstructura.add(estructuraDTO);
    //
    // }
    //
    // estructuraContratoTra.registrarListaEstructura(listaEstructura);
    // JSFUtils.infoMessage("Registro Estructura", "Proceso realizado
    // correctamente");
    // } catch (EstructuraException e) {
    // JSFUtils.errorMessage("Error", e.getMessage());
    // }
    //
    // }

    private void settingDataDAT(List<EstructuraDTO> data) {

        Iterator<EstructuraDTO> arrayIterator = data.iterator();
        List<SiifSeguroPopularDTO> listaEstructura = new ArrayList<>();
        try {
            while (arrayIterator.hasNext()) {

                EstructuraDTO genericoDTO = arrayIterator.next();
                SiifSeguroPopularDTO DTO = new SiifSeguroPopularDTO();

                DTO.setRfc(genericoDTO.getDato(0, String.class));
                DTO.setQuincena(genericoDTO.getDato(1, Integer.class));
                DTO.setPeriodo(genericoDTO.getDato(2, String.class));
                DTO.setIdTipoNomina(genericoDTO.getDato(3, Integer.class));

                listaEstructura.add(DTO);

            }
            estructuraContratoTra.registrarListaSeguroPopular(listaEstructura);
            JSFUtils.infoMessage("Registro Seguro Popular",
                    "Proceso realizado correctamente");
        } catch (EstructuraException e) {
            JSFUtils.errorMessage("Error", e.getMessage());
        }
    }

    private void settingDataDeudores(List<EstructuraDTO> data) {

        Iterator<EstructuraDTO> arrayIterator = data.iterator();
        List<SiifDeudoresDiversosDTO> listaEstructura = new ArrayList<>();
        try {
            while (arrayIterator.hasNext()) {

                EstructuraDTO genericoDTO = arrayIterator.next();
                SiifDeudoresDiversosDTO DTO = new SiifDeudoresDiversosDTO();

                DTO.setRfc(genericoDTO.getDato(0, String.class));
                DTO.setImporte(genericoDTO.getDato(1, BigDecimal.class));
                DTO.setQuincena(genericoDTO.getDato(2, Integer.class));
                // System.out.println(DTO.getQuincena());
                listaEstructura.add(DTO);

            }
            estructuraContratoTra
                    .registrarListaDeudoresDiversos(listaEstructura);
            JSFUtils.infoMessage("Registro Deudores Diversos",
                    "Proceso realizado correctamente");
        } catch (EstructuraException e) {
            JSFUtils.errorMessage("Error", e.getMessage());
        }
    }

    private void settingDataDispersion(List<EstructuraDTO> data) {

        Iterator<EstructuraDTO> arrayIterator = data.iterator();
        List<SiifDispersionChequesDTO> listaEstructura = new ArrayList<>();
        try {
            while (arrayIterator.hasNext()) {

                EstructuraDTO genericoDTO = arrayIterator.next();
                SiifDispersionChequesDTO DTO = new SiifDispersionChequesDTO();

                DTO.setNum(genericoDTO.getDato(0, String.class));
                DTO.setNumCheque(genericoDTO.getDato(1, String.class));
                DTO.setNombre(genericoDTO.getDato(2, String.class));
                DTO.setImporte(genericoDTO.getDato(3, BigDecimal.class));
                DTO.setTipoNomina(genericoDTO.getDato(4, String.class));
                DTO.setQuincena(genericoDTO.getDato(5, Integer.class));
                DTO.setRfc(genericoDTO.getDato(6, String.class));
                listaEstructura.add(DTO);

            }
            estructuraContratoTra
                    .registrarListaDispersionCheques(listaEstructura);
            JSFUtils.infoMessage("Registro Dispersion Cheques",
                    "Proceso realizado correctamente");
        } catch (EstructuraException e) {
            JSFUtils.errorMessage("Error", e.getMessage());
        }
    }

    public void uploadFileDatoPersonal() {
        uploadedFile = new UploadExcelFileAnexo();
        uploadedFile.validate(view.getDatoPersonal());
        settingDataPersonal(uploadedFile.getAnexoDTOs());
    }

    private void settingDataPersonal(List<EstructuraDTO> data) {

        Iterator<EstructuraDTO> arrayIterator = data.iterator();
        List<DatosPersonalesDTO> listaEstructura = new ArrayList<>();
        try {
            while (arrayIterator.hasNext()) {

                EstructuraDTO genericoDTO = arrayIterator.next();
                DatosPersonalesDTO DTO = new DatosPersonalesDTO();

                DTO.setIdEmpleadoDatosPersonales(
                        genericoDTO.getDato(0, Integer.class));
                DTO.setRfc(genericoDTO.getDato(1, String.class));
                DTO.setApellidoPaterno(genericoDTO.getDato(2, String.class));
                DTO.setApellidoMaterno(genericoDTO.getDato(3, String.class));
                DTO.setNombre(genericoDTO.getDato(4, String.class));
                DTO.setFechaNacimiento(genericoDTO.getDato(5, Date.class));
                DTO.setSexo(genericoDTO.getDato(6, String.class));
                DTO.setIdLocalidad(genericoDTO.getDato(7, Integer.class));
                DTO.setIdColonia(genericoDTO.getDato(8, Integer.class));
                DTO.setCalle(genericoDTO.getDato(9, String.class));
                DTO.setNumeroExterior(genericoDTO.getDato(10, String.class));
                DTO.setNumeroInterior(genericoDTO.getDato(11, String.class));
                DTO.setCodigoPostal(genericoDTO.getDato(12, String.class));
                DTO.setTelefono(genericoDTO.getDato(13, String.class));
                DTO.setIdEstadoEmpleado(genericoDTO.getDato(14, String.class));

                listaEstructura.add(DTO);
            }
            // estructuraContratoTra.registrarListaDispersionCheques(listaEstructura);
            datoPersonalLaboralEJB.registrarListaDatoPersonal(listaEstructura);
            JSFUtils.infoMessage("Registro Datos Personales: ",
                    "Proceso realizado correctamente");
        } catch (EstructuraException | ReglaNegocioException e) {
            JSFUtils.errorMessage("Error", e.getMessage());
        }
    }

    public void uploadFileDatoLaboral() {
        uploadedFile = new UploadExcelFileAnexo();
        uploadedFile.validate(view.getDatoLaboral());
        settingDataLaboral(uploadedFile.getAnexoDTOs());
    }

    private void settingDataLaboral(List<EstructuraDTO> data) {

        Iterator<EstructuraDTO> arrayIterator = data.iterator();
        List<SiifDatosLaboralesDTO> listaEstructura = new ArrayList<>();
        try {
            while (arrayIterator.hasNext()) {

                EstructuraDTO genericoDTO = arrayIterator.next();
                SiifDatosLaboralesDTO DTO = new SiifDatosLaboralesDTO();

                DTO.setIdEmpleadoDatosLaborales(
                        genericoDTO.getDato(0, Integer.class));
                DTO.setIdEmpleadoDatosPersonales(
                        genericoDTO.getDato(1, Integer.class));
                DTO.setRfc(genericoDTO.getDato(2, String.class));
                DTO.setIdRfc(genericoDTO.getDato(3, Integer.class));
                DTO.setIdPlaza(genericoDTO.getDato(4, String.class));
                DTO.setIdProyecto(genericoDTO.getDato(5, Integer.class));
                DTO.setIdDependencia(genericoDTO.getDato(6, Integer.class));
                DTO.setIdUnidadResponsable(
                        genericoDTO.getDato(7, Integer.class));
                DTO.setNombramiento(genericoDTO.getDato(8, String.class));
                DTO.setIdPuesto((genericoDTO.getDato(9, String.class)));
                DTO.setIdSindicato(genericoDTO.getDato(10, Integer.class));
                DTO.setIdHabilitado(genericoDTO.getDato(11, Integer.class));
                DTO.setFechaIngreso(genericoDTO.getDato(12, Date.class));
                DTO.setNoQuinquenios(genericoDTO.getDato(13, Integer.class));
                DTO.setSueldoMensual(genericoDTO.getDato(14, BigDecimal.class));
                DTO.setPercepcionComplementaria(
                        genericoDTO.getDato(15, BigDecimal.class));
                DTO.setDespensa(genericoDTO.getDato(16, BigDecimal.class));
                DTO.setIncentivoAhorro(
                        genericoDTO.getDato(17, BigDecimal.class));
                DTO.setCompensacion(genericoDTO.getDato(18, BigDecimal.class));
                DTO.setQuinquenio(genericoDTO.getDato(19, BigDecimal.class));
                DTO.setNoCuenta(genericoDTO.getDato(20, String.class));
                DTO.setPolicia(genericoDTO.getDato(21, Integer.class));
                DTO.setIdFuenteFinanciamiento(
                        genericoDTO.getDato(22, Integer.class));
                DTO.setIdSubfuenteFinanciamiento(
                        genericoDTO.getDato(23, Integer.class));
                DTO.setIdTipoRecurso(genericoDTO.getDato(24, Integer.class));
                DTO.setIdEstadoEmpleado(genericoDTO.getDato(25, String.class));
                DTO.setIdNomina(genericoDTO.getDato(26, Integer.class));

                listaEstructura.add(DTO);

            }
            // estructuraContratoTra.registrarListaDispersionCheques(listaEstructura);
            datoPersonalLaboralEJB.registrarListaDatoLaboral(listaEstructura);
            JSFUtils.infoMessage("Registro Datos Laborales: ",
                    "Proceso realizado correctamente");
        } catch (EstructuraException | ReglaNegocioException e) {
            JSFUtils.errorMessage("Error", e.getMessage());
        }
    }

}
