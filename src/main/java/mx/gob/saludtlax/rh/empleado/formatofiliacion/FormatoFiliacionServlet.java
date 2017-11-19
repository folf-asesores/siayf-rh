/**
 * Copyright © 2016
 */

package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 14:38:46 13/10/2016
 */
@WebServlet("/FormatoFiliacionServlet")
public class FormatoFiliacionServlet extends HttpServlet {

    /**
    * 
    */
    private static final long serialVersionUID = 3946310335496722188L;
    private static final Logger LOGGER = Logger.getLogger(FormatoFiliacionServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));

        byte[] bytes = doReport(idEmpleado);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-Disposition", "inline;filename=" + "Formato_Filiacion.pdf");
        servletOutputStream.write(bytes, 0, bytes.length);
        servletOutputStream.flush();
        servletOutputStream.close();
    }

    private byte[] doReport(Integer idEmpleado) {
        byte[] bytes = null;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/reportes/FORMATO_FILIACION.jasper");
        Connection conexion = null;
        try {
            Context initcontext = new InitialContext();
            DataSource ds = (DataSource) initcontext.lookup("java:jboss/datasources/SIAYFRHDS");

            conexion = ds.getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("ID_EMPLEADO", idEmpleado);

            bytes = JasperRunManager.runReportToPdf(inputStream, parameters, conexion);

        } catch (NamingException ex) {
            System.err.println("Error al cargar tratar de resolver el nombre: java:jboss/datasources/SIAYFRHDS" + getClass().getName());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Error al tratar obtener la conexión con la base de datos en " + getClass().getName());
            ex.printStackTrace();
        } catch (JRException ex) {
            System.err.println("Error durante la generación del reporte ");
            ex.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al tratar cerrar la conexión con la base de datos en " + getClass().getName());
                    e.printStackTrace();
                }
            }
        }

        return bytes;
    }
}
