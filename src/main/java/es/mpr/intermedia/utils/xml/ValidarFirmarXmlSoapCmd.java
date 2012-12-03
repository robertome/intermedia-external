/**
 * 
 */
package es.mpr.intermedia.utils.xml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
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
public class ValidarFirmarXmlSoapCmd{
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
		if(args.length != 2){
			System.out
						.println("Tiene que pasar como argumentos: "
									+ "\nParametro 1.- Path del archivo XML con el mensaje SOAP a firmar	 "
									+ "\nParametro 2.- Path del archivo crypto.properties con la configuracion del certificado a utilizar	 ");
		}else{
			String xmlFilePath = args[0];
			File xmlFile = new File(xmlFilePath);
			Utils.checkFile(xmlFile);
			String cryptoFilePath = args[1];
			File cryptoFile = new File(cryptoFilePath);
			Utils.checkFile(cryptoFile);
			Document soapDocument = es.mpr.intermedia.utils.xml.DOMUtils.createDocument(new FileReader(xmlFile));
			GestorCertificado gestorCertificados = inicializarGestorCertificados(cryptoFilePath);
			System.out.printf("La firma del mensaje %s es %s",xmlFilePath,
						validarFirmaMensaje(soapDocument,gestorCertificados));
		}
		System.exit(0);
	}

	private static boolean validarFirmaMensaje(final Document soapDocument,final GestorCertificado gestorCertificado)
				throws XPathExpressionException,XmlFirmaException{
		XmlFirmadorFactory factory = XmlFirmadorFactory.getInstance();
		XmlFirmador xmlFirmador = factory.crearXmlFirmador(IdentificadorXmlFirma.identificarXmlFirmador(soapDocument),
					gestorCertificado);
		return xmlFirmador.verificarFirma(soapDocument);
	}

	private static GestorCertificado inicializarGestorCertificados(final String cryptoFilePath) throws IOException{
		GestorCertificado gestorCertificados = new GestorCertificado();
		gestorCertificados.cargarPropiedades(new File(cryptoFilePath));
		return gestorCertificados;
	}
}
