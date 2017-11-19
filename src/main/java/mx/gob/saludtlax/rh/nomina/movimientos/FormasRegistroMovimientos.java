
package mx.gob.saludtlax.rh.nomina.movimientos;

public enum FormasRegistroMovimientos {
    NINGUNO(0, ""),
    MOVIMIENTOS_FIJOS(1,
            "/contenido/nomina/movimientos/movimientosfijos.xhtml?faces-redirect=true"),
    MOVIMIENTOS_IMPORTE(2,
            "/contenido/nomina/movimientos/movimientoImporte.xhtml?faces-redirect=true"),
    MOVIMIENTOS_DIA(3,
            "/contenido/nomina/movimientos/movimientosDias.xhtml?faces-redirect=true"),
    MOVIMIENTOS_PERMANENTES(4,
            "/contenido/nomina/movimientos/movimientoPermanente.xhtml?faces-redirect=true"),
    CONSULTA_MOVIMIENTOS(5,
            "/contenido/nomina/movimientos/consultaMovimientos.xhtml?faces-redirect=true");

    private Integer id;
    private String url;

    private FormasRegistroMovimientos(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public static String getPorId(Integer id) {
        if (id > 0 && id < FormasRegistroMovimientos.values().length) {
            return FormasRegistroMovimientos.values()[id].getUrl();
        } else {
            return NINGUNO.getUrl();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}