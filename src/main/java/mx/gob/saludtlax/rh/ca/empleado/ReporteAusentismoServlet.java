
package mx.gob.saludtlax.rh.ca.empleado;

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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "reporteAusentismoServlet", urlPatterns = { "/reporte-ausentismo_empleado", "/reporte-ausentismo_empleado" })
public class ReporteAusentismoServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5073900726653316038L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        Integer idAdscripcion = Integer.parseInt(request.getParameter("ida"));

        byte[] bytes = doReport(fechaInicio, fechaFin, idAdscripcion);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        servletOutputStream.write(bytes, 0, bytes.length);
        servletOutputStream.flush();
        servletOutputStream.close();
    }

    private byte[] doReport(String fechaInicio, String fechaFin, Integer idAdscripcion) {
        byte[] bytes = null;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/reportes/reporte_ausentismo.jasper");
        Connection conexion = null;
        try {
            Context initcontext = new InitialContext();
            DataSource ds = (DataSource) initcontext.lookup("java:jboss/datasources/SIAYFRHDS");

            conexion = ds.getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("fecha_inicio", fechaInicio);
            parameters.put("fecha_fin", fechaFin);
            parameters.put("unidad_administrativa", idAdscripcion);

            bytes = JasperRunManager.runReportToPdf(inputStream, parameters, conexion);

        } catch (NamingException ex) {
            System.err.println("Error al cargar tratar de resolver el nombre: java:jboss/datasources/SIAYFRHDS" + getClass().getName());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Error al tratar obtener la conexiÃ³n con la base de datos en " + getClass().getName());
            ex.printStackTrace();
        } catch (JRException ex) {
            System.err.println("Error durante la generación del reporte ");
            ex.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al tratar cerrar la conexiÃ³n con la base de datos en " + getClass().getName());
                    e.printStackTrace();
                }
            }
        }

        return bytes;
    }

}
