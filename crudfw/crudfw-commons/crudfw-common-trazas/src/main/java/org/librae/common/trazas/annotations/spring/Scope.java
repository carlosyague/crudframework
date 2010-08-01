/*
Empresa desarrolladora: INGENIA S.A.

Autor: Junta de Andalucía

Derechos de explotación propiedad de la Junta de Andalucía.

Éste programa es software libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los términos de la Licencia EUPL European Public License publicada por el organismo IDABC de la Comisión Europea, en su versión 1.0. o posteriores.

Éste programa se distribuye de buena fe, pero SIN NINGUNA GARANTÍA, incluso sin las presuntas garantías implícitas de USABILIDAD o ADECUACIÓN A PROPÓSITO CONCRETO. Para mas información consulte la Licencia EUPL European Public License.

Usted recibe una copia de la Licencia EUPL European Public License junto con este programa, si por algún motivo no le es posible visualizarla, puede consultarla en la siguiente URL: http://ec.europa.eu/idabc/servlets/Doc?id=31099

You should have received a copy of the EUPL European Public License along with this program. If not, see http://ec.europa.eu/idabc/servlets/Doc?id=31096

Vous devez avoir reçu une copie de la EUPL European Public License avec ce programme. Si non, voir http://ec.europa.eu/idabc/servlets/Doc?id=31205

Sie sollten eine Kopie der EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919

*/ 
package org.librae.common.trazas.annotations.spring;

/**
 * Tipo enumerado para los scope de ejecución.
 * 
 * @author http://internna.blogspot.com/2007/01/one-of-questions-that-arise-time-and.html
 */
public enum Scope {
    
    SINGLETON(0, "singleton"),
    PROTOTYPE(1, "prototype"),
    REQUEST(2, "request"),
    SESSION(3, "session"), 
    GLOBALSESSIOM(4, "globalSession");
    
    private final int value;
    private final String scopeName;

    private Scope(final int value, final String scopeName) {
        this.value = value;
        this.scopeName  = scopeName;
    }
    
    public int getValue() {
        return value;
    }

    public String getScopeName() {
        return scopeName;
    }
}