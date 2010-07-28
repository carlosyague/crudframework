package org.librae.common.webapp.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.el.ValueExpression;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.component.html.ext.HtmlCommandLink;
import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.component.html.ext.HtmlOutputText;
import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;
import org.apache.myfaces.component.html.ext.HtmlSelectManyCheckbox;
import org.apache.myfaces.custom.column.HtmlSimpleColumn;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.DateUtil;
import org.librae.common.util.ReflectionUtil;
import org.librae.common.util.StringUtil;

import org.librae.common.util.Propiedades;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Convierte los listados a excel y pdf.
 * 
 * @author jcisneros, jpserrano
 */
public final class ExportUtils {

    protected static final Log log                    = LogFactory
                                                              .getLog(ExportUtils.class);

    public static final String LISTADOS_NUMERO_MAXIMO = "listados.numero.maximo";

    /**
     * Busca las cabeceras.
     * 
     * @param sheet
     * @param fila
     * @param columns
     */
    private static Integer addColumnHeaders(HSSFWorkbook workbook,
            HSSFSheet sheet, Integer fila, List<UIComponent> columns) {
        fila = fila + 1;
        final HSSFRow rowHeader = sheet.createRow(fila);
        for (int i = 0; i < columns.size(); i++) {
            final UIColumn column = (UIColumn) columns.get(i);
            addColumnValueHeader(workbook, rowHeader, column, i);
        }
        fila = fila + 1;
        return fila;
    }

    /**
     * Busca las cabeceras.
     * 
     * @param documento
     * @param columns
     */
    private static void addColumnPdfHeaders(PdfPTable table,
            List<UIComponent> columns) {
        for (int i = 0; i < columns.size(); i++) {
            final UIColumn column = (UIColumn) columns.get(i);
            addColumnValuePdfHeader(table, column, i);
        }
    }

    /**
     * Busca las columnas del datatable.
     * 
     * @param table
     * @return
     */
    private static List<UIComponent> getColumns(HtmlDataTable table) {
        final List<UIComponent> columns = new ArrayList<UIComponent>();
        for (int i = 0; i < table.getChildCount(); i++) {
            final UIComponent child = table.getChildren().get(i);
            if ((child instanceof UIColumn)
                    && (!((UIColumn) child).getId().startsWith("j_"))
                    && (child.isRendered())) {
                columns.add(child);
            }
        }
        return columns;
    }

    /**
     * Añade una celda al excel.
     * 
     * @param rowHeader
     * @param component
     * @param index
     */
    private static void addColumnValueHeader(HSSFWorkbook workbook,
            HSSFRow rowHeader, UIComponent component, int index) {
        // seleccion del tipo de letra
        final HSSFFont fuente = crearFuente(workbook, HSSFColor.WHITE.index,
                (short) 10);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        final HSSFCellStyle estiloCelda = crearEstiloCelda(workbook, fuente);
        estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCelda.setFillForegroundColor(HSSFColor.TEAL.index);
        /*
         * final HSSFFont fuente = workbook.createFont();
         * fuente.setFontName(fuente.FONT_ARIAL);
         * fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         * fuente.setColor(HSSFColor.WHITE.index); // estilo de la celda final
         * HSSFCellStyle estiloCelda = workbook.createCellStyle();
         * //estiloCelda.setWrapText(true); estiloCelda.setFont(fuente);
         * HSSFPalette palette = workbook.getCustomPalette(); HSSFColor color=
         * palette.findSimilarColor((byte) 128, (byte) 128, (byte) 128);
         * //especificamos el color de las celdas
         * estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         * estiloCelda.setFillForegroundColor(HSSFColor.TEAL.index); //Color de
         * los bordes estiloCelda.setBorderBottom((short) 3);
         * estiloCelda.setBorderTop((short) 3);
         * estiloCelda.setBorderRight((short) 3);
         * estiloCelda.setBorderLeft((short) 3);
         * estiloCelda.setRightBorderColor(color.getIndex());
         * estiloCelda.setLeftBorderColor(color.getIndex());
         * estiloCelda.setBottomBorderColor(color.getIndex());
         * estiloCelda.setTopBorderColor(color.getIndex());
         */

        final HSSFCell cell = rowHeader.createCell((short) index);
        cell.setCellStyle(estiloCelda);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        if (component instanceof HtmlSimpleColumn) {
            final HtmlSimpleColumn columna = (HtmlSimpleColumn) component;
            cell.setCellValue(ComboLocaleManager.get(columna.getId()
                    .replaceAll("_", ".")));
        }
    }

