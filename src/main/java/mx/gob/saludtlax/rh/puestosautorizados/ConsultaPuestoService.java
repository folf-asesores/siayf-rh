/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 13:54:55 12/08/2016
 */
public class ConsultaPuestoService {

	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;

	/**
	 * Consulta
	 */
	public List<InfoPuestoDTO> consultarVacantesDisponibles() {

		return inventarioVacanteRepository.consultarVacantesDisponibles();
	}

	protected List<InfoPuestoDTO> consultaVacantesPorCriterio(FiltroVacanteDTO filtroVacanteDTO) {

		List<InfoPuestoDTO> vacantes = new ArrayList<InfoPuestoDTO>();
		Integer idTipoBusqueda = filtroVacanteDTO.getTipoBusqueda();
		if (idTipoBusqueda == EnumFiltroConsultaVacante.TIPO_CONTRATACION) {
			vacantes = inventarioVacanteRepository
					.consultaVacantesPorTipoContratacion(filtroVacanteDTO.getIdentificador());
		} else if (idTipoBusqueda == EnumFiltroConsultaVacante.TIPO_CONTRATACION_ESTATUS) {
			vacantes = inventarioVacanteRepository.consultaVacantesPorTipoContratacionEstatus(
					filtroVacanteDTO.getIdentificador(), filtroVacanteDTO.getCriterio());
		} else if (idTipoBusqueda == EnumFiltroConsultaVacante.DISPONIBLES_POSTULADAS) {

			vacantes = inventarioVacanteRepository
					.consultaVacantesDisponiblesPostuladas(filtroVacanteDTO.getIdentificador(), "SI");

		} else if (idTipoBusqueda == EnumFiltroConsultaVacante.SIN_ADSCRIPCION) {
			vacantes = inventarioVacanteRepository.consultaVacantesSinAdscripcion();
		} else if (idTipoBusqueda == EnumFiltroConsultaVacante.SIN_AREA_ADSCRIPCION) {
			vacantes = inventarioVacanteRepository.consultaVacantesSinAreaAdscripcion();
		} else if (idTipoBusqueda == EnumFiltroConsultaVacante.SIN_LUGAR_ADSCRIPCION) {
			vacantes = inventarioVacanteRepository.consultaVacantesSinLugarAdscripcion();
		} else if (idTipoBusqueda == EnumFiltroConsultaVacante.UNIDAD_RESPONSABLE) {
			vacantes = inventarioVacanteRepository
					.consultaVacantesPorUnidadResponsable(filtroVacanteDTO.getIdentificador());
		} else if (idTipoBusqueda == EnumFiltroConsultaVacante.NOMBRE_RFC) {
			if (ValidacionUtil.esCadenaVacia(filtroVacanteDTO.getCriterio())) {
				throw new ValidacionException("El criterio de búsqueda es requerido.s",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}
			if (filtroVacanteDTO.getCriterio().trim().length() < 3) {
				throw new ValidacionException("El criterio debe contener mas de 3 caracteres",
						ValidacionCodigoError.VALOR_MUY_CORTO);
			}
			vacantes = inventarioVacanteRepository.consultaVacantesRfcNombre(filtroVacanteDTO.getCriterio());
		}

		return vacantes;
	}

	protected PuestoEmpleadoDTO obtenerInformacionPuesto(Integer idPuesto) {

		if (!ValidacionUtil.esNumeroPositivo(idPuesto)) {
			throw new ValidacionException("Es requerido indicar el puesto del que desea obtener información",
					ValidacionCodigoError.NUMERO_NEGATIVO);
		}

		InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPorId(idPuesto);

		PuestoEmpleadoDTO puestoEmpleado = new PuestoEmpleadoDTO();

		puestoEmpleado.setCodigoPuesto(puesto.getConfiguracion().getPuesto().getCodigo());
		
		if(puesto.getConfiguracion().getDependencia()!=null){
		puestoEmpleado.setDependencia(puesto.getConfiguracion().getDependencia().getDescripcion());
		}
		
		if (puesto.getServicio() != null) {
			puestoEmpleado.setIdServicio(puesto.getServicio().getIdServicio());
		}
		if (puesto.getAdscripcion() != null) {
			puestoEmpleado.setIdAdscripcion(puesto.getAdscripcion().getIdAdscripcion());
		}
		if (puesto.getSubadscripcion() != null) {
			puestoEmpleado.setIdSubadscripcion(puesto.getSubadscripcion().getIdSubadscripcion());

		}
		if (puesto.getFuncion() != null) {
			puestoEmpleado.setIdFuncion(puesto.getFuncion().getIdFuncion());
		}

		if (puesto.getClue() != null) {
			puestoEmpleado.setIdClue(puesto.getClue().getIdClues());
		}

		if (puesto.getConfiguracion() != null) {
			if (puesto.getConfiguracion().getEmpleado() != null) {
				puestoEmpleado.setNombre(puesto.getConfiguracion().getEmpleado().getNombreCompleto());
			}

			if (puesto.getConfiguracion().getCentroResponsabilidad() != null) {
				puestoEmpleado.setIdCentroResponsabilidad(
						puesto.getConfiguracion().getCentroResponsabilidad().getIdCentroResponsabilidad());

			}
		}

		if (puesto.getPrograma() != null) {
			puestoEmpleado.setIdPrograma(puesto.getPrograma().getIdPrograma());
		}

		puestoEmpleado.setNumeroEmpleado(puesto.getConfiguracion().getNumeroEmpleado());
		puestoEmpleado.setNumeroVacante(puesto.getFolioVacante());
		
		if(puesto.getConfiguracion().getProyecto()!=null){
		puestoEmpleado.setProyecto(puesto.getConfiguracion().getProyecto().getDescripcion());
		}
		puestoEmpleado.setPuesto(puesto.getConfiguracion().getPuesto().getPuesto());
		puestoEmpleado.setTipoContratacion(puesto.getTipoContratacion().getTipoContratacion());
		puestoEmpleado.setTipoNombramiento(puesto.getConfiguracion().getNombramiento().getDescripcion());
		
		if(puesto.getConfiguracion().getUnidadResponsable()!=null){
		puestoEmpleado.setUnidadResponsable(puesto.getConfiguracion().getUnidadResponsable().getDescripcion());
		}
		puestoEmpleado.setSueldoAutorizado(puesto.getConfiguracion().getSueldo());
		puestoEmpleado.setIdPuesto(puesto.getIdVacante());
		puestoEmpleado.setSeguroPopular(puesto.isSeguroPopular());

		return puestoEmpleado;
	}

	protected PuestoEmpleadoDTO obtenerInformacionPuestoIdEmpleado(Integer idEmpleado) {
		if (!ValidacionUtil.esNumeroPositivo(idEmpleado)) {
			throw new ValidacionException("Es requerido el id del empleado", ValidacionCodigoError.NUMERO_NEGATIVO);
		}

		InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPuestoActivoEmpleado(idEmpleado);
		if (puesto == null) {
			throw new ValidacionException("No se encontró registro del puesto.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		PuestoEmpleadoDTO puestoEmpleado = toPuestoEmpleadoDTO(puesto);
		return puestoEmpleado;

	}

	protected PuestoEmpleadoDTO obtenerInformacionPuestoIdPuesto(Integer idPuesto) {
		if (!ValidacionUtil.esNumeroPositivo(idPuesto)) {
			throw new ValidacionException("Es requerido el id del puesto", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPorId(idPuesto);
		if (puesto == null) {
			throw new ValidacionException("No se encontró registro del puesto.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		PuestoEmpleadoDTO puestoEmpleado = toPuestoEmpleadoDTO(puesto);
		return puestoEmpleado;

	}

	private PuestoEmpleadoDTO toPuestoEmpleadoDTO(InventarioVacanteEntity puesto) {
		PuestoEmpleadoDTO puestoEmpleado = new PuestoEmpleadoDTO();
		puestoEmpleado.setIdPuesto(puesto.getIdVacante());
		puestoEmpleado.setCodigoPuesto(puesto.getConfiguracion().getPuesto().getCodigo());
		puestoEmpleado.setDependencia(puesto.getConfiguracion().getDependencia().getDescripcion());
		if (puesto.getServicio() != null) {
			puestoEmpleado.setIdServicio(puesto.getServicio().getIdServicio());
			puestoEmpleado.setServicio(puesto.getServicio().getServicio());
		}
		if (puesto.getAdscripcion() != null) {
			puestoEmpleado.setIdAdscripcion(puesto.getAdscripcion().getIdAdscripcion());
			puestoEmpleado.setAdscripcion(puesto.getAdscripcion().getAdscripcion());
		}
		if (puesto.getSubadscripcion() != null) {
			puestoEmpleado.setIdSubadscripcion(puesto.getSubadscripcion().getIdSubadscripcion());
			puestoEmpleado.setSubadscripcion(puesto.getSubadscripcion().getSubadscripcion());

		}
		if (puesto.getFuncion() != null) {
			puestoEmpleado.setIdFuncion(puesto.getFuncion().getIdFuncion());
			puestoEmpleado.setFuncion(puesto.getFuncion().getFuncion());
		}

		if (puesto.getConfiguracion().getEmpleado() != null) {
			puestoEmpleado.setNombre(puesto.getConfiguracion().getEmpleado().getNombreCompleto());
		}

		puestoEmpleado.setNumeroEmpleado(puesto.getConfiguracion().getNumeroEmpleado());
		puestoEmpleado.setNumeroVacante(puesto.getFolioVacante());
		if (puesto.getConfiguracion() != null) {
			puestoEmpleado.setProyecto(puesto.getConfiguracion().getProyecto().getDescripcion());
			puestoEmpleado.setPuesto(puesto.getConfiguracion().getPuesto().getPuesto());
			puestoEmpleado.setTipoContratacion(puesto.getTipoContratacion().getTipoContratacion());
			puestoEmpleado.setTipoNombramiento(puesto.getConfiguracion().getNombramiento().getDescripcion());
			puestoEmpleado.setUnidadResponsable(puesto.getConfiguracion().getUnidadResponsable().getDescripcion());
			puestoEmpleado.setSueldoAutorizado(puesto.getConfiguracion().getSueldo());
			puestoEmpleado.setFuente(puesto.getConfiguracion().getFuenteFinanciamiento().getDescripcion());
			puestoEmpleado.setSubfuente(puesto.getConfiguracion().getSubfuenteFinanciamiento().getDescripcion());

		}

		return puestoEmpleado;
	}

	protected List<InventarioVacanteDTO> globalPuestos() {
		List<InventarioVacanteDTO> globalAutorizados = inventarioVacanteRepository.globlalPuestosAutorizados();
		List<InventarioVacanteDTO> globalActivos = inventarioVacanteRepository
				.globalPuestosPorEstatus(EnumEstatusPuesto.EMPLEADO_ACTIVO);
		List<InventarioVacanteDTO> globalPermiso = inventarioVacanteRepository
				.globalPuestosPorEstatus(EnumEstatusPuesto.EMPLEADO_EN_PERMISO);
		List<InventarioVacanteDTO> globalInterinatos = inventarioVacanteRepository.globalPuestosInterinatos();
		List<InventarioVacanteDTO> globalDisponibles = inventarioVacanteRepository.globalPuestosDisponibles();

		if (!globalAutorizados.isEmpty()) {

			// Agregar el total de los activos
			if (!globalActivos.isEmpty()) {
				for (InventarioVacanteDTO puestoPrincipal : globalAutorizados) {
					for (InventarioVacanteDTO puestoActivo : globalActivos) {
						if (puestoPrincipal.getIdTipoContratacion().equals(puestoActivo.getIdTipoContratacion())) {
							puestoPrincipal.setTotalActivos(puestoActivo.getTotal());
						}
					}
				}
			}

			// Agregar el total de permisos.
			if (!globalPermiso.isEmpty()) {
				for (InventarioVacanteDTO puestoPrincipal : globalAutorizados) {
					for (InventarioVacanteDTO puestoPermiso : globalPermiso) {
						if (puestoPrincipal.getIdTipoContratacion().equals(puestoPermiso.getIdTipoContratacion())) {
							puestoPrincipal.setTotalActivos(puestoPermiso.getTotal());
						}
					}
				}
			}

			// Agregar interinatos

			if (!globalInterinatos.isEmpty()) {
				for (InventarioVacanteDTO puestoPrincipal : globalAutorizados) {
					for (InventarioVacanteDTO puestoInterinato : globalInterinatos) {
						if (puestoPrincipal.getIdTipoContratacion().equals(puestoInterinato.getIdTipoContratacion())) {
							puestoPrincipal.setTotalInterinatos(puestoInterinato.getTotal());
						}
					}
				}
			}

			// Agregar el total de disponibles

			if (!globalDisponibles.isEmpty()) {

				for (InventarioVacanteDTO puestoPrincipal : globalAutorizados) {
					for (InventarioVacanteDTO puestoDisponible : globalDisponibles) {
						if (puestoPrincipal.getIdTipoContratacion().equals(puestoDisponible.getIdTipoContratacion())) {
							puestoPrincipal.setTotalDisponibles(puestoDisponible.getTotal());
						}
					}
				}
			}

		}

		return globalAutorizados;
	}

	protected List<ResumenPuestoDTO> consultarDetallesCodigosPorContratacion(Integer tipoContratacion) {
		return inventarioVacanteRepository.consultarResumenCodigosPorContratacion(tipoContratacion);
	}

	protected List<DetallePuestoDTO> porContratacionYEstatus(Integer tipoContratacion, int idEstatus) {
		List<DetallePuestoDTO> detalles = inventarioVacanteRepository
				.inventarioPorContratacionYEstatus(tipoContratacion, idEstatus);
		return detalles;
	}

	protected EstructuraContratoDTO obtenerEstructuraContratoPuesto(Integer idPuesto) {
		InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPorId(idPuesto);
		if (puesto == null) {
			throw new SistemaException("No existe el puesto con identificador " + idPuesto,
					SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}

		if (puesto.getTipoContratacion().getId() != EnumTipoContratacion.CONTRATO_ESTATAL) {
			throw new ReglaNegocioException("La estructura solo está permitida para contrato estatal.",
					ReglaNegocioCodigoError.TIPO_CONTRATACION_NO_PERMITIDA);
		}
		EstructuraContratoDTO estructura = new EstructuraContratoDTO();
		estructura.setFinanciamiento(puesto.getFinanciamiento());
		estructura.setFuncion(puesto.getFuncionEspecifica());
		estructura.setJornada(puesto.getJornada());
		estructura.setSubfuncion(puesto.getSubfuncion());

		return estructura;
	}

}
