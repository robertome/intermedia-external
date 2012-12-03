/**
 * 
 */
package es.mpr.intermedia.utils.requisitos.testlink;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import es.mpr.intermedia.utils.requisitos.FileRequirementsBuilderException;
import es.mpr.intermedia.utils.requisitos.model.Requirement;
import es.mpr.intermedia.utils.requisitos.testlink.model.TestLinkRequirement;
import es.mpr.intermedia.utils.requisitos.testlink.model.TestLinkRequirementStatus;
import es.mpr.intermedia.utils.requisitos.testlink.model.TestLinkRequirementType;
import es.mpr.intermedia.utils.requisitos.testlink.model.TestLinkRequirementsList;

/**
 * @author rmartine
 */
public class TestLinkXmlFileRequirementsBuilder{
	private static final String MSG_ERROR_JAXB = "Error utilizando JAXB";
	private static Logger log = LoggerFactory.getLogger(TestLinkXmlFileRequirementsBuilder.class);
	private List<Requirement> requirementsList;
	private File outputFile;

	public TestLinkXmlFileRequirementsBuilder(List<Requirement> requirementsList,File outputFile){
		super();
		this.requirementsList = requirementsList;
		this.outputFile = outputFile;
	}

	public void build() throws FileRequirementsBuilderException{
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(TestLinkRequirementsList.class);
			Marshaller marshaller = config(jaxbContext.createMarshaller());
			TestLinkRequirementsList testLinkRequirementsList = createTestLinkRequirementsList(requirementsList);
			marshaller.marshal(testLinkRequirementsList,outputFile);
		}catch(JAXBException e){
			log.error(MSG_ERROR_JAXB,e);
			throw new FileRequirementsBuilderException(e);
		}catch(Exception e){
			log.error("Error inesperado",e);
			throw new FileRequirementsBuilderException(e);
		}
	}

	private Marshaller config(final Marshaller marshaller) throws PropertyException{
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
		marshaller.setProperty("com.sun.xml.bind.characterEscapeHandler",new CharacterEscapeHandler(){
			@Override
			public void escape(char[] ac,int i,int j,boolean flag,Writer writer) throws IOException{
				// do not escape
				writer.write(ac,i,j);
			}
		});
		return marshaller;
	}

	private TestLinkRequirementsList createTestLinkRequirementsList(List<Requirement> requirementsList){
		TestLinkRequirementsList list = new TestLinkRequirementsList();
		Requirement req = null;
		TestLinkRequirement testLinkReq = null;
		for(int i = 0;i < requirementsList.size();i++){
			req = requirementsList.get(i);
			testLinkReq = new TestLinkRequirement();
			testLinkReq.setDocId(req.getId());
			testLinkReq.setTitle(req.getTitulo());
			testLinkReq.setNodeOrder(i);
			testLinkReq.setDescription(req.getDescripcionHtmlFormat());
			testLinkReq.setStatus(TestLinkRequirementStatus.BORRADOR);
			testLinkReq.setType(TestLinkRequirementType.CASO_DE_USO);
			testLinkReq.setExpectedCoverage(1);
			list.add(testLinkReq);
		}
		return list;
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
