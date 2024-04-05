package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;

import base.ClaseBase;

public class GenerarReportePdf extends ClaseBase{
	static String nombre="prueba";
	static String fecha;
	static Document documento;
	static FileOutputStream archivo;
	static Paragraph titulo;
	String rutaImagen;
	String hora;
	String horaIni, horaFin;

	public GenerarReportePdf() {
		super(driver);
	}
	
	
	/**
	 * Seteamos la imagen
	 * @param rutaImagen
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	
	
	/**
	 * Metodo para crear la plantilla
	 * @param nomTest nombre del test
	 * @param rutaCarpeta ruta carpeta output
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void crearPlantilla(String nomTest, File rutaCarpeta) throws FileNotFoundException, DocumentException, IOException {
		// INSTANCIAR DOCUMENTO
		documento = new Document();
		// TRAER HORA DEL SISTEMA
		hora = getDate();
		horaIni = fechaHora2();
		try {
			// CREAR RUTA Y NOMBRE DEL PDF
			archivo = new FileOutputStream(rutaCarpeta+"\\"+"Reporte-"+nomTest+"-"+hora+".pdf");
			PdfWriter.getInstance(documento, archivo);
			// CREAR ENCABEZADO 
			// UBICACION DE LA IMAGEN
			Image header = Image.getInstance(rutaImagen);
			// CAMBIAR EL SIZE DE LA IMAGEN
			header.scaleToFit(250,1000);
			header.setWidthPercentage(100);
			
			// CREAR EL TITULO DEL PDF
			titulo = new Paragraph(nomTest+"\n\n"+"Fecha Inicio: "+horaIni);
			titulo.setAlignment(1);
			
			// CREAR TABLA DEL ENCABEZADO
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			
			PdfPCell pos1 = new PdfPCell(header);
			pos1.setHorizontalAlignment(1);
			pos1.setVerticalAlignment(2);
			
			PdfPCell pos2 = new PdfPCell(titulo);
			pos2.setHorizontalAlignment(1);
			pos2.setVerticalAlignment(2);
			
			table.addCell(pos2);
			table.addCell(pos1);
			
			// GENERAR MARGENES
			documento.setMargins(30, 30, 30, 30);
			documento.open();
			// INSERTAR IMAGEN
			documento.add(table);
			documento.add(Chunk.NEWLINE);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo para crear el cuerpo del pdf
	 * @param locator elemento de la pagina
	 * @param rutaImagen 
	 * @throws DocumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void crearBody(By locator, String rutaImagen) throws DocumentException, MalformedURLException, IOException{
		// OBTENER EL NOMBRE DEL LOCALIZADO
		String locator1 = locator.toString();
		// DAR FORMATO A LA FUENTE
		Paragraph parrafo = new Paragraph();
		parrafo.setAlignment(Chunk.ALIGN_LEFT);
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));
		parrafo.add("Se realiza accion sobre el elemento: "+locator1);
		// AGREGAR MENSAJE AL PDF
		documento.add(parrafo);
		// INSERTAR IMAGEN
		Image imagen = Image.getInstance(rutaImagen);
		imagen.setBorderColor(BaseColor.BLACK);
		// CAMBIAR LA ESCALA DE LA IMAGEN
		imagen.scaleToFit(150,450);
		imagen.setAlignment(Chunk.ALIGN_CENTER);
		documento.add(imagen);
		
	}
	
	/**
	 * Metodo para crear el cuerpo del error
	 * @param locator elemento de la pagina
	 * @param rutaImagen
	 * @param msnError mensaje de error
	 * @throws DocumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void crearBodyError(By locator, String rutaImagen, String msnError) throws DocumentException, MalformedURLException, IOException{
		// OBTENER EL NOMBRE DEL LOCALIZADO
		String locator1 = locator.toString();
		// DAR FORMATO A LA FUENTE
		Paragraph parrafo = new Paragraph();
		parrafo.setAlignment(Chunk.ALIGN_LEFT);
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));
		parrafo.add("Se realiza accion sobre el elemento: "+locator1);
		// AGREGAR EL MENSAJE AL PDF
		documento.add(parrafo);
		// INSERTAR LA IMAGEN
		Image imagen = Image.getInstance(rutaImagen);
		// CAMBIAR LA ESCALA DE LA IMAGEN
		imagen.scaleToFit(700,1000);
		imagen.setAlignment(Chunk.ALIGN_LEFT);
		documento.add(imagen);
		// MENSAJE ERROR
		// DAR FORMATO A LA FUENTE
		Paragraph parrafoError = new Paragraph();
		parrafoError.setAlignment(Chunk.ALIGN_LEFT);
		parrafoError.setFont(FontFactory.getFont("Arial",8,Font.NORMAL,BaseColor.BLACK));
		parrafoError.add("EL MENSAJE DE ERROR: "+"\n"+msnError);
		documento.add(parrafoError);
	}
	
	/**
	 * Metodo para crear la plantilla
	 * @throws DocumentException
	 */
	public void cerrarPlantilla() throws DocumentException{
		documento.add(Chunk.NEWLINE);
		// DAR FORMATO A LA FUENTE
		Paragraph parrafo = new Paragraph();
		parrafo.setAlignment(Chunk.ALIGN_LEFT);
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.BOLD));
		parrafo.add("Fecha Fin: "+horaFin);
		// AGREGAR EL MENSAJE AL PDF
		horaFin = fechaHora2();
		parrafo.add("Fecha fin :"+horaFin);
		//AGREGAR EL MENSAJE AL PDF
		documento.add(parrafo);
		documento.close();
	}

}
