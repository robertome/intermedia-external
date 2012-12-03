/**
 * 
 */
package es.mpr.intermedia.utils.requisitos.testlink.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.mpr.intermedia.utils.requisitos.testlink.CDATAAdapter;

/**
 * @author rmartine
 */
@XmlRootElement(name = "requirement")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"docId","title","nodeOrder","description","status","type","expectedCoverage"})
public class TestLinkRequirement{
	@XmlElement(name = "docid")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	// @XmlCDATA
	private String docId;
	// @XmlElement(name = "title")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	// @XmlCDATA
	private String title;
	@XmlElement(name = "node_order")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	// @XmlCDATA
	private int nodeOrder;
	// @XmlElement(name = "description")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	// @XmlCDATA
	private String description;
	// @XmlElement(name = "status")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	// @XmlCDATA
	private String status = TestLinkRequirementStatus.BORRADOR;
	// @XmlElement(name = "type")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	// @XmlCDATA
	private int type = TestLinkRequirementType.CASO_DE_USO;
	@XmlElement(name = "expected_coverage")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	// @XmlCDATA
	private int expectedCoverage = 1; // Numero de casos de prueba

	public String getDocId(){
		return docId;
	}

	public void setDocId(String docId){
		this.docId = docId;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public int getNodeOrder(){
		return nodeOrder;
	}

	public void setNodeOrder(int nodeOrder){
		this.nodeOrder = nodeOrder;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getExpectedCoverage(){
		return expectedCoverage;
	}

	public void setExpectedCoverage(int expectedCoverage){
		this.expectedCoverage = expectedCoverage;
	}
}
