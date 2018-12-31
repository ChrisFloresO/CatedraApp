package ec.edu.ups.app.catedra.controlador;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import ec.edu.ups.app.catedra.datos.PublicacionDAO;
import ec.edu.ups.app.catedra.modelo.Publicacion;


@ManagedBean
@RequestScoped
public class PublicacionControler 
{
	//variables
	private Publicacion publicacion;
	private List<Publicacion> publicaciones;
	int id;

	@Inject
	private PublicacionDAO pdao;
	
	@Inject
	private Sesion sesion;
	
	/**
	 * metodo para inicializar las variables
	 */
	@PostConstruct
	public void init() {
		publicacion = new Publicacion();
		loadPublicaciones();
	}
	public String guardar() 
	{
		try {
			
		pdao.guardar(publicacion);
		System.out.println("se guardo");
		//loadPublicaciones();
		} catch (Exception e) {
			e.getMessage();
			
		}
		return "ListarPubli.xhtml";
	}
	
	public void loadPublicaciones() {
		publicaciones = pdao.listadoPublicaciones();
	}
	
	public void Borrar1(int codigo) {
		System.out.println("aqui en el borrar"+codigo);
		pdao.borrar(codigo);
		loadPublicaciones();
	}

	@PreDestroy
	public void close(){
		System.out.println("Cerrando");}

	/**
	 * este metodo se genera cuando al invocar el metodo guardar no se puede
	 * guardar; este metodo nos mostrara la causa por que no se guardo reciviendo un
	 * parametro de excepcion (e) y retornara un String con la informacion del error
	 * 
	 * @param e
	 * @return
	 */
	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda
	 * (codigo) y nos retornara un String (Editar_Plato) que es un nombre de una
	 * pagina Xhtml
	 * 
	 * @param codigo
	 * @return
	 */
	public String listaPublicacionEditar(int codigo) {
		publicacion = pdao.leer(codigo);
		return "EditarPubli";
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		listaPublicacionEditar(id);
	}
	
	
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}
	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	public PublicacionDAO getPdao() {
		return pdao;
	}
	public void setPdao(PublicacionDAO pdao) {
		this.pdao = pdao;
	}
	public Sesion getSesion() {
		return sesion;
	}
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	
}