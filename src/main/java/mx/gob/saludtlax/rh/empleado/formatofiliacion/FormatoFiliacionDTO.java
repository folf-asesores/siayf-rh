/**
 * 
 */
package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import java.util.Date;

import javax.persistence.Column;

/**
 * @author Eduardo Mex
 *
 */
public class FormatoFiliacionDTO {

	private Integer idEmpleado;
	private String filiacion;
	private String clave;
	private String nombrePadre;
	private String nombreMadre;
	private String actaNumero;
	private Integer anio;
	private String foja;
	private String libro;
	private String cartillaSmn;
	private String clabeCartilla;
	private String nombreConyuge;
	private String domicilio;
	private String extranjeros;
	private Date fecha;
	private String nombrePersonaConocidoUno;
	private String domicilioPersonaConocidoUno;
	private String lugarPersonaConocidoUno;
	private String nombrePersonaConocidoDos;
	private String domicilioPersonaConocidoDos;
	private String lugarPersonaConocidoDos;
	private String nombreParienteUno;
	private String domicilioParienteUno;
	private String lugarParienteUno;
	private String nombreParienteDos;
	private String domicilioParienteDos;
	private String lugarParienteDos;
	private boolean colorBlanco;
	private boolean colorNegro;
	private boolean colorMorenoClaro;
	private boolean colorMorenoOscuro;
	private boolean colorAmarillo;
	private boolean cabelloCastClaro;
	private boolean cabelloCastOscuro;
	private boolean cabelloNegro;
	private boolean cabelloRubio;
	private boolean cabelloRojo;
	private boolean cabelloAlbino;
	private boolean cabelloEntrecano;
	private boolean cabelloCano;
	private boolean frentePequenia;
	private boolean frenteMediana;
	private boolean frenteGrande;
	private boolean cejasAbundantes;
	private boolean cejasEscasas;
	private boolean cejasRegulares;
	private boolean ojosAzules;
	private boolean ojosVerdes;
	private boolean ojosCastClaro;
	private boolean ojosCastOscuro;
	private boolean ojosPardos;
	private boolean ojosVerdosos;
	private boolean ojosNegros;
	private boolean narizConvexa;
	private boolean narizConcava;
	private boolean narizRectilinea;
	private boolean bocaPequenia;
	private boolean bocaRegular;
	private boolean bocaGrande;
	private String seniasVisibles;
	
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
	public String getSeniasVisibles() {
		return seniasVisibles;
	}
	public void setSeniasVisibles(String seniasVisibles) {
		this.seniasVisibles = seniasVisibles;
	}
	public String getCartillaSmn() {
		return cartillaSmn;
	}
	public void setCartillaSmn(String cartillaSmn) {
		this.cartillaSmn = cartillaSmn;
	}
	public boolean isColorAmarillo() {
		return colorAmarillo;
	}
	public void setColorAmarillo(boolean colorAmarillo) {
		this.colorAmarillo = colorAmarillo;
	}

}
