
package mx.gob.saludtlax.rh.siif;

import static mx.gob.saludtlax.rh.util.Configuracion.DATASOURCE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;
import mx.gob.saludtlax.rh.util.Configuracion;

@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class ReporteSiifEJB {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Resource
    private UserTransaction ut;

    @Resource(mappedName = DATASOURCE)
    private DataSource ds;

    @Inject
    private ReporteSiifService reporteSiifService;

    public List<SiifBitacoraDTO> obtenerReporteSiifPorPeriodo(String periodo,
            Integer anio) {
        List<SiifBitacoraDTO> reporteSiifList = reporteSiifService
                .obtenerReporteSiifPorPeriodo(periodo, anio);
        return reporteSiifList;
    }

    //@TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
    public SiifBitacoraDTO procesarNominaTheosToSIIF(
            PaqueteEntradaFederalDTO paqueteEntrada) {
        UploadedFile dat = paqueteEntrada.getDat();
        UploadedFile tra = paqueteEntrada.getTra();
        SiifBitacoraDTO bitacora = null;
        try {
            ut.begin();
            bitacora = reporteSiifService.crearSiifBitacora(paqueteEntrada);
            bitacora = reporteSiifService.importarNominaTheosToSIIF(dat, tra,
                    bitacora);

            ut.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bitacora;
    }

    public SiifBitacoraDTO procesarNominaTheosToSIIF_2(
            SiifBitacoraDTO bitacora) {
        try {
            ut.begin();
            reporteSiifService.clasificarEncabezados2(bitacora);
            reporteSiifService.clasificarEncabezados3(bitacora);
            ut.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bitacora;
    }

    public SiifBitacoraDTO procesarNominaTheosToSIIF_3(
            List<SIIFEncabezadoDTO> result, SiifBitacoraDTO bitacora,
            PaqueteEntradaFederalDTO entrada) {
        try {
            ut.begin();
            bitacora = reporteSiifService.clasificarRegularizadosSiif(result,
                    bitacora, entrada);
            bitacora = reporteSiifService.verificarDatos(bitacora);
            bitacora = reporteSiifService
                    .asignarEncabezadosTrailersSiif(bitacora);
            ut.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bitacora;
    }

    public SiifBitacoraDTO calcularEncabezados(SiifBitacoraDTO bitacora) {
        try {
            ut.begin();
            bitacora = reporteSiifService.calcularEncabezados(bitacora);
            ut.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bitacora;
    }

    public SiifBitacoraDTO clasificaClaveConceptos(SiifBitacoraDTO bitacora)
            throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(
                    " UPDATE estructuras_nominas_trailers AS nt "
                            + "INNER JOIN	siif_conceptos_nominas AS cn ON nt.concepto_siif = cn.concepto_nomina "
                            + "SET nt.id_concepto = cn.id_siif_concepto_nomina "
                            + "WHERE nt.t_concep = cn.tipo "
                            + "AND nt.id_siif_bitacoras =?");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();

        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public SiifBitacoraDTO clasificaClaveConceptosMod(SiifBitacoraDTO bitacora)
            throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(
                    " UPDATE estructuras_nominas_trailers AS nt "
                            + "INNER JOIN    siif_conceptos_nominas AS cn ON nt.concepto_siif = cn.concepto_nomina "
                            + "SET nt.id_concepto = cn.id_siif_concepto_nomina "
                            + "WHERE nt.t_concep = cn.tipo "
                            + "AND nt.id_siif_bitacoras =?");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public SiifBitacoraDTO clasificaNominaTarjetas(SiifBitacoraDTO bitacora)
            throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement("UPDATE estructuras_nominas AS n "
                    + "INNER JOIN estructuras_nominas_trailers as t on t.rfc=n.rfc "
                    + "SET n.tipo_emision_nomina ='T' "
                    + "WHERE  n.id_siif_bitacoras =? "
                    + "AND    t.id_siif_bitacoras =? "
                    + "AND t.sub_cheque = 29");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.setInt(2, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public SiifBitacoraDTO clasificaNominaCheques(SiifBitacoraDTO bitacora)
            throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement("UPDATE estructuras_nominas AS n "
                    + "INNER JOIN estructuras_nominas_trailers as t on t.rfc=n.rfc "
                    + "SET n.tipo_emision_nomina ='C' "
                    + "WHERE  n.id_siif_bitacoras =? "
                    + "AND    t.id_siif_bitacoras =? "
                    + "AND t.sub_cheque != 29 ");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.setInt(2, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public SiifBitacoraDTO clasificaNominaTarjetas610(SiifBitacoraDTO bitacora)
            throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement("UPDATE estructuras_nominas AS n "
                    + "INNER JOIN estructuras_nominas_trailers as t on t.rfc=n.rfc "
                    + "SET n.tipo_emision_nomina ='T' "
                    + "WHERE  n.id_siif_bitacoras =? "
                    + "AND t.id_siif_bitacoras =? " + "AND t.sub_cheque != 29 "
                    + "AND n.ur='610' ");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.setInt(2, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public SiifBitacoraDTO clasificaNominaNombramientoSubfuente(
            SiifBitacoraDTO bitacora) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement("UPDATE estructuras_nominas AS n "
                    + "LEFT JOIN tipos_nombramientos_temp AS t ON n.ur = t.clave "
                    + "SET n.id_nombramiento = t.id_nombramiento, "
                    + "n.id_subfuente_financiamiento	= t.id_subfuente_financiamiento "
                    + "WHERE  n.id_siif_bitacoras =? ");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public SiifBitacoraDTO crearEncabezadosSiif(SiifBitacoraDTO bitacora)
            throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(
                    "INSERT INTO siif_encabezados( fecha_fin_quincena, id_tipo_emision_nomina, "
                            + "percepciones, deducciones, neto, id_siif_bitacora, id_nombramiento) "
                            + "SELECT	cast(n.p_pago_F as date), n.tipo_emision_nomina, "
                            + "SUM(n.per), SUM(n.ded ), SUM(n.neto ), ?, n.id_nombramiento "
                            + "FROM estructuras_nominas AS n WHERE  n.id_siif_bitacoras = ? "
                            //+ "AND n.id_nombramiento != 4 "
                            + "GROUP BY n.id_nombramiento, n.tipo_emision_nomina ");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.setInt(2, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public SiifBitacoraDTO asignarEncabezadosDats(SiifBitacoraDTO bitacora)
            throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement("UPDATE estructuras_nominas AS n "
                    + "INNER JOIN siif_encabezados AS e on e.id_siif_bitacora = n.id_siif_bitacoras "
                    + "SET n.id_siif_encabezados = e.id_siif_encabezado "
                    + "WHERE n.id_siif_bitacoras = ? "
                    + "AND n.id_nombramiento = e.id_nombramiento "
                    + "AND n.tipo_emision_nomina = e.id_tipo_emision_nomina ");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            stmt.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return bitacora;
    }

    public List<SIIFEncabezadoDTO> obtenerEncabezadosSiif(
            SiifBitacoraDTO bitacora) throws SQLException {
        List<SIIFEncabezadoDTO> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement("SELECT "
                    + " e.id_siif_encabezado AS idSIIFEncabezado, "
                    + " e.id_nomina AS idNomina, " + " e.id_poder AS idPoder, "
                    + " e.id_tipo_nomina AS idTipoNomina, "
                    + " e.fecha_fin_quincena AS fechaFinQuincena, "
                    + " e.id_tipo_emision_nomina AS idTipoEmisionNomina, "
                    + " e.id_cuenta_bancaria AS idCuentaBancaria, "
                    + " e.percepciones AS percepciones, "
                    + " e.deducciones AS deducciones, " + " e.neto AS neto, "
                    + " e.id_estado_nomina AS idEstadoNomina, "
                    + " e.id_siif_bitacora AS idSIIFBitacora, "
                    + " e.id_nombramiento AS idNombramiento, "
                    + " e.sub_programa AS subPrograma "
                    + "FROM siif_encabezados AS e "
                    + "WHERE e.id_siif_bitacora =? ");

            stmt.setInt(1, bitacora.getIdSiifBitacora());
            ResultSet rs = stmt.executeQuery();

            int i = 0;
            rs.beforeFirst();
            while (rs.next()) {
                SIIFEncabezadoDTO aux = new SIIFEncabezadoDTO();// create aux object here

                aux.setIdSIIFEncabezado(rs.getInt("idSIIFEncabezado"));
                aux.setIdNomina(rs.getInt("idNomina"));
                aux.setIdPoder(rs.getString("idPoder").charAt(0));
                aux.setIdTipoNomina(rs.getInt("idTipoNomina"));
                aux.setFechaFinQuincena(rs.getDate("fechaFinQuincena"));
                aux.setIdTipoEmisionNomina(rs.getString("idTipoEmisionNomina"));
                aux.setIdCuentaBancaria(rs.getInt("idCuentaBancaria"));
                aux.setPercepciones(rs.getBigDecimal("percepciones"));
                aux.setDeducciones(rs.getBigDecimal("deducciones"));
                aux.setNeto(rs.getBigDecimal("neto"));
                aux.setIdEstadoNomina(rs.getString("idEstadoNomina").charAt(0));
                aux.setIdSIIFBitacora(rs.getInt("idSIIFBitacora"));
                aux.setIdNombramiento(rs.getInt("idNombramiento"));
                aux.setSubPrograma(rs.getString("subPrograma"));

                result.add(aux);
                i++;
            }
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public SiifBitacoraDTO procesarNominaContTheosToSIIF(
            PaqueteEntradaFederalDTO paqueteEntrada) {
        UploadedFile cont = paqueteEntrada.getCont();

        SiifBitacoraDTO bitacora = reporteSiifService
                .crearSiifBitacora(paqueteEntrada);

        bitacora = reporteSiifService.importarNominaContSIIF(cont, bitacora,
                paqueteEntrada);
        //		bitacora = reporteSiifService.cambiarClaveConceptosTra(bitacora);
        bitacora = reporteSiifService.clasificarEncabezadosContratos(bitacora);
        //		bitacora = reporteSiifService.verificarDatos(bitacora);
        bitacora = reporteSiifService
                .calcularTotalesEncabezadosContrato(bitacora);
        return bitacora;
    }

    public List<CuentaBancariaDTO> obtenerCuentaBancariaList() {
        return reporteSiifService.obtenerCuentaBancariaList();
    }

    public List<TipoNominaDTO> obtenerTipoNominaList() {
        return reporteSiifService.obtenerTipoNominaList();
    }

    public SiifBitacoraDTO obtenerSiifBitacora(SiifBitacoraDTO bitacoraDTO) {
        return reporteSiifService
                .obtenerSiiifBitacoraById(bitacoraDTO.getIdSiifBitacora());
    }

    public void deudores(Integer idSiifBitacora, Integer TipoNomina,
            String periodo) {
        reporteSiifService.deudores(idSiifBitacora, TipoNomina, periodo);
    }

    public void bitacora(Integer idSiifBitacora, Integer TipoNomina,
            String periodo) {
        reporteSiifService.eliminaBitacoraPorId(idSiifBitacora);

    }

    public List<EstructuraNominaTrailersDTO> listaDeudores(
            Integer idSiifBitacora) {
        return reporteSiifService.listaDeudores(idSiifBitacora);
    }

    public List<EstructuraNominaTrailersDTO> listaDispersion(
            Integer idSiifBitacora) {
        return reporteSiifService.listaDispersion(idSiifBitacora);
    }

    public List<SIIFEncabezadoDTO> generarEncabezadoSiif(Integer anio,
            String periodo) {
        return reporteSiifService.generarEncabezadoSiif(anio, periodo);
    }

    public SIIFEncabezadoDTO obtenerEncabezado(
            SIIFEncabezadoDTO encabezadoDTO) {
        return reporteSiifService
                .obtenerEncabezadoPorId(encabezadoDTO.getIdSIIFEncabezado());
    }

    public SIIFEncabezadoDTO actualizarEncabezado(
            SIIFEncabezadoDTO encabezadoDTO) {
        return reporteSiifService.actualizarEncabezado(encabezadoDTO);

    }

    public SIIFEncabezadoDTO actualizarCheques(SIIFEncabezadoDTO encabezadoDTO,
            String periodo) {
        reporteSiifService.estatusEncabezado(encabezadoDTO, periodo);
        Integer qna = Integer.parseInt(periodo);
        return reporteSiifService.actualizarCheques(encabezadoDTO, 20);

    }

    public SIIFEncabezadoDTO actualizarCheques(SIIFEncabezadoDTO encabezadoDTO,
            Integer idBitacora) {
        SiifBitacoraDTO dtoBitacora = reporteSiifService
                .obtenerSiiifBitacoraById(idBitacora);
        Integer qna = Integer.parseInt(dtoBitacora.getPeriodoAfectacion());
        return reporteSiifService.actualizarCheques(encabezadoDTO, qna);

    }

}
