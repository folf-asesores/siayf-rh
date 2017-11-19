
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Eduardo Mex
 *
 */
@Entity
@Table(name = "formatos_filiaciones")
public class FormatoFiliacionEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2346067380813656985L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formato_filiacion")
    private Integer idFormatoFiliacion;

    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "filiacion")
    private String filiacion;

    @Column(name = "clave")
    private String clave;

    @Column(name = "nombre_padre")
    private String nombrePadre;

    @Column(name = "nombre_madre")
    private String nombreMadre;

    @Column(name = "acta_numero")
    private String actaNumero;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "foja")
    private String foja;

    @Column(name = "libro")
    private String libro;

    @Column(name = "cartilla_smn")
    private String cartillaSmn;

    @Column(name = "clase_cartilla")
    private String clabeCartilla;

    @Column(name = "nombre_conyuge")
    private String nombreConyuge;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "extranjeros")
    private String extranjeros;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "nombre_persona_conocido_uno")
    private String nombrePersonaConocidoUno;

    @Column(name = "domicilio_persona_conocido_uno")
    private String domicilioPersonaConocidoUno;

    @Column(name = "lugar_persona_conocido_uno")
    private String lugarPersonaConocidoUno;

    @Column(name = "nombre_persona_conocido_dos")
    private String nombrePersonaConocidoDos;

    @Column(name = "domicilio_persona_conocido_dos")
    private String domicilioPersonaConocidoDos;

    @Column(name = "lugar_persona_conocido_dos")
    private String lugarPersonaConocidoDos;

    @Column(name = "nombre_pariente_uno")
    private String nombreParienteUno;

    @Column(name = "domicilio_pariente_uno")
    private String domicilioParienteUno;

    @Column(name = "lugar_pariente_uno")
    private String lugarParienteUno;

    @Column(name = "nombre_pariente_dos")
    private String nombreParienteDos;

    @Column(name = "domicilio_pariente_dos")
    private String domicilioParienteDos;

    @Column(name = "lugar_pariente_dos")
    private String lugarParienteDos;

    @Column(name = "color_blanco")
    private boolean colorBlanco;

    @Column(name = "color_negro")
    private boolean colorNegro;

    @Column(name = "color_moreno_claro")
    private boolean colorMorenoClaro;

    @Column(name = "color_moreno_oscuro")
    private boolean colorMorenoOscuro;

    @Column(name = "color_amarillo")
    private boolean colorAmarillo;

    @Column(name = "cabello_cast_claro")
    private boolean cabelloCastClaro;

    @Column(name = "cabello_cast_oscuro")
    private boolean cabelloCastOscuro;

    @Column(name = "cabello_negro")
    private boolean cabelloNegro;

    @Column(name = "cabello_rubio")
    private boolean cabelloRubio;

    @Column(name = "cabello_rojo")
    private boolean cabelloRojo;

    @Column(name = "cabello_albino")
    private boolean cabelloAlbino;

    @Column(name = "cabello_entrecano")
    private boolean cabelloEntrecano;

    @Column(name = "cabello_cano")
    private boolean cabelloCano;

    @Column(name = "frente_pequenia")
    private boolean frentePequenia;

    @Column(name = "frente_mediana")
    private boolean frenteMediana;

    @Column(name = "frente_grande")
    private boolean frenteGrande;

    @Column(name = "cejas_abundantes")
    private boolean cejasAbundantes;

    @Column(name = "cejas_escasas")
    private boolean cejasEscasas;

    @Column(name = "cejas_regulares")
    private boolean cejasRegulares;

    @Column(name = "ojos_azules")
    private boolean ojosAzules;

    @Column(name = "ojos_verdes")
    private boolean ojosVerdes;

    @Column(name = "ojos_cast_claro")
    private boolean ojosCastClaro;

    @Column(name = "ojos_cast_oscuro")
    private boolean ojosCastOscuro;

    @Column(name = "ojos_pardos")
    private boolean ojosPardos;

    @Column(name = "ojos_verdosos")
    private boolean ojosVerdosos;

    @Column(name = "ojos_negros")
    private boolean ojosNegros;

    @Column(name = "nariz_convexa")
    private boolean narizConvexa;

    @Column(name = "nariz_concava")
    private boolean narizConcava;

    @Column(name = "nariz_rectilinea")
    private boolean narizRectilinea;

    @Column(name = "boca_pequenia")
    private boolean bocaPequenia;

    @Column(name = "boca_regular")
    private boolean bocaRegular;

    @Column(name = "boca_grande")
    private boolean bocaGrande;

    @Column(name = "senias_visibles")
    private String seniasVisibles;

    @Column(name = "estatus")
    private String estatus;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdFormatoFiliacion() {
        return idFormatoFiliacion;
    }

    public void setIdFormatoFiliacion(Integer idFormatoFiliacion) {
        this.idFormatoFiliacion = idFormatoFiliacion;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(String filiacion) {
        this.filiacion = filiacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public String getActaNumero() {
        return actaNumero;
    }

    public void setActaNumero(String actaNumero) {
        this.actaNumero = actaNumero;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getFoja() {
        return foja;
    }

    public void setFoja(String foja) {
        this.foja = foja;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getClabeCartilla() {
        return clabeCartilla;
    }

    public void setClabeCartilla(String clabeCartilla) {
        this.clabeCartilla = clabeCartilla;
    }

    public String getNombreConyuge() {
        return nombreConyuge;
    }

    public void setNombreConyuge(String nombreConyuge) {
        this.nombreConyuge = nombreConyuge;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getExtranjeros() {
        return extranjeros;
    }

    public void setExtranjeros(String extranjeros) {
        this.extranjeros = extranjeros;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombrePersonaConocidoUno() {
        return nombrePersonaConocidoUno;
    }

    public void setNombrePersonaConocidoUno(String nombrePersonaConocidoUno) {
        this.nombrePersonaConocidoUno = nombrePersonaConocidoUno;
    }

    public String getDomicilioPersonaConocidoUno() {
        return domicilioPersonaConocidoUno;
    }

    public void setDomicilioPersonaConocidoUno(String domicilioPersonaConocidoUno) {
        this.domicilioPersonaConocidoUno = domicilioPersonaConocidoUno;
    }

    public String getLugarPersonaConocidoUno() {
        return lugarPersonaConocidoUno;
    }

    public void setLugarPersonaConocidoUno(String lugarPersonaConocidoUno) {
        this.lugarPersonaConocidoUno = lugarPersonaConocidoUno;
    }

    public String getNombrePersonaConocidoDos() {
        return nombrePersonaConocidoDos;
    }

    public void setNombrePersonaConocidoDos(String nombrePersonaConocidoDos) {
        this.nombrePersonaConocidoDos = nombrePersonaConocidoDos;
    }

    public String getDomicilioPersonaConocidoDos() {
        return domicilioPersonaConocidoDos;
    }

    public void setDomicilioPersonaConocidoDos(String domicilioPersonaConocidoDos) {
        this.domicilioPersonaConocidoDos = domicilioPersonaConocidoDos;
    }

    public String getLugarPersonaConocidoDos() {
        return lugarPersonaConocidoDos;
    }

    public void setLugarPersonaConocidoDos(String lugarPersonaConocidoDos) {
        this.lugarPersonaConocidoDos = lugarPersonaConocidoDos;
    }

    public String getNombreParienteUno() {
        return nombreParienteUno;
    }

    public void setNombreParienteUno(String nombreParienteUno) {
        this.nombreParienteUno = nombreParienteUno;
    }

    public String getDomicilioParienteUno() {
        return domicilioParienteUno;
    }

    public void setDomicilioParienteUno(String domicilioParienteUno) {
        this.domicilioParienteUno = domicilioParienteUno;
    }

    public String getLugarParienteUno() {
        return lugarParienteUno;
    }

    public void setLugarParienteUno(String lugarParienteUno) {
        this.lugarParienteUno = lugarParienteUno;
    }

    public String getNombreParienteDos() {
        return nombreParienteDos;
    }

    public void setNombreParienteDos(String nombreParienteDos) {
        this.nombreParienteDos = nombreParienteDos;
    }

    public String getDomicilioParienteDos() {
        return domicilioParienteDos;
    }

    public void setDomicilioParienteDos(String domicilioParienteDos) {
        this.domicilioParienteDos = domicilioParienteDos;
    }

    public String getLugarParienteDos() {
        return lugarParienteDos;
    }

    public void setLugarParienteDos(String lugarParienteDos) {
        this.lugarParienteDos = lugarParienteDos;
    }

    public boolean isColorBlanco() {
        return colorBlanco;
    }

    public void setColorBlanco(boolean colorBlanco) {
        this.colorBlanco = colorBlanco;
    }

    public boolean isColorNegro() {
        return colorNegro;
    }

    public void setColorNegro(boolean colorNegro) {
        this.colorNegro = colorNegro;
    }

    public boolean isColorMorenoClaro() {
        return colorMorenoClaro;
    }

    public void setColorMorenoClaro(boolean colorMorenoClaro) {
        this.colorMorenoClaro = colorMorenoClaro;
    }

    public boolean isColorMorenoOscuro() {
        return colorMorenoOscuro;
    }

    public void setColorMorenoOscuro(boolean colorMorenoOscuro) {
        this.colorMorenoOscuro = colorMorenoOscuro;
    }

    public boolean isCabelloCastClaro() {
        return cabelloCastClaro;
    }

    public void setCabelloCastClaro(boolean cabelloCastClaro) {
        this.cabelloCastClaro = cabelloCastClaro;
    }

    public boolean isCabelloCastOscuro() {
        return cabelloCastOscuro;
    }

    public void setCabelloCastOscuro(boolean cabelloCastOscuro) {
        this.cabelloCastOscuro = cabelloCastOscuro;
    }

    public boolean isCabelloNegro() {
        return cabelloNegro;
    }

    public void setCabelloNegro(boolean cabelloNegro) {
        this.cabelloNegro = cabelloNegro;
    }

    public boolean isCabelloRubio() {
        return cabelloRubio;
    }

    public void setCabelloRubio(boolean cabelloRubio) {
        this.cabelloRubio = cabelloRubio;
    }

    public boolean isCabelloRojo() {
        return cabelloRojo;
    }

    public void setCabelloRojo(boolean cabelloRojo) {
        this.cabelloRojo = cabelloRojo;
    }

    public boolean isCabelloAlbino() {
        return cabelloAlbino;
    }

    public void setCabelloAlbino(boolean cabelloAlbino) {
        this.cabelloAlbino = cabelloAlbino;
    }

    public boolean isCabelloEntrecano() {
        return cabelloEntrecano;
    }

    public void setCabelloEntrecano(boolean cabelloEntrecano) {
        this.cabelloEntrecano = cabelloEntrecano;
    }

    public boolean isCabelloCano() {
        return cabelloCano;
    }

    public void setCabelloCano(boolean cabelloCano) {
        this.cabelloCano = cabelloCano;
    }

    public boolean isFrentePequenia() {
        return frentePequenia;
    }

    public void setFrentePequenia(boolean frentePequenia) {
        this.frentePequenia = frentePequenia;
    }

    public boolean isFrenteMediana() {
        return frenteMediana;
    }

    public void setFrenteMediana(boolean frenteMediana) {
        this.frenteMediana = frenteMediana;
    }

    public boolean isFrenteGrande() {
        return frenteGrande;
    }

    public void setFrenteGrande(boolean frenteGrande) {
        this.frenteGrande = frenteGrande;
    }

    public boolean isCejasAbundantes() {
        return cejasAbundantes;
    }

    public void setCejasAbundantes(boolean cejasAbundantes) {
        this.cejasAbundantes = cejasAbundantes;
    }

    public boolean isCejasEscasas() {
        return cejasEscasas;
    }

    public void setCejasEscasas(boolean cejasEscasas) {
        this.cejasEscasas = cejasEscasas;
    }

    public boolean isCejasRegulares() {
        return cejasRegulares;
    }

    public void setCejasRegulares(boolean cejasRegulares) {
        this.cejasRegulares = cejasRegulares;
    }

    public boolean isOjosAzules() {
        return ojosAzules;
    }

    public void setOjosAzules(boolean ojosAzules) {
        this.ojosAzules = ojosAzules;
    }

    public boolean isOjosVerdes() {
        return ojosVerdes;
    }

    public void setOjosVerdes(boolean ojosVerdes) {
        this.ojosVerdes = ojosVerdes;
    }

    public boolean isOjosCastClaro() {
        return ojosCastClaro;
    }

    public void setOjosCastClaro(boolean ojosCastClaro) {
        this.ojosCastClaro = ojosCastClaro;
    }

    public boolean isOjosCastOscuro() {
        return ojosCastOscuro;
    }

    public void setOjosCastOscuro(boolean ojosCastOscuro) {
        this.ojosCastOscuro = ojosCastOscuro;
    }

    public boolean isOjosPardos() {
        return ojosPardos;
    }

    public void setOjosPardos(boolean ojosPardos) {
        this.ojosPardos = ojosPardos;
    }

    public boolean isOjosVerdosos() {
        return ojosVerdosos;
    }

    public void setOjosVerdosos(boolean ojosVerdosos) {
        this.ojosVerdosos = ojosVerdosos;
    }

    public boolean isOjosNegros() {
        return ojosNegros;
    }

    public void setOjosNegros(boolean ojosNegros) {
        this.ojosNegros = ojosNegros;
    }

    public boolean isNarizConvexa() {
        return narizConvexa;
    }

    public void setNarizConvexa(boolean narizConvexa) {
        this.narizConvexa = narizConvexa;
    }

    public boolean isNarizConcava() {
        return narizConcava;
    }

    public void setNarizConcava(boolean narizConcava) {
        this.narizConcava = narizConcava;
    }

    public boolean isNarizRectilinea() {
        return narizRectilinea;
    }

    public void setNarizRectilinea(boolean narizRectilinea) {
        this.narizRectilinea = narizRectilinea;
    }

    public boolean isBocaPequenia() {
        return bocaPequenia;
    }

    public void setBocaPequenia(boolean bocaPequenia) {
        this.bocaPequenia = bocaPequenia;
    }

    public boolean isBocaRegular() {
        return bocaRegular;
    }

    public void setBocaRegular(boolean bocaRegular) {
        this.bocaRegular = bocaRegular;
    }

    public boolean isBocaGrande() {
        return bocaGrande;
    }

    public void setBocaGrande(boolean bocaGrande) {
        this.bocaGrande = bocaGrande;
    }

    public String getCartillaSmn() {
        return cartillaSmn;
    }

    public void setCartillaSmn(String cartillaSmn) {
        this.cartillaSmn = cartillaSmn;
    }

    public String getSeniasVisibles() {
        return seniasVisibles;
    }

    public void setSeniasVisibles(String seniasVisibles) {
        this.seniasVisibles = seniasVisibles;
    }

    public boolean isColorAmarillo() {
        return colorAmarillo;
    }

    public void setColorAmarillo(boolean colorAmarillo) {
        this.colorAmarillo = colorAmarillo;
    }

}
