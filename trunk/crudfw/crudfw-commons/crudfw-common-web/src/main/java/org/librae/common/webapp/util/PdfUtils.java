package org.librae.common.webapp.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.DateUtil;
import com.lowagie.text.Document;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Clase que se encarga de pasar una tabla a un pdf o un excel.
 * 
 * @author jpserrano
 */
public class PdfUtils extends PdfPageEventHelper {

	private static final Log log = LogFactory.getLog(PdfUtils.class);
	
	protected PdfTemplate total;

	protected BaseFont helv;

	protected String urlImagen;

	protected String titulo;

	protected byte[] logo;

	protected String nombreBiblioteca;

	protected String ancestros;

	/**
	 *
	 */
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(100, 100);

		try {
			helv = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,
					BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();

		try {

			// Imagen
			Image gif = getImagen(20, 560, Image.LEFT);
			cb.addImage(gif);
			// Logotipo
			if (logo != null) {
				Image gif1 = getLogo(780, 540, Image.RIGHT);
				cb.addImage(gif1);
			}
			// imprimir una línea
			cb.moveTo(34, 34);
			cb.lineTo(809, 34);
			cb.stroke();

		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
		imprimirNombreBiblioteca(writer, document, cb);
		imprimir(writer, document, cb);
		imprimirNumeroPagina(writer, document, cb);
		imprimirFecha(writer, document, cb);
		cb.restoreState();
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		total.beginText();
		total.setFontAndSize(helv, 8);
		total.setTextMatrix(0, 0);
		total.showText(String.valueOf(writer.getPageNumber() - 1));
		total.endText();
	}

	private void imprimirNumeroPagina(PdfWriter writer, Document document,
			PdfContentByte cb) {

		String text = ComboLocaleManager.get("pagina") + " "
				+ writer.getPageNumber() + " " + ComboLocaleManager.get("de")
				+ " ";
		float textBase = document.bottom() - 35;
		cb.setFontAndSize(helv, 8);
		cb.beginText();
		float adjust = helv.getWidthPoint("0", 8);
		float textSize = helv.getWidthPoint(text, 8);
		cb.setTextMatrix(document.right() - textSize - adjust, textBase);
		cb.showText(text);
		cb.endText();
		cb.addTemplate(total, document.right() - adjust, textBase);

	}

	private void imprimirNombreBiblioteca(PdfWriter writer, Document document,
			PdfContentByte cb) {
		PdfTemplate pt = writer.getDirectContent().createTemplate(100, 100);
		float textBase = document.bottom() + 480;
		cb.setFontAndSize(helv, 8);
		cb.beginText();
		float textSize = helv.getWidthPoint(nombreBiblioteca, 8);
		cb.setTextMatrix(document.right() - textSize + 5, textBase);
		cb.showText(nombreBiblioteca);
		cb.endText();
		cb.addTemplate(pt, document.right(), textBase);
	}

	private void imprimirFecha(PdfWriter writer, Document document,
			PdfContentByte cb) {

		float textBase = document.bottom() - 35;
		cb.setFontAndSize(helv, 8);
		cb.beginText();
		String text = DateUtil.getFechaHora(DateUtil.getCurrentDate());
		cb.setTextMatrix(400, textBase);
		// document.left()
		cb.showText(text);
		cb.endText();
		cb.addTemplate(total, document.left(), -10);
	}

	private void imprimir(PdfWriter writer, Document document, PdfContentByte cb) {
		float textBase = document.bottom() - 35;
		cb.setFontAndSize(helv, 8);
		cb.beginText();
		cb.setTextMatrix(document.left(), textBase);
		cb.showText(ancestros);
		cb.endText();
		cb.addTemplate(total, document.left(), -10);
	}

	public void imprimirTitulo(Document document, String titulo, Font font,
			int align) {

		try {

			Phrase p = new Phrase(titulo, font);
			Paragraph parag = new Paragraph(p);
			parag.setAlignment(align);
			document.add(parag);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	// <======================== Getter y Setter ======================>

	private Image getImagen(int ejeX, int ejeY, int alineacion) {
		Image gif = null;
		try {
			gif = Image.getInstance(urlImagen);
			gif.scalePercent(50);
			gif.setAbsolutePosition(ejeX, ejeY);
			gif.setAlignment(alineacion);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return gif;
	}

	private Image getLogo(int ejeX, int ejeY, int alineacion) {
		Image gif = null;
		try {
			gif = Image.getInstance(logo);
			gif.scaleAbsolute(40, 50);
			gif.setAbsolutePosition(ejeX, ejeY);
			gif.setAlignment(alineacion);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return gif;
	}

	public String getAncestros() {
		return ancestros;
	}

	public byte[] getLogo() {
		return logo;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public String getNombreBiblioteca() {
		return nombreBiblioteca;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setNombreBiblioteca(String nombreBiblioteca) {
		this.nombreBiblioteca = nombreBiblioteca;
	}

	public void setUrlImagen(String url) {
		this.urlImagen = url;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public void setAncestros(String ancestros) {
		this.ancestros = ancestros;
	}

}
