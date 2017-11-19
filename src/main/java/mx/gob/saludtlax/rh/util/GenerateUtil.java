/*
 * Copyright ® 2015
 */

package mx.gob.saludtlax.rh.util;

import java.util.UUID;

/**
 * @author Eduardo Mex
 *
 * @version 15/01/2016 17:39:22
 */
public class GenerateUtil {

    /**
     * Genera identificadores aleatoreos
     *
     * @return Id
     */
    public static String generarId() {

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        return randomUUIDString;
    }

}
