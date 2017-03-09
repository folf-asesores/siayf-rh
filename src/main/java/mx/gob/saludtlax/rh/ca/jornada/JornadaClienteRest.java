package mx.gob.saludtlax.rh.ca.jornada;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.excepciones.RESTClientJornadaException;
import mx.gob.saludtlax.rh.excepciones.RESTClientReglaAsistenciaJornadaException;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

public class JornadaClienteRest extends ClienteRest implements Serializable {

	public JornadaClienteRest() {
		super(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656431642454175625L;

	static Logger log = Logger.getLogger(Configuracion.LOGGER_CONTROL_ASISTENCIA);

	private final String RESOURCE_JORNADA_NUEVO = "/jornada/agregar";

	private final String RESOURCE_JORNADA_ACTUALIZAR = "/jornada/actualizar";

	private final String RESOURCE_REGLAASISTENCIA_NUEVO = "/jornada/regla/asistencia/agregar";

	private final String RESOURCE_REGLA_ASISTENCIA_ELIMINAR = "/jornada/regla/asistencia/eliminar/";

	private final String RESOURCE_REGLAASISTENCIA_ID_JORNADA = "/jornada/regla/asistencia/buscar/id/jornada/";

	private final String RESOURCE_HORARIO_ID_JORNADA = "/jornada/horario/buscar/id/jornada/";

	private final String RESOURCE_JORNADA_LISTADO = "/jornada/listado";

	private final String RESOURCE_JORNADA_ID = "/jornada/id/";

	private final String RESOURCE_HORARIO_JORNADA_AGREGAR = "/jornada/horario/agregar";

	private final String RESOURCE_HORARIO_JORNADA_ELIMINAR = "/jornada/horario/eliminar/";

	public JornadaFormModel obtenerJornadaPorId(Integer idJornada) throws RESTClientException {
		JornadaFormModel jornadaFormModel = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.debug(url_serivicio + RESOURCE_JORNADA_ID + idJornada);

		HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_JORNADA_ID + idJornada);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

			log.debug("Estatus:" + servicioResponse.getStatusLine().getStatusCode()
					+ servicioResponse.getStatusLine().getReasonPhrase());

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:

				String resultJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultJornada);

				Gson jornadaGson = new Gson();
				jornadaFormModel = jornadaGson.fromJson(resultJornada, JornadaFormModel.class);

				log.debug(jornadaFormModel);

				break;
			case 400:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:

				log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
						+ " " + servicioResponse.getStatusLine().toString());

				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());

			}

		} catch (IOException e) {

			log.error(e.toString());
			log.error(e.getMessage());
			log.error(e.getCause());

			throw new RESTClientException(e.getMessage());
		}

		return jornadaFormModel;
	}

	public void eliminarReglaAsistencia(Integer idRegla) throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.debug(url_serivicio + RESOURCE_REGLA_ASISTENCIA_ELIMINAR + idRegla);

		HttpDelete httpDelete = new HttpDelete(url_serivicio + RESOURCE_REGLA_ASISTENCIA_ELIMINAR + idRegla);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpDelete);

			log.debug("Estatus:" + servicioResponse.getStatusLine().getStatusCode()
					+ servicioResponse.getStatusLine().getReasonPhrase());

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:

				break;
			case 400:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:

				log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
						+ " " + servicioResponse.getStatusLine().toString());

				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());

			}

		} catch (IOException e) {

			log.error(e.toString());
			log.error(e.getMessage());
			log.error(e.getCause());

			throw new RESTClientException(e.getMessage());
		}

	}

	public void eliminarHorarioJornada(Integer idHorarioJornada) throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.debug(url_serivicio + RESOURCE_HORARIO_JORNADA_ELIMINAR + idHorarioJornada);

		HttpDelete httpDelete = new HttpDelete(url_serivicio + RESOURCE_HORARIO_JORNADA_ELIMINAR + idHorarioJornada);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpDelete);

			log.debug("Estatus:" + servicioResponse.getStatusLine().getStatusCode()
					+ servicioResponse.getStatusLine().getReasonPhrase());

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:

				break;
			case 400:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:

				log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
						+ " " + servicioResponse.getStatusLine().toString());

				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());

			}

		} catch (IOException e) {

			log.error(e.toString());
			log.error(e.getMessage());
			log.error(e.getCause());

			throw new RESTClientException(e.getMessage());
		}

	}

	public List<ReglaAsistenciaViewModel> listadoReglasAsistenciaPorJornada(Integer idJornada)
			throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		List<ReglaAsistenciaViewModel> listadoReglaAsistenciaViewModel = new ArrayList<ReglaAsistenciaViewModel>();

		log.debug(url_serivicio + RESOURCE_REGLAASISTENCIA_ID_JORNADA + idJornada);
		HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_REGLAASISTENCIA_ID_JORNADA + idJornada);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				String resultReglaAsistencia = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultReglaAsistencia);
				Gson incidenciaGson = new Gson();
				TypeToken<ArrayList<ReglaAsistenciaViewModel>> tokenListadoReglaJornada = new TypeToken<ArrayList<ReglaAsistenciaViewModel>>() {
				};
				listadoReglaAsistenciaViewModel = incidenciaGson.fromJson(resultReglaAsistencia,
						tokenListadoReglaJornada.getType());
				log.debug(listadoReglaAsistenciaViewModel);

				break;
			case 400:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:
				log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
						+ " " + servicioResponse.getStatusLine().toString());
				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());

			}

		} catch (IOException e) {
			log.error(e.toString());
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientException(e.getMessage());
		}

		return listadoReglaAsistenciaViewModel;

	}

	public List<HorarioJornadaViewModel> listadoHorarioPorJornada(Integer idHorario) throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		List<HorarioJornadaViewModel> listadoHorarioJornadaViewModel = new ArrayList<HorarioJornadaViewModel>();

		log.debug(url_serivicio + RESOURCE_HORARIO_ID_JORNADA + idHorario);
		HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_HORARIO_ID_JORNADA + idHorario);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				String resultHorarioJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultHorarioJornada);
				Gson horarioJornadaGson = new Gson();
				TypeToken<ArrayList<HorarioJornadaViewModel>> tokenListadoHorarioJornada = new TypeToken<ArrayList<HorarioJornadaViewModel>>() {
				};
				listadoHorarioJornadaViewModel = horarioJornadaGson.fromJson(resultHorarioJornada,
						tokenListadoHorarioJornada.getType());
				log.debug(listadoHorarioJornadaViewModel);

				break;
			case 400:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:
				log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
						+ " " + servicioResponse.getStatusLine().toString());
				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());

			}

		} catch (IOException e) {
			log.error(e.toString());
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientException(e.getMessage());
		}

		return listadoHorarioJornadaViewModel;

	}

	public List<JornadaFormModel> listadoJornada() throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		List<JornadaFormModel> listadoJornadaViewModel = new ArrayList<JornadaFormModel>();

		log.debug(url_serivicio + RESOURCE_JORNADA_LISTADO);
		HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_JORNADA_LISTADO);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				String resultJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultJornada);
				Gson incidenciaGson = new Gson();
				TypeToken<ArrayList<JornadaFormModel>> tokenListadoJornada = new TypeToken<ArrayList<JornadaFormModel>>() {
				};
				listadoJornadaViewModel = incidenciaGson.fromJson(resultJornada, tokenListadoJornada.getType());
				log.debug(listadoJornadaViewModel);

				break;
			case 400:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:
				log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
						+ " " + servicioResponse.getStatusLine().toString());
				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());

			}

		} catch (IOException e) {
			log.error(e.toString());
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientException(e.getMessage());
		}

		return listadoJornadaViewModel;

	}

	/**
	 * Guarda una nueva jornada.
	 * 
	 * @param jornadaFormModel
	 *            Datos de la jornada que se va a guardar.
	 * 
	 * @return Identificador unico de la jornada.
	 * @throws RESTClientJornadaException
	 *             En caso que ocurra un error al momento de guardar la
	 *             informacion de la jornada.
	 * @throws RESTClientReglaAsistenciaJornadaException
	 *             En caso que ocurra un error al guarda las reglas de
	 *             asistencia.
	 */
	public Integer nuevaJornada(JornadaFormModel jornadaFormModel)
			throws RESTClientJornadaException, RESTClientReglaAsistenciaJornadaException {

		Integer idJornada = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.debug(url_serivicio + RESOURCE_JORNADA_NUEVO);

		HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_JORNADA_NUEVO);

		Gson gson = new Gson();
		StringEntity nuevaJornadaJSON;
		try {
			nuevaJornadaJSON = new StringEntity(gson.toJson(jornadaFormModel));
			nuevaJornadaJSON.setContentType("application/json");
			log.debug(nuevaJornadaJSON);

			httpPost.setEntity(nuevaJornadaJSON);

			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultNuevaJornada;

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevaJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultNuevaJornada);
				idJornada = new Integer(resultNuevaJornada);
				break;
			case 400:
				resultNuevaJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.error(resultNuevaJornada);
				throw new RESTClientJornadaException(resultNuevaJornada);

			default:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				log.error(servicioResponse.getStatusLine().getStatusCode());
				throw new RESTClientJornadaException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());

			}

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		}

		log.debug("Termina de guardar las reglas de asistencia de la jornada: " + idJornada);

		return idJornada;

	}

	public Integer nuevoHorarioJornada(HorarioJornadaFormModel horarioJornadaFormModel)
			throws RESTClientJornadaException, RESTClientReglaAsistenciaJornadaException {

		Integer idHorarioJornada = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.debug(url_serivicio + RESOURCE_HORARIO_JORNADA_AGREGAR);

		HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_HORARIO_JORNADA_AGREGAR);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		StringEntity nuevoHorarioJSON;
		try {
			nuevoHorarioJSON = new StringEntity(gson.toJson(horarioJornadaFormModel));
			nuevoHorarioJSON.setContentType("application/json");
			
			
			log.debug(nuevoHorarioJSON);

			httpPost.setEntity(nuevoHorarioJSON);

			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultNuevoHorarioJornada;

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevoHorarioJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultNuevoHorarioJornada);
				idHorarioJornada = new Integer(resultNuevoHorarioJornada);
				break;
			case 400:
				resultNuevoHorarioJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.error(resultNuevoHorarioJornada);
				throw new RESTClientJornadaException(resultNuevoHorarioJornada);

			default:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				log.error(servicioResponse.getStatusLine().getStatusCode());
				throw new RESTClientJornadaException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());

			}

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		}

		log.debug("Termina de guardar el horario de la jornada: " + idHorarioJornada);

		return idHorarioJornada;

	}

	/**
	 * Guarda una nueva jornada.
	 * 
	 * @param jornadaFormModel
	 *            Datos de la jornada que se va a guardar.
	 * 
	 * @return Identificador unico de la jornada.
	 * @throws RESTClientJornadaException
	 *             En caso que ocurra un error al momento de guardar la
	 *             informacion de la jornada.
	 * @throws RESTClientReglaAsistenciaJornadaException
	 *             En caso que ocurra un error al guarda las reglas de
	 *             asistencia.
	 */
	public Integer actuializarJornada(JornadaFormModel jornadaFormModel)
			throws RESTClientJornadaException, RESTClientReglaAsistenciaJornadaException {

		Integer idJornada = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.debug(url_serivicio + RESOURCE_JORNADA_ACTUALIZAR);

		HttpPut httpPut = new HttpPut(url_serivicio + RESOURCE_JORNADA_ACTUALIZAR);

		Gson gson = new Gson();
		StringEntity jornadaJSON;
		try {
			jornadaJSON = new StringEntity(gson.toJson(jornadaFormModel));
			jornadaJSON.setContentType("application/json");
			log.debug(jornadaJSON);

			httpPut.setEntity(jornadaJSON);

			CloseableHttpResponse servicioResponse = httpClient.execute(httpPut);

			String resultJornada;

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultJornada);
				idJornada = new Integer(resultJornada);
				break;
			case 400:
				resultJornada = EntityUtils.toString(servicioResponse.getEntity());
				log.error(resultJornada);
				throw new RESTClientJornadaException(resultJornada);

			default:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				log.error(servicioResponse.getStatusLine().getStatusCode());
				throw new RESTClientJornadaException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());

			}

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientJornadaException(e.getMessage());
		}

		log.debug("Termina de guardar las reglas de asistencia de la jornada: " + idJornada);

		return idJornada;

	}

	/**
	 * Guarda la informacion de una regla de asistencia.
	 * 
	 * @param reglaAsistencia
	 *            Regla que se aplica para la jornada.
	 * @return Identificador de la jornada.
	 * @throws RESTClientException
	 *             en caso que ocurra un error al momento de persistir la
	 *             informacion.
	 */
	public Integer nuevaReglaAsistenciaJornada(ReglaAsistenciaJornadaFormModel reglaAsistencia)
			throws RESTClientException {

		Integer idReglaAsistenciaJornada = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.debug(url_serivicio + RESOURCE_REGLAASISTENCIA_NUEVO);

		HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_REGLAASISTENCIA_NUEVO);

		Gson gson = new Gson();
		StringEntity nuevaReglaAsistenciaJSON;

		try {
			nuevaReglaAsistenciaJSON = new StringEntity(gson.toJson(reglaAsistencia));
			nuevaReglaAsistenciaJSON.setContentType("application/json");
			log.debug(nuevaReglaAsistenciaJSON);
			httpPost.setEntity(nuevaReglaAsistenciaJSON);
			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultNuevaReglaAsistencia;
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevaReglaAsistencia = EntityUtils.toString(servicioResponse.getEntity());
				log.debug(resultNuevaReglaAsistencia);
				idReglaAsistenciaJornada = new Integer(resultNuevaReglaAsistencia);
				break;
			case 400:
				resultNuevaReglaAsistencia = EntityUtils.toString(servicioResponse.getEntity());
				log.error(resultNuevaReglaAsistencia);
				throw new RESTClientException(resultNuevaReglaAsistencia);
			default:
				log.error(servicioResponse.getStatusLine().getReasonPhrase());
				log.error(servicioResponse.getStatusLine().getStatusCode());
				throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientException(e.getMessage());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.getCause());
			throw new RESTClientException(e.getMessage());
		}

		return idReglaAsistenciaJornada;

	}

}
