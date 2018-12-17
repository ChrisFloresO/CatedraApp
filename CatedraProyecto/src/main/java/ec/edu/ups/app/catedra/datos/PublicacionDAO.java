package ec.edu.ups.app.catedra.datos;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Part;
import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.modelo.Publicacion;


/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class PublicacionDAO {
	//variables
	private Part file;
	private Part file2;
	private Imagen imagen;
	private List<Imagen> imagenes;
	private List<Publicacion> publicaciones;
	
	@Inject
	private ImagenDAO daoImg;
	
	@Inject
	private EntityManager em;

	
	//getters an setters
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public Part getFile2() {
		return file2;
	}

	public void setFile2(Part file2) {
		this.file2 = file2;
	}
	
	//metodos de crud
	public void insertar(Publicacion publicacion) {
		em.persist(publicacion);
	}

	public void actualizar(Publicacion publicacion) {
		em.merge(publicacion);
	}

	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}

	public Publicacion leer(int codigo) {
		return em.find(Publicacion.class, codigo);
	}
	
	public Publicacion leer1(String nombre) {
		return em.find(Publicacion.class, nombre);
	}

	public List<Publicacion> listadoPublicaciones() {
		String jppql = "SELECT pu FROM Publicacion pu";
		Query query = em.createQuery(jppql, Publicacion.class);
		@SuppressWarnings("unchecked")
		List<Publicacion> listado = query.getResultList();
		return listado;
	}
	
	public List<Imagen> listadoImagenes() {
		String jppql = "SELECT i FROM Imagen i";
		Query query = em.createQuery(jppql, Imagen.class);
		@SuppressWarnings("unchecked")
		List<Imagen> listado = query.getResultList();
		return listado;
	}

	
	/**
	 * Este metodo permite guardar la imagen como tal en la base de datos
	 * @return
	 * @throws IOException
	 */
	public String guardarImagen() throws IOException
	{
		int fotoSize = (int)file2.getSize();
           System.out.println("tamno     "+fotoSize);
           byte[] foto;
            if(fotoSize>0){
            	foto = new byte [fotoSize];
            	file2.getInputStream().read(foto);
            	imagen.setImagen(foto);		
    			daoImg.save(imagen);
            
   		}
            return null;
	}
	
	/**
	 * este metodo permite guardar un plato resiviendo como parametro el plato y verificando que los campos que resiva sena diferentes de nulos.
	 * @param plato
	 */
	public void guardar(Publicacion publicacion)
	{
		Publicacion pl = leer(publicacion.getId());
		try {
		if (pl == null) {
			int fotoSize = (int)file.getSize();
        	System.out.println("tamno     "+fotoSize);
        	byte[] foto;
        	if(fotoSize>0){
        		foto = new byte [fotoSize];
        		
					file.getInputStream().read(foto);
				
					
        		publicacion.setImagenes(imagenes);		
        		insertar(publicacion);
        	}
		} 
		else
			actualizar(publicacion);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
				
		}
	
	/**
	 * Este metodo permite transformar un arreglo de byte a string para poder mostrar la foto al cliente resiviendo como parametro el arreglo de byte.
	 * Y retorna el string con el nombre de la imagen.
	 * @param photo
	 * @return
	 */
	public String convertir (byte[] photo ) 
	{
		String bphoto = Base64.getEncoder().encodeToString(photo);
		return bphoto;
		
	}
	
}