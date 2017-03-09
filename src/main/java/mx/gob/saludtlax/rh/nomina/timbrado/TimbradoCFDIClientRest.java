package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipOutputStream;

import javax.ejb.Stateless;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

import com.google.gson.Gson;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
@Stateless
public class TimbradoCFDIClientRest extends ClienteRest implements Serializable {

	public TimbradoCFDIClientRest() {
		super(ServicioWebEnum.FACTURACION_ELECTRONICA);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1470996607543536812L;
	static Logger log = Logger.getLogger(Configuracion.LOGGER_TIMBRADO);

	/**
	 * Realiza el timbrado con el PAC
	 * 
	 * @param comprobante
	 *            CFDI en Base 64
	 * @return CFDIRespuest con la informacion retornada por el PAC
	 * @throws RESTClientException
	 *             en caso que ocurra un error en la comunicacion
	 */
	public CFDIRespuesta timbrarCFDI(String comprobante) throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		// String urlTimbrado = Config.DFACURA_RS_HOST_TEST;
		String urlTimbrado = url_serivicio;
		
		System.out.println(urlTimbrado);

		log.debug(urlTimbrado);

		HttpPost httpPost = new HttpPost(urlTimbrado);

		CFDIPeticion cfdiPeticion = new CFDIPeticion();
		cfdiPeticion.setPassword(this.clave);
		cfdiPeticion.setUser(this.usuario);
		cfdiPeticion.setXml(comprobante);

		Gson gson = new Gson();
		StringEntity cfdiPeticionJSON;
		CFDIRespuesta cfdiRespuesta = null;
		try {
			cfdiPeticionJSON = new StringEntity(gson.toJson(cfdiPeticion));
			cfdiPeticionJSON.setContentType("application/json");
			log.debug(cfdiPeticionJSON);
			httpPost.setEntity(cfdiPeticionJSON);

			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultTimbradoCFDI;

			
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultTimbradoCFDI = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultTimbradoCFDI);
				Gson timbradoCFDIGson = new Gson();
				cfdiRespuesta = timbradoCFDIGson.fromJson(resultTimbradoCFDI, CFDIRespuesta.class);

				break;
			case 400:
				resultTimbradoCFDI = EntityUtils.toString(servicioResponse.getEntity());
				log.error(resultTimbradoCFDI);
				throw new RESTClientException(resultTimbradoCFDI);
			case 502:
				System.out.println("Error 502 al Timbrar Intentando de Nuevo");
				cfdiRespuesta = timbrarCFDI(comprobante);
				break;
			default:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				log.error(servicioResponse.getStatusLine().getStatusCode());
				throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());

			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getLocalizedMessage());
			throw new RESTClientException(e.getMessage());
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getLocalizedMessage());
			throw new RESTClientException(e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getLocalizedMessage());
			throw new RESTClientException(e.getMessage());
		}

		return cfdiRespuesta;

	}

}
