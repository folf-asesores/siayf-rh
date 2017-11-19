
package mx.gob.saludtlax.rh.nomina.timbrado.cancelar;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.ejb.Stateless;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

import com.google.gson.Gson;

@Stateless
public class CancelarCFDIClientRest extends ClienteRest
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8426016565192629308L;

    public CancelarCFDIClientRest() {
        super(ServicioWebEnum.CANCELACION_CFDI);

    }

    public String cancelarCFDI(String certificado, String llave, String rfc,
            String uuid, String claveLlave) throws RESTClientException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String urlTimbrado = url_serivicio;

        System.out.println(urlTimbrado);

        HttpPost httpPost = new HttpPost(urlTimbrado);
        CancelacionCFDIPeticion cancelarPeticion = new CancelacionCFDIPeticion();
        cancelarPeticion.setCertificado(certificado);
        cancelarPeticion.setLlave(llave);
        cancelarPeticion.setPassword(clave);
        cancelarPeticion.setPassword_llave(claveLlave);
        cancelarPeticion.setRfc(rfc);
        cancelarPeticion.setUser(usuario);
        cancelarPeticion.setUuid(uuid);

        Gson gson = new Gson();
        StringEntity cfdiPeticionJSON;
        CancelarCFDIRespuesta cfdiRespuesta = null;

        try {
            cfdiPeticionJSON = new StringEntity(gson.toJson(cancelarPeticion));

            cfdiPeticionJSON.setContentType("application/json");
            httpPost.setEntity(cfdiPeticionJSON);
            CloseableHttpResponse servicioResponse = httpClient
                    .execute(httpPost);

            String resultTimbradoCFDI;
            Gson timbradoCFDIGson;

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultTimbradoCFDI = EntityUtils
                            .toString(servicioResponse.getEntity());

                    timbradoCFDIGson = new Gson();
                    cfdiRespuesta = timbradoCFDIGson.fromJson(
                            resultTimbradoCFDI, CancelarCFDIRespuesta.class);

                    System.out.println(cfdiRespuesta.getMensaje());
                    System.out.println(cfdiRespuesta.getCodigo());
                    return cfdiRespuesta.getCodigo();
                case 201:
                    resultTimbradoCFDI = EntityUtils
                            .toString(servicioResponse.getEntity());

                    timbradoCFDIGson = new Gson();
                    cfdiRespuesta = timbradoCFDIGson.fromJson(
                            resultTimbradoCFDI, CancelarCFDIRespuesta.class);

                    System.out.println(cfdiRespuesta.getMensaje());
                    System.out.println(cfdiRespuesta.getCodigo());
                    return cfdiRespuesta.getCodigo();

                case 400:
                    resultTimbradoCFDI = EntityUtils
                            .toString(servicioResponse.getEntity());

                    throw new RESTClientException(resultTimbradoCFDI);
                case 502:
                    System.out.println(
                            "Error 502 al Timbrar Intentando de Nuevo");
                    break;
                default:

                    throw new RESTClientException(
                            servicioResponse.getStatusLine().getStatusCode()
                                    + " " + servicioResponse.getStatusLine()
                                            .getReasonPhrase());

            }
            return "";
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            return "";
        } catch (ClientProtocolException e) {

            e.printStackTrace();
            return "";
        } catch (IOException e) {

            e.printStackTrace();
            return "";
        }

    }

}
