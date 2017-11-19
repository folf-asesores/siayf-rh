
package mx.gob.saludtlax.rh.empleado.issste;

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
 * Servlet implementation class AdministracionMovimientoIsssteServlet
 */
@WebServlet("/AdministracionMovimientoIsssteServlet")
public class AdministracionMovimientoIsssteServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -3304606871154820598L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministracionMovimientoIsssteServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String accion = "";
        String nombrePdf = "";

        Integer idMovimientoIssste = Integer
                .parseInt(request.getParameter("idMovimientoIssste"));
        Integer idAccionMovimientoIssste = Integer
                .parseInt(request.getParameter("idAccionMovimientoIssste"));
        Integer idTipoMovimientoIssste = Integer
                .parseInt(request.getParameter("idTipoMovimientoIssste"));

        if (idAccionMovimientoIssste
                .equals(EnumAccionMovimientoIssste.VISUALIZAR)) {
            accion = "inline;filename=";
        }

        if (idAccionMovimientoIssste
                .equals(EnumAccionMovimientoIssste.DESCARGAR)) {
            accion = "attachment;filename=";
        }

        if (idTipoMovimientoIssste
                .equals(EnumTipoMovimientoIssste.ALTA_TRABAJADOR)) {
            nombrePdf = "Formato_Altas_ISSSTE.pdf";
        }

        if (idTipoMovimientoIssste
                .equals(EnumTipoMovimientoIssste.BAJA_ISSSTE)) {
            nombrePdf = "Formato_Bajas_ISSSTE.pdf";
        }

        if (idTipoMovimientoIssste
                .equals(EnumTipoMovimientoIssste.MODIFICACIÓN_SUELDO)) {
            nombrePdf = "Formato_Modificacion_ISSSTE.pdf";
        }

        byte[] bytes = doReport(idMovimientoIssste, idTipoMovimientoIssste);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-Disposition", accion + nombrePdf);
        servletOutputStream.write(bytes, 0, bytes.length);
        servletOutputStream.flush();
        servletOutputStream.close();
    }

    private byte[] doReport(Integer idMovimientoIssste,
            Integer idTipoMovimientoIssste) {
        byte[] bytes = null;

        String rutaReporte = "";

        if (idTipoMovimientoIssste
                .equals(EnumTipoMovimientoIssste.ALTA_TRABAJADOR)) {
            rutaReporte = "/reportes/REPORTE_FORMATO_ALTAS_ISSSTE.jasper";
        }

        if (idTipoMovimientoIssste
                .equals(EnumTipoMovimientoIssste.BAJA_ISSSTE)) {
            rutaReporte = "/reportes/REPORTE_FORMATO_BAJAS_ISSSTE.jasper";
        }

        if (idTipoMovimientoIssste
                .equals(EnumTipoMovimientoIssste.MODIFICACIÓN_SUELDO)) {
            rutaReporte = "/reportes/REPORTE_FORMATO_MODIFICACION_ISSSTE.jasper";
        }

        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(rutaReporte);
        Connection conexion = null;
        try {
            Context initcontext = new InitialContext();
            DataSource ds = (DataSource) initcontext
                    .lookup("java:jboss/datasources/SIAYFRHDS");

            conexion = ds.getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("ID_MOVIMIENTO_ISSSTE", idMovimientoIssste);

            bytes = JasperRunManager.runReportToPdf(inputStream, parameters,
                    conexion);

        } catch (NamingException ex) {
            System.err.println(
                    "Error al cargar tratar de resolver el nombre: java:jboss/datasources/SIAYFRHDS"
                            + getClass().getName());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println(
                    "Error al tratar obtener la conexion con la base de datos en "
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
                            "Error al tratar cerrar la conexion con la base de datos en "
                                    + getClass().getName());
                    e.printStackTrace();
                }
            }
        }

        return bytes;
    }

}
