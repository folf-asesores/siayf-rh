
package mx.gob.saludtlax.rh.seguridad.autenticacion;

import java.io.Serializable;
import java.util.Calendar;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.IpBloqueadaEntity;
import mx.gob.saludtlax.rh.persistencia.IpBloqueadaRepository;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;

public class IpBloqueadaService implements Serializable {

    /** Valor de serialización. */
    private static final long serialVersionUID = -5489760917153048835L;

    @Inject
    private IpBloqueadaRepository ipRepository;

    /**
     * Permite saber si una IP está bloqueda.
     *
     * @param ip
     *            la IP que será validada.
     * @return <code>true</code> si la IP está bloqueada en caso contrario
     *         <code>false</code>.
     */
    protected boolean ipEstaBloqueada(String ip) {
        IpBloqueadaEntity ipBloqueada = ipRepository.obtenerIpBloqueada(ip);

        if (ipBloqueada != null) {
            Calendar fechaExpiraBloqueo = Calendar.getInstance();
            fechaExpiraBloqueo.setTime(ipBloqueada.getFechaExpira());
            Calendar horaExpiraBloqueo = Calendar.getInstance();
            horaExpiraBloqueo.setTime(ipBloqueada.getHoraExpira());

            fechaExpiraBloqueo.set(Calendar.HOUR_OF_DAY,
                    horaExpiraBloqueo.get(Calendar.HOUR_OF_DAY));
            fechaExpiraBloqueo.set(Calendar.MINUTE,
                    horaExpiraBloqueo.get(Calendar.MINUTE));
            fechaExpiraBloqueo.set(Calendar.SECOND,
                    horaExpiraBloqueo.get(Calendar.SECOND));

            Calendar fechaActual = Calendar.getInstance();

            return fechaActual.compareTo(fechaExpiraBloqueo) < 0;
        } else {
            return false;
        }
    }

    /**
     * Registra una IP que será bloqueada al superar el número máximo de
     * intentos ({@link ConfiguracionConst#LIMITE_DE_INTENTOS_DE_INICIO_SESION})
     * de inicio de sesión.
     *
     * @param direccionIp
     *            la IP que será bloqueada.
     */
    protected void registrarIpBloqueada(String direccionIp) {
        IpBloqueadaEntity ipBloqueada = new IpBloqueadaEntity();
        Calendar fechaExpira = Calendar.getInstance();

        fechaExpira.add(Calendar.MINUTE, 30);

        ipBloqueada.setFechaExpira(fechaExpira.getTime());
        ipBloqueada.setHoraExpira(fechaExpira.getTime());
        ipBloqueada.setDireccionIp(direccionIp);

        ipRepository.crear(ipBloqueada);
    }
}
