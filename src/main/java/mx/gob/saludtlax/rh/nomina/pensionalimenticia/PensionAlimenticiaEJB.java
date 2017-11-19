
package mx.gob.saludtlax.rh.nomina.pensionalimenticia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.BancoSatEntity;
import mx.gob.saludtlax.rh.persistencia.BancoSatRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaEntity;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoCoutaPensionAlimenticiaEntity;
import mx.gob.saludtlax.rh.persistencia.TipoCoutaPensionAlimenticiaRepository;

@Stateless
public class PensionAlimenticiaEJB implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7026823591324590422L;
    @Inject
    EmpleadoRepository empleadoRepository;
    @Inject
    PensionAlimenticiaRepository pensionAlimenticiaRepository;
    @Inject
    TipoCoutaPensionAlimenticiaRepository tipoCoutaRepository;
    @Inject
    private BancoSatRepository bancoSatRepository;

    public void guardar(BeneficiarioPensionAlimenticiaForm beneficiarioPension) {

        PensionAlimenticiaEntity pensionAlimenticiaEntity = new PensionAlimenticiaEntity();

        pensionAlimenticiaEntity.setBeneficiario(beneficiarioPension.beneficiario);
        pensionAlimenticiaEntity.setNumeroCuentaBancaria(beneficiarioPension.getNumeroCuentaBancaria());

        if (beneficiarioPension.idBanco > 0) {

            BancoSatEntity bancoSatEntity = bancoSatRepository.obtenerPorId(beneficiarioPension.idBanco);

            if (bancoSatEntity != null) {
                pensionAlimenticiaEntity.setBanco(bancoSatEntity);
            }
        }

        pensionAlimenticiaEntity.setEstatus(1);
        pensionAlimenticiaEntity.setFechaAlta(new Date());
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(beneficiarioPension.getIdEmpleado());
        pensionAlimenticiaEntity.setEmpleado(empleado);
        TipoCoutaPensionAlimenticiaEntity tipoCoutaAlimenticia = tipoCoutaRepository.obtenerPorId(beneficiarioPension.getIdTipoCoutaPension());
        pensionAlimenticiaEntity.setTipoCoutaAlimenticia(tipoCoutaAlimenticia);
        pensionAlimenticiaEntity.setNumeroExpediente(beneficiarioPension.getNumeroExpediente());
        pensionAlimenticiaEntity.setNumeroJuzgado(beneficiarioPension.getNumeroJuzgado());
        pensionAlimenticiaEntity.setOficio(beneficiarioPension.getOficio());
        pensionAlimenticiaEntity.setRfc(beneficiarioPension.getRfc());
        pensionAlimenticiaEntity.setValor(beneficiarioPension.valor);

        pensionAlimenticiaRepository.crear(pensionAlimenticiaEntity);

    }

    public void editar(BeneficiarioPensionAlimenticiaForm beneficiarioPension) {

        PensionAlimenticiaEntity pensionAlimenticiaEntity = pensionAlimenticiaRepository.obtenerPorId(beneficiarioPension.idPensionAlimenticia);

        pensionAlimenticiaEntity.setBeneficiario(beneficiarioPension.beneficiario);
        pensionAlimenticiaEntity.setNumeroCuentaBancaria(beneficiarioPension.getNumeroCuentaBancaria());

        if (beneficiarioPension.idBanco > 0) {

            BancoSatEntity bancoSatEntity = bancoSatRepository.obtenerPorId(beneficiarioPension.idBanco);

            if (bancoSatEntity != null) {
                pensionAlimenticiaEntity.setBanco(bancoSatEntity);
            }
        }

        if (beneficiarioPension.estatus == 2) {//inactivo
            pensionAlimenticiaEntity.setEstatus(beneficiarioPension.estatus);
            pensionAlimenticiaEntity.setFechaBaja(new Date());
        } else {//activo
            pensionAlimenticiaEntity.setEstatus(beneficiarioPension.estatus);
            pensionAlimenticiaEntity.setFechaBaja(null);
        }

        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(beneficiarioPension.getIdEmpleado());
        pensionAlimenticiaEntity.setEmpleado(empleado);
        TipoCoutaPensionAlimenticiaEntity tipoCoutaAlimenticia = tipoCoutaRepository.obtenerPorId(beneficiarioPension.getIdTipoCoutaPension());
        pensionAlimenticiaEntity.setTipoCoutaAlimenticia(tipoCoutaAlimenticia);
        pensionAlimenticiaEntity.setNumeroExpediente(beneficiarioPension.getNumeroExpediente());
        pensionAlimenticiaEntity.setNumeroJuzgado(beneficiarioPension.getNumeroJuzgado());
        pensionAlimenticiaEntity.setOficio(beneficiarioPension.getOficio());
        pensionAlimenticiaEntity.setRfc(beneficiarioPension.getRfc());
        pensionAlimenticiaEntity.setValor(beneficiarioPension.valor);

        pensionAlimenticiaRepository.actualizar(pensionAlimenticiaEntity);

    }

    public void eliminarBeneficiarioPension(int id) {

        pensionAlimenticiaRepository.eliminarPorId(id);

    }

    public InformacionEmpleadoDTO buscarEmpleado(int idEmpleado) {
        EmpleadoEntity empleadoEntity = empleadoRepository.obtenerPorId(idEmpleado);

        if (empleadoEntity == null) {
            return null;
        }
        InformacionEmpleadoDTO informacionEmpleadoDTO = new InformacionEmpleadoDTO();

        informacionEmpleadoDTO.setCurp(empleadoEntity.getCurp());
        informacionEmpleadoDTO.setIdEmpleado(empleadoEntity.getIdEmpleado());
        informacionEmpleadoDTO.setNombre(empleadoEntity.getNombreCompleto());
        informacionEmpleadoDTO.setRfc(empleadoEntity.getRfc());

        informacionEmpleadoDTO.setBeneficiarioRegistrados(llenarPensionesRegistradas(empleadoEntity.getIdEmpleado()));

        return informacionEmpleadoDTO;
    }

    public List<SelectItem> listadoTipoCoutas() {

        List<TipoCoutaPensionAlimenticiaEntity> listadoTipoCoutasPensionAlimenticiaEntity = tipoCoutaRepository.obtenerListadoTipoCoutas();

        List<SelectItem> listadoTipoCoutasSelectItem = new ArrayList<>();
        for (TipoCoutaPensionAlimenticiaEntity tipoCoutaPensionAlimenticiaEntity : listadoTipoCoutasPensionAlimenticiaEntity) {

            if (tipoCoutaPensionAlimenticiaEntity != null) {
                SelectItem tipoCoutas = new SelectItem(tipoCoutaPensionAlimenticiaEntity.getIdTipoCoutaPensionAlimenticia(),
                        tipoCoutaPensionAlimenticiaEntity.getNombre());
                tipoCoutas.setDescription(tipoCoutaPensionAlimenticiaEntity.getDescripcion());
                listadoTipoCoutasSelectItem.add(tipoCoutas);
            }

        }

        return listadoTipoCoutasSelectItem;

    }

    public List<SelectItem> listadoBanco() {
        List<BancoSatEntity> listadoBancos = bancoSatRepository.obtenerListaBanco();

        List<SelectItem> listadoBancoSelectItem = new ArrayList<>();

        for (BancoSatEntity banco : listadoBancos) {
            SelectItem item = new SelectItem(banco.getIdBanco(), banco.getNombreCorto());

            listadoBancoSelectItem.add(item);
        }

        return listadoBancoSelectItem;
    }

    public InformacionEmpleadoDTO buscarEmpleado(String rfc) {

        EmpleadoEntity empleadoEntity = empleadoRepository.obtenerEmpleadoRfc(rfc);

        if (empleadoEntity == null) {
            return null;
        }
        InformacionEmpleadoDTO informacionEmpleadoDTO = new InformacionEmpleadoDTO();

        informacionEmpleadoDTO.setCurp(empleadoEntity.getCurp());
        informacionEmpleadoDTO.setIdEmpleado(empleadoEntity.getIdEmpleado());
        informacionEmpleadoDTO.setNombre(empleadoEntity.getNombreCompleto());
        informacionEmpleadoDTO.setRfc(empleadoEntity.getRfc());

        informacionEmpleadoDTO.setBeneficiarioRegistrados(llenarPensionesRegistradas(empleadoEntity.getIdEmpleado()));
        return informacionEmpleadoDTO;
    }

    public BeneficiarioPensionAlimenticiaForm obtenerPensionAlimenticiaPorId(Integer idPensionAlimenticia) {
        BeneficiarioPensionAlimenticiaForm dto = new BeneficiarioPensionAlimenticiaForm();
        PensionAlimenticiaEntity entity = pensionAlimenticiaRepository.obtenerPorId(idPensionAlimenticia);

        dto.setIdPensionAlimenticia(entity.getIdPensionAlimenticia());
        dto.setIdEmpleado(entity.getEmpleado().getIdEmpleado());
        dto.setRfc(entity.getRfc());
        dto.setBeneficiario(entity.getBeneficiario());
        dto.setOficio(entity.getOficio());
        dto.setNumeroExpediente(entity.getNumeroExpediente());
        dto.setNumeroJuzgado(entity.getNumeroJuzgado());
        dto.setFechaAlta(entity.getFechaAlta());
        dto.setIdTipoCoutaPension(entity.getTipoCoutaAlimenticia().getIdTipoCoutaPensionAlimenticia());
        dto.setEstatus(entity.getEstatus());
        dto.setNumeroCuentaBancaria(entity.getNumeroCuentaBancaria());

        if (entity.getBanco() != null) {
            dto.setIdBanco(entity.getBanco().getIdBanco());
        }

        dto.setValor(entity.getValor());

        return dto;
    }

    private List<BeneficiarioPensionAlimienticiaDTO> llenarPensionesRegistradas(int idEmpleado) {

        List<PensionAlimenticiaEntity> listadoPensionAlimenticiaEntity = pensionAlimenticiaRepository.obtenerListadoPensionesPorIdEmpleado(idEmpleado);

        List<BeneficiarioPensionAlimienticiaDTO> listadoBeneficiariosDTO = new ArrayList<>();
        for (PensionAlimenticiaEntity pensionAlimenticiaEntity : listadoPensionAlimenticiaEntity) {

            BeneficiarioPensionAlimienticiaDTO pension = new BeneficiarioPensionAlimienticiaDTO();
            pension.setBeneficiario(pensionAlimenticiaEntity.getBeneficiario());
            if (pensionAlimenticiaEntity.getEstatus() == 1) {
                pension.setEstatus("Activo");
            } else {
                pension.setEstatus("Inactivo");
            }

            pension.setClaveCouta(pensionAlimenticiaEntity.getTipoCoutaAlimenticia().getClave());
            pension.setFechaAlta(pensionAlimenticiaEntity.getFechaAlta());
            pension.setFechaBaja(pensionAlimenticiaEntity.getFechaBaja());
            pension.setIdPensionAlimenticia(pensionAlimenticiaEntity.getIdPensionAlimenticia());
            pension.setNumeroExpediente(pensionAlimenticiaEntity.getNumeroExpediente());
            pension.setNumeroJuzgado(pensionAlimenticiaEntity.getNumeroJuzgado());
            pension.setOficio(pensionAlimenticiaEntity.getOficio());
            pension.setRfc(pensionAlimenticiaEntity.getRfc());
            pension.setValor(pensionAlimenticiaEntity.getValor());
            pension.setIdTipoCoutasPension(pensionAlimenticiaEntity.getTipoCoutaAlimenticia().getIdTipoCoutaPensionAlimenticia());
            listadoBeneficiariosDTO.add(pension);

        }

        return listadoBeneficiariosDTO;

    }
}