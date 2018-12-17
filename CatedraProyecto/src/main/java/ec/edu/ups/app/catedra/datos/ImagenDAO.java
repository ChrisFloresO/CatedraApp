package ec.edu.ups.app.catedra.datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Persona;


@Stateless
public class ImagenDAO {

	@Inject
	private EntityManager em;
	
	public void save(Imagen archivo){
		if(em.find(Imagen.class,archivo.getId())==null)
			insertar(archivo);
		else
			actualizar(archivo);
	}
	
	public void insertar(Imagen archivo){
		em.persist(archivo);
	}
	
	public void actualizar(Imagen archivo){
		em.merge(archivo);
	}
	public Imagen leer(int id) {
		return em.find(Imagen.class, id);
	}
}
