/**
 * 
 */
package es.mpr.intermedia.utils.xml;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.xml.sax.SAXException;

import es.mpr.intermedia.utils.Utils;

/**
 * Aplicacion de linea de comandos para validar de XML contra Schema
 * 
 * @author rmartine
 */
public class ValidarXmlContraSchemaCmd{
	/**
	 * Parametro 1.- Path Schema <br>
	 * Parametro 2.- Path archivo XML a validar utilizando el Schema
	 * 
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(final String[] args) throws SAXException,IOException{
		Utils.printArgs(args);
		if(args.length != 2){
			System.out.println("Tiene que pasar como argumentos " + "\nParametro 1.- Path Schema \n"
						+ "Parametro 2.- Path archivo XML a validar utilizando el Schema");
		}else{
			Arrays.sort(args,new Comparator<String>(){
				@Override
				public int compare(final String o1,final String o2){
					if(o1.endsWith(".xsd")){
						return -1;
					}else{
						return 1;
					}
				}
			});
			String schemaFilePath = args[0];
			File schemaFile = new File(schemaFilePath);
			Utils.checkFile(schemaFile);
			String xmlFilePath = args[1];
			File xmlFile = new File(xmlFilePath);
			Utils.checkFile(xmlFile);
			System.out.printf("Validando el archivo XML <%s> contra el Schema <%s> ...\n",xmlFile.getAbsolutePath(),
						schemaFile.getAbsolutePath());
			ValidatorErrorHandler validateSchema = DOMUtils.validateSchema(xmlFile,schemaFile);
			if(validateSchema.isValidationError()){
				// System.out.printf("La validacion del XML <%s> contra el Schema <%s> fue ERRONEA",xmlFile.getAbsolutePath(),
				// schemaFile.getAbsolutePath());
				// List<String> listaMsg = validateSchema.getListaMsg();
				// for(String msg:listaMsg){
				// System.out.println(msg);
				// }
			}else{
				System.out.printf("La validacion del \nXML <%s> \ncontra el Schema <%s> \nfue CORRECTA\n",
							xmlFile.getAbsolutePath(),schemaFile.getAbsolutePath());
			}
		}
		System.exit(0);
	}
}
