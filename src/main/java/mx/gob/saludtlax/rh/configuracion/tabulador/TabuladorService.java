/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalEntity;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.SubClasificacionTabuladorRepository;
import mx.gob.saludtlax.rh.persistencia.SubclasificacionTabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TabuladorRepository;
import mx.gob.saludtlax.rh.persistencia.TipoTabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TipoTabuladorRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 28/07/2016 13:11:03
 */
public class TabuladorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7214725794377644170L;

	@Inject
	private EjercicioFiscalRepository ejercicioFiscalRepository;
	@Inject
	private TabuladorRepository tabuladorRepository;
	@Inject
	private TipoTabuladorRepository tipoTabuladorRepository;
	@Inject
	private PuestoGeneralRepository puestoGeneralRepository;
	@Inject
	private SubClasificacionTabuladorRepository subClasificacionTabuladorRepository;

	protected void crearTabulador(TabuladorDTO tabuladorDTO) {

		TabuladorEntity tabuladorEntity = new TabuladorEntity();

		PuestoGeneralEntity puestoGeneralEntity = puestoGeneralRepository
				.obtenerPorId(tabuladorDTO.getIdPuestoGeneral());

		TipoTabuladorEntity tipoTabuladorEntity = tipoTabuladorRepository
				.obtenerPorId(tabuladorDTO.getIdTipoTabulador());

		EjercicioFiscalEntity ejercicioFiscalEntity = ejercicioFiscalRepository
				.obtenerPorId(tabuladorDTO.getIdEjercicioFiscal());

		tabuladorEntity.setPuestoGeneral(puestoGeneralEntity);
		tabuladorEntity.setTipoTabulador(tipoTabuladorEntity);
		tabuladorEntity.setEjercicioFiscal(ejercicioFiscalEntity
				.getEjercicioFiscal());
		tabuladorEntity.setSueldoBrutoMensual(tabuladorDTO
				.getSueldoBrutoMensual());
		tabuladorEntity.setAsignacionBrutaMensual(tabuladorDTO
				.getAsignacionBrutaMensual());
		tabuladorEntity.setAgaBrutaMensual(tabuladorDTO.getAgaBrutaMensual());
		tabuladorEntity.setTotalBrutoMensual(tabuladorDTO
				.getTotalBrutoMensual());
		tabuladorEntity.setSueldoBaseMensualMinimo(tabuladorDTO
				.getSueldoBaseMensualMinimo());
		tabuladorEntity.setSueldoBaseMensualMedio(tabuladorDTO
				.getSueldoBaseMensualMedio());
		tabuladorEntity.setSueldoBaseMensualMaximo(tabuladorDTO
				.getSueldoBaseMensualMaximo());
		tabuladorEntity.setSueldoDiario(tabuladorDTO.getSueldoDiario());

		if (ValidacionUtil.esNumeroPositivoInt(tabuladorDTO
				.getIdSubClasificacion())) {
			SubclasificacionTabuladorEntity subclasificacionTabuladorEntity = subClasificacionTabuladorRepository
					.obtenerPorId(tabuladorDTO.getIdSubClasificacion());

			if (subclasificacionTabuladorEntity != null) {
				tabuladorEntity
						.setSubclasificacion(subclasificacionTabuladorEntity);
			}

		}

		tabuladorRepository.crear(tabuladorEntity);

	}

	protected void actualizarTabulador(TabuladorDTO tabuladorDTO) {

		TabuladorEntity tabuladorEntity = tabuladorRepository
				.obtenerPorId(tabuladorDTO.getIdTabulador());

		PuestoGeneralEntity puestoGeneralEntity = puestoGeneralRepository
				.obtenerPorId(tabuladorDTO.getIdPuestoGeneral());

		TipoTabuladorEntity tipoTabuladorEntity = tipoTabuladorRepository
				.obtenerPorId(tabuladorDTO.getIdTipoTabulador());

		EjercicioFiscalEntity ejercicioFiscalEntity = ejercicioFiscalRepository
				.obtenerPorId(tabuladorDTO.getIdEjercicioFiscal());

		tabuladorEntity.setPuestoGeneral(puestoGeneralEntity);
		tabuladorEntity.setTipoTabulador(tipoTabuladorEntity);
		tabuladorEntity.setEjercicioFiscal(ejercicioFiscalEntity
				.getEjercicioFiscal());
		tabuladorEntity.setSueldoBrutoMensual(tabuladorDTO
				.getSueldoBrutoMensual());
		tabuladorEntity.setAsignacionBrutaMensual(tabuladorDTO
				.getAsignacionBrutaMensual());
		tabuladorEntity.setAgaBrutaMensual(tabuladorDTO.getAgaBrutaMensual());
		tabuladorEntity.setTotalBrutoMensual(tabuladorDTO
				.getTotalBrutoMensual());

		tabuladorEntity.setSueldoBaseMensualMinimo(tabuladorDTO
				.getSueldoBaseMensualMinimo());
		tabuladorEntity.setSueldoBaseMensualMedio(tabuladorDTO
				.getSueldoBaseMensualMedio());
		tabuladorEntity.setSueldoBaseMensualMaximo(tabuladorDTO
				.getSueldoBaseMensualMaximo());
		tabuladorEntity.setSueldoDiario(tabuladorDTO.getSueldoDiario());

		if (ValidacionUtil.esNumeroPositivoInt(tabuladorDTO
				.getIdSubClasificacion())) {
			SubclasificacionTabuladorEntity subclasificacionTabuladorEntity = subClasificacionTabuladorRepository
					.obtenerPorId(tabuladorDTO.getIdSubClasificacion());

			if (subclasificacionTabuladorEntity != null) {
				tabuladorEntity
						.setSubclasificacion(subclasificacionTabuladorEntity);
			}

		} else {
			tabuladorEntity.setSubclasificacion(null);
		}

		tabuladorRepository.actualizar(tabuladorEntity);

	}

	protected void eliminarTabulador(Integer idTabulador) {

		tabuladorRepository.eliminarPorId(idTabulador);

	}

	protected List<TabuladorDTO> listaTabulador(Integer idTipoTabulador) {
		List<TabuladorDTO> listaTabuladorDTOs = new ArrayList<TabuladorDTO>();

		List<TabuladorEntity> listaTabuladores = tabuladorRepository
				.consultarTabuladoresPorTipo(idTipoTabulador);

		if (!listaTabuladores.isEmpty()) {
			for (TabuladorEntity tabuladorEntity : listaTabuladores) {
				TabuladorDTO dto = new TabuladorDTO();

				dto.setIdTabulador(tabuladorEntity.getIdTabulador());

				if (tabuladorEntity.getPuestoGeneral() != null) {
					dto.setIdPuestoGeneral(tabuladorEntity.getPuestoGeneral()
							.getIdPuestoGeneral());
					dto.setCodigoPuestoGeneral(tabuladorEntity
							.getPuestoGeneral().getCodigo());
					dto.setNivelTipoPuesto(tabuladorEntity.getPuestoGeneral()
							.getIdTipoPuesto().getDescripcion());

					if (tabuladorEntity.getPuestoGeneral().getIdRama() != null) {
						dto.setNombreRamaPuesto(tabuladorEntity
								.getPuestoGeneral().getIdRama()
								.getNombreRamaPuesto());
					}

					dto.setNombrePuestoGeneral(tabuladorEntity
							.getPuestoGeneral().getPuesto());
				}

				if (tabuladorEntity.getTipoTabulador() != null) {
					dto.setIdTipoTabulador(tabuladorEntity.getTipoTabulador()
							.getIdTipoTabulador());
					dto.setDescripcionTipoTabulador(tabuladorEntity
							.getTipoTabulador().getDescripcion());
				}

				if (tabuladorEntity.getEjercicioFiscal().compareTo(
						new Integer(0)) > 0) {

					EjercicioFiscalEntity ejercicioFiscalEntity = ejercicioFiscalRepository
							.obtenerEjercioPorEjercicionFiscal(tabuladorEntity
									.getEjercicioFiscal());

					if (ejercicioFiscalEntity != null) {

						dto.setIdEjercicioFiscal(ejercicioFiscalEntity
								.getIdEjercicioFiscal());
						dto.setEjercicioFiscal(ejercicioFiscalEntity
								.getEjercicioFiscal());
					}

				}

				if (tabuladorEntity.getTipoTabulador().getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
					dto.setSueldoBrutoMensual(tabuladorEntity
							.getSueldoBrutoMensual());
					dto.setAsignacionBrutaMensual(tabuladorEntity
							.getAsignacionBrutaMensual());
					dto.setAgaBrutaMensual(tabuladorEntity.getAgaBrutaMensual());
					dto.setTotalBrutoMensual(tabuladorEntity
							.getTotalBrutoMensual());
				} else if (tabuladorEntity.getTipoTabulador()
						.getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
					dto.setSueldoBaseMensualMinimo(tabuladorEntity
							.getSueldoBaseMensualMinimo());
					dto.setSueldoBaseMensualMedio(tabuladorEntity
							.getSueldoBaseMensualMedio());
					dto.setSueldoBaseMensualMaximo(tabuladorEntity
							.getSueldoBaseMensualMaximo());
					dto.setSubClasificacion(tabuladorEntity
							.getSubclasificacion() == null ? ""
							: tabuladorEntity.getSubclasificacion()
									.getSubclasificacion());
					dto.setIdSubClasificacion(tabuladorEntity
							.getSubclasificacion() == null ? 0
							: tabuladorEntity.getSubclasificacion()
									.getIdSubclasificacion());

				} else if (tabuladorEntity.getTipoTabulador()
						.getIdTipoTabulador() == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
					dto.setSueldoDiario(tabuladorEntity.getSueldoDiario());
				}

				listaTabuladorDTOs.add(dto);
			}
		}

		return listaTabuladorDTOs;
	}

	protected List<TabuladorDTO> obtenerListaTabulador() {
		List<TabuladorDTO> listaTabuladorDTOs = new ArrayList<TabuladorDTO>();

		List<TabuladorEntity> listaTabuladores = tabuladorRepository
				.consultarTabuladores();

		if (!listaTabuladores.isEmpty()) {
			for (TabuladorEntity tabuladorEntity : listaTabuladores) {
				TabuladorDTO dto = new TabuladorDTO();

				dto.setIdTabulador(tabuladorEntity.getIdTabulador());

				if (tabuladorEntity.getPuestoGeneral() != null) {
					dto.setIdPuestoGeneral(tabuladorEntity.getPuestoGeneral()
							.getIdPuestoGeneral());
					dto.setCodigoPuestoGeneral(tabuladorEntity
							.getPuestoGeneral().getCodigo());
					dto.setNivelTipoPuesto(tabuladorEntity.getPuestoGeneral()
							.getIdTipoPuesto().getDescripcion());

					if (tabuladorEntity.getPuestoGeneral().getIdRama() != null) {
						dto.setNombreRamaPuesto(tabuladorEntity
								.getPuestoGeneral().getIdRama()
								.getNombreRamaPuesto());
					}

					dto.setNombrePuestoGeneral(tabuladorEntity
							.getPuestoGeneral().getPuesto());
				}

				if (tabuladorEntity.getTipoTabulador() != null) {
					dto.setIdTipoTabulador(tabuladorEntity.getTipoTabulador()
							.getIdTipoTabulador());
					dto.setDescripcionTipoTabulador(tabuladorEntity
							.getTipoTabulador().getDescripcion());
				}

				if (tabuladorEntity.getEjercicioFiscal().compareTo(
						new Integer(0)) > 0) {

					EjercicioFiscalEntity ejercicioFiscalEntity = ejercicioFiscalRepository
							.obtenerEjercioPorEjercicionFiscal(tabuladorEntity
									.getEjercicioFiscal());

					if (ejercicioFiscalEntity != null) {

						dto.setIdEjercicioFiscal(ejercicioFiscalEntity
								.getIdEjercicioFiscal());
						dto.setEjercicioFiscal(ejercicioFiscalEntity
								.getEjercicioFiscal());
					}

				}

				if (tabuladorEntity.getTipoTabulador().getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
					dto.setSueldoBrutoMensual(tabuladorEntity
							.getSueldoBrutoMensual());
					dto.setAsignacionBrutaMensual(tabuladorEntity
							.getAsignacionBrutaMensual());
					dto.setAgaBrutaMensual(tabuladorEntity.getAgaBrutaMensual());
					dto.setTotalBrutoMensual(tabuladorEntity
							.getTotalBrutoMensual());
				} else if (tabuladorEntity.getTipoTabulador()
						.getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
					dto.setSueldoBaseMensualMinimo(tabuladorEntity
							.getSueldoBaseMensualMinimo());
					dto.setSueldoBaseMensualMedio(tabuladorEntity
							.getSueldoBaseMensualMedio());
					dto.setSueldoBaseMensualMaximo(tabuladorEntity
							.getSueldoBaseMensualMaximo());
					dto.setSubClasificacion(tabuladorEntity
							.getSubclasificacion() == null ? ""
							: tabuladorEntity.getSubclasificacion()
									.getSubclasificacion());
					dto.setIdSubClasificacion(tabuladorEntity
							.getSubclasificacion() == null ? 0
							: tabuladorEntity.getSubclasificacion()
									.getIdSubclasificacion());

				}

				listaTabuladorDTOs.add(dto);
			}
		}

		return listaTabuladorDTOs;
	}

	protected InfoTabuladorPuestoDTO obtenerInfoPuesto(Integer idPuestoGeneral) {
		InfoTabuladorPuestoDTO infoTabuladorPuestoDTO = new InfoTabuladorPuestoDTO();

		PuestoGeneralEntity puestoGeneralEntity = puestoGeneralRepository
				.obtenerPorId(idPuestoGeneral);

		if (puestoGeneralEntity != null) {

			if (puestoGeneralEntity.getIdRama() != null) {
				infoTabuladorPuestoDTO.setNombreRamaPuesto(puestoGeneralEntity
						.getIdRama().getNombreRamaPuesto());
			}

			infoTabuladorPuestoDTO.setCodigoPuestoGeneral(puestoGeneralEntity
					.getCodigo());
			infoTabuladorPuestoDTO.setNivelTipoPuesto(puestoGeneralEntity
					.getIdTipoPuesto().getDescripcion());

		}

		return infoTabuladorPuestoDTO;
	}

	public InfoSueldoDTO obtenerInfoSueldoIdTabulador(TabuladorEntity tabulador) {

		InfoSueldoDTO sueldo = new InfoSueldoDTO();

		if (tabulador != null) {
			sueldo.setIdTabulador(tabulador.getIdTabulador());
			if (tabulador.getTipoTabulador().getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {

				sueldo.setAgaBrutaMensual(tabulador.getAgaBrutaMensual());
				sueldo.setAsignacionBrutaMensual(tabulador
						.getAsignacionBrutaMensual());
				sueldo.setSueldoBrutoMensual(tabulador.getSueldoBrutoMensual());
				sueldo.setTotalBrutoMensual(tabulador.getTotalBrutoMensual());
			}

		} else {
			throw new ReglaNegocioException(
					"El puesto seleccionado no tiene configurado un salario en el tabulador, es requerido registrarlo en el tabulador.",
					ReglaNegocioCodigoError.TABULADOR_NO_CONFIGURADO);
		}

		return sueldo;

	}

	protected InfoSueldoDTO obtenerSueldoPorPuestoTabulador(Integer idPuesto,
			Integer tipoContratacion) {

		InfoSueldoDTO sueldo = new InfoSueldoDTO();
		Integer tipoTabulador = generarTipoTabulador(tipoContratacion);

		if (tipoTabulador != null) {
			TabuladorEntity tabulador = tabuladorRepository
					.obtenerSueldoActualPorPuestoTipoTabulador(idPuesto,
							tipoTabulador);

			if (tabulador != null) {
				sueldo.setIdTabulador(tabulador.getIdTabulador());
				if (tabulador.getTipoTabulador().getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {

					sueldo.setAgaBrutaMensual(tabulador.getAgaBrutaMensual());
					sueldo.setAsignacionBrutaMensual(tabulador
							.getAsignacionBrutaMensual());
					sueldo.setSueldoBrutoMensual(tabulador
							.getSueldoBrutoMensual());
					sueldo.setTotalBrutoMensual(tabulador
							.getTotalBrutoMensual());
				} else if (tabulador.getTipoTabulador().getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
					if (tipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL) {
						sueldo.setSueldoBaseMensualMinimo(tabulador
								.getSueldoBaseMensualMinimo());
						sueldo.setSueldoBaseMensualMedio(tabulador
								.getSueldoBaseMensualMedio());
						sueldo.setSueldoBaseMensualMaximo(tabulador
								.getSueldoBaseMensualMaximo());
						sueldo.setSubClasificacion(tabulador
								.getSubclasificacion().getSubclasificacion());
					}

				}

			} else {
				throw new ReglaNegocioException(
						"El puesto seleccionado no tiene configurado un salario en el tabulador, es requerido registrarlo en el tabulador.",
						ReglaNegocioCodigoError.TABULADOR_NO_CONFIGURADO);
			}

		} else {
			throw new ReglaNegocioException(
					"No se ha registrado un tabulador para el tipo de contratación.",
					ReglaNegocioCodigoError.TABULADOR_NO_CONFIGURADO);
		}

		return sueldo;

	}

	private Integer generarTipoTabulador(Integer idTipoContratacion) {
		Integer tipoTabulador = null;
		if (idTipoContratacion == EnumTipoContratacion.BASE
				|| idTipoContratacion == EnumTipoContratacion.CONFIANZA
				|| idTipoContratacion == EnumTipoContratacion.INTERINATO
				|| idTipoContratacion == EnumTipoContratacion.HOMOLOGADOS
				|| idTipoContratacion == EnumTipoContratacion.FORMALIZADOS
				|| idTipoContratacion == EnumTipoContratacion.REGULARIZADOS) {
			tipoTabulador = EnumTipoTabulador.FEDERAL;
		} else if (idTipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL
				|| idTipoContratacion == EnumTipoContratacion.CONTRATO_FEDERAL) {
			tipoTabulador = EnumTipoTabulador.ESTATAL;
		}
		return tipoTabulador;
	}

	protected BigDecimal obtenerSueldoDiarioPorIdTabulador(Integer idTabulador) {
		return tabuladorRepository.obtenerSueldoDiarioIdTabulador(idTabulador);
	}

	public TabuladorDTO obtenerTabuladorPorPuesto(Integer idPuesto,
			Integer anioFiscal) {
		TabuladorDTO dto = new TabuladorDTO();
		TabuladorEntity tabuladorEntity = tabuladorRepository
				.obtenerTabuladorActualPorPuesto(idPuesto, 2);
		if (tabuladorEntity != null) {
			dto.setIdTabulador(tabuladorEntity.getIdTabulador());

			if (tabuladorEntity.getPuestoGeneral() != null) {
				dto.setIdPuestoGeneral(tabuladorEntity.getPuestoGeneral()
						.getIdPuestoGeneral());
				dto.setCodigoPuestoGeneral(tabuladorEntity.getPuestoGeneral()
						.getCodigo());
				dto.setNivelTipoPuesto(tabuladorEntity.getPuestoGeneral()
						.getIdTipoPuesto().getDescripcion());

				if (tabuladorEntity.getPuestoGeneral().getIdRama() != null) {
					dto.setNombreRamaPuesto(tabuladorEntity.getPuestoGeneral()
							.getIdRama().getNombreRamaPuesto());
				}
				dto.setNombrePuestoGeneral(tabuladorEntity.getPuestoGeneral()
						.getPuesto());
			}

			if (tabuladorEntity.getTipoTabulador() != null) {
				dto.setIdTipoTabulador(tabuladorEntity.getTipoTabulador()
						.getIdTipoTabulador());
				dto.setDescripcionTipoTabulador(tabuladorEntity
						.getTipoTabulador().getDescripcion());
			}

			if (tabuladorEntity.getEjercicioFiscal().compareTo(new Integer(0)) > 0) {

				EjercicioFiscalEntity ejercicioFiscalEntity = ejercicioFiscalRepository
						.obtenerEjercioPorEjercicionFiscal(tabuladorEntity
								.getEjercicioFiscal());

				if (ejercicioFiscalEntity != null) {

					dto.setIdEjercicioFiscal(ejercicioFiscalEntity
							.getIdEjercicioFiscal());
					dto.setEjercicioFiscal(ejercicioFiscalEntity
							.getEjercicioFiscal());
				}

			}

			if (tabuladorEntity.getTipoTabulador().getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
				dto.setSueldoBrutoMensual(tabuladorEntity
						.getSueldoBrutoMensual());
				dto.setAsignacionBrutaMensual(tabuladorEntity
						.getAsignacionBrutaMensual());
				dto.setAgaBrutaMensual(tabuladorEntity.getAgaBrutaMensual());
				dto.setTotalBrutoMensual(tabuladorEntity.getTotalBrutoMensual());
			} else if (tabuladorEntity.getTipoTabulador().getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
				dto.setSueldoBaseMensualMinimo(tabuladorEntity
						.getSueldoBaseMensualMinimo());
				dto.setSueldoBaseMensualMedio(tabuladorEntity
						.getSueldoBaseMensualMedio());
				dto.setSueldoBaseMensualMaximo(tabuladorEntity
						.getSueldoBaseMensualMaximo());
				dto.setSubClasificacion(tabuladorEntity.getSubclasificacion() == null ? ""
						: tabuladorEntity.getSubclasificacion()
								.getSubclasificacion());
				dto.setIdSubClasificacion(tabuladorEntity.getSubclasificacion() == null ? 0
						: tabuladorEntity.getSubclasificacion()
								.getIdSubclasificacion());

			}
		}
		return dto;
	}
}
