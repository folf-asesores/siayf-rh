/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.datolaboral.EnumEstatusConfiguracion;
import mx.gob.saludtlax.rh.empleados.suplencia.EnumEstatusSuplencia;
import mx.gob.saludtlax.rh.persistencia.AdscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.AdscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.BaseFederalEntity;
import mx.gob.saludtlax.rh.persistencia.BaseFederalRepository;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadEntity;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadRepository;
import mx.gob.saludtlax.rh.persistencia.CluesEntity;
import mx.gob.saludtlax.rh.persistencia.CluesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.DatosLaboralesCruceEntity;
import mx.gob.saludtlax.rh.persistencia.DatosLaboralesCruceRepository;
import mx.gob.saludtlax.rh.persistencia.DatosPersonalesEntity;
import mx.gob.saludtlax.rh.persistencia.DatosPersonalesRepository;
import mx.gob.saludtlax.rh.persistencia.DatosSuplentesEntity;
import mx.gob.saludtlax.rh.persistencia.DatosSuplentesRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosRepository;
import mx.gob.saludtlax.rh.persistencia.FuncionEntity;
import mx.gob.saludtlax.rh.persistencia.FuncionRepository;
import mx.gob.saludtlax.rh.persistencia.GlobalEntity;
import mx.gob.saludtlax.rh.persistencia.GlobalRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.PadronEventualEntity;
import mx.gob.saludtlax.rh.persistencia.PadronEventualEntity24;
import mx.gob.saludtlax.rh.persistencia.PadronEventualEntity24Repository;
import mx.gob.saludtlax.rh.persistencia.PadronEventualRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasRepository;
import mx.gob.saludtlax.rh.persistencia.SeguroPopularEntity;
import mx.gob.saludtlax.rh.persistencia.SeguroPopularRespository;
import mx.gob.saludtlax.rh.persistencia.ServicioEntity;
import mx.gob.saludtlax.rh.persistencia.ServicioRepository;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.SuplenteAutorizadoEntity;
import mx.gob.saludtlax.rh.persistencia.SuplenteAutorizadoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoJornadaEntity;
import mx.gob.saludtlax.rh.persistencia.TipoJornadaRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.persistencia.VoluntarioEntity;
import mx.gob.saludtlax.rh.persistencia.VoluntarioRepository;
import mx.gob.saludtlax.rh.persistencia.VoluntarioTemporalEntity;
import mx.gob.saludtlax.rh.persistencia.VoluntariosPadronRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumEstatusPuesto;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.voluntarios.EnumEstatusVoluntarios;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 12/10/2016 18:34:12
 */
public class CruceInformacionService {

	@Inject
	private AdscripcionRepository adscripcionRepository;
	@Inject
	private CluesRepository clueRepository;
	@Inject
	private SubadscripcionRepository subadscripcionRepository;
	@Inject
	private ServicioRepository servicioRepository;
	@Inject
	private FuncionRepository funcionRepository;
	@Inject
	private EmpleadoRepository empleadoRepository;
	@Inject
	private GlobalRepository globalRepository;
	@Inject
	private HistorialAcademicoRepository historialAcademicoRepository;
	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;
	@Inject
	private EstatusPuestosRepository estatusPuestoRepository;
	@Inject
	private PadronEventualRepository padronEventualRepository;
	@Inject
	private DatosLaboralesCruceRepository datosLaboralesRepository;
	@Inject
	private EstatusConfiguracionesRepository estatusConfiguracionesRepository;
	@Inject
	private TiposNombramientosRepository nombramientoRepository;
	@Inject
	private PuestoGeneralRepository puestoGeneralRepository;
	@Inject
	private ConfiguracionPresupuestoRepository configuracionPresupuestalRepository;

	@Inject
	private TipoContratacionRepository tipoContratacionRepository;
	@Inject
	private VoluntariosPadronRepository voluntariosPadronRepository;
	@Inject
	private VoluntarioRepository voluntarioRepository;
	@Inject
	private SuplenteAutorizadoRepository suplenteAutorizadoRepository;
	@Inject
	private QuincenasSuplenciasRepository quincenasSuplenciasRepository;
	@Inject
	private DatosPersonalesRepository datosPersonalesRepository;
	@Inject
	private TipoEmpleadoRepository tipoEmpleadoRepository;
	@Inject
	private SeguroPopularRespository seguroPopularRespository;
	@Inject
	private TipoJornadaRepository tipoJornadaRepository;
	@Inject
	private PadronEventualEntity24Repository padronEventualEntity24Repository;
	@Inject
	private CentroResponsabilidadRepository centroResponsabilidadRepository;
	@Inject
	private DatosSuplentesRepository datosSuplentesRepository;
	@Inject
	private BaseFederalRepository baseFederalRepository;

