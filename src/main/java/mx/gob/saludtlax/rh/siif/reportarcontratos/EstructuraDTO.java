/**
 * 
 */
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraException;


public class EstructuraDTO {
	
	private Map<Integer, Object> datos;
	
	public EstructuraDTO() {
	    datos = new HashMap<Integer, Object>();
	}
	
	public void agregarDato(char columna, Object dato){
	    int indice = obtenerIndice(columna);
	  
	    agregarDato(indice, dato);
	}
	
	public void agregarDato(int indice, Object dato){
	    datos.put(indice, dato);
	}
	
	public <T> T getDato(char columna, Class<T> t) throws EstructuraException{
	    int indice = obtenerIndice(columna);
	    return getDato(indice, t);
	}

	@SuppressWarnings("unchecked")
	public <T> T getDato(int indice, Class<T> t) throws EstructuraException {
	    Object obj = datos.get(indice);
	    
	    if((indice > getSize()) && (obj == null)){
	    	System.out.println("tamaño:"+getSize()+" ind"+indice);
	        throw new IndexOutOfBoundsException("No se ha proporcionado índice valido:" + indice);
	    	
	    	//throw new AnexoException(AnexoException.ERROR_INDICE_INVALIDO, indice);
	    } else if(obj == null) {
	    	
	    	if(Double.class.isAssignableFrom(t)){
	    		
	    		Double doubleV = 0.0;
	            
	    		return (T) doubleV;
	    	} else if(Integer.class.isAssignableFrom(t)){
	            Integer integer = 0;
	            
	            return (T) integer;
	        } else if(BigDecimal.class.isAssignableFrom(t)){
	            BigDecimal decimal = BigDecimal.ZERO;
	           
	            return (T) decimal; 
	        } else if (Long.class.isAssignableFrom(t)){
	            Long longV = 0L;
	            
	            return (T) longV;
	        } else if(Date.class.isAssignableFrom(t)){
	        	Date date = new Date();
	            
	        	return (T) date;
	        } else if(String.class.isAssignableFrom(t)){
	        	String cadena = "";
	        	
	        	return (T) cadena;
	        }else{
	        	
	        }
	    } else if(obj.getClass().isAssignableFrom(t)){
	        
	    	return (T) obj;
	    } else if(obj.getClass().isAssignableFrom(Double.class)){
	        Double valorDouble = (Double) obj;
	
	        if(Integer.class.isAssignableFrom(t)){
	            Integer integer = valorDouble.intValue();
	            
	            return (T) integer;
	        } else if(BigDecimal.class.isAssignableFrom(t)){
	            BigDecimal decimal = BigDecimal.valueOf(valorDouble.doubleValue()).setScale(2, RoundingMode.HALF_EVEN);
	            //log.debug("Valor de conversión: " + decimal.toPlainString());
	            return (T) decimal; 
	        } else if (Long.class.isAssignableFrom(t)){
	            Long longV = valorDouble.longValue();
	            
	            return (T) longV;
	        } else if (String.class.isAssignableFrom(t)){
	        	String cadena = valorDouble.toString();
	        	
	        	return (T) cadena;
	        }
	    } else if(obj.getClass().isAssignableFrom(String.class)){
	        String valorCadena = (String) obj; 
	
	        if(Date.class.isAssignableFrom(t)){
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            try {
	                Date date = sdf.parse(valorCadena);
	                
	                return (T) date;
	            } catch (ParseException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }  else if(obj.getClass().isAssignableFrom(CampoExcel.class)){
	    	CampoExcel campo = (CampoExcel) obj;
	    	
	    	return (T) campo;
	    }
	    
	   throw new EstructuraException(exceptionAnexo(t.getSimpleName(), obj.getClass().getSimpleName()), ((Integer)datos.get(-1)+1), obtenerColumna(indice));
	    
	    /**
	    throw new IllegalArgumentException("Tipo invalido\nClase requerida: " 
	    		+ t.getSimpleName() + "\nEl objeto es de clase: " 
	    		+ obj.getClass().getSimpleName()
	    		+ " En la Columna: " + obtenerColumna(indice)
	    		);
	    */
	}

	private String exceptionAnexo(String requiere, String obtiene){
		
		if(requiere.equalsIgnoreCase("Date") && obtiene.equalsIgnoreCase("String")){
			return EstructuraException.ERROR_STRING_TO_DATE;
			
		}else if(requiere.equalsIgnoreCase("Integer") && obtiene.equalsIgnoreCase("String")){
			return EstructuraException.ERROR_STRING_TO_INTEGER;
			
		}if(requiere.equalsIgnoreCase("Double") && obtiene.equalsIgnoreCase("String")){
			return EstructuraException.ERROR_STRING_TO_DOUBLE;
			
		}else if(requiere.equalsIgnoreCase("BigDecimal") && obtiene.equalsIgnoreCase("String")){
			return EstructuraException.ERROR_STRING_TO_BIGDECIMAL;
			
		}if(requiere.equalsIgnoreCase("Long") && obtiene.equalsIgnoreCase("String")){
			return EstructuraException.ERROR_STRING_TO_LONG;
			
		}else if(requiere.equalsIgnoreCase("Date") && obtiene.equalsIgnoreCase("Integer")){
			return EstructuraException.ERROR_INTEGER_TO_DATE;
			
		}else if(requiere.equalsIgnoreCase("Date") && obtiene.equalsIgnoreCase("Double")){
			return EstructuraException.ERROR_DOUBLE_TO_DATE;
			
		}else if(requiere.equalsIgnoreCase("Date") && obtiene.equalsIgnoreCase("BigDecimal")){
			return EstructuraException.ERROR_BIGDECIMAL_TO_DATE;
			
		}else if(requiere.equalsIgnoreCase("Date") && obtiene.equalsIgnoreCase("Long")){
			return EstructuraException.ERROR_LONG_TO_DATE;
			
		}else{
			return EstructuraException.ERROR_CLASE_INVALIDA;
		}
	}

	private int obtenerIndice(char columna){
	    if(Character.isLetter(columna)){
	        columna = Character.toUpperCase(columna);
	        return columna - 'A';
	    } else {
	        throw new IllegalArgumentException("Se esperaba una letra y se recibio:" + columna);
	    }
	}
	
	private char obtenerColumna(int indice){
		char columna = Character.toChars(indice + 'A')[0];
		
		if(Character.isLetter(columna)){
			System.out.println("Columna: " + columna + " indice: " + indice);
			return Character.toUpperCase(columna);
		} else {
			throw new IllegalArgumentException("Se esperaba un valor que sea comparable con la table UTF-16 se obtuvo:" + indice + " char: " + columna);			
		}
	}


	public int getSize(){
		return datos.size();
	}
}