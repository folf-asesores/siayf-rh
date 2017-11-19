package mx.gob.saludtlax.rh.nomina.timbrado.cancelar;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.CertificadoSelloDigitalEntity;
import mx.gob.saludtlax.rh.persistencia.CertificadoSelloDigitalRepository;
import mx.gob.saludtlax.rh.persistencia.ComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEntity;
import mx.gob.saludtlax.rh.util.CadenaOriginalServices;

@Stateless
public class CancelarCFDIService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405935292700351415L;

	@Inject
	ComprobanteRepository comprobanteDAO;
	@Inject
	CancelarCFDIClientRest cancelarCFDIRest;
	@Inject
	CertificadoSelloDigitalRepository certificadoSelloDigitalRepository;
	@Inject
	CadenaOriginalServices cadenaOriginaServices;

	public ComprobanteEntity buscarCFDICancelarPorUIID(String uuid) {

		return comprobanteDAO.obtenerUUID(uuid);

	}

	public void cancelar(String uuid) throws RESTClientException {

		CertificadoSelloDigitalEntity certificadoSelloDigitalEntity = certificadoSelloDigitalRepository
				.obtenerCertificadoSelloDigitalActivo();
		InputStream certificadoDigital = new ByteArrayInputStream(certificadoSelloDigitalEntity.getCertificado());
		InputStream llaveCertificado = new ByteArrayInputStream(certificadoSelloDigitalEntity.getArchivoKey());
		try {
			byte[] llaveCertificadoByte = IOUtils.toByteArray(llaveCertificado);
			byte[] certificadoDigitalByte = IOUtils.toByteArray(certificadoDigital);
			byte[] laveBase64Byte = org.apache.commons.codec.binary.Base64.encodeBase64(llaveCertificadoByte);
			byte[] certificadoDigital64Byte = org.apache.commons.codec.binary.Base64
					.encodeBase64(certificadoDigitalByte);

			String llaveBase64 = new String(laveBase64Byte);
			String CertificadoBase64 = new String(certificadoDigital64Byte);

			String codigo = cancelarCFDIRest.cancelarCFDI(CertificadoBase64, llaveBase64, "STL961105HT8", uuid,
					certificadoSelloDigitalEntity.getClave());

			System.out.println(codigo);

			if (codigo.equals("100") || codigo.equals("201")) {
				ComprobanteEntity comprobante = comprobanteDAO.obtenerUUID(uuid);
				if (comprobante != null) {
					comprobante.setCancelado(true);
					comprobanteDAO.actualizar(comprobante);
				}
			} else {
				throw new RESTClientException("No se pudo cancelar el CFDI");
			}

		} catch (IOException e) {
			 
			e.printStackTrace();
		}

	}

}
