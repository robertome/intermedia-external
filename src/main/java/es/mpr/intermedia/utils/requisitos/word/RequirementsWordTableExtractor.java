/**
 * 
 */
package es.mpr.intermedia.utils.requisitos.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.ListEntry;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.mpr.intermedia.utils.Utils;
import es.mpr.intermedia.utils.requisitos.model.Requirement;

/**
 * @author rmartine
 */
public class RequirementsWordTableExtractor{
	private static final int INDEX_ID = 0;
	private static final int INDEX_DESCRIPCION = 1;
	private static final int IDENTACION_HTML = 30;
	private static Logger log = LoggerFactory.getLogger(RequirementsWordTableExtractor.class);

	public List<Requirement> extractRequirements(File wordFile) throws IOException{
		Utils.checkFile(wordFile);
		List<Requirement> requirementsList = new ArrayList<Requirement>();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(wordFile));
		HWPFDocument doc = new HWPFDocument(fs);
		Table table = getTable(doc);
		if(table == null){
			log.info("No se encontro tabla en el archivo '{}'",wordFile.getAbsolutePath());
		}else{
			TableRow row = null;
			TableCell cell = null;
			Requirement requirement = null;
			/*
			 * Pasamos de la cabecera y la 1ra fila
			 */
			for(int rowIdx = 2;rowIdx < table.numRows();rowIdx++){
				row = table.getRow(rowIdx);
				requirement = new Requirement();
				/*
				 * Id Requisito
				 */
				cell = row.getCell(INDEX_ID);
				requirement.setId(getText(cell));
				/*
				 * Titulo Requisito
				 */
				requirement.setTitulo(requirement.getId());
				/*
				 * Descripcion
				 */
				cell = row.getCell(INDEX_DESCRIPCION);
				requirement.setDescripcion(getText(cell));
				requirement.setDescripcionHtmlFormat(getHtmlText(cell));
				requirementsList.add(requirement);
			}
		}
		return requirementsList;
	}

	private Table getTable(HWPFDocument doc){
		Table table = null;
		Range range = doc.getRange();
		int numParagraphs = range.numParagraphs();
		for(int i = 0;i < numParagraphs && table == null;i++){
			Paragraph tableParagraph = range.getParagraph(i);
			if(tableParagraph.isInTable()){
				table = range.getTable(tableParagraph);
			}
		}
		return table;
	}

	private String getText(TableCell cell){
		StringBuilder sb = new StringBuilder();
		int numParagraphs = cell.numParagraphs();
		for(int i = 0;i < numParagraphs;i++){
			Paragraph paragraph = cell.getParagraph(i);
			if(paragraph instanceof ListEntry){
				int nivelIdentacionLista = paragraph.getIlvl();
				sb.append(identacionListToken(nivelIdentacionLista));
			}
			sb.append(paragraph.text());
		}
		return sb.toString().trim();
	}

	private String identacionListToken(int nivelIdentacionLista){
		StringBuilder sb = new StringBuilder();
		int i = 0;
		do{
			sb.append("\t");
			i++;
		}while(i < nivelIdentacionLista);
		sb.append("- ");
		return sb.toString();
	}

	private String getHtmlText(TableCell cell){
		StringBuilder sb = new StringBuilder();
		int numParagraphs = cell.numParagraphs();
		for(int i = 0;i < numParagraphs;i++){
			Paragraph paragraph = cell.getParagraph(i);
			String textCell = paragraph.text().trim();
			if(paragraph instanceof ListEntry){
				int nivelIdentacionLista = paragraph.getIlvl();
				sb.append(String.format("<div style=\"margin-left:%spt\">",(nivelIdentacionLista + 1) * IDENTACION_HTML));
				sb.append("<span>-</span><span>&nbsp;</span>");
				sb.append(textCell);
				sb.append("</div>");
			}else{
				sb.append("<div style=\"margin-left:0cm\">").append(textCell).append("</div>");
			}
		}
		return sb.toString().trim();
	}
}
