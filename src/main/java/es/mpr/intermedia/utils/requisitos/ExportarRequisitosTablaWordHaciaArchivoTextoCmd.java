/**
 * 
 */
package es.mpr.intermedia.utils.requisitos;

import java.io.File;
import java.io.IOException;
import java.util.List;

import es.mpr.intermedia.utils.Utils;
import es.mpr.intermedia.utils.requisitos.model.Requirement;
import es.mpr.intermedia.utils.requisitos.word.RequirementsWordTableExtractor;

/**
 * @author rmartine
 */
public class ExportarRequisitosTablaWordHaciaArchivoTextoCmd{
	public static void main(String[] args) throws IOException,FileRequirementsBuilderException{
		Utils.printArgs(args);
		if(args.length != 1){
			System.out.println("Tiene que pasar como argumentos: "
						+ "\nParametro 1.- Path del archivo Word con la tabla de requisitos a procesar");
		}else{
			String wordFilePath = args[0];
			File wordFile = new File(wordFilePath);
			List<Requirement> requirementsList = new RequirementsWordTableExtractor().extractRequirements(wordFile);
			if(requirementsList == null || requirementsList.isEmpty()){
				System.out.printf("No se encontraron requisitos en el archivo '%s'",wordFile.getAbsolutePath());
			}else{
				SimplePlainFileRequirementsBuilder builder = new SimplePlainFileRequirementsBuilder(requirementsList,
							new File(buildOutputFilePath(wordFilePath)));
				builder.build();
				System.out.println("Finalizado OK!");
			}
		}
	}

	private static String buildOutputFilePath(final String path){
		String newPath = path.substring(0,path.indexOf(".doc"));
		return String.format("%s.txt",newPath);
	}
}
