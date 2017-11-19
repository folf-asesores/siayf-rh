
package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.util.InitTest;

@RunWith(Arquillian.class)
@Transactional
public class TimbradoServicesTest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1056012740716514919L;

    @Inject
    TimbradoService timbradoService;

    @Deployment
    public static WebArchive createDeployment() {

        return InitTest.crearWar();
    }

    @Test
    public void timbrar() {
        DatosCFDINomina datosCFDI = new DatosCFDINomina();

        datosCFDI.setAntiguedad(1);
        datosCFDI.setBanco("001");
        datosCFDI.setCalle("BAJA");
        datosCFDI.setCodigoPostal("24023");
        datosCFDI.setColonia("Revolucion");
        datosCFDI.setCurp("GADJ850506HCCNMN09");
        datosCFDI.setDepartamento("NINGUNO");
        datosCFDI.setEstado("CAMPECHE");
        datosCFDI.setFechaFinalPago(new Date());
        datosCFDI.setFechaInicalPago(new Date());
        datosCFDI.setFechaInicioRelLaboral(new Date());
        datosCFDI.setFechaPago(new Date());
        datosCFDI.setFolio("1");
        datosCFDI.setMunicipio("CAMPECHE");
        datosCFDI.setNoExterio("21");
        datosCFDI.setNoInterior("24");
        datosCFDI.setNombre("JUAN CARLOS IVAN GANZO DOMINGUEZ");
        datosCFDI.setNumeroDiasPagados(new BigDecimal("10"));
        datosCFDI.setNumeroEmpleado("A1023763");
        datosCFDI.setNumeroSeguroSocial("128378243984");
        datosCFDI.setPais("MEXICO");
        datosCFDI.setPeriocidadPago("QUINCENAL");
        datosCFDI.setPuesto("PROGRAMADOR");
        datosCFDI.setRfc("GADJ850506M84");
        datosCFDI.setRiesgoPuesto(1);
        datosCFDI.setSalarioBaseCotApor(new BigDecimal("10"));
        datosCFDI.setSalarioDiarioIntegrado(new BigDecimal("10"));
        datosCFDI.setSerie("2016-1");
        datosCFDI.setTipoContrato("PLAZA");
        datosCFDI.setTipoJornada("JORNADA MIXTA");
        datosCFDI.setTipoRegimen(1);

        List<PercepcionCFDI> listadoPercepciones = new ArrayList<>();

        PercepcionCFDI percepcionCFDI = new PercepcionCFDI();
        percepcionCFDI.setClave("0700");
        percepcionCFDI.setConcepto("SUELDOS BASE");
        percepcionCFDI.setImporteExcento(new BigDecimal("0"));
        percepcionCFDI.setImporteGravado(new BigDecimal("3765.50"));
        percepcionCFDI.setTipoPercepcion("001");
        listadoPercepciones.add(percepcionCFDI);

        percepcionCFDI = new PercepcionCFDI();
        percepcionCFDI.setClave("30AR");
        percepcionCFDI.setConcepto("COMPENSACIONES ADICIONALES POR SERV ESPECIALES");
        percepcionCFDI.setImporteExcento(new BigDecimal("0"));
        percepcionCFDI.setImporteGravado(new BigDecimal("753.10"));
        percepcionCFDI.setTipoPercepcion("016");
        listadoPercepciones.add(percepcionCFDI);

        percepcionCFDI = new PercepcionCFDI();
        percepcionCFDI.setClave("3800");
        percepcionCFDI.setConcepto("AYUDA DE DESPENSA");
        percepcionCFDI.setImporteExcento(new BigDecimal("0"));
        percepcionCFDI.setImporteGravado(new BigDecimal("182.50"));
        percepcionCFDI.setTipoPercepcion("016");
        listadoPercepciones.add(percepcionCFDI);

        percepcionCFDI = new PercepcionCFDI();
        percepcionCFDI.setClave("4200");
        percepcionCFDI.setConcepto("ASIGNACION BRUTA");
        percepcionCFDI.setImporteExcento(new BigDecimal("0"));
        percepcionCFDI.setImporteGravado(new BigDecimal("1921.00"));
        percepcionCFDI.setTipoPercepcion("016");
        listadoPercepciones.add(percepcionCFDI);

        percepcionCFDI = new PercepcionCFDI();
        percepcionCFDI.setClave("59DT");
        percepcionCFDI.setConcepto("DIA DEL TRABAJADOR DE LA SS");
        percepcionCFDI.setImporteExcento(new BigDecimal("0"));
        percepcionCFDI.setImporteGravado(new BigDecimal("1000"));
        percepcionCFDI.setTipoPercepcion("016");
        listadoPercepciones.add(percepcionCFDI);

        datosCFDI.setPercepciones(listadoPercepciones);

        List<DeduccionCFDI> listadoDeduccion = new ArrayList<>();
        DeduccionCFDI deduccion = new DeduccionCFDI();
        deduccion.setClave("0100");
        deduccion.setConcepto("IMPUESTO SOBRE LA RENTA");
        deduccion.setImporteExcento(new BigDecimal("0"));
        deduccion.setImporteGravado(new BigDecimal("1294.39"));
        deduccion.setTipoPercepcion("002");
        listadoDeduccion.add(deduccion);

        deduccion = new DeduccionCFDI();
        deduccion.setClave("02SR");
        deduccion.setConcepto("SEGURO DE RETIRO, ISSSTE");
        deduccion.setImporteExcento(new BigDecimal("0"));
        deduccion.setImporteGravado(new BigDecimal("279.83"));
        deduccion.setTipoPercepcion("001");
        listadoDeduccion.add(deduccion);

        deduccion = new DeduccionCFDI();
        deduccion.setClave("5800");
        deduccion.setConcepto("CUOTAS SINDICALES");
        deduccion.setImporteExcento(new BigDecimal("0"));
        deduccion.setImporteGravado(new BigDecimal("75.31"));
        deduccion.setTipoPercepcion("019");
        listadoDeduccion.add(deduccion);

        deduccion = new DeduccionCFDI();
        deduccion.setClave("7000");
        deduccion.setConcepto("FONDO DE AHORRO PARA AUXILIO DE DEFUNCION");
        deduccion.setImporteExcento(new BigDecimal("0"));
        deduccion.setImporteGravado(new BigDecimal("5"));
        deduccion.setTipoPercepcion("004");
        listadoDeduccion.add(deduccion);

        datosCFDI.setDeducciones(listadoDeduccion);

        List<HorasExtraCFDI> listadoHorasExtra = new ArrayList<>();
        HorasExtraCFDI horaExtra = new HorasExtraCFDI();
        horaExtra.setDias(1);
        horaExtra.setHorasExtra(1);
        horaExtra.setImportePagado(new BigDecimal("100.00"));
        horaExtra.setTipoHoras("Dobles");
        listadoHorasExtra.add(horaExtra);
        datosCFDI.setHorasExtra(listadoHorasExtra);

        List<IncapacidadCFDI> listadoIncapacidad = new ArrayList<>();
        IncapacidadCFDI incapacidadCFDI = new IncapacidadCFDI();
        incapacidadCFDI.setDescuento(new BigDecimal("10"));
        incapacidadCFDI.setDiasIncapacidad(new BigDecimal(1));
        incapacidadCFDI.setTipoIncapacidad(1);
        listadoIncapacidad.add(incapacidadCFDI);

        datosCFDI.setIncapacidadCFDI(listadoIncapacidad);

        // DatosCFDITimbrado datosCFDITimbrado = timbradoService.generarCFDI(datosCFDI, "C:\\ArchivosXSLT\\cadenaoriginal_3_2.xslt");

    }

}
