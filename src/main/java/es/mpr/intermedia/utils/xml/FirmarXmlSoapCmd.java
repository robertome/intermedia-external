/**
 * 
 */
package es.mpr.intermedia.utils.xml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import es.mpr.intermedia.utils.Utils;
import es.mpr.ws.seguridad.certificados.GestorCertificado;
import es.mpr.ws.seguridad.firma.IdentificadorXmlFirma;
import es.mpr.ws.seguridad.firma.XmlFirmaException;
import es.mpr.ws.seguridad.firma.XmlFirmador;
import es.mpr.ws.seguridad.firma.XmlFirmadorFactory;

/**
 * Aplicacion de linea de comandos para firmar XML de un mensaje SOAP
 * 
 * @author rmartine
 */
public class FirmarXmlSoapCmd{
	private static final String ENCODING = "UTF-8";
	private static final String[] TIPOS_FIRMA = {"XMLDSig","XAdES","WSSecurity","WSSTSA"};
	static{
		org.apache.xml.security.Init.init();
	}

	/**
	 * Parametro 1.- Path del archivo XML con el mensaje SOAP a firmar<br>
	 * Parametro 2.- Path del archivo crypto.properties con la configuracion del certificado a utilizar<br>
	 * Parametro 3.- String con el tipo de firma a Utilizar: 'XMLDSig', 'XAdES','WSSecurity' o 'WSSTSA'"
	 * 
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws XmlFirmaException
	 */
	public static void main(final String[] args) throws SAXException,IOException,ParserConfigurationException,
				XPathExpressionException,XmlFirmaException{
		Utils.printArgs(args);
		if(args.length != 3){
			System.out
						.println("Tiene que pasar como argumentos: "
									+ "\nParametro 1.- Path del archivo XML con el mensaje SOAP a firmar	 "
									+ "\nParametro 2.- Path del archivo crypto.properties con la configuracion del certificado a utilizar	 "
									+ "\nParametro 3.- String con el tipo de firma a Utilizar: 'XMLDSig', 'XAdES','WSSecurity' o 'WSSTSA'");
		}else{
			String xmlFilePath = args[0];
			File xmlFile = new File(xmlFilePath);
			Utils.checkFile(xmlFile);
			String cryptoFilePath = args[1];
			File cryptoFile = new File(cryptoFilePath);
			Utils.checkFile(cryptoFile);
			String tipoFirma = args[2];
			checkTipoFirma(tipoFirma);
			Document soapDocument = es.mpr.intermedia.utils.xml.DOMUtils.createDocument(new FileReader(xmlFile));
			GestorCertificado inicializarGestorCertificados = inicializarGestorCertificados(cryptoFilePath);
			Document soapDocumentFirmado = firmarMensaje(soapDocument,tipoFirma,inicializarGestorCertificados);
			String outputFilePah = buildOutputFilePah(xmlFile.getPath(),tipoFirma);
			almacenarMensajeFirmado(soapDocumentFirmado,outputFilePah);
		}
		System.exit(0);
	}

	private static Document firmarMensaje(final Document soapDocument,final String tipoFirma,
				final GestorCertificado gestorCertificado) throws XPathExpressionException,XmlFirmaException{
		XmlFirmadorFactory factory = XmlFirmadorFactory.getInstance();
		XmlFirmador xmlFirmador = factory.crearXmlFirmador(IdentificadorXmlFirma.getXmlFirmadorClassName(tipoFirma),
					gestorCertificado);
		Element elementoFirmar = DOMUtils.findElementByXPath(soapDocument,null,"//*[local-name()='Body']");
		Document soapDocumentFirmado = xmlFirmador.firmarSOAPDocument(soapDocument,elementoFirmar);
		return soapDocumentFirmado;
	}

	private static void checkTipoFirma(String tipoFirma) throws IllegalArgumentException{
		if(!ArrayUtils.contains(TIPOS_FIRMA,tipoFirma)){
			String msg = String.format("El tipo de firma <%s> no es un tipo valido [%s]",tipoFirma,TIPOS_FIRMA);
			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
	}

	private static void almacenarMensajeFirmado(final Document soapFirmado,final String xmlFilePathDestino){
		String documentToString = DOMUtils.documentToString(soapFirmado);
		File xmlFile = new File(xmlFilePathDestino);
		// System.out.println("XML FIRMADO:\n" + documentToString);
		try{
			FileUtils.writeStringToFile(xmlFile,documentToString,ENCODING);
		}catch(IOException e){
			System.out.println("Error en la creacion del fichero xml firmado");
		}
	}

	private static String buildOutputFilePah(final String path,final String tipoFirma){
		String newPath = path.substring(0,path.indexOf(".xml"));
		return String.format("%s_%s_firmado.xml",newPath,tipoFirma);
	}

	private static GestorCertificado inicializarGestorCertificados(final String cryptoFilePath) throws IOException{
		GestorCertificado gestorCertificados = new GestorCertificado();
		gestorCertificados.cargarPropiedades(new File(cryptoFilePath));
		return gestorCertificados;
	}
}
