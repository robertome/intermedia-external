/**
 * 
 */
package es.mpr.intermedia.utils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author rmartine
 */
public class Utils{
	public static void printArgs(final String[] args){
		StringBuilder sb = new StringBuilder("Args: ");
		for(String arg:args){
			sb.append('<');
			sb.append(arg);
			sb.append("> ");
		}
		System.out.println(sb);
	}

	public static void checkFile(final File f) throws FileNotFoundException{
		if(f != null && !f.exists()){
			String msg = String.format("No se encontro el archivo <%s>",f.getAbsolutePath());
			System.out.println(msg);
			throw new FileNotFoundException(msg);
		}
	}
}
