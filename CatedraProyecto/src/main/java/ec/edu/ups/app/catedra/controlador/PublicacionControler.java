package ec.edu.ups.app.catedra.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.app.catedra.datos.ImagenDAO;
import ec.edu.ups.app.catedra.datos.PersonaDAO;
import ec.edu.ups.app.catedra.datos.PublicacionDAO;
import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.modelo.Publicacion;


@ManagedBean
public class PublicacionControler 
{
	//variables
	private Publicacion publicacion;
	private List<Publicacion> publicaciones;
	private List<Imagen> imagenes;
	private Imagen imagen;
	int codigo;

	@Inject
	private PublicacionDAO pdao;
	
	@Inject
	private ImagenDAO idao;
	
	
	@Inject
	private PersonaDAO perdao;
	
	@Inject
	private Sesion sesion;
	
	/**
	 * metodo para inicializar las variables
	 */
	@PostConstruct
	public void init() {
		publicacion = new Publicacion();
	}
	public String guardar() 
	{
		
		publicacion = pdao.leer(codigo);
		publicacion.setImagenes(imagenes);
		System.out.println(publicacion);
		// invoque al DAO y envie la entidad a persistir
		try {
			pdao.guardar(publicacion);
			loadImagenes();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			// facesContext.addMessage(null, m);
			return null;
		}

		return "ListarPubli";
	}

	
	public void loadPublicaciones() {
		publicaciones = pdao.listadoPublicaciones();
	}
	
	public void loadImagenes() {
		imagenes = pdao.listadoImagenes();
	}
	public void Borrar1(int codigo) {
		System.out.println("aqui en el borrar"+codigo);
		pdao.borrar(codigo);
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
		return "EditarP";
	}
	public String listaImagenEditar(int codigo) {
		imagen = idao.leer(codigo);
		return "EditarI";
	}
	//getters and setters
	
	
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
	public List<Imagen> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	public Imagen getImagen() {
		return imagen;
	}
	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public PublicacionDAO getPdao() {
		return pdao;
	}
	public void setPdao(PublicacionDAO pdao) {
		this.pdao = pdao;
	}
	public ImagenDAO getIdao() {
		return idao;
	}
	public void setIdao(ImagenDAO idao) {
		this.idao = idao;
	}
	public PersonaDAO getPerdao() {
		return perdao;
	}
	public void setPerdao(PersonaDAO perdao) {
		this.perdao = perdao;
	}
	public Sesion getSesion() {
		return sesion;
	}
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	
}