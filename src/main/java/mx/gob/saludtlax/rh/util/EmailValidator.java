package mx.gob.saludtlax.rh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private EmailValidator() {
    }

    /**
     * Valida el correo electrónico por medio de un patrón
     *
     * @param email el correo electrónico a evaluar.
     * @return true si la email es valido, false si email no es valido.
     */
    public static boolean validate(final String email) {
        if(email == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
