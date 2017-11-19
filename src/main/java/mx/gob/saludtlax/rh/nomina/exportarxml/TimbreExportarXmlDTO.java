
package mx.gob.saludtlax.rh.nomina.exportarxml;

public class TimbreExportarXmlDTO {
    private Integer idComprobante;
    private String rfc;
    private String uuid;
    private byte[] comprobanteXml;

    public TimbreExportarXmlDTO() {
    }

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public byte[] getComprobanteXml() {
        return comprobanteXml;
    }

    public void setComprobanteXml(byte[] comprobanteXml) {
        this.comprobanteXml = comprobanteXml;
    }
}
