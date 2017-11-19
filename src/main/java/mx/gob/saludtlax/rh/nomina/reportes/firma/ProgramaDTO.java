/*
 * ProgramaDTO.java
 * Creado el 27/jun/2017 3:15:11 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class ProgramaDTO implements Serializable, Comparable<ProgramaDTO>, Iterable<FirmaEmpleadoDTO> {

    private static final long serialVersionUID = 3213100192452199926L;

    private final Integer idPrograma;
    private final String programa;
    private final Date inicioPeriodo;
    private final Date finPeriodo;
    private final Map<String, FirmaEmpleadoDTO> firmasEmpleados;

    public ProgramaDTO(Integer idPrograma, String programa, Date inicioPeriodo, Date finPeriodo, Map<String, FirmaEmpleadoDTO> firmasEmpleados) {
        this.idPrograma = idPrograma;
        this.programa = programa;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.firmasEmpleados = firmasEmpleados;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public Map<String, FirmaEmpleadoDTO> getFirmasEmpleados() {
        return firmasEmpleados;
    }

    @Override
    public int compareTo(ProgramaDTO o) {
        if (idPrograma == null && o.idPrograma == null) {
            return 0;
        }
        if (idPrograma == null) {
            return -1;
        }
        if (o.idPrograma == null) {
            return 1;
        }

        return idPrograma.compareTo(o.idPrograma);
    }

    @Override
    public Iterator<FirmaEmpleadoDTO> iterator() {
        return firmasEmpleados.values().iterator();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(idPrograma);
        hash = 13 * hash + Objects.hashCode(programa);
        hash = 13 * hash + Objects.hashCode(inicioPeriodo);
        hash = 13 * hash + Objects.hashCode(finPeriodo);
        hash = 13 * hash + Objects.hashCode(firmasEmpleados);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgramaDTO other = (ProgramaDTO) obj;
        if (!Objects.equals(programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(idPrograma, other.idPrograma)) {
            return false;
        }
        if (!Objects.equals(inicioPeriodo, other.inicioPeriodo)) {
            return false;
        }
        if (!Objects.equals(finPeriodo, other.finPeriodo)) {
            return false;
        }
        return Objects.equals(firmasEmpleados, other.firmasEmpleados);
    }

    @Override
    public String toString() {
        return "ProgramaDTO{" + "idPrograma : " + idPrograma + ", programa : " + programa + ", inicioPeriodo : " + inicioPeriodo + ", finPeriodo : "
                + finPeriodo + ", firmasEmpleados : [" + firmasEmpleados + "]}";
    }

    public static final class Builder {

        private Integer idPrograma;
        private String programa;
        private Date inicioPeriodo;
        private Date finPeriodo;
        private Map<String, FirmaEmpleadoDTO> firmasEmpleados;

        public Builder(Integer idPrograma, String programa, Date inicioPeriodo, Date finPeriodo) {
            this.idPrograma = idPrograma;
            this.programa = programa;
            this.inicioPeriodo = inicioPeriodo;
            this.finPeriodo = finPeriodo;
            firmasEmpleados = null;
        }

        public Builder setIdPrograma(Integer idPrograma) {
            this.idPrograma = idPrograma;
            return this;
        }

        public Builder setPrograma(String programa) {
            this.programa = programa;
            return this;
        }

        public Builder setInicioPeriodo(Date inicioPeriodo) {
            this.inicioPeriodo = inicioPeriodo;
            return this;
        }

        public Builder setFinPeriodo(Date finPeriodo) {
            this.finPeriodo = finPeriodo;
            return this;
        }

        public Builder setFirmasEmpleados(Map<String, FirmaEmpleadoDTO> firmasEmpleados) {
            this.firmasEmpleados = firmasEmpleados;
            return this;
        }

        public ProgramaDTO construirProgramaDTO() {
            return new ProgramaDTO(idPrograma, programa, inicioPeriodo, finPeriodo, firmasEmpleados);
        }
    }

}
