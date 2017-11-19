
package mx.gob.saludtlax.rh.reporteslaborales.formatoaltaissste;

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

/**
 * Servlet implementation class FormatoAltaIsssteServlet
 */
@WebServlet("/FormatoAltaIsssteServlet")
public class FormatoAltaIsssteServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -3180334720127531888L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormatoAltaIsssteServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        Integer idEmpleado = Integer
                .parseInt(request.getParameter("idEmpleado"));

        byte[] bytes = doReport(idEmpleado);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-Disposition",
                "inline;filename=" + "Formato_Altas_ISSSTE.pdf");
        servletOutputStream.write(bytes, 0, bytes.length);
        servletOutputStream.flush();
        servletOutputStream.close();
    }

    private byte[] doReport(Integer idEmpleado) {
        byte[] bytes = null;

        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(
                        "/reportes/REPORTE_FORMATO_ALTAS_ISSSTE.jasper");
        Connection conexion = null;
        try {
            Context initcontext = new InitialContext();
            DataSource ds = (DataSource) initcontext
                    .lookup("java:jboss/datasources/SIAYFRHDS");

            conexion = ds.getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("ID_EMPLEADO", idEmpleado);

            bytes = JasperRunManager.runReportToPdf(inputStream, parameters,
                    conexion);

        } catch (NamingException ex) {
            System.err.println(
                    "Error al cargar tratar de resolver el nombre: java:jboss/datasources/SIAYFRHDS"
                            + getClass().getName());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println(
                    "Error al tratar obtener la conexiÃ³n con la base de datos en "
                            + getClass().getName());
            ex.printStackTrace();
        } catch (JRException ex) {
            System.err.println("Error durante la generación del reporte ");
            ex.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println(
                            "Error al tratar cerrar la conexiÃ³n con la base de datos en "
                                    + getClass().getName());
                    e.printStackTrace();
                }
            }
        }

        return bytes;
    }

}
