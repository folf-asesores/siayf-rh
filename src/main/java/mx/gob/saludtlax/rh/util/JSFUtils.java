package mx.gob.saludtlax.rh.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JSFUtils {

        private JSFUtils() {
        }
    
        public static Object getManagedBean(final String beanName) {
    	    Object bean;
    	    try {
    	        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	        bean = elContext.getELResolver().getValue(elContext, null, beanName);
    		}catch (RuntimeException e) {
    	        throw new FacesException(e.getMessage(), e);
    	    }
    	    if (bean == null) {
    	        throw new FacesException("Managed bean with name '" + beanName + "' was not found. Check your faces-config.xml or @ManagedBean annotation.");
    	    }
    	    return bean;
    	}

    	public static Object getService(final String service) {
    		Object obj= new Object();
    		
    			try {
					obj= (new InitialContext()).lookup("java:global/siayf-rh/" + service);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
    		return obj;
    	}

	public static void infoMessage(String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static void warningMessage(String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static void errorMessage(String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static void errorMessages(String control, String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(control, facesMessage);
		if (control != null) {
			atributoInvalido(control);
		}
	}

	public static void warningMessageEspecifico(String clientId, String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
	}

	public static void errorMessageEspecifico(String clientId, String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
	}

	public static void infoMessageEspecifico(String clientId, String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
	}

	private static UIInput atributoInvalido(String atributo) {
		UIInput field = (UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(atributo);
		if (field != null) {
			field.setValid(false);
			return field;
		}
		return null;
	}

	public static void redireccionarInterna(String url) throws IOException {
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
		System.out.println("contextPath:: " + contextPath);
		FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);

	}
        
	public static void redireccionar(String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);

	}
        
        /**
	 * Este método permite la descargar de archivo para el usuario.
	 * 
	 * <p>
	 * <b>Nota:</b> En la vista se debe de deshabilitar el AJAX al llamar este
	 * método.
	 * </p>
         * 
	 * @param bytes
	 *            el arreglo de bytes que representa el archivo.
	 * @param nombreArchivo
	 *            el nombre que tendrá el archivo sin extensión.
         * @param contentType
	 *            el tipo de MIME Type del archivo.
         * @throws IOException 
         */
	public static void descargarArchivo(byte[] bytes, String nombreArchivo,
			String contentType) throws IOException {
            TipoArchivo extension = TipoArchivo.getTipoArchivoPorMIMEType(contentType);
            descargarArchivo(bytes, nombreArchivo, extension);
        }
        
        /**
	 * Este método permite la descargar de archivo para el usuario.
	 * 
	 * <p>
	 * <b>Nota:</b> En la vista se debe de deshabilitar el AJAX al llamar este
	 * método.
	 * </p>
	 * @param bytes
	 *            el arreglo de bytes que representa el archivo.
	 * @param nombreArchivo
	 *            el nombre que tendrá el archivo sin extensión.
	 * @param extension
	 *            la extensión del archivo.
	 * @throws IOException
	 * @see http
	 *      ://stackoverflow.com/questions/9391838/how-to-provide-a-file-download
	 *      -from-a-jsf-backing-bean
	 */
	public static void descargarArchivo(byte[] bytes, String nombreArchivo,
			TipoArchivo extension) throws IOException {

		if (bytes == null) {
			throw new IllegalArgumentException(
					"No puede enviar un valor vacio para descarga.");
		}

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
                        ExternalContext ec = fc.getExternalContext();

			ec.responseReset();
			ec.setResponseContentType(extension.getMIMEType());
			ec.setResponseContentLength(bytes.length);
			ec.setResponseHeader("Content-Disposition", "attachment;filename="
					+ nombreArchivo.trim() + extension.getExtension(true));

                    try (OutputStream outputStream = ec.getResponseOutputStream()) {
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.flush();
                    }
		} finally {
			fc.responseComplete();
		}
	}
        
        /**
         * Este permite obtener la ruta en la que esta corriendo la aplicación
         * esta ruta incluye el esquema, el nombre del servidor, el puerto y
         * por supuesto el context path de la aplicación.
         * 
         * @return la ruta completa de la aplicación.
         * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
         */
        public static String getPath() {
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();

                StringBuilder sb = new StringBuilder(ec.getRequestScheme());
                sb.append("://");
                sb.append(ec.getRequestServerName());
                sb.append(':');
                sb.append(ec.getRequestServerPort());
                sb.append('/');
                sb.append(ec.getContextName());
                sb.append('/');

                return sb.toString();
        }
}