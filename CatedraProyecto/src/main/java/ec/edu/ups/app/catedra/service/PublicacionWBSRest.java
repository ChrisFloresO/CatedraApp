package ec.edu.ups.app.catedra.service;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.app.catedra.datos.PersonaDAO;
import ec.edu.ups.app.catedra.datos.PublicacionDAO;

import ec.edu.ups.app.catedra.modelo.Publicacion;

@Path("/publicacion")
public class PublicacionWBSRest {
	
		@Inject
		private PublicacionDAO pdao;
		@Inject
		private PersonaDAO perdao;

		@GET
		@Path("/listarpublicaciones")
		@Produces("application/json")
		public List<Publicacion> getPlatos(){		 
			List<Publicacion> lista = pdao.listadoPublicaciones();
			
			return lista;
		}
		
		
		@GET
		@Path("/buscar")
		@Produces("application/json")
		public List<Publicacion> getPlatosB(@QueryParam("fecha") String fecha){
			return pdao.listadoPubliFecha(fecha);
		}
}
