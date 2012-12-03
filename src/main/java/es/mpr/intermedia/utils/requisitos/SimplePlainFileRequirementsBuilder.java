/**
 * 
 */
package es.mpr.intermedia.utils.requisitos;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.mpr.intermedia.utils.requisitos.model.Requirement;

/**
 * @author rmartine
 */
public class SimplePlainFileRequirementsBuilder{
	private static Logger log = LoggerFactory.getLogger(SimplePlainFileRequirementsBuilder.class);
	private List<Requirement> requirementsList;
	private File outputFile;

	public SimplePlainFileRequirementsBuilder(List<Requirement> requirementsList,File outputFile){
		super();
		this.requirementsList = requirementsList;
		this.outputFile = outputFile;
	}

	public void build() throws FileRequirementsBuilderException{
		StringBuilder sb = new StringBuilder();
		for(Requirement requirement:requirementsList){
			sb.append("REQ Id: ").append(requirement.getId()).append("\n");
			sb.append("REQ Titulo: ").append(requirement.getTitulo()).append("\n");
			sb.append("REQ Descripcion:\n").append(requirement.getDescripcion()).append("\n");
			sb.append("------------------\n\n");
			try{
				FileUtils.writeStringToFile(outputFile,sb.toString());
			}catch(IOException e){
				log.error("Error al construir el archivo de texto",e);
				throw new FileRequirementsBuilderException(e);
			}
		}
	}

	public List<Requirement> getRequirementsList(){
		return requirementsList;
	}

	public void setRequirementsList(List<Requirement> requirementsList){
		this.requirementsList = requirementsList;
	}

	public File getOutputFile(){
		return outputFile;
	}

	public void setOutputFile(File outputFile){
		this.outputFile = outputFile;
	}
}