	/**
	 * Cruza la información del archivo global con la tabla de empleados
	 */
	public void globalEmpleados() {
		List<GlobalEntity> globales = globalRepository.consultarGlobales();
		System.out.println("empleados global " + globales.size());

		int contador = 1;
		for (GlobalEntity g : globales) {
			if (g.getIdGlobal() < 3500) {
				System.out.println("procesado" + contador);
				if (empleadoRepository.existeEmpleadoConRfc(g.getRfc())) {
					// Si el empleado existe se actualiza su información de
					// clave
					System.out.println("actualizado");
					EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(g.getRfc());
					empleado.setClaveCobro(g.getClavePresupuestal());
					empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);

					empleadoRepository.actualizar(empleado);

					g.setIdEmpleado(empleado.getIdEmpleado());
				} else {
					System.out.println("creado");
					EmpleadoEntity empleado = new EmpleadoEntity();

					if (!ValidacionUtil.esCadenaVacia(g.getCurp())) {
						if (!empleadoRepository.existeEmpleadoConCurp(g.getCurp())) {

							empleado.setCurp(g.getCurp());
							empleado.setRfc(g.getRfc());
							empleado.setNombre(g.getNombre());
							empleado.setApellidoPaterno(g.getApellidoPaterno());
							empleado.setApellidoMaterno(g.getApellidoMaterno());
							empleado.setNacionalidad("MEXICANA");
							if (g.getSexo().equals("F")) {
								empleado.setIdSexo(EnumTipoSexo.FEMENINO);
							} else if (g.getSexo().equals("H") || g.getSexo().equals("M")) {
								empleado.setIdSexo(EnumTipoSexo.MASCULINO);
							}
							empleado.setDireccionCompleta(g.getDireccion());
							if (g.getEstadoCivil().equals("CASAD9O") || g.getEstadoCivil().equals("CASADA")
									|| g.getEstadoCivil().equals("CASADO")) {
								empleado.setEstadoCivil(EnumEstadoCivil.CASADO);
							} else if (g.getEstadoCivil().equals("DIVORCIADA")
									|| g.getEstadoCivil().equals("DIVORCIADO")
									|| g.getEstadoCivil().equals("DVORCIADA")) {
								empleado.setEstadoCivil(EnumEstadoCivil.DIVORCIADO);
							} else if (g.getEstadoCivil().equals("MADRE SOLTERA")
									|| g.getEstadoCivil().equals("SOLTERA") || g.getEstadoCivil().equals("SOLTERO")) {
								empleado.setEstadoCivil(EnumEstadoCivil.SOLTERO);
							} else if (g.getEstadoCivil().equals("SEPARADA")) {
								empleado.setEstadoCivil(EnumEstadoCivil.SEPARADO);
							} else if (g.getEstadoCivil().equals("UNION LIBRE")) {
								empleado.setEstadoCivil(EnumEstadoCivil.UNION_LIBRE);
							} else if (g.getEstadoCivil().equals("VIUDA") || g.getEstadoCivil().equals("VIUDO")) {
								empleado.setEstadoCivil(EnumEstadoCivil.VIUDO);
							}

							empleado.setTelefono(g.getTelefono());
							empleado.setCorreoElectronico(g.getCorreo());
							empleado.setFechaIngreso(g.getFechaIngreso());
							empleado.setClaveCobro(g.getClavePresupuestal());
							empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
							empleadoRepository.crear(empleado);

							g.setNuevo(Boolean.TRUE);
							g.setIdEmpleado(empleado.getIdEmpleado());
						}
					} else {
						empleado.setCurp(g.getCurp());
						empleado.setRfc(g.getRfc());
						empleado.setNombre(g.getNombre());
						empleado.setApellidoPaterno(g.getApellidoPaterno());
						empleado.setApellidoMaterno(g.getApellidoMaterno());
						empleado.setNacionalidad("MEXICANA");
						if (g.getSexo().equals("F")) {
							empleado.setIdSexo(EnumTipoSexo.FEMENINO);
						} else if (g.getSexo().equals("H") || g.getSexo().equals("M")) {
							empleado.setIdSexo(EnumTipoSexo.MASCULINO);
						}
						empleado.setDireccionCompleta(g.getDireccion());
						if (g.getEstadoCivil().equals("CASAD9O") || g.getEstadoCivil().equals("CASADA")
								|| g.getEstadoCivil().equals("CASADO")) {
							empleado.setEstadoCivil(EnumEstadoCivil.CASADO);
						} else if (g.getEstadoCivil().equals("DIVORCIADA") || g.getEstadoCivil().equals("DIVORCIADO")
								|| g.getEstadoCivil().equals("DVORCIADA")) {
							empleado.setEstadoCivil(EnumEstadoCivil.DIVORCIADO);
						} else if (g.getEstadoCivil().equals("MADRE SOLTERA") || g.getEstadoCivil().equals("SOLTERA")
								|| g.getEstadoCivil().equals("SOLTERO")) {
							empleado.setEstadoCivil(EnumEstadoCivil.SOLTERO);
						} else if (g.getEstadoCivil().equals("SEPARADA")) {
							empleado.setEstadoCivil(EnumEstadoCivil.SEPARADO);
						} else if (g.getEstadoCivil().equals("UNION LIBRE")) {
							empleado.setEstadoCivil(EnumEstadoCivil.UNION_LIBRE);
						} else if (g.getEstadoCivil().equals("VIUDA") || g.getEstadoCivil().equals("VIUDO")) {
							empleado.setEstadoCivil(EnumEstadoCivil.VIUDO);
						}

						empleado.setTelefono(g.getTelefono());
						empleado.setCorreoElectronico(g.getCorreo());
						empleado.setFechaIngreso(g.getFechaIngreso());
						empleado.setClaveCobro(g.getClavePresupuestal());
						empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
						empleadoRepository.crear(empleado);

						g.setNuevo(Boolean.TRUE);
						g.setIdEmpleado(empleado.getIdEmpleado());
					}

				}

				globalRepository.actualizar(g);
				contador++;
			}

		}

	}

	public void cruzarInformacionLaboralSuplentes() {
		List<SuplenteAutorizadoEntity> suplentesAutorizados = suplenteAutorizadoRepository.consultarTodos();
		if (!suplentesAutorizados.isEmpty()) {
			int contador = 1;
			for (SuplenteAutorizadoEntity s : suplentesAutorizados) {
				System.out.println("procesados " + contador);
				if (s.getEmpleado().getTipoEmpleado().getIdTipoEmpleado() != EnumTipoEmpleado.EMPLEADO) {
					List<DatosLaboralesCruceEntity> laborales = datosLaboralesRepository
							.obtenerDatoLaboralEventual(s.getEmpleado().getRfc());
					if (!laborales.isEmpty()) {
						if (laborales.size() == 1) {
							DatosLaboralesCruceEntity laboral = laborales.get(0);
							PuestoGeneralEntity puesto = puestoGeneralRepository
									.puestoPorClave(laboral.getCodigoPuesto());
							s.setNumeroLaboral(laboral.getIdDatoLaboral());
							s.setPuesto(puesto);
							s.setDependencia(laboral.getDependencia());
							s.setProyecto(laboral.getProyecto());
							s.setUnidadResponsable(laboral.getUnidadResponsable());
							suplenteAutorizadoRepository.actualizar(s);
							List<QuincenasSuplenciasEntity> quincenas = quincenasSuplenciasRepository
									.consultarQuincenasIdSuplente(s.getIdSuplenteAutorizado());

							for (QuincenasSuplenciasEntity e : quincenas) {
								e.setProyecto(laboral.getProyecto());
								e.setDependencia(laboral.getDependencia());
								e.setUnidadResponsable(laboral.getUnidadResponsable());
								quincenasSuplenciasRepository.actualizar(e);
							}
						}
					}

				}
				contador++;
			}

		}

	}

	public void cruzarSuplencias() {
		List<PadronEventualEntity> suplentes = padronEventualRepository.consultarSuplentes();
		TipoEmpleadoEntity tipoEmpleadoSuplente = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);
		TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.EMPLEADO);
		System.out.println("suplentes " + suplentes.size());
		int contador = 1;
		if (!suplentes.isEmpty()) {
			for (PadronEventualEntity suplente : suplentes) {
				System.out.println("procesado " + contador);
				EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(suplente.getRfc().trim());
				if (empleado != null) {
					// Si no tiene puesto asignado se modifica a tipo suplente
					if (inventarioVacanteRepository.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado())) {
						empleado.setTipoEmpleado(tipoEmpleado);
					} else {
						empleado.setTipoEmpleado(tipoEmpleadoSuplente);
					}
					empleadoRepository.actualizar(empleado);

					SuplenteAutorizadoEntity suplenteAutorizado = new SuplenteAutorizadoEntity();
					suplenteAutorizado.setCentroResponsabilidad(suplente.getIdCentroResponsabilidad());
					suplenteAutorizado.setEmpleado(empleado);
					suplenteAutorizado.setEstatus("ACTIVO");
					suplenteAutorizado.setFechaAlta(FechaUtil.fechaActual());
					suplenteAutorizado.setHoraAlta(FechaUtil.horaActual());
					suplenteAutorizado.setMetodoPago(2);
					suplenteAutorizado.setNumero(" ");
					suplenteAutorizadoRepository.crear(suplenteAutorizado);

					QuincenasSuplenciasEntity q = new QuincenasSuplenciasEntity();
					q.setEjercicioFiscal(2016);
					q.setEstatus(EnumEstatusSuplencia.CERRADA);
					q.setIdMes(10);
					q.setNumeroQuincena(20);
					q.setSuplente(suplenteAutorizado);
					q.setTotal(suplente.getSueldo());
					quincenasSuplenciasRepository.crear(q);

					suplente.setSuplencia(true);
					suplente.setIdQuincenaSuplencia(q.getIdQuincenaSuplencia());
					padronEventualRepository.actualizar(suplente);

					contador++;
				}

			}
		}

	}

	public String actualizarEstatusSuplentes() {
		List<SuplenteAutorizadoEntity> suplentes = suplenteAutorizadoRepository.consultarTodos();
		String m = "Han sido procesados " + suplentes.size() + " suplentes.";
		int contador = 1;
		for (SuplenteAutorizadoEntity s : suplentes) {
			System.out.println("procesados" + contador);
			PadronEventualEntity24 eventual = padronEventualEntity24Repository
					.obtenerSuplentePorRfc(s.getEmpleado().getRfc().trim());
			if (eventual == null) {
				s.setEstatus(EnumEstatusEmpleado.INACTIVO);
				suplenteAutorizadoRepository.actualizar(s);
			} else {
				eventual.setActivo(true);
				padronEventualEntity24Repository.actualizar(eventual);
			}
			contador++;
		}
		return m;
	}

	public void cruzarSuplencias24() {
		List<PadronEventualEntity24> suplentes = padronEventualEntity24Repository.consultarPadronSuplentesNuevos();
		TipoEmpleadoEntity tipoEmpleadoSuplente = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);
		TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.EMPLEADO);
		System.out.println("suplentes " + suplentes.size());
		int contador = 1;
		if (!suplentes.isEmpty()) {

			for (PadronEventualEntity24 suplente : suplentes) {
				System.out.println("procesado " + contador);
				EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(suplente.getRfc().trim());
				if (empleado != null) {
					// Si no tiene puesto asignado se modifica a tipo suplente
					if (inventarioVacanteRepository.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado())) {
						empleado.setTipoEmpleado(tipoEmpleado);
					} else {
						empleado.setTipoEmpleado(tipoEmpleadoSuplente);
					}
					empleadoRepository.actualizar(empleado);

					CentroResponsabilidadEntity c = centroResponsabilidadRepository
							.obtenerCentroResponsabilidad(suplente.getClaveCentroResponsabilidad());
					SuplenteAutorizadoEntity suplenteAutorizado = new SuplenteAutorizadoEntity();
					suplenteAutorizado.setCentroResponsabilidad(c);
					suplenteAutorizado.setEmpleado(empleado);
					suplenteAutorizado.setEstatus("ACTIVO");
					suplenteAutorizado.setFechaAlta(FechaUtil.fechaActual());
					suplenteAutorizado.setHoraAlta(FechaUtil.horaActual());
					suplenteAutorizado.setMetodoPago(2);
					suplenteAutorizado.setNumero(" ");
					suplenteAutorizadoRepository.crear(suplenteAutorizado);

					suplente.setActivo(true);
					padronEventualEntity24Repository.actualizar(suplente);

					contador++;
				}

			}
		}

	}

	public void crearEmpleado24() {

	}

	public void crearEmpleados() {

		List<DatosPersonalesEntity> personales = datosPersonalesRepository.consultarDatosPersonalesNuevos();
		TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.EMPLEADO);
		System.out.println("total de empleados nuevos" + personales.size());
		int contador = 1;
		for (DatosPersonalesEntity g : personales) {
			if (!empleadoRepository.existeEmpleadoConRfc(g.getRfc().trim())) {
				EmpleadoEntity empleado = new EmpleadoEntity();
				empleado.setCurp(" ");
				empleado.setRfc(g.getRfc());
				empleado.setNombre(g.getNombre());
				empleado.setApellidoPaterno(g.getApellidoPaterno());
				empleado.setApellidoMaterno(g.getApellidoMaterno());
				empleado.setNombreCompleto(g.getNombre() + " " + g.getApellidoPaterno() + " " + g.getApellidoMaterno());
				empleado.setNacionalidad("MEXICANA");
				if (g.getSexo().equals("F")) {
					empleado.setIdSexo(EnumTipoSexo.FEMENINO);
				} else if (g.getSexo().equals("M")) {
					empleado.setIdSexo(EnumTipoSexo.MASCULINO);
				}
				empleado.setTelefono(g.getTelefono());
				empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
				empleado.setTipoEmpleado(tipoEmpleado);
				empleadoRepository.crear(empleado);
				contador++;
				System.out.println("procesados" + contador);
			}
		}

	}

	public void crearEmpleados2() {

		List<PadronEventualEntity24> eventuales = padronEventualEntity24Repository.consultarPadronSuplentesNuevos();
		TipoEmpleadoEntity tipoEmpleadoSuplente = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);
		System.out.println("total de empleados sin registro" + eventuales.size());
		int contador = 1;
		if (!eventuales.isEmpty()) {
			for (PadronEventualEntity24 p : eventuales) {

				DatosPersonalesEntity g = datosPersonalesRepository.obtenerDatoPersonalPorRfc(p.getRfc());
				if (g != null) {
					if (!empleadoRepository.existeEmpleadoConRfc(g.getRfc().trim())) {
						EmpleadoEntity empleado = new EmpleadoEntity();
						empleado.setCurp(" ");
						empleado.setRfc(g.getRfc());
						empleado.setNombre(g.getNombre());
						empleado.setApellidoPaterno(g.getApellidoPaterno());
						empleado.setApellidoMaterno(g.getApellidoMaterno());
						empleado.setNombreCompleto(
								g.getNombre() + " " + g.getApellidoPaterno() + " " + g.getApellidoMaterno());
						empleado.setNacionalidad("MEXICANA");
						if (g.getSexo().equals("F")) {
							empleado.setIdSexo(EnumTipoSexo.FEMENINO);
						} else if (g.getSexo().equals("H") || g.getSexo().equals("M")) {
							empleado.setIdSexo(EnumTipoSexo.MASCULINO);
						}
						empleado.setTelefono(g.getTelefono());
						empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
						empleado.setTipoEmpleado(tipoEmpleadoSuplente);
						empleadoRepository.crear(empleado);
						contador++;
						System.out.println("procesados" + contador);
					}

				}

			}
		}
	}

	public void cruzarEventual() {

		List<PadronEventualEntity> eventuales = padronEventualRepository.consultarEventuales();

		System.out.println("eventuales sin puesto" + eventuales.size());
		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);

		EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);

		if (!eventuales.isEmpty()) {
			int contador = 1;

			for (PadronEventualEntity padron : eventuales) {

				if (padron.getTipoContratacion().getId() == EnumTipoContratacion.CONTRATO_ESTATAL) {

					// Consultar que el rfc tenga asignado un empleado
					EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(padron.getRfc().trim());

					// Si encuentra al empleado
					if (empleado != null) {

						// Valida si tiene puesto asignado.
						boolean tieneEmpleadoPuestoAsignado = inventarioVacanteRepository
								.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado());
						System.out.println("tienePuesto asignado" + tieneEmpleadoPuestoAsignado);

						if (!tieneEmpleadoPuestoAsignado) {
							// Si no tiene puesto asignado lo procesa
							List<DatosLaboralesCruceEntity> laborales = datosLaboralesRepository
									.obtenerDatoLaboralEventual(padron.getRfc().trim());

							if (!laborales.isEmpty()) {
								if (laborales.size() > 1) {
									// Si tiene mas de un dato laboral no lo
									// procesa.
									padron.setDuplicadoSiif(true);
									padronEventualRepository.actualizar(padron);
									System.out.println("duplicado siif" + padron.getRfc());
								} else {
									// Si no está duplicado en el siif lo
									// procesa
									System.out.println("procesado" + contador);
									System.out.println(padron.getRfc());

									DatosLaboralesCruceEntity laboral = laborales.get(0);
									PuestoGeneralEntity puesto = puestoGeneralRepository
											.puestoPorClave(laboral.getCodigoPuesto());
									TiposNombramientosEntity tipoNombramiento = nombramientoRepository
											.nombramientoPorClave(laboral.getNombramiento());
									ConfiguracionPresupuestoEntity conf = new ConfiguracionPresupuestoEntity();
									conf.setCuenta(null);
									conf.setDependencia(laboral.getDependencia());
									conf.setEmpleado(empleado);
									conf.setEstatus(estatus);
									conf.setFechaAltaConfiguracion(FechaUtil.fechaActual());
									conf.setFuenteFinanciamiento(laboral.getFuenteFinanciamiento());
									conf.setNombramiento(tipoNombramiento);
									conf.setNumeroEmpleado(laboral.getIdEmpleadoDatosLaborales());
									conf.setProyecto(laboral.getProyecto());
									conf.setPuesto(puesto);
									conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
									conf.setSueldo(padron.getSueldo());
									conf.setSueldo01(padron.getSueldo1());
									conf.setSueldo14(padron.getSueldo14());
									conf.setTipoContratacion(padron.getTipoContratacion());
									conf.setTipoRecurso(laboral.getTipoRecurso());
									conf.setUnidadResponsable(laboral.getUnidadResponsable());
									conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
									conf.setIdPlaza(laboral.getIdPlaza());
									configuracionPresupuestalRepository.crear(conf);

									empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
									empleadoRepository.actualizar(empleado);
									// Generar folios
									Integer ultimoFolio = inventarioVacanteRepository
											.ultimoFolioVacanteContratacion(padron.getTipoContratacion().getId());
									Integer siguienteNumeroVacante = 1;
									if (ultimoFolio != null) {
										siguienteNumeroVacante = ultimoFolio + 1;
									}
									String folioVacante = generarFolioVacante(siguienteNumeroVacante,
											padron.getTipoContratacion().getCodigo());

									// Crear puesto
									InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
									puestoEmpleado.setTipoContratacion(padron.getTipoContratacion());
									puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
									puestoEmpleado.setCodigoVacante(padron.getTipoContratacion().getCodigo());
									puestoEmpleado.setFolioVacante(folioVacante);
									puestoEmpleado.setEstatus(estatusPuesto);
									puestoEmpleado.setDisponible("NO");
									puestoEmpleado.setEmpleadoActivo(empleado);
									puestoEmpleado.setPrograma(padron.getPrograma());

									// Si tiene un empleado consultar su
									// información
									// global
									GlobalEntity global = globalRepository.obtenerGlobalRfc(empleado.getRfc());

									if (global != null) {

										if (!ValidacionUtil.esCadenaVacia(global.getAdscripcion())) {
											AdscripcionEntity adscripcion = adscripcionRepository
													.obtenerAdscripcionPorNombre(global.getAdscripcion());
											puestoEmpleado.setAdscripcion(adscripcion);
										}

										if (!ValidacionUtil.esCadenaVacia(global.getSubadscripcion())) {
											SubadscripcionEntity subadscripcion = subadscripcionRepository
													.obtenerSubadscripcionPorDescripcion(global.getSubadscripcion());
											puestoEmpleado.setSubadscripcion(subadscripcion);
										}
										if (!ValidacionUtil.esCadenaVacia(global.getServicioLabora())) {
											ServicioEntity servicio = servicioRepository
													.obtenerServicioPorDescripcion(global.getServicioLabora());
											puestoEmpleado.setServicio(servicio);
										}

										if (!ValidacionUtil.esCadenaVacia(global.getFuncion())) {
											FuncionEntity funcion = funcionRepository
													.obtenerFuncionPorDescripcion(global.getFuncion());
											puestoEmpleado.setFuncion(funcion);
										}

										if (!ValidacionUtil.esCadenaVacia(global.getClueReal())) {
											CluesEntity clue = clueRepository.obtenerCluePorClave(global.getClueReal());
											if (clue == null) {
												puestoEmpleado.setClue(clue);
											}
										}

									}

									inventarioVacanteRepository.crear(puestoEmpleado);
									padron.setIdInventario(puestoEmpleado.getIdVacante());
									padron.setConPuesto(true);
									padronEventualRepository.actualizar(padron);

									if (global != null) {
										global.setProcesado(true);
										global.setIdInventarioVacante(puestoEmpleado.getIdVacante());
										globalRepository.actualizar(global);
									}

									puestoEmpleado.setConfiguracion(conf);
									inventarioVacanteRepository.actualizar(puestoEmpleado);

									contador++;

								}
							} else {
								System.out.println("no esta en siif" + padron.getRfc());
							}

						} else {
							System.out.println("ya tiene asignado puesto " + padron.getRfc());
						}

					} else {
						System.out.println("no se encontro rfc" + padron.getRfc());
					}
				} else {
					System.out.println("no es contrato federal");
				}

			}
		}
	}

	public void crearPuestosEmpleados() {

		List<GlobalEntity> puestosGlobal = globalRepository
				.obtenerGlobalPorContratacion(EnumTipoContratacion.HOMOLOGADOS);
		System.out.println("puestos global" + puestosGlobal.size());
		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);
		EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);
		// TipoContratacionEntity tipoContratacion = tipoContratacionRepository
		// .obtenerPorId(EnumTipoContratacion.CONFIANZA);

		int contador = 1;
		int puestos = 1;

		for (GlobalEntity global : puestosGlobal) {
			System.out.println("procesado" + contador);

			// Consultar si el empleado está registrado.
			EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(global.getRfc().trim());
			if (empleado != null) {

				boolean tieneEmpleadoPuestoAsignado = inventarioVacanteRepository
						.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado());

				System.out.println("tienePuesto asignado" + tieneEmpleadoPuestoAsignado);

				if (!tieneEmpleadoPuestoAsignado) {

					List<DatosLaboralesCruceEntity> laborales = datosLaboralesRepository
							.obtenerDatoLaboral(global.getRfc().trim());

					// Si tiene configuración presupuestal se procesa
					if (!laborales.isEmpty()) {
						if (laborales.size() > 1) {
							global.setDuplicadosSiif(true);
							globalRepository.actualizar(global);
						} else {
							DatosLaboralesCruceEntity laboral = laborales.get(0);
							PuestoGeneralEntity puesto = puestoGeneralRepository
									.puestoPorClave(laboral.getCodigoPuesto());
							TiposNombramientosEntity tipoNombramiento = nombramientoRepository
									.nombramientoPorClave(laboral.getNombramiento());
							ConfiguracionPresupuestoEntity conf = new ConfiguracionPresupuestoEntity();

							conf.setCuenta(null);
							conf.setDependencia(laboral.getDependencia());
							conf.setEmpleado(empleado);
							conf.setEstatus(estatus);
							conf.setFechaAltaConfiguracion(FechaUtil.fechaActual());
							conf.setFuenteFinanciamiento(laboral.getFuenteFinanciamiento());
							conf.setNombramiento(tipoNombramiento);
							conf.setNumeroEmpleado(laboral.getIdEmpleadoDatosPersonales());
							conf.setProyecto(laboral.getProyecto());
							conf.setPuesto(puesto);
							conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
							conf.setTipoContratacion(global.getTipoContratacion());
							conf.setTipoRecurso(laboral.getTipoRecurso());
							conf.setUnidadResponsable(laboral.getUnidadResponsable());
							conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
							conf.setIdPlaza(laboral.getIdPlaza());
							configuracionPresupuestalRepository.crear(conf);

							empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
							empleadoRepository.actualizar(empleado);
							// Generar folios
							Integer ultimoFolio = inventarioVacanteRepository
									.ultimoFolioVacanteContratacion(global.getTipoContratacion().getId());
							Integer siguienteNumeroVacante = 1;
							if (ultimoFolio != null) {
								siguienteNumeroVacante = ultimoFolio + 1;
							}
							String folioVacante = generarFolioVacante(siguienteNumeroVacante,
									global.getTipoContratacion().getCodigo());

							// Crear puesto
							InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
							puestoEmpleado.setTipoContratacion(global.getTipoContratacion());
							puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
							puestoEmpleado.setCodigoVacante(global.getTipoContratacion().getCodigo());
							puestoEmpleado.setFolioVacante(folioVacante);
							puestoEmpleado.setEstatus(estatusPuesto);
							puestoEmpleado.setDisponible("NO");
							puestoEmpleado.setEmpleadoActivo(empleado);
							puestoEmpleado.setConfiguracion(conf);

							if (!ValidacionUtil.esCadenaVacia(global.getAdscripcion())) {
								AdscripcionEntity adscripcion = adscripcionRepository
										.obtenerAdscripcionPorNombre(global.getAdscripcion());
								puestoEmpleado.setAdscripcion(adscripcion);
							}

							if (!ValidacionUtil.esCadenaVacia(global.getSubadscripcion())) {
								SubadscripcionEntity subadscripcion = subadscripcionRepository
										.obtenerSubadscripcionPorDescripcion(global.getSubadscripcion());
								puestoEmpleado.setSubadscripcion(subadscripcion);
							}
							if (!ValidacionUtil.esCadenaVacia(global.getServicioLabora())) {
								ServicioEntity servicio = servicioRepository
										.obtenerServicioPorDescripcion(global.getServicioLabora());
								puestoEmpleado.setServicio(servicio);
							}

							if (!ValidacionUtil.esCadenaVacia(global.getFuncion())) {
								FuncionEntity funcion = funcionRepository
										.obtenerFuncionPorDescripcion(global.getFuncion());
								puestoEmpleado.setFuncion(funcion);
							}

							if (!ValidacionUtil.esCadenaVacia(global.getClueReal())) {
								CluesEntity clue = clueRepository.obtenerCluePorClave(global.getClueReal());
								if (clue == null) {
									puestoEmpleado.setClue(clue);
								}
							}

							inventarioVacanteRepository.crear(puestoEmpleado);

							global.setIdInventarioVacante(puestoEmpleado.getIdVacante());
							global.setProcesado(true);
							globalRepository.actualizar(global);

							System.out.println("puestos creados" + puestos);
							puestos++;
						}

					}

					else {
						System.out.println("no tiene laborales" + global.getRfc());
					}

				}
			} else {
				System.out.println("no se encontró empleado con rfc" + global.getRfc());
			}

			contador++;
		}

	}

	public void crearPuestoEmpleado() {

		GlobalEntity global = globalRepository.obtenerGlobalRfc("BAAM890529QJ3");

		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);
		EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);
		// TipoContratacionEntity tipoContratacion = tipoContratacionRepository
		// .obtenerPorId(EnumTipoContratacion.CONFIANZA);

		// Consultar si el empleado está registrado.
		EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(global.getRfc().trim());
		if (empleado != null) {

			boolean tieneEmpleadoPuestoAsignado = inventarioVacanteRepository
					.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado());

			System.out.println("tienePuesto asignado" + tieneEmpleadoPuestoAsignado);

			if (!tieneEmpleadoPuestoAsignado) {

				List<DatosLaboralesCruceEntity> laborales = datosLaboralesRepository
						.obtenerDatoLaboral(global.getRfc().trim());

				// Si tiene configuración presupuestal se procesa
				if (!laborales.isEmpty()) {
					if (laborales.size() > 1) {
						global.setDuplicadosSiif(true);
						globalRepository.actualizar(global);
					} else {
						DatosLaboralesCruceEntity laboral = laborales.get(0);
						PuestoGeneralEntity puesto = puestoGeneralRepository.puestoPorClave(laboral.getCodigoPuesto());
						TiposNombramientosEntity tipoNombramiento = nombramientoRepository
								.nombramientoPorClave(laboral.getNombramiento());
						ConfiguracionPresupuestoEntity conf = new ConfiguracionPresupuestoEntity();

						conf.setCuenta(null);
						conf.setDependencia(laboral.getDependencia());
						conf.setEmpleado(empleado);
						conf.setEstatus(estatus);
						conf.setFechaAltaConfiguracion(FechaUtil.fechaActual());
						conf.setFuenteFinanciamiento(laboral.getFuenteFinanciamiento());
						conf.setNombramiento(tipoNombramiento);
						conf.setNumeroEmpleado(laboral.getIdEmpleadoDatosPersonales());
						conf.setProyecto(laboral.getProyecto());
						conf.setPuesto(puesto);
						conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
						conf.setTipoContratacion(global.getTipoContratacion());
						conf.setTipoRecurso(laboral.getTipoRecurso());
						conf.setUnidadResponsable(laboral.getUnidadResponsable());
						conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
						conf.setIdPlaza(laboral.getIdPlaza());
						configuracionPresupuestalRepository.crear(conf);

						empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
						empleadoRepository.actualizar(empleado);
						// Generar folios
						Integer ultimoFolio = inventarioVacanteRepository
								.ultimoFolioVacanteContratacion(global.getTipoContratacion().getId());
						Integer siguienteNumeroVacante = 1;
						if (ultimoFolio != null) {
							siguienteNumeroVacante = ultimoFolio + 1;
						}
						String folioVacante = generarFolioVacante(siguienteNumeroVacante,
								global.getTipoContratacion().getCodigo());

						// Crear puesto
						InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
						puestoEmpleado.setTipoContratacion(global.getTipoContratacion());
						puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
						puestoEmpleado.setCodigoVacante(global.getTipoContratacion().getCodigo());
						puestoEmpleado.setFolioVacante(folioVacante);
						puestoEmpleado.setEstatus(estatusPuesto);
						puestoEmpleado.setDisponible("NO");
						puestoEmpleado.setEmpleadoActivo(empleado);
						puestoEmpleado.setConfiguracion(conf);
						puestoEmpleado.setSeguroPopular(true);// Solo cuando el
																// empleado sea
																// del seguro
																// popular

						if (!ValidacionUtil.esCadenaVacia(global.getAdscripcion())) {
							AdscripcionEntity adscripcion = adscripcionRepository
									.obtenerAdscripcionPorNombre(global.getAdscripcion());
							puestoEmpleado.setAdscripcion(adscripcion);
						}

						if (!ValidacionUtil.esCadenaVacia(global.getSubadscripcion())) {
							SubadscripcionEntity subadscripcion = subadscripcionRepository
									.obtenerSubadscripcionPorDescripcion(global.getSubadscripcion());
							puestoEmpleado.setSubadscripcion(subadscripcion);
						}
						if (!ValidacionUtil.esCadenaVacia(global.getServicioLabora())) {
							ServicioEntity servicio = servicioRepository
									.obtenerServicioPorDescripcion(global.getServicioLabora());
							puestoEmpleado.setServicio(servicio);
						}

						if (!ValidacionUtil.esCadenaVacia(global.getFuncion())) {
							FuncionEntity funcion = funcionRepository.obtenerFuncionPorDescripcion(global.getFuncion());
							puestoEmpleado.setFuncion(funcion);
						}

						if (!ValidacionUtil.esCadenaVacia(global.getClueReal())) {
							CluesEntity clue = clueRepository.obtenerCluePorClave(global.getClueReal());
							if (clue == null) {
								puestoEmpleado.setClue(clue);
							}
						}

						inventarioVacanteRepository.crear(puestoEmpleado);

						global.setIdInventarioVacante(puestoEmpleado.getIdVacante());
						global.setProcesado(true);
						globalRepository.actualizar(global);

					}

				}

				else {
					System.out.println("no tiene laborales" + global.getRfc());
				}

			}
		} else {
			System.out.println("no se encontró empleado con rfc" + global.getRfc());
		}

	}

	private String generarFolioVacante(Integer siguienteNumeroVacante, String codigoContratacion) {
		String folioVacante = "";

		if (siguienteNumeroVacante < 10) {
			folioVacante = codigoContratacion + "-00" + siguienteNumeroVacante;
		} else if (siguienteNumeroVacante < 100) {
			folioVacante = codigoContratacion + "-0" + siguienteNumeroVacante;
		} else {
			folioVacante = codigoContratacion + "-" + siguienteNumeroVacante;
		}

		return folioVacante;
	}

	public void actualizarPerfilEmpleado() {

		List<EmpleadoEntity> empleadosActivos = empleadoRepository
				.consultarEmpleadosPorEstatus(EnumEstatusEmpleado.ACTIVO);
		System.out.println("empleados activos " + empleadosActivos.size());

		int contador = 1;

		if (!empleadosActivos.isEmpty()) {
			for (EmpleadoEntity e : empleadosActivos) {
				List<String> profesiones = historialAcademicoRepository.consultarProfesionesEmpleado(e.getIdEmpleado());

				if (!profesiones.isEmpty()) {
					System.out.println("empleados actualizados" + contador);
					StringBuilder perfilEmpleado = new StringBuilder();
					for (String profesion : profesiones) {
						if (!ValidacionUtil.esCadenaVacia(profesion)) {
							if (!perfilEmpleado.toString().contains(profesion)) {
								perfilEmpleado.append(profesion).append("/");
							}

						}
					}

					if (!perfilEmpleado.toString().trim().isEmpty()) {
						int ultimaPosicion = perfilEmpleado.toString().length() - 1;
						e.setPerfilAcademico(perfilEmpleado.toString().substring(0, ultimaPosicion));
						empleadoRepository.actualizar(e);
						contador++;
					}

				}

			}
		}

	}

	protected void procesarVoluntarios() {
		List<VoluntarioTemporalEntity> voluntarios = voluntariosPadronRepository.consultarTodos();
		System.out.println("total voluntarios " + voluntarios.size());
		TipoContratacionEntity tipoContratacion = tipoContratacionRepository
				.obtenerPorId(EnumTipoContratacion.VOLUNTARIOS);
		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);

		int contador = 1;
		for (VoluntarioTemporalEntity entity : voluntarios) {
			System.out.println("procesados" + contador);

			VoluntarioEntity v = new VoluntarioEntity();
			v.setNombreCompleto(entity.getNombre());
			v.setCurp(entity.getCurp());
			v.setEstatus(EnumEstatusVoluntarios.ACTIVO);

			v.setNumeroCuenta(entity.getNumeroCuenta());
			v.setRfc(entity.getRfc());
			v.setSueldo(entity.getSueldo());
			voluntarioRepository.crear(v);

			// Generar folios
			Integer ultimoFolio = inventarioVacanteRepository.ultimoFolioVacanteContratacion(tipoContratacion.getId());
			System.out.println("ultimo folio " + ultimoFolio);
			Integer siguienteNumeroVacante = 1;
			if (ultimoFolio != null) {
				siguienteNumeroVacante = ultimoFolio + 1;
			}
			String folioVacante = generarFolioVacante(siguienteNumeroVacante, tipoContratacion.getCodigo());

			System.out.println("siguientNumero " + siguienteNumeroVacante);
			// Crear puesto
			InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
			puestoEmpleado.setTipoContratacion(tipoContratacion);
			puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
			puestoEmpleado.setCodigoVacante(tipoContratacion.getCodigo());
			puestoEmpleado.setFolioVacante(folioVacante);
			puestoEmpleado.setEstatus(estatusPuesto);
			puestoEmpleado.setDisponible("NO");
			puestoEmpleado.setVoluntario(v);
			puestoEmpleado.setAdscripcion(entity.getAdscripcion());
			puestoEmpleado.setSubadscripcion(entity.getSubadscripcion());
			puestoEmpleado.setServicio(entity.getServicio());
			puestoEmpleado.setFuncion(entity.getFuncion());
			inventarioVacanteRepository.crear(puestoEmpleado);

			contador++;
		}
	}

	public void marcarSeguroPopular() {
		List<SeguroPopularEntity> seguroPopular = seguroPopularRespository.consultarTodos();
		System.out.println("total seguro popular " + seguroPopular.size());
		int contador = 1;
		for (SeguroPopularEntity s : seguroPopular) {

			InventarioVacanteEntity i = inventarioVacanteRepository.obtenerPuestoPorRFC(s.getRfc());
			if (i != null) {
				System.out.println("procesados" + contador);
				s.setIdInventario(i.getIdVacante());
				seguroPopularRespository.actualizar(s);
				i.setSeguroPopular(true);
				inventarioVacanteRepository.actualizar(i);
				contador++;
			}

			System.out.println("----");
		}

	}

	public void actualizarSeguroPopular() {
		List<SeguroPopularEntity> seguroPopular = seguroPopularRespository.consultarTodos();

		System.out.println("total seguro popular " + seguroPopular.size());
		int contador = 1;
		for (SeguroPopularEntity s : seguroPopular) {
			System.out.println("procesados" + contador);
			InventarioVacanteEntity i = inventarioVacanteRepository.obtenerPorId(s.getIdInventario());

			if (i != null) {
				TipoJornadaEntity tipoJornada = tipoJornadaRepository
						.obtenerJornadaPorDescripcion(s.getTipoJornada().trim());

				SubadscripcionEntity subadscripcion = subadscripcionRepository
						.obtenerSubadscripcionPorDescripcion(s.getDepartamento());

				if (subadscripcion == null) {
					SubadscripcionEntity nuevaSubadscripcion = new SubadscripcionEntity();
					nuevaSubadscripcion.setSubadscripcion(s.getDepartamento());
					subadscripcionRepository.crear(nuevaSubadscripcion);
					i.setSubadscripcion(nuevaSubadscripcion);
				} else {
					i.setSubadscripcion(subadscripcion);
				}
				i.setTipoJornada(tipoJornada);
				inventarioVacanteRepository.actualizar(i);

			}

			contador++;
		}

	}

	public void complementarSeguroPopular() {
		List<SeguroPopularEntity> faltantes = seguroPopularRespository.consultarSeguroPopularSinPuesto();
		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);
		EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);
		int contador = 0;
		for (SeguroPopularEntity s : faltantes) {
			System.out.println("procesados" + contador);
			GlobalEntity g = globalRepository.obtenerGlobalRfc(s.getRfc());
			if (g != null) {
				if (g.getTipoContratacion().getId() == EnumTipoContratacion.REGULARIZADOS) {
					System.out.println("rfc ==========" + g.getRfc());
					// Validar si el empleado existe
					EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(g.getRfc().trim());
					if (empleado != null) {
						boolean tieneEmpleadoPuestoAsignado = inventarioVacanteRepository
								.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado());

						System.out.println("tienePuesto asignado" + tieneEmpleadoPuestoAsignado);

						if (!tieneEmpleadoPuestoAsignado) {
							DatosLaboralesCruceEntity laboral = datosLaboralesRepository.obtenerDatoLaboral(s.getRfc(),
									"R");
							if (laboral != null) {
								PuestoGeneralEntity puesto = puestoGeneralRepository
										.puestoPorClave(laboral.getCodigoPuesto());
								TiposNombramientosEntity tipoNombramiento = nombramientoRepository
										.nombramientoPorClave(laboral.getNombramiento());
								ConfiguracionPresupuestoEntity conf = new ConfiguracionPresupuestoEntity();

								conf.setCuenta(null);
								conf.setDependencia(laboral.getDependencia());
								conf.setEmpleado(empleado);
								conf.setEstatus(estatus);
								conf.setFechaAltaConfiguracion(FechaUtil.fechaActual());
								conf.setFuenteFinanciamiento(laboral.getFuenteFinanciamiento());
								conf.setNombramiento(tipoNombramiento);
								conf.setNumeroEmpleado(laboral.getIdEmpleadoDatosPersonales());
								conf.setProyecto(laboral.getProyecto());
								conf.setPuesto(puesto);
								conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
								conf.setTipoContratacion(g.getTipoContratacion());
								conf.setTipoRecurso(laboral.getTipoRecurso());
								conf.setUnidadResponsable(laboral.getUnidadResponsable());
								conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
								conf.setIdPlaza(laboral.getIdPlaza());
								configuracionPresupuestalRepository.crear(conf);

								empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
								empleadoRepository.actualizar(empleado);
								// Generar folios
								Integer ultimoFolio = inventarioVacanteRepository
										.ultimoFolioVacanteContratacion(g.getTipoContratacion().getId());
								Integer siguienteNumeroVacante = 1;
								if (ultimoFolio != null) {
									siguienteNumeroVacante = ultimoFolio + 1;
								}
								String folioVacante = generarFolioVacante(siguienteNumeroVacante,
										g.getTipoContratacion().getCodigo());

								// Crear puesto
								InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
								puestoEmpleado.setTipoContratacion(g.getTipoContratacion());
								puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
								puestoEmpleado.setCodigoVacante(g.getTipoContratacion().getCodigo());
								puestoEmpleado.setFolioVacante(folioVacante);
								puestoEmpleado.setEstatus(estatusPuesto);
								puestoEmpleado.setDisponible("NO");
								puestoEmpleado.setEmpleadoActivo(empleado);
								puestoEmpleado.setConfiguracion(conf);

								if (!ValidacionUtil.esCadenaVacia(g.getAdscripcion())) {
									AdscripcionEntity adscripcion = adscripcionRepository
											.obtenerAdscripcionPorNombre(g.getAdscripcion());
									puestoEmpleado.setAdscripcion(adscripcion);
								}

								if (!ValidacionUtil.esCadenaVacia(g.getSubadscripcion())) {
									SubadscripcionEntity subadscripcion = subadscripcionRepository
											.obtenerSubadscripcionPorDescripcion(g.getSubadscripcion());
									if (subadscripcion == null) {
										SubadscripcionEntity nuevaSubadscripcion = new SubadscripcionEntity();
										nuevaSubadscripcion.setSubadscripcion(s.getDepartamento());
										subadscripcionRepository.crear(nuevaSubadscripcion);
										puestoEmpleado.setSubadscripcion(nuevaSubadscripcion);
									} else {
										puestoEmpleado.setSubadscripcion(subadscripcion);
									}
								}
								if (!ValidacionUtil.esCadenaVacia(g.getServicioLabora())) {
									ServicioEntity servicio = servicioRepository
											.obtenerServicioPorDescripcion(g.getServicioLabora());
									puestoEmpleado.setServicio(servicio);
								}

								if (!ValidacionUtil.esCadenaVacia(g.getFuncion())) {
									FuncionEntity funcion = funcionRepository
											.obtenerFuncionPorDescripcion(g.getFuncion());
									puestoEmpleado.setFuncion(funcion);
								}

								if (!ValidacionUtil.esCadenaVacia(g.getClueReal())) {
									CluesEntity clue = clueRepository.obtenerCluePorClave(g.getClueReal());
									if (clue != null) {
										puestoEmpleado.setClue(clue);
									}
								}

								TipoJornadaEntity tipoJornada = tipoJornadaRepository
										.obtenerJornadaPorDescripcion(s.getTipoJornada().trim());
								puestoEmpleado.setTipoJornada(tipoJornada);
								puestoEmpleado.setSeguroPopular(true);

								inventarioVacanteRepository.crear(puestoEmpleado);

								g.setIdInventarioVacante(puestoEmpleado.getIdVacante());
								g.setProcesado(true);
								g.setDobleLaboral(true);
								g.setIdLaboralPadre(laboral.getIdDatoLaboral());
								globalRepository.actualizar(g);
								s.setIdInventario(puestoEmpleado.getIdVacante());
								seguroPopularRespository.actualizar(s);

							}

						} else {
							System.out.println("ya tiene asignado puesto");
						}
					} else {
						System.out.println("No existe ");
					}
				}
			}
			contador++;
		}

	}

	public void actualizarPuestos() {
		List<InventarioVacanteEntity> puestos = inventarioVacanteRepository.consultarTodos();
		System.out.println("puestos " + puestos.size());
		int contador = 0;
		for (InventarioVacanteEntity i : puestos) {
			System.out.println("procesados" + contador + " " + i.getEmpleadoActivo().getRfc());

			GlobalEntity global = globalRepository.obtenerGlobalRfc(i.getEmpleadoActivo().getRfc());
			if (global != null) {
				CluesEntity clue = clueRepository.obtenerCluePorClave(global.getClueReal());
				if (clue != null) {
					i.setClue(clue);
					inventarioVacanteRepository.actualizar(i);
				}
			}

			contador++;
		}

	}

	public void agregarSuplentesFaltantes(String rfc) {
		// List<PadronEventualEntity24> suplentes =
		// padronEventualEntity24Repository.consultarPadronSuplentesNuevos();
		// System.out.println("faltantes" + suplentes.size());
		// int contador = 1;
		// for (PadronEventualEntity24 p : suplentes) {
		// System.out.println("procesados" + contador);

		PadronEventualEntity24 p = padronEventualEntity24Repository.obtenerSuplentePorRfc(rfc);
		DatosSuplentesEntity datosSuplente = datosSuplentesRepository.obtenerDatosPorRfc(p.getRfc());
		if (datosSuplente != null) {
			p.setActivo(true);
			padronEventualEntity24Repository.actualizar(p);

			TipoEmpleadoEntity tipoEmpleadoSuplente = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);

			EmpleadoEntity empleado = new EmpleadoEntity();
			empleado.setRfc(datosSuplente.getRfc());
			empleado.setCurp(datosSuplente.getCurp());
			empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
			empleado.setIdMetodoPago(2);
			empleado.setNombreCompleto(datosSuplente.getNombre());
			empleado.setTelefono(datosSuplente.getTelefono());
			empleado.setTipoEmpleado(tipoEmpleadoSuplente);
			empleado.setFechaAlta(FechaUtil.fechaActual());
			empleadoRepository.crear(empleado);

			SuplenteAutorizadoEntity suplenteAutorizado = new SuplenteAutorizadoEntity();
			if (datosSuplente.getCentroResponsabilidad() != null) {
				suplenteAutorizado.setCentroResponsabilidad(datosSuplente.getCentroResponsabilidad());
			}

			suplenteAutorizado.setEmpleado(empleado);
			suplenteAutorizado.setEstatus("ACTIVO");
			suplenteAutorizado.setFechaAlta(FechaUtil.fechaActual());
			suplenteAutorizado.setHoraAlta(FechaUtil.horaActual());
			suplenteAutorizado.setMetodoPago(2);
			suplenteAutorizado.setNumero(" ");
			suplenteAutorizadoRepository.crear(suplenteAutorizado);
			// contador++;

		} else {
			System.out.println("no está en datos suplentes");
		}
		// }
	}

	public void agregarSuplentesFaltantes2() {
		List<PadronEventualEntity24> suplentes = padronEventualEntity24Repository.consultarPadronSuplentesNuevos();
		System.out.println("faltantes" + suplentes.size());
		int contador = 1;
		for (PadronEventualEntity24 p : suplentes) {
			System.out.println("procesados" + contador);
			DatosPersonalesEntity datosPersonales = datosPersonalesRepository.obtenerDatoPersonalPorRfc(p.getRfc());
			if (datosPersonales != null) {
				p.setActivo(true);
				padronEventualEntity24Repository.actualizar(p);

				TipoEmpleadoEntity tipoEmpleadoSuplente = tipoEmpleadoRepository
						.obtenerPorId(EnumTipoEmpleado.SUPLENTE);

				EmpleadoEntity empleado = new EmpleadoEntity();
				empleado.setRfc(datosPersonales.getRfc());
				empleado.setCurp("");
				empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
				empleado.setIdMetodoPago(2);
				empleado.setNombreCompleto(datosPersonales.getNombre() + " " + datosPersonales.getApellidoPaterno()
						+ " " + datosPersonales.getApellidoMaterno());
				empleado.setTelefono(datosPersonales.getTelefono());
				empleado.setTipoEmpleado(tipoEmpleadoSuplente);
				empleado.setFechaAlta(FechaUtil.fechaActual());
				empleadoRepository.crear(empleado);

				CentroResponsabilidadEntity centroResponsabilidadEntity = centroResponsabilidadRepository
						.obtenerCentroResponsabilidad(p.getClaveCentroResponsabilidad());
				SuplenteAutorizadoEntity suplenteAutorizado = new SuplenteAutorizadoEntity();
				if (centroResponsabilidadEntity != null) {
					suplenteAutorizado.setCentroResponsabilidad(centroResponsabilidadEntity);
				}

				suplenteAutorizado.setEmpleado(empleado);
				suplenteAutorizado.setEstatus("ACTIVO");
				suplenteAutorizado.setFechaAlta(FechaUtil.fechaActual());
				suplenteAutorizado.setHoraAlta(FechaUtil.horaActual());
				suplenteAutorizado.setMetodoPago(2);
				suplenteAutorizado.setNumero(" ");
				suplenteAutorizadoRepository.crear(suplenteAutorizado);
				contador++;

			} else {
				System.out.println("no está en datos suplentes");
			}
		}
	}

	public void inactivarContratosFederales() {
		List<InventarioVacanteEntity> contratosFederales = inventarioVacanteRepository
				.consultarPuestosPorTipoContratacion(EnumTipoContratacion.CONTRATO_ESTATAL);
		System.out.println("total " + contratosFederales.size());
		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.CANCELADA);
		EstatusConfiguracionesEntity estatusConfiguracion = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.INACTIVO);
		TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);
		int contador = 1;
		for (InventarioVacanteEntity puesto : contratosFederales) {
			System.out.println("procesados " + contador);
			puesto.setEstatus(estatusPuesto);
			inventarioVacanteRepository.actualizar(puesto);

			ConfiguracionPresupuestoEntity configuracion = puesto.getConfiguracion();

			if (configuracion != null) {
				configuracion.setEstatus(estatusConfiguracion);
				configuracionPresupuestalRepository.actualizar(configuracion);
			}

			EmpleadoEntity empleado = puesto.getEmpleadoActivo();
			empleado.setIdEstatus(EnumEstatusEmpleado.INACTIVO);

			empleadoRepository.actualizar(empleado);

			SuplenteAutorizadoEntity suplente = suplenteAutorizadoRepository
					.obtenerEmpleadoSuplente(empleado.getIdEmpleado());
			if (suplente != null) {
				if (suplente.getEstatus().equals("ACTIVO")) {
					empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
					empleado.setTipoEmpleado(tipoEmpleado);
					empleadoRepository.actualizar(empleado);
				}
			}
			contador++;
		}
	}

	public void altasBajasEstatal() {

		// BAJAS
		List<PadronEventualEntity24> bajas = padronEventualEntity24Repository.consultarBajas();
		System.out.println("bajas contratos " + bajas.size());
		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.CANCELADA);
		EstatusConfiguracionesEntity estatusConfiguracion = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.INACTIVO);
		TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);

		int contador = 1;
		for (PadronEventualEntity24 p : bajas) {
			InventarioVacanteEntity i = inventarioVacanteRepository.obtenerPuestoPorRFCTipoContratacion(p.getRfc(),
					EnumTipoContratacion.CONTRATO_ESTATAL);
			if (i != null) {
				System.out.println("bajas " + contador);
				i.setEstatus(estatusPuesto);
				inventarioVacanteRepository.actualizar(i);

				ConfiguracionPresupuestoEntity configuracion = i.getConfiguracion();

				if (configuracion != null) {
					configuracion.setEstatus(estatusConfiguracion);
					configuracionPresupuestalRepository.actualizar(configuracion);
				}

				EmpleadoEntity empleado = i.getEmpleadoActivo();
				empleado.setIdEstatus(EnumEstatusEmpleado.INACTIVO);

				empleadoRepository.actualizar(empleado);

				SuplenteAutorizadoEntity suplente = suplenteAutorizadoRepository
						.obtenerEmpleadoSuplente(empleado.getIdEmpleado());
				if (suplente != null) {
					if (suplente.getEstatus().equals("ACTIVO")) {
						empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
						empleado.setTipoEmpleado(tipoEmpleado);
						empleadoRepository.actualizar(empleado);
					}
				}
				contador++;
			}

		}

		List<PadronEventualEntity24> altas = padronEventualEntity24Repository.consultarAltas();
		System.out.println("contratos nuevos " + altas.size());

		int contadorAltas = 1;
		for (PadronEventualEntity24 p : altas) {
			System.out.println("procesados " + contadorAltas);
			crearPuestoEmpleado(p.getRfc(), p);
			contador++;
		}

	}

	public void actualizarContratoEstatal() {
		List<PadronEventualEntity24> contratosEstatales = padronEventualEntity24Repository
				.consultarPadronContratosEstatales();
		System.out.println("contratos esttales eventual " + contratosEstatales.size());
		EstatusPuestosEntity estatus = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);
		EstatusConfiguracionesEntity estatusc = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);
		int contador = 1;
		for (PadronEventualEntity24 p : contratosEstatales) {
			System.out.println("procesados" + contador);
			InventarioVacanteEntity i = inventarioVacanteRepository.obtenerPuestoPorRFCTipoContratacion(p.getRfc(),
					EnumTipoContratacion.CONTRATO_ESTATAL);
			if (i != null) {
				i.setEstatus(estatus);
				inventarioVacanteRepository.actualizar(i);

				ConfiguracionPresupuestoEntity c = i.getConfiguracion();
				if (c != null) {
					c.setSueldo01(p.getSueldo1().multiply(new BigDecimal(2)));
					c.setSueldo14(p.getSueldo14().multiply(new BigDecimal(2)));
					c.setSueldo(c.getSueldo01().add(c.getSueldo14()));
					c.setEstatus(estatusc);
					configuracionPresupuestalRepository.actualizar(c);
					p.setActivo(true);
					padronEventualEntity24Repository.actualizar(p);
				} else {

					List<DatosLaboralesCruceEntity> laborales = datosLaboralesRepository
							.obtenerDatoLaboral(p.getRfc().trim());

					p.setActivo(true);
					padronEventualEntity24Repository.actualizar(p);

					DatosLaboralesCruceEntity laboral = laborales.get(0);
					PuestoGeneralEntity puesto = puestoGeneralRepository.puestoPorClave(laboral.getCodigoPuesto());
					TiposNombramientosEntity tipoNombramiento = nombramientoRepository
							.nombramientoPorClave(laboral.getNombramiento());
					ConfiguracionPresupuestoEntity conf = new ConfiguracionPresupuestoEntity();

					conf.setCuenta(null);
					conf.setDependencia(laboral.getDependencia());
					conf.setEmpleado(i.getEmpleadoActivo());
					conf.setEstatus(estatusc);
					conf.setFechaAltaConfiguracion(FechaUtil.fechaActual());
					conf.setFuenteFinanciamiento(laboral.getFuenteFinanciamiento());
					conf.setNombramiento(tipoNombramiento);
					conf.setNumeroEmpleado(laboral.getIdEmpleadoDatosPersonales());
					conf.setProyecto(laboral.getProyecto());
					conf.setPuesto(puesto);
					conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
					conf.setTipoContratacion(i.getTipoContratacion());
					conf.setTipoRecurso(laboral.getTipoRecurso());
					conf.setUnidadResponsable(laboral.getUnidadResponsable());
					conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
					conf.setIdPlaza(laboral.getIdPlaza());
					configuracionPresupuestalRepository.crear(conf);

				}
			} else {
				crearPuestoEmpleado(p.getRfc(), p);
			}
			contador++;
		}
	}

	public void puestosPlantillaAutorizada() {

		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);
		EstatusConfiguracionesEntity estatusConfiguracion = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);
		TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);
		List<BaseFederalEntity> plantillaPendiente = baseFederalRepository.consultarPlantillaPendiente();
		System.out.println("procesados " + plantillaPendiente.size());
		int procesados = 1;
		int nuevos = 0;
		int reactivados = 0;

		for (BaseFederalEntity b : plantillaPendiente) {
			System.out.println("procesados " + procesados);
			// Consultar si tiene un puesto asignado con el mismo tipo de
			// contratación.
			InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPuestoPorIdEmpleadoTipoContratacion(
					b.getEmpleado().getIdEmpleado(), b.getTipoContratacion().getId());

			if (puesto != null) {
				// Si tiene un puesto asignado lo reactiva
				puesto.setEstatus(estatusPuesto);
				puesto.setPuestoAutorizado(b.getPuesto());
				puesto.setNombramiento(b.getNombramiento());
				puesto.setNumeroPuestoAutorizado(b.getNumeroPuesto());
				inventarioVacanteRepository.actualizar(puesto);
				b.setIdInventario(puesto.getIdVacante());

				// Se reactiva al empleado
				EmpleadoEntity empleadoPuesto = b.getEmpleado();
				empleadoPuesto.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
				empleadoPuesto.setTipoEmpleado(tipoEmpleado);
				empleadoRepository.actualizar(empleadoPuesto);

				ConfiguracionPresupuestoEntity datoLaboral = puesto.getConfiguracion();
				datoLaboral.setEstatus(estatusConfiguracion);
				configuracionPresupuestalRepository.actualizar(datoLaboral);

				reactivados++;
			} else {
				// Si no crea un puesto nuevo.

				// Consulta sus datos laborales
				List<DatosLaboralesCruceEntity> laborales = datosLaboralesRepository
						.obtenerDatoLaboral(b.getEmpleado().getRfc());

				/*
				 * if (laborales.size() > 1) { b.setDobleLaboral(true); } else
				 * if (laborales.size() == 1) {
				 */

				if (!laborales.isEmpty()) {

					DatosLaboralesCruceEntity laboral = laborales.get(0);
					PuestoGeneralEntity puestoLaboral = puestoGeneralRepository
							.puestoPorClave(laboral.getCodigoPuesto());
					TiposNombramientosEntity tipoNombramientoLaboral = nombramientoRepository
							.nombramientoPorClave(laboral.getNombramiento());
					ConfiguracionPresupuestoEntity conf = new ConfiguracionPresupuestoEntity();

					conf.setCuenta(null);
					conf.setDependencia(laboral.getDependencia());
					conf.setEmpleado(b.getEmpleado());
					conf.setEstatus(estatusConfiguracion);
					conf.setFechaAltaConfiguracion(FechaUtil.fechaActual());
					conf.setFuenteFinanciamiento(laboral.getFuenteFinanciamiento());
					conf.setNombramiento(tipoNombramientoLaboral);
					conf.setNumeroEmpleado(laboral.getIdEmpleadoDatosPersonales());
					conf.setProyecto(laboral.getProyecto());
					conf.setPuesto(puestoLaboral);
					conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
					conf.setTipoContratacion(b.getTipoContratacion());
					conf.setTipoRecurso(laboral.getTipoRecurso());
					conf.setUnidadResponsable(laboral.getUnidadResponsable());
					conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
					conf.setIdPlaza(laboral.getIdPlaza());
					configuracionPresupuestalRepository.crear(conf);

					EmpleadoEntity empleado = b.getEmpleado();
					empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
					empleado.setTipoEmpleado(tipoEmpleado);
					empleadoRepository.actualizar(empleado);
					// Generar folios
					Integer ultimoFolio = inventarioVacanteRepository
							.ultimoFolioVacanteContratacion(b.getTipoContratacion().getId());
					Integer siguienteNumeroVacante = 1;
					if (ultimoFolio != null) {
						siguienteNumeroVacante = ultimoFolio + 1;
					}
					String folioVacante = generarFolioVacante(siguienteNumeroVacante,
							b.getTipoContratacion().getCodigo());

					// Crear puesto
					InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
					puestoEmpleado.setTipoContratacion(b.getTipoContratacion());
					puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
					puestoEmpleado.setCodigoVacante(b.getTipoContratacion().getCodigo());
					puestoEmpleado.setFolioVacante(folioVacante);
					puestoEmpleado.setEstatus(estatusPuesto);
					puestoEmpleado.setDisponible("NO");
					puestoEmpleado.setEmpleadoActivo(empleado);
					puestoEmpleado.setConfiguracion(conf);
					puestoEmpleado.setSeguroPopular(false);
					puestoEmpleado.setPuestoAutorizado(b.getPuesto());
					puestoEmpleado.setNombramiento(b.getNombramiento());
					puestoEmpleado.setNumeroPuestoAutorizado(b.getNumeroPuesto());

					inventarioVacanteRepository.crear(puestoEmpleado);

					b.setIdInventario(puestoEmpleado.getIdVacante());
					b.setNuevoPuesto(true);

					nuevos++;

				} else {
					b.setSinLaboral(true);
				}

			} /*
				 * else if (laborales.isEmpty()) { b.setSinLaboral(true); }
				 * 
				 * }
				 */

			baseFederalRepository.actualizar(b);

			procesados++;
		}

		System.out.println("nuevos " + nuevos);
		System.out.println("reactivados " + reactivados);

	}

	public void crearPuestoEmpleado(String rfc, PadronEventualEntity24 p) {
		System.out.println("========= entro a crear puesto");
		EmpleadoEntity empleado = empleadoRepository.obtenerEmpleadoRfc(rfc);
		EstatusPuestosEntity estatus = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);
		EstatusConfiguracionesEntity estatusc = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);
		TipoContratacionEntity tipoContratacion = tipoContratacionRepository
				.obtenerPorId(EnumTipoContratacion.CONTRATO_ESTATAL);

		if (empleado == null) {

			DatosPersonalesEntity g = datosPersonalesRepository.obtenerDatoPersonalPorRfc(p.getRfc().trim());
			TipoEmpleadoEntity tipoEmpleadoSuplente = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.EMPLEADO);
			if (g != null) {
				System.out.println("crear empleado " + rfc);
				EmpleadoEntity empleadoNuevo = new EmpleadoEntity();
				empleadoNuevo.setCurp(" ");
				empleadoNuevo.setRfc(g.getRfc());
				empleadoNuevo.setNombre(g.getNombre());
				empleadoNuevo.setApellidoPaterno(g.getApellidoPaterno());
				empleadoNuevo.setApellidoMaterno(g.getApellidoMaterno());
				empleadoNuevo
						.setNombreCompleto(g.getNombre() + " " + g.getApellidoPaterno() + " " + g.getApellidoMaterno());
				empleadoNuevo.setNacionalidad("MEXICANA");
				if (g.getSexo().equals("F")) {
					empleadoNuevo.setIdSexo(EnumTipoSexo.FEMENINO);
				} else if (g.getSexo().equals("H") || g.getSexo().equals("M")) {
					empleadoNuevo.setIdSexo(EnumTipoSexo.MASCULINO);
				}
				empleadoNuevo.setTelefono(g.getTelefono());
				empleadoNuevo.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
				empleadoNuevo.setTipoEmpleado(tipoEmpleadoSuplente);
				empleadoRepository.crear(empleadoNuevo);

				empleado = empleadoNuevo;

			}
		}

		if (empleado != null) {

			boolean tieneEmpleadoPuestoAsignado = inventarioVacanteRepository
					.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado());

			if (tieneEmpleadoPuestoAsignado) {
				System.out.println("tiene puesto asignado " + rfc + " idEmpleado " + empleado.getIdEmpleado());
			}

			if (!tieneEmpleadoPuestoAsignado) {

				List<DatosLaboralesCruceEntity> laborales = datosLaboralesRepository.obtenerDatoLaboral(rfc.trim());

				// Si tiene configuración presupuestal se procesa
				if (!laborales.isEmpty()) {
					if (laborales.size() > 1) {
						System.out.println("duplicado en laboral" + rfc);
					} else {

						p.setActivo(true);
						padronEventualEntity24Repository.actualizar(p);

						DatosLaboralesCruceEntity laboral = laborales.get(0);
						PuestoGeneralEntity puesto = puestoGeneralRepository.puestoPorClave(laboral.getCodigoPuesto());
						TiposNombramientosEntity tipoNombramiento = nombramientoRepository
								.nombramientoPorClave(laboral.getNombramiento());
						ConfiguracionPresupuestoEntity conf = new ConfiguracionPresupuestoEntity();

						conf.setCuenta(null);
						conf.setDependencia(laboral.getDependencia());
						conf.setEmpleado(empleado);
						conf.setEstatus(estatusc);
						conf.setFechaAltaConfiguracion(FechaUtil.fechaActual());
						conf.setFuenteFinanciamiento(laboral.getFuenteFinanciamiento());
						conf.setNombramiento(tipoNombramiento);
						conf.setNumeroEmpleado(laboral.getIdEmpleadoDatosPersonales());
						conf.setProyecto(laboral.getProyecto());
						conf.setPuesto(puesto);
						conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
						conf.setTipoContratacion(tipoContratacion);
						conf.setTipoRecurso(laboral.getTipoRecurso());
						conf.setUnidadResponsable(laboral.getUnidadResponsable());
						conf.setSubfuenteFinanciamiento(laboral.getSubfuenteFinanciamiento());
						conf.setIdPlaza(laboral.getIdPlaza());
						configuracionPresupuestalRepository.crear(conf);

						empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
						empleadoRepository.actualizar(empleado);
						// Generar folios
						Integer ultimoFolio = inventarioVacanteRepository
								.ultimoFolioVacanteContratacion(tipoContratacion.getId());
						Integer siguienteNumeroVacante = 1;
						if (ultimoFolio != null) {
							siguienteNumeroVacante = ultimoFolio + 1;
						}
						String folioVacante = generarFolioVacante(siguienteNumeroVacante, tipoContratacion.getCodigo());

						// Crear puesto
						InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
						puestoEmpleado.setTipoContratacion(tipoContratacion);
						puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
						puestoEmpleado.setCodigoVacante(tipoContratacion.getCodigo());
						puestoEmpleado.setFolioVacante(folioVacante);
						puestoEmpleado.setEstatus(estatus);
						puestoEmpleado.setDisponible("NO");
						puestoEmpleado.setEmpleadoActivo(empleado);
						puestoEmpleado.setConfiguracion(conf);
						puestoEmpleado.setSeguroPopular(false);

						inventarioVacanteRepository.crear(puestoEmpleado);

					}

				}

				else {
					System.out.println("no tiene laborales" + rfc);
				}

			}
		} else {
			System.out.println("no se encontró empleado con rfc" + rfc);
		}

	}
}
