/**
 * 
 */
package es.mpr.intermedia.utils.requisitos.testlink.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rmartine
 */
@XmlRootElement(name = "requirements")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestLinkRequirementsList implements List<TestLinkRequirement>{
	@XmlElement(name = "requirement")
	private List<TestLinkRequirement> requirements = new ArrayList<TestLinkRequirement>();

	public List<TestLinkRequirement> getRequirements(){
		return requirements;
	}

	public void setRequirements(List<TestLinkRequirement> requirements){
		this.requirements = requirements;
	}

	@Override
	public int size(){
		return requirements.size();
	}

	@Override
	public boolean isEmpty(){
		return requirements.isEmpty();
	}

	@Override
	public boolean contains(Object o){
		return requirements.contains(o);
	}

	@Override
	public Iterator<TestLinkRequirement> iterator(){
		return requirements.iterator();
	}

	@Override
	public Object[] toArray(){
		return requirements.toArray();
	}

	@Override
	public <T>T[] toArray(T[] a){
		return requirements.toArray(a);
	}

	@Override
	public boolean add(TestLinkRequirement e){
		return requirements.add(e);
	}

	@Override
	public boolean remove(Object o){
		return requirements.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c){
		return requirements.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends TestLinkRequirement> c){
		return requirements.addAll(c);
	}

	@Override
	public boolean addAll(int index,Collection<? extends TestLinkRequirement> c){
		return requirements.addAll(index,c);
	}

	@Override
	public boolean removeAll(Collection<?> c){
		return requirements.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c){
		return requirements.retainAll(c);
	}

	@Override
	public void clear(){
		requirements.clear();
	}

	@Override
	public boolean equals(Object o){
		return requirements.equals(o);
	}

	@Override
	public int hashCode(){
		return requirements.hashCode();
	}

	@Override
	public TestLinkRequirement get(int index){
		return requirements.get(index);
	}

	@Override
	public TestLinkRequirement set(int index,TestLinkRequirement element){
		return requirements.set(index,element);
	}

	@Override
	public void add(int index,TestLinkRequirement element){
		requirements.add(index,element);
	}

	@Override
	public TestLinkRequirement remove(int index){
		return requirements.remove(index);
	}

	@Override
	public int indexOf(Object o){
		return requirements.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o){
		return requirements.lastIndexOf(o);
	}

	@Override
	public ListIterator<TestLinkRequirement> listIterator(){
		return requirements.listIterator();
	}

	@Override
	public ListIterator<TestLinkRequirement> listIterator(int index){
		return requirements.listIterator(index);
	}

	@Override
	public List<TestLinkRequirement> subList(int fromIndex,int toIndex){
		return requirements.subList(fromIndex,toIndex);
	}
}
