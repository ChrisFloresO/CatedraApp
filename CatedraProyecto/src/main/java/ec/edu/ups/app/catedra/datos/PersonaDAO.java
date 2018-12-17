package ec.edu.ups.app.catedra.datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ec.edu.ups.app.catedra.modelo.Persona;
/**
 * 
 * @author Christian Flores
 */

@Stateless
public class PersonaDAO 
{
	
	@Inject
	private EntityManager em;
	
	//getters and setters
	
	public void insertar (Persona p) {
		
		em.persist(p);
	}
	
	public void actualizar(Persona p) {
		
		em.merge(p);
	}
	public void borrar(int id) {
		em.remove(leer(id));
	}
	public Persona leer(int id) {
		return em.find(Persona.class, id);
	}
	

	public List<Persona> listadoPersonas()
	{
		//selects  entidades mapeadas usuario
		String jppql = "SELECT p FROM Persona p";
		Query query = em.createQuery(jppql,Persona.class);
		@SuppressWarnings("unchecked")
		List<Persona> listado =query.getResultList();
		return listado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonasLogin(String correo,String clave)
	{
		
		String sql = "SELECT p FROM Persona p "
				+ "WHERE email = ? "
				+" AND contrasena = ?";
	
		Query q = em.createQuery(sql,Persona.class);
		q.setParameter(1, correo);
		q.setParameter(2, clave);
		List<Persona> persona = q.getResultList();
		return persona;
	}
	
	
	
    @SuppressWarnings("unchecked")
	public List<Persona> getPersonasLoginRC(String correo)
    {
		String sql = "SELECT p FROM Persona p "
				+ "WHERE email = ? ";
		Query q = em.createQuery(sql,Persona.class);
		q.setParameter(1, correo);
		List<Persona> personas = q.getResultList();
		return personas;
	}
    
	public boolean ValidarCedula(String cedula)
    {
		Persona persona = em.find(Persona.class, cedula);
		if(persona == null) {
			return true;
		}
		else
			return false;
	}
	/**
	 * este metodo permite guardar un plato resiviendo como parametro el plato y verificando que los campos que resiva sena diferentes de nulos.
	 * @param plato
	 */
	public void guardar(Persona p)
	{
		Persona p1 = leer(p.getId());
		try {
		if (p1 == null) {
        		insertar(p);
        	
		} 
		else
			actualizar(p);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		}
}