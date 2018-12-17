package ec.edu.ups.app.catedra.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.app.catedra.datos.PersonaDAO;
import ec.edu.ups.app.catedra.datos.PublicacionDAO;
import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.modelo.Publicacion;
@Path("/publicacion")
public class PublicacionWBSRest {
	
		@Inject
		private PublicacionDAO pdao;
		@Inject
		private PersonaDAO perdao;
		
		@GET
		@Path("/perfil")
		@Produces("application/json")
		public List<Persona> getUsuario (@QueryParam("correo") String correo,@QueryParam("clave") String clave){
			return perdao.getPersonasLogin(correo, clave);
		}

		@GET
		@Path("/perfil")
		@Produces("application/json")
		public List<Publicacion> getPublicacion (@QueryParam("id") String id,@QueryParam("texto") String texto,@QueryParam("tipo") int tipo,@QueryParam("fecha") Date fecha,@QueryParam("fotos") List<Imagen> imagenes){
			return pdao.listadoPublicaciones();
		}
}