    /**
     * Añade una celda al excel.
     * 
     * @param rowHeader
     * @param component
     * @param index
     */
    private static void addColumnValuePdfHeader(PdfPTable table,
            UIComponent component, int index) {
        if (component instanceof HtmlSimpleColumn) {
            final HtmlSimpleColumn columna = (HtmlSimpleColumn) component;

            // Colores, letra y tipo celda para la cabecera
            final Font font = new Font(Font.HELVETICA, 8, Font.BOLD);
            font.setColor(Color.WHITE);
            final Phrase p = new Phrase(ComboLocaleManager.get(columna.getId()
                    .replaceAll("_", ".")), font);
            final PdfPCell celda = new PdfPCell(p);
            celda.setBackgroundColor(new Color(63, 179, 126));
            celda.setBorderColor(new Color(235, 235, 235));
            celda.setBorderWidth(1f);
            table.addCell(celda);
        }
    }

    /**
     * Creamos una fuente para el EXCELL según el color de la letra
     * 
     * @param workbook
     * @param color
     * @return
     */
    private static HSSFFont crearFuente(HSSFWorkbook workbook, short color,
            short size) {
        final HSSFFont fuente = workbook.createFont();
        fuente.setFontHeightInPoints(size);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setColor(color);
        return fuente;
    }

    /**
     * crea un estilo para las celdas, tamaño y color de bordes, fuente
     * 
     * @param workbook
     * @param fuente
     * @return
     */
    private static HSSFCellStyle crearEstiloCelda(HSSFWorkbook workbook,
            HSSFFont fuente) {

        final HSSFCellStyle estiloCelda = workbook.createCellStyle();
        estiloCelda.setFont(fuente);
        // tamaño bordes
        estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THIN);
        estiloCelda.setBorderRight(HSSFCellStyle.BORDER_THIN);
        estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        // color de los bordes
        final HSSFPalette palette = workbook.getCustomPalette();
        final HSSFColor color = palette.findSimilarColor((byte) 128,
                (byte) 128, (byte) 128);
        estiloCelda.setRightBorderColor(color.getIndex());
        estiloCelda.setLeftBorderColor(color.getIndex());
        estiloCelda.setBottomBorderColor(color.getIndex());
        estiloCelda.setTopBorderColor(color.getIndex());

