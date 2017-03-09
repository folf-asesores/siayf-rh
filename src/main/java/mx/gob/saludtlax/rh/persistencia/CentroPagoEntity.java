package mx.gob.saludtlax.rh.persistencia;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table (name="centros_pago")
public class CentroPagoEntity implements Serializable{
private static final long serialVersionUID = 3049706593709307279L ;  

       @Id
       @Column(name = "id_centro_pago")
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Integer idCentroPago;

      @Column(name = "centro_pago")
      private String centro_pago;
	
      public Integer getIdCentroPago() {
		return idCentroPago;
	}

      public void setIdCentroPago(Integer idCentroPago) {
		this.idCentroPago = idCentroPago;
	}


	public String getCentro_pago() {
		return centro_pago;
	}
    public void setCentro_pago(String centro_pago) {
		this.centro_pago = centro_pago;
	}
       public static long getSerialversionuid() {
				return serialVersionUID;
			}
}

