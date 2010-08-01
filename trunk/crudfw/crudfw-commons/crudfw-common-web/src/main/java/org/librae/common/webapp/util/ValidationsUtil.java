package org.librae.common.webapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;

/**
 * Clase para gestionar las validaciones realizadas sobre las pantallas.
 * 
 * @author Sopra Group
 */
public class ValidationsUtil {

    public enum TipoUltCaracter {
        NUMERO, LETRA, AMBOS
    };

    public enum TipoIdentificador {
        NIF, NIE, CIF, Pasaporte, Desconocido
    };

    /**
     * Método que valida el tipo de identificación, en el caso de encontrar
     * incidencias o errores lanza excepciones del tipo LibraeException para
     * mostrarlas mediante mensajes popup en la pantalla.
     * 
     * @param tipoIdentificador
     *            tipo de identificación a validar
     * @param identificador
     *            cadena alfanumérica asociada al tipo de identificación a
     *            validar
     */
    public static void validarIdentificador(String tipoIdentificador,
            String identificador) {
        if (tipoIdentificador != null) {
            switch (TipoIdentificador.valueOf(tipoIdentificador)) {
                case NIF:
                    if (null != identificador) {
                        final String ident = identificador.toUpperCase();
                        ValidationsUtil.validarNIF(ident);
                    }
                    break;
                case NIE:
                    if (null != identificador) {
                        final String ident = identificador.toUpperCase();
                        ValidationsUtil.validarNIE(ident);
                    }
                    break;
                case CIF:
                    if (null != identificador) {
                        final String ident = identificador.toUpperCase();
                        ValidationsUtil.validarCIF(ident);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * Método que valida el tipo de documento NIE.
     * 
     * @param nie
     *            cadena alfanumérica asociada al tipo de identificación a
     *            validar
     */
    private static void validarNIE(String nie) {

        if (null != nie) {

            // Se comprueba la validez del primer caracter {X,Y,Z}
            if ("XYZ".indexOf(nie.charAt(0)) == -1) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_GENERAL, "VALID_ERROR_NIE");
            }

            // Se comprueba la longitud correcta del NIE
            if (nie.length() != 9) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_GENERAL, "VALID_ERROR_FORMAT");
            }

            final String nieUP = nie.toUpperCase();
            String nieval = nieUP.substring(1, 9);
            final Pattern mask = Pattern.compile("[0-9]{7}[A-Z]?");
            final Matcher matcher = mask.matcher(nieval);

            // Se comprueba la validez del formato
            if (!matcher.matches()) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_GENERAL, "VALID_ERROR_FORMAT");
            }

            // Se genera un documento NIF considerando la letra X de comienzo
            if (nieUP.charAt(0) == 'X') {
                nieval = "0".concat(nieval);
            }

            // Se genera un documento NIF considerando la letra Y de comienzo
            if (nieUP.charAt(0) == 'Y') {
                nieval = "1".concat(nieval);
            }

            // Se genera un documento NIF considerando la letra Z de comienzo
            if (nieUP.charAt(0) == 'Z') {
                nieval = "2".concat(nieval);
            }