        return estiloCelda;
    }

    /**
     * Añade una celda al excel.
     * 
     * @param workbook
     * @param rowHeader
     * @param list
     * @param index
     * @param i
     */
    private static void addColumnValue(HSSFWorkbook workbook,
            HSSFRow rowHeader, List<UIComponent> list, int index, int i,
            Object object, Map<String, Object> mapa) {
        String resultado = null;
        final StringBuilder sb = new StringBuilder();
        final HSSFCellStyle estiloCelda = crearEstiloCelda(workbook,
                crearFuente(workbook, HSSFColor.BLACK.index, (short) 10));
        estiloCelda.setFillPattern(HSSFCellStyle.FINE_DOTS);

        if (i % 2 == 0) {
            estiloCelda.setFillForegroundColor(HSSFColor.WHITE.index);
        } else {
            final HSSFPalette palette = workbook.getCustomPalette();
            palette.setColorAtIndex(HSSFColor.LIGHT_GREEN.index, (byte) 243,
                    (byte) 249, (byte) 246);
            estiloCelda.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        }

        // creamos la celda y le especificamos su estilo
        final HSSFCell cell = rowHeader.createCell((short) index);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellStyle(estiloCelda);

        for (final UIComponent component : list) {
            if (component instanceof HtmlOutputText) {
                final HtmlOutputText columna = (HtmlOutputText) component;
                if (columna.isRendered()) {
                    final ValueExpression expression = columna
                            .getValueExpression("value");
                    if (expression == null) {
                        resultado = columna.getValue().toString();
                    } else {
                        resultado = expression.getExpressionString();
                    }

                    final DateTimeConverter converter = (DateTimeConverter) columna
                            .getConverter();
                    final String value = getValueFromExpressionEL(object,
                            resultado, converter);
                    sb.append(value);
                }
            } else if (component instanceof HtmlCommandLink) {
                final HtmlCommandLink columna = (HtmlCommandLink) component;
                for (final UIComponent componente : columna.getChildren()) {
                    if (componente instanceof HtmlOutputText) {
                        final ValueExpression expression = componente
                                .getValueExpression("value");
                        final String value = getValueFromExpressionEL(object,
                                expression.getExpressionString(), null);
                        sb.append(value);
                    }
                }
            } else if (component instanceof HtmlSelectManyCheckbox) {
                final HtmlSelectManyCheckbox columna = (HtmlSelectManyCheckbox) component;
                final ValueExpression expression = columna
                        .getValueExpression("value");
                final String value = getValueFromExpressionELCheckBox(object,
                        expression.getExpressionString(), mapa);
                sb.append(value);
            } else if (component instanceof HtmlSelectBooleanCheckbox) {
                final HtmlSelectBooleanCheckbox columna = (HtmlSelectBooleanCheckbox) component;
                final ValueExpression expression = columna
                        .getValueExpression("value");
                final String value = getValueFromExpressionELCheckBox(object,
                        expression.getExpressionString(), mapa);
                sb.append(value);
            }
        }
        cell.setCellValue(sb.toString());
    }

    /**
     * Transforma la expresion EL a una expresion que podamos utilizar por
     * reflection.
     * 
     * @param object
     * @param el
     * @return
     */
    private static String getValueFromExpressionEL(Object object,
            String elExpression, DateTimeConverter converter) {
        int i = 0;
        final StringBuilder sb = new StringBuilder();
        final StringBuilder sbExpresion = new StringBuilder(elExpression);
        while ((i < elExpression.length()) && (elExpression.charAt(i) != '#')) {
            sb.append(elExpression.charAt(i));
            i++;
        }

        if (elExpression.contains("#")) {
            final String evaluar = sbExpresion.substring(i + 2, elExpression
                    .length() - 1);
            Object value = null;
            String expresion = null;
            final StringTokenizer st = new StringTokenizer(evaluar, ".");
            if (st.hasMoreTokens()) {
                expresion = st.nextToken();
                try {
                    value = object;
                    while (st.hasMoreTokens()) {
                        expresion = st.nextToken();
                        if (expresion.contains("[0]")) {
                            value = ReflectionUtil.getBeanArrayList(value,
                                    expresion);
                        } else {
                            value = ReflectionUtil.getBean(value, expresion);
                        }
                    }

                } catch (final Exception e) {
                    log.error(e);
                }
            }
            if (converter != null && value != null) {
                sb.append(DateUtil.getDatePattern(converter.getPattern(),
                        (Date) value));
            } else if (value != null && converter == null) {
                sb.append(value);
            }
        }
        return sb.toString();
    }

    /**
     * Transforma la expresion EL checkBox a una expresion que podamos utilizar
     * por reflection.
     * 
     * @param object
     * @param elExpression
     * @return
     */
    private static String getValueFromExpressionELCheckBox(Object object,
            String elExpression, Map<String, Object> mapa) {
        int i = 0;
        final StringBuilder sb = new StringBuilder();
        final StringBuilder sbExpresion = new StringBuilder(elExpression);
        while ((i < elExpression.length()) && (elExpression.charAt(i) != '#')) {
            sb.append(elExpression.charAt(i));
            i++;
        }

        if (elExpression.contains("#")) {
            final String evaluar = sbExpresion.substring(i + 2, elExpression
                    .length() - 1);
            Object value = null;
            String expresion = null;
            String check = null;
            final StringTokenizer st = new StringTokenizer(evaluar, ".");

            check = evaluar.toString();
            if (evaluar.charAt(0) == '!') {
                  check = evaluar.replace('!', ' ');
            }
            if (st.hasMoreTokens()) {
                expresion = st.nextToken();
                try {
                    value = object;
                    while (st.hasMoreTokens()) {
                        expresion = st.nextToken();
                        value = ReflectionUtil.getBean(value, expresion);
                    }
                    check = traducirCheckBox(mapa, value);
                } catch (final Exception e) {
                    log.error(e);
                }
            }
            if (value != null) {
                sb.append(check);
            }
        }
        return sb.toString();
    }

    private static String traducirCheckBox(Map<String, Object> mapa,
            Object value) {
        String check = "";

        if (value instanceof List) {
            final ArrayList<String> lista = (ArrayList<String>) value;

            if (lista.size() != 0) {
                check = check + mapa.get(lista.get(0)).toString();
            }
            for (int i = 1; i < lista.size(); i++) {
                check = check + ", " + mapa.get(lista.get(i)).toString();
            }
        }
        if (value instanceof Boolean) {
            final String valor = value.toString();
            if (mapa != null) {
                check = mapa.get(valor).toString();
            }
        }
        if (((check == null) || (StringUtil.esVacia(check)))
                && ((value instanceof Boolean))) {
            check = value.toString();
            
        }
        return check;
    }

    /**
     * Genera el contenido de la tabla
     * 
     * @param workbook
     * @param facesContext
     * @param sheet
     * @param columns
     * @param dataTable
     * @param listado
     * @param fila
     */
    private static Integer generateTableContent(HSSFWorkbook workbook,
            FacesContext facesContext, HSSFSheet sheet,
            List<UIComponent> columns, HtmlDataTable dataTable, List listado,
            Integer fila, Map<String, Object> mapa) {

        final int numberOfColumns = columns.size();
        final int startFrom = 0;
        // int currentIndex = fila;
        final int endAt = listado.size();

        /* fill the table with the data. */
        for (int i = startFrom; i < endAt; ++i) {
            final Object objeto = listado.get(i);
            final HSSFRow row = sheet.createRow(fila);
            for (int j = 0; j < numberOfColumns; ++j) {
                final UIColumn column = (UIColumn) columns.get(j);
                addColumnValue(workbook, row, column.getChildren(), j, i,
                        objeto, mapa);
            }
            fila = fila + 1;
        }
        return fila;
    }

    /**
     * Escribe el excel en el response.
     * 
     * @param response
     * @param fileName
     * @throws IOException
     */
    private static void writeToResponse(HttpServletResponse response,
            String fileName, String suffixFile, String contentType)
            throws IOException {
        response.setContentType(contentType);
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        final StringBuilder sb = new StringBuilder();
        sb.append("attachment;filename=").append(fileName).append(suffixFile);
        response.setHeader("Content-disposition", sb.toString());
    }

    /**
     * Genera el excel a partir de la tabla.
     * 
     * @param facesContext
     * @param dataTable
     * @param listado
     * @param conditions
     * @param fileName
     * @param ancestros
     * @param nombreBiblioteca
     * @param logo
     * @return
     */
    private static HSSFWorkbook generateExcelTableModel(
            FacesContext facesContext, HtmlDataTable dataTable, List listado,
            Map<String, Object> conditions, String fileName, String ancestros,
            String nombreBiblioteca, byte[] logo, Map<String, Object> mapa) {
        Integer fila = new Integer(0);
        final HSSFWorkbook workbook = new HSSFWorkbook();
        final HSSFSheet sheetBusqueda = workbook.createSheet(ComboLocaleManager
                .get("busqueda"));
        final HSSFSheet sheetResultado = workbook
                .createSheet(ComboLocaleManager.get("resultados"));

        final HSSFPalette palette = workbook.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.TEAL.index, (byte) 0, (byte) 154,
                (byte) 84);

        // configuración y especificacion Hoja de Búsqueda
        sheetBusqueda.setDefaultColumnWidth((short) 20);

        // titulo de busqueda
        final HSSFRow row = sheetBusqueda.createRow(fila);
        final HSSFCell cell1 = row.createCell((short) 0);

        // estilo celda
        final HSSFFont fuente = crearFuente(workbook, HSSFColor.TEAL.index,
                (short) 14);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        final HSSFCellStyle estiloCelda = crearEstiloCelda(workbook, fuente);
        estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCelda.setFillForegroundColor(HSSFColor.WHITE.index);

        // Titulo del listado
        cell1.setCellStyle(estiloCelda);
        cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell1.setCellValue(ComboLocaleManager.get(fileName));
        fila = fila + 2;

        // tabla de con el nombre, ancestros y fecha del excel
        fila = generateTableDatosExcel(workbook, sheetBusqueda, fila, DateUtil
                .getFechaHora(DateUtil.getCurrentDate()), nombreBiblioteca,
                ancestros);
        fila = fila + 1;

        // titulo busqueda
        final HSSFRow rowb = sheetBusqueda.createRow(fila);
        final HSSFCell cellb = rowb.createCell((short) 0);
        cellb.setCellStyle(estiloCelda);
        cellb.setEncoding(HSSFCell.ENCODING_UTF_16);
        cellb.setCellValue(ComboLocaleManager.get("busqueda"));
        fila = fila + 2;

        // Tabla busqueda
        if (!conditions.isEmpty()) {
            fila = generateTableSearchContent(workbook, sheetBusqueda, fila,
                    conditions);
        } else {
            final HSSFRow rowBusqueda = sheetBusqueda.createRow(fila);
            final HSSFCell cellBusqueda = rowBusqueda.createCell((short) 1);
            cellBusqueda.setEncoding(HSSFCell.ENCODING_UTF_16);
            cellBusqueda.setCellValue(ComboLocaleManager.get("criterios"));
        }

        // titulo de resultado
        fila = new Integer(0);
        sheetResultado.setDefaultColumnWidth((short) 20);
        final HSSFRow row1 = sheetResultado.createRow(fila);
        final HSSFCell cell2 = row1.createCell((short) 0);
        cell2.setCellStyle(estiloCelda);
        cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
        final Integer numeroRegistros = new Integer(listado.size());
        cell2.setCellValue(ComboLocaleManager.get("resultados") + " ("
                + numeroRegistros.toString() + " "
                + ComboLocaleManager.get("filas") + ")");

        // tabla resultado
        final List<UIComponent> columns = getColumns(dataTable);
        fila = addColumnHeaders(workbook, sheetResultado, fila, columns);
        fila = generateTableContent(workbook, facesContext, sheetResultado,
                columns, dataTable, listado, fila, mapa);
        return workbook;
    }

    /**
     * Genera una tabla con datos como la fecha, el nombre de la biblioteca y
     * sus padres
     * 
     * @param workbook
     * @param sheetBusqueda
     * @param fila
     * @return
     */

    private static Integer generateTableDatosExcel(HSSFWorkbook workbook,
            HSSFSheet sheetBusqueda, Integer fila, String fechaHora,
            String nombreBiblioteca, String ancestros) {
        insertarFilaExcel(workbook, sheetBusqueda, ComboLocaleManager
                .get("nombre"), nombreBiblioteca, fila);
        fila = fila + 1;
        insertarFilaExcel(workbook, sheetBusqueda, ComboLocaleManager
                .get("padres"), ancestros, fila);
        fila = fila + 1;
        insertarFilaExcel(workbook, sheetBusqueda, ComboLocaleManager
                .get("fechaHora"), fechaHora, fila);
        fila = fila + 1;
        return fila;
    }

    /**
     * Inserta una fila en una tabla excel con una fuente y un estilo
     * determinado
     * 
     * @param workbook
     * @param sheetBusqueda
     * @param colum1
     * @param colum2
     * @param fila
     */
    private static void insertarFilaExcel(HSSFWorkbook workbook,
            HSSFSheet sheetBusqueda, String colum1, String colum2, Integer fila) {

        final HSSFRow rowPie = sheetBusqueda.createRow(fila);
        final HSSFCell cell1 = rowPie.createCell((short) 0);
        final HSSFCell cell2 = rowPie.createCell((short) 1);

        final HSSFFont fuente = crearFuente(workbook, HSSFColor.WHITE.index,
                (short) 10);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle estiloCelda = crearEstiloCelda(workbook, fuente);

        estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCelda.setFillForegroundColor(HSSFColor.TEAL.index);
        cell1.setCellStyle(estiloCelda);
        cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell1.setCellValue(colum1);

        estiloCelda = crearEstiloCelda(workbook, crearFuente(workbook,
                HSSFColor.BLACK.index, (short) 10));
        estiloCelda.setFillPattern(HSSFCellStyle.FINE_DOTS);
        estiloCelda.setFillForegroundColor(HSSFColor.WHITE.index);
        cell2.setCellStyle(estiloCelda);
        cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell2.setCellValue(colum2);

    }

    /**
     * Genera el contenido de la tabla de los criterios de búsqueda
     * 
     * @param workbook
     * @param sheet
     * @param fila
     * @param conditions
     * @return
     */
    private static Integer generateTableSearchContent(HSSFWorkbook workbook,
            HSSFSheet sheet, Integer fila, Map<String, Object> conditions) {

        final HSSFPalette palette = workbook.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.TEAL.index, (byte) 0, (byte) 154,
                (byte) 84);
        final Iterator<Entry<String, Object>> it = conditions.entrySet()
                .iterator();
        // fila = fila + 1;
        while (it.hasNext()) {
            final Map.Entry e = it.next();
            insertarFilaExcel(workbook, sheet, e.getKey().toString(), e
                    .getValue().toString(), fila);
            fila = fila + 1;
        }
        return fila;

    }

    /**
     * Genera un excel de la lista.
     * 
     * @param workBook
     * @param response
     * @param fileName
     * @param listado
     * @param ancestros
     * @param nombreBiblioteca
     * @param logo
     * @throws IOException
     */
    public static void generateEXCEL(FacesContext facesContext,
            HttpServletResponse response, String fileName,
            HtmlDataTable dataTable, List listado,
            Map<String, Object> conditions, String ancestros,
            String nombreBiblioteca, byte[] logo, Map<String, Object> mapa)
            throws IOException {
        final Propiedades properties = Propiedades
                .getInstance(Constants.USER_WATCHDOG_PROPERTIES);
        final Integer numeroMaximo = new Integer(properties
                .getPropiedad(LISTADOS_NUMERO_MAXIMO));
        if (listado.size() <= numeroMaximo) {
            final HSSFWorkbook generatedExcel = generateExcelTableModel(
                    facesContext, dataTable, listado, conditions, fileName,
                    ancestros, nombreBiblioteca, logo, mapa);
            writeToResponse(response, fileName, Constants.SUFFIX_EXCEL,
                    Constants.CONTENT_TYPE_EXCEL);
            generatedExcel.write(response.getOutputStream());
            facesContext.responseComplete();
        } else {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_GENERAL, "ERROR_NUMERO_MAXIMO");
        }
    }

    /**
     * Genera un pdf a partir de un datatable.
     * 
     * @param facesContext
     * @param response
     * @param fileName
     * @param dataTable
     * @param listado
     * @param logo
     * @param nombreBiblioteca
     * @param string
     * @param numeroRegistros
     * @param map
     * @throws Exception
     */
    public static void generatePDF(FacesContext facesContext,
            HttpServletResponse response, String fileName,
            HtmlDataTable dataTable, List listado, byte[] logo,
            Map<String, Object> conditions, String url,
            String nombreBiblioteca, String ancestros, Map<String, Object> mapa)
            throws Exception {
        final Propiedades properties = Propiedades
                .getInstance(Constants.USER_WATCHDOG_PROPERTIES);
        final Integer numeroMaximo = new Integer(properties
                .getPropiedad(LISTADOS_NUMERO_MAXIMO));
        if (listado == null) {
            listado = new ArrayList();
        }
        if (listado.size() <= numeroMaximo) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final Document documento = generatePdfTableModel(facesContext,
                    dataTable, listado, baos, fileName, logo, conditions, url,
                    nombreBiblioteca, ancestros, mapa);
            writeToResponse(response, fileName, Constants.SUFFIX_PDF,
                    Constants.CONTENT_TYPE_PDF);
            baos.writeTo(response.getOutputStream());
            facesContext.responseComplete();
        } else {
            String[] argumentos = { numeroMaximo.toString() };
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_GENERAL, "ERROR_NUMERO_MAXIMO",
                    argumentos);
        }
    }

    /**
     * Genera la tabla a partir del datatable.
     * 
     * @param facesContext
     * @param dataTable
     * @param listado
     * @param baos
     * @param conditions
     * @param logo
     * @param nombreBiblioteca
     * @param ancestros
     * @param numeroRegistros
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static Document generatePdfTableModel(FacesContext facesContext,
            HtmlDataTable dataTable, List listado, ByteArrayOutputStream baos,
            String fileName, byte[] logo, Map<String, Object> conditions,
            String url, String nombreBiblioteca, String ancestros,
            Map<String, Object> mapa) throws Exception {
        final Document documento = new Document(PageSize.A4.rotate(), 36, 36,
                75, 50);
        try {

            final List<UIComponent> columns = getColumns(dataTable);
            // definimos la tabla de busqueda y de resultado
            final PdfPTable table = new PdfPTable(columns.size());
            final PdfPTable tableSearch = new PdfPTable(2);

            table.setWidthPercentage(100);
            tableSearch.setWidthPercentage(60);

            final PdfWriter writer = PdfWriter.getInstance(documento, baos);

            /*
             * Creamos un PdfUtils para poder imprimir los pies de página la
             * fecha, las imagenes de la cabecera, así como el titulo
             */
            final PdfUtils pdf = new PdfUtils();
            writer.setPageEvent(pdf);
            pdf.setUrlImagen(url);
            pdf.setLogo(logo);
            pdf.setAncestros(ancestros);
            pdf.setNombreBiblioteca(nombreBiblioteca);
            pdf.setTitulo(ComboLocaleManager.get(fileName));

            // abrimos el documento
            documento.open();

            // imprimimos el título del documento y el subtitulo
            pdf.imprimirTitulo(documento, ComboLocaleManager.get(fileName),
                    new Font(Font.HELVETICA, 24, Font.NORMAL, new Color(0, 154,
                            84)), Paragraph.ALIGN_CENTER);
            pdf.imprimirTitulo(documento, ComboLocaleManager.get("busqueda"),
                    new Font(Font.HELVETICA, 18, Font.NORMAL, new Color(0, 154,
                            84)), Paragraph.ALIGN_LEFT);
            documento.add(new Paragraph(new Phrase(" ", new Font(
                    Font.HELVETICA, 10, Font.NORMAL))));

            // comprobamos si las condiciones de busqueda son vacias
            if (conditions.isEmpty()) {
                pdf.imprimirTitulo(documento, ComboLocaleManager
                        .get("criterios"), new Font(Font.HELVETICA, 10,
                        Font.NORMAL), Paragraph.ALIGN_CENTER);
                documento.add(new Paragraph(new Phrase(" ", new Font(
                        Font.HELVETICA, 10, Font.NORMAL))));

            }
            // Tabla con los criterios de busqueda
            generateTableSearchPdf(tableSearch, conditions);
            documento.add(tableSearch);
            // subtitulos para los resultados
            if (listado == null) {
                listado = new ArrayList();
            }
            final Integer numeroRegistros = new Integer(listado.size());

            pdf.imprimirTitulo(documento, ComboLocaleManager.get("resultados")
                    + " (" + numeroRegistros.toString() + " "
                    + ComboLocaleManager.get("filas") + ")", new Font(
                    Font.HELVETICA, 18, Font.NORMAL, new Color(0, 154, 84)),
                    Paragraph.ALIGN_LEFT);

            documento.add(new Paragraph(new Phrase(" ", new Font(
                    Font.HELVETICA, 10, Font.NORMAL))));

            // Tabla con el contenido de la busqueda
            addColumnPdfHeaders(table, columns);
            generateTableContentPdf(facesContext, table, columns, dataTable,
                    listado, mapa);

            // seleccionamos las filas de la tabla que queramos que se
            // repitan
            // en las distintas paginas del documento
            table.setHeaderRows(1);
            table.setSplitLate(false);
            documento.add(table);

        } catch (final Exception e) {
            documento.close();
            log.error("error...", e);
            throw e;
        } finally {
            documento.close();
        }
        return documento;
    }

    @SuppressWarnings("unchecked")
    private static void generateTableSearchPdf(PdfPTable table1,
            Map<String, Object> conditions) throws DocumentException {
        final float[] rows = { 50f, 250f };
        table1.setTotalWidth(rows);
        PdfPCell celda1 = null;
        PdfPCell celda2 = null;
        final Font font = new Font(Font.HELVETICA, 8, Font.NORMAL);
        final Font font1 = new Font(Font.HELVETICA, 8, Font.BOLD);
        font1.setColor(Color.WHITE);
        final Iterator<Entry<String, Object>> it = conditions.entrySet()
                .iterator();
        String keyMap;
        String valueMap;
        while (it.hasNext()) {
            final Map.Entry e = it.next();
            if (null == e.getKey()) {
                keyMap = "";
            } else {
                keyMap = e.getKey().toString();
            }
            if (null == e.getValue()) {
                valueMap = "";
            } else {
                valueMap = e.getValue().toString();
            }
            final Phrase p1 = new Phrase(keyMap, font1);
            final Phrase p2 = new Phrase(valueMap, font);
            // creamos las celdas
            celda1 = new PdfPCell(p1);
            celda2 = new PdfPCell(p2);
            // especificamos las características de las celdas
            celda1.setBorderColor(new Color(235, 235, 235));
            celda1.setBackgroundColor(new Color(63, 179, 126));
            celda1.getLeading();

            celda1.setBorderWidth(1f);

            celda2.setBorderColor(new Color(235, 235, 235));
            celda2.setBorderWidth(1f);

            // insertamos las celdas

            table1.addCell(celda1);
            table1.addCell(celda2);
        }

    }

    /**
     * Genera el contenido de la tabla
     * 
     * @param facesContext
     * @param sheet
     * @param columns
     * @param dataTable
     * @param listado
     */
    private static void generateTableContentPdf(FacesContext facesContext,
            PdfPTable table, List<UIComponent> columns,
            HtmlDataTable dataTable, List listado, Map<String, Object> mapa)
            throws Exception {

        final int numberOfColumns = columns.size();
        final int startFrom = 0;
        final int endAt = listado.size();

        /* fill the table with the data. */
        for (int i = startFrom; i < endAt; ++i) {
            final Object objeto = listado.get(i);
            for (int j = 0; j < numberOfColumns; ++j) {
                final UIColumn column = (UIColumn) columns.get(j);
                addColumnValuePdf(table, column.getChildren(), i, j, objeto,
                        mapa);
            }
        }
    }

    /**
     * Añade una celda al excel.
     * 
     * @param rowHeader
     * @param list
     * @param index
     */
    private static void addColumnValuePdf(PdfPTable table,
            List<UIComponent> list, int i, int index, Object object,
            Map<String, Object> mapa) throws Exception {
        PdfPCell celda = null;
        String resultado = null;
        final StringBuilder sb = new StringBuilder();
        final Font font = new Font(Font.HELVETICA, 8, Font.NORMAL);
        for (final UIComponent component : list) {
            if (component instanceof HtmlOutputText) {
                final HtmlOutputText columna = (HtmlOutputText) component;
                if (columna.isRendered()) {
                    final ValueExpression expression = columna
                            .getValueExpression("value");
                    if (expression == null) {
                        resultado = columna.getValue().toString();
                    } else {
                        resultado = expression.getExpressionString();
                    }
                    final DateTimeConverter converter = (DateTimeConverter) columna
                            .getConverter();
                    final String value = getValueFromExpressionEL(object,
                            resultado, converter);
                    sb.append(value);
                }

            } else if (component instanceof HtmlCommandLink) {
                final HtmlCommandLink columna = (HtmlCommandLink) component;
                for (final UIComponent componente : columna.getChildren()) {
                    if (componente instanceof HtmlOutputText) {
                        final ValueExpression expression = componente
                                .getValueExpression("value");
                        final String value = getValueFromExpressionEL(object,
                                expression.getExpressionString(), null);
                        sb.append(value);
                    }
                }
            } else if (component instanceof HtmlSelectManyCheckbox) {
                final HtmlSelectManyCheckbox columna = (HtmlSelectManyCheckbox) component;
                final ValueExpression expression = columna
                        .getValueExpression("value");
                final String value = getValueFromExpressionELCheckBox(object,
                        expression.getExpressionString(), mapa);
                sb.append(value);
            } else if (component instanceof HtmlSelectBooleanCheckbox) {
                final HtmlSelectBooleanCheckbox columna = (HtmlSelectBooleanCheckbox) component;
                final ValueExpression expression = columna
                        .getValueExpression("value");
                final String value = getValueFromExpressionELCheckBox(object,
                        expression.getExpressionString(), mapa);
                sb.append(value);
            }
        }

        celda = new PdfPCell(new Phrase(sb.toString(), font));
        if (i % 2 == 0) {
            celda.setBackgroundColor(Color.WHITE);
        } else {
            celda.setBackgroundColor(new Color(243, 249, 246));
        }
        celda.setBorderColor(new Color(235, 235, 235));
        celda.setBorderWidth(1f);
        table.addCell(celda);
    }
}
