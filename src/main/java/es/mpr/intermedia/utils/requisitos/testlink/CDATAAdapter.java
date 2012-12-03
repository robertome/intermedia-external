/**
 * 
 */
package es.mpr.intermedia.utils.requisitos.testlink;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author rmartine
 */
public class CDATAAdapter extends XmlAdapter{
	@Override
	public Object unmarshal(Object v) throws Exception{
		return v;
	}

	@Override
	public Object marshal(Object v) throws Exception{
		return "<![CDATA[" + v + "]]>";
	}
}
