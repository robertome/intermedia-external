/**
 * 
 */
package es.mpr.intermedia.utils.requisitos.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain model
 * 
 * @author rmartine
 */
public class Requirement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3125803313076454818L;
	private String id;
	private String titulo;
	private String descripcion;
	private String estado;
	private String tipo;
	private String prioridad;
	private Date fechaSolicitud;
	private Date fechaAceptacion;
	private Date fechaImplementacion;
	private String descripcionHtmlFormat;

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getTitulo(){
		return titulo;
	}

	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getEstado(){
		return estado;
	}

	public void setEstado(String estado){
		this.estado = estado;
	}

	public String getTipo(){
		return tipo;
	}

	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	public String getPrioridad(){
		return prioridad;
	}

	public void setPrioridad(String prioridad){
		this.prioridad = prioridad;
	}

	public Date getFechaSolicitud(){
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud){
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaAceptacion(){
		return fechaAceptacion;
	}

	public void setFechaAceptacion(Date fechaAceptacion){
		this.fechaAceptacion = fechaAceptacion;
	}

	public Date getFechaImplementacion(){
		return fechaImplementacion;
	}

	public void setFechaImplementacion(Date fechaImplementacion){
		this.fechaImplementacion = fechaImplementacion;
	}

	public String getDescripcionHtmlFormat(){
		return descripcionHtmlFormat;
	}

	public void setDescripcionHtmlFormat(String descripcionHtmlFormat){
		this.descripcionHtmlFormat = descripcionHtmlFormat;
	}
}
