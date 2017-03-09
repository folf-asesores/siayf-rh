/*
 * CSVService.java
 * Creado el 26/06/2016 01:23:39 AM
 *
 */
package mx.gob.saludtlax.rh.siif.layout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.NumeroUtil;
import org.jboss.logging.Logger;

/**
 * Esta clase ayuda en la conversión de un DTO a un archivo de valores 
 * separados por comas.
 * 
 * Se apoya en la generalización y en la refleción pata inferir el nombre de los
 * métodos y las propiedades de la clase a convertir.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class CSVService {
    
    private static final Logger LOGGER = Logger.getLogger(CSVService.class.getName());
    
    /**
     * Este método se encarga de convertir una lista de DTOs a un archivo de 
     * valores separados por comas.
     * 
     * @param <E> tipo del DTO.
     * @param listItems la lista de 
     * @param aClass la clase tipo del DTO.
     * @return un arreglo de bytes que representa el archivo de valores 
     * separados por comas.
     */
    protected <E> byte[] getAsCSV(List<E> listItems, Class<E> aClass) {
        
        String nombreArchivo = aClass.getSimpleName();
        List<Method> getters = getMethods(aClass);
        
        try {
            File file = File.createTempFile(nombreArchivo, ".csv");
            
            try (FileWriter fileWriter = new FileWriter(file)) {
                // llenarEncabezado(fileWriter, getters);
                llenarDetalles(fileWriter, getters, listItems);
                fileWriter.flush();
            }
            
            Path path = Paths.get(file.toURI());
            return Files.readAllBytes(path);
            
        } catch (IOException ex) {
            LOGGER.error("Error de escritura al guradar la información. ", ex);
            return null;
        }
    }
    
    /**
     * Este metodo devuelve todos los metodos get de una clase.
     * 
     * @param <T> el tipo de la clase.
     * @param aClass la clase.
     * @return los metodos get de la clase.
     */
    private <T> List<Method> getMethods(Class<T> aClass){
        List<Method> getters = new ArrayList<>();
        
        Method[] methods = aClass.getMethods();
        Arrays.sort(methods, new MethodComparator());
        
        for(Method method : methods) { 
            if ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getName().equals("getClass")) {
                getters.add(method);
            }
        }
        
        return Collections.unmodifiableList(getters);
    }

    /**
     * Se encarga de llenar el encabezado del archivo.
     * @param fileWriter el escritor del archivo.
     * @param getters los metodos getter.
     * @throws IOException 
     */
    private void llenarEncabezado(FileWriter fileWriter, List<Method> getters) throws IOException {
        Iterator<Method> iterator = getters.iterator();
        
        while (iterator.hasNext()) {
            Method getter = iterator.next();
            String methodName = getter.getName();
            String columnName;
            columnName = methodName.contains("get") ? methodName.substring(3) : methodName.substring(2);
            fileWriter.append(columnName);
            fileWriter.append(iterator.hasNext() ? ',': '\n');
        }
    }

    /**
     * Se encarga de llenar las filas detalle del archivo.
     * 
     * @param <E> El tipo de la clase.
     * @param fileWriter el escritor del archivo.
     * @param getters los metodos getter.
     * @param listItems los detalles.
     */
    private <E> void llenarDetalles(final FileWriter fileWriter, final List<Method> getters, final List<E> listItems) {
        AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    for (E items : listItems) {

                        Iterator<Method> iterator = getters.iterator();
                        
                        while (iterator.hasNext()) {
                            Method method = iterator.next();
                            
                            if (!method.isAccessible()) {
                                method.setAccessible(true);
                            }

                            Object invoke = method.invoke(items);
                            
                            if (invoke == null) {
                                fileWriter.append("");
                            } else if(invoke instanceof Date){
                                Date fecha = (Date) invoke;
                                String fechaFormateada = FechaUtil.formatoFecha(fecha);
                                fileWriter.append(fechaFormateada);
                            } else if (invoke instanceof BigDecimal) {
                                BigDecimal decimal = (BigDecimal) invoke;
                                String decimalFormateado = NumeroUtil.formatoDecimal(decimal);
                                fileWriter.append(decimalFormateado);
                            } else {
                                fileWriter.append(invoke != null ? invoke.toString() : "");
                            }
                            
                            fileWriter.append(iterator.hasNext() ? ',': '\n');
                        }
                    }

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    LOGGER.error("Error al cargar el método.", ex);
                } catch (IOException ex) {
                    LOGGER.error("Error de escritura al guardar la información.", ex);
                }

                return null;
            }
        });
    }
    
    /**
     * Esta clase se emplea para realizar la comparación y determinar el orden 
     * de los métodos de una clase.
     */
    protected class  MethodComparator implements Comparator<Method> {
        
        @Override
        public int compare(Method o1, Method o2) {
            MethodOrder or1 = o1.getAnnotation(MethodOrder.class);
            MethodOrder or2 = o2.getAnnotation(MethodOrder.class);

            if (or1 != null && or2 != null) {
                return or1.value() - or2.value();
            } else if (or1 != null && or2 == null) {
                return -1;
            } else if (or1 == null && or2 != null) {
                return 1;
            }

            return o1.getName().compareTo(o2.getName());
        }   
    }
}