            // Se realiza la misma validación que para el NIF
            ValidationsUtil.validarNIF(nieval);
        }
    }

    /**
     * Método que valida el tipo de documento NIF.
     * 
     * @param nif
     *            cadena alfanumérica asociada al tipo de identificación a
     *            validar
     */
    private static void validarNIF(String nif) {

        if (null != nif) {
            final String nifUP = nif.toUpperCase();
            final Pattern mask = Pattern.compile("[0-9]{8}[A-Z]?");
            final Matcher matcher = mask.matcher(nifUP);

            // Se comprueba la validez del formato
            if (!matcher.matches()) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_GENERAL, "VALID_ERROR_FORMAT");
            }

            final char ultimoCar = nifUP.charAt(nifUP.length() - 1);

            // Se comprueba que el último caracter sea una letra
            if (ultimoCar >= 'A' && ultimoCar <= 'Z') {
                // Se establece el conjunto de letras válidos
                final String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

                final String dni = nifUP.substring(0, 8);
                final String digitoControl = nifUP.substring(8, 9);

                // Se calcula la letra de control
                final int posicion_modulo = Integer.parseInt(dni) % 23;

                final String digitoControlCalculado = letras.substring(
                        posicion_modulo, posicion_modulo + 1);

                // Se comprueba la validez de la letra de control
                if (!digitoControl.equalsIgnoreCase(digitoControlCalculado)) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_GENERAL,
                            "VALID_ERROR_CONTROL");
                }
            }
        }
    }

    /**
     * Método que valida el tipo de documento CIF.
     * 
     * @param cif
     *            cadena alfanumérica asociada al tipo de identificación a
     *            validar
     */
    private static void validarCIF(String cif) {

        if (cif != null) {
            final String cifUP = cif.toUpperCase();

            // Se comprueba la validez del código de empresa
            if ("ABCDEFGHJNPQRSUVW".indexOf(cifUP.charAt(0)) == -1) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_GENERAL, "VALID_ERROR_CIF_EMPR");
            }

            final Pattern mask = Pattern
                    .compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
            final Matcher matcher = mask.matcher(cifUP);

            // Se comprueba la validez del formato
            if (!matcher.matches()) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_GENERAL, "VALID_ERROR_FORMAT");
            }

            final Integer codigoProv = Integer.parseInt(cifUP.substring(1, 3));
            final int codigo = codigoProv.intValue();

            // Se comprueba la validez del código de provincia
            if ((codigo >= 65 && codigo <= 69) || (codigo == 72)
                    || (codigo >= 86 && codigo <= 90)) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_GENERAL, "VALID_ERROR_CIF_PROV");
            }

            final char primerCar = cifUP.charAt(0);
            final char ultimoCar = cifUP.charAt(cifUP.length() - 1);

            // Se especifica el tipo de caracter que debe ser el último caracter
            // del cif (caracter de control) en función del primer caracter
            // (caracter de empresa)
            TipoUltCaracter tipUltCar;

            if (primerCar == 'P' || primerCar == 'Q' || primerCar == 'S') {
                // Se establece que el último caracter debe de ser una LETRA
                tipUltCar = TipoUltCaracter.LETRA;
                if (!(ultimoCar >= 'A' && ultimoCar <= 'Z')) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_GENERAL,
                            "VALID_ERROR_CONTROL");
                }

            } else if (primerCar == 'A' || primerCar == 'B' || primerCar == 'E'
                    || primerCar == 'H') {
                // Se establece que el último caracter debe de ser una NUMERO
                tipUltCar = TipoUltCaracter.NUMERO;
                if (!(ultimoCar >= '0' && ultimoCar <= '9')) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_GENERAL,
                            "VALID_ERROR_CONTROL");
                }

            } else {
                // Se establece que el último caracter debe de ser una LETRA o
                // un NUMERO
                tipUltCar = TipoUltCaracter.AMBOS;
            }

            // Se calcula el caracter de control a partir de los 7 dígitos
            // centrales
            final String digitos = cifUP.substring(1, cifUP.length() - 1);
            // 1.- Sumar los dígitos de las posiciones pares
            Integer sumaPares = 0;
            for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
                sumaPares += Integer.parseInt(digitos.substring(i, i + 1));
            }
            // 2.- Para cada uno de losdígitos de las posiciones impares,
            // multiplicarlo por 2 y sumar los d�gitos del resultado.
            Integer sumaImpares = 0;
            for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
                Integer cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
                if (cal.toString().length() > 1) {
                    cal = Integer.parseInt(cal.toString().substring(0, 1))
                            + Integer.parseInt(cal.toString().substring(1, 2));
                }
                sumaImpares += cal;
            }
            // 3.- Acumulamos los resultados obtenidos.
            final Integer total = sumaPares + sumaImpares;
            // 4.- Tomar s�lo el d�gito de las unidades del total acumulado y
            // restárselo a 10
            final Integer numControl = 10 - (total % 10);
            /*
             * 5.- Si el carácter de control debe ser NUMERO se toma
             * directamente el dígito obtenido de las unidades. Si el carácter
             * de control debe ser LETRA se corresponde con la relaci�n
             * siguiente: A=1,B=2,C=3,D=4,E=5,F=6,G=7,H=8,I=9,J=10
             */
            final char carControl = "ABCDEFGHIJ".charAt(numControl - 1);

            if (tipUltCar == TipoUltCaracter.NUMERO) {
                // Se comprueba que el caracter de control debe ser un NUMERO.
                final Integer ultCar = Integer.parseInt(Character
                        .toString(ultimoCar));
                if (numControl.intValue() != ultCar.intValue()) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_GENERAL,
                            "VALID_ERROR_CONTROL_NUM");
                }

            } else if (tipUltCar == TipoUltCaracter.LETRA) {
                // Se comprueba que el caracter de control debe ser una LETRA.
                if (carControl != ultimoCar) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_GENERAL,
                            "VALID_ERROR_CONTROL_LETR");
                }

            } else {
                // Se comprueba que el caracter de control puede ser tanto un
                // NUMERO como una
                // LETRA.
                final Integer ultCar = Integer.parseInt(Character
                        .toString(ultimoCar));
                if ((numControl.intValue() != ultCar.intValue())
                        && (carControl != ultimoCar)) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_GENERAL,
                            "VALID_ERROR_CONTROL_AMB");
                }
            }
        }
    }
}