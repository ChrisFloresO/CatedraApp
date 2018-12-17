package ec.edu.ups.app.catedra.controlador;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.app.catedra.datos.PersonaDAO;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.util.Validar;


/**
 * @author Christian Flores
 */

@ManagedBean
@SessionScoped
public class PersonaControler 
{
	private int codigo;
	private Validar v;
	private String id;
	private List<Persona> personas;
	private String correoI;
	private String claveI;
	private List<Persona> listadoLogin;
	private String contraseñaA;
	private String contraseñaN;
	
	@Inject
	private PersonaDAO pdao;
	
	@Inject
	private Sesion sesion;
	
	
	private Persona persona;
	
	
	@PostConstruct		
	public void init() {
		persona=new Persona();
		loadPersonas();
		v = new Validar();
	}
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	/*
	 * metodos para crud
	 */
	/**
	 * este metod permite guardar una calificacion al momento de llamar al objeto pdao
	 * que tiene el metodo guardar que se le pasa el parametro calificacion (objeto de la clase
	 * usuari) y recarga el metodo loadUsuario, retornando un String (Logueo), siendo este
	 * un nombre de un archivo html.
	 * 
	 * @return Lista_Plato
	 */
	@SuppressWarnings("static-access")
	public String Guardar()
	{
		boolean t =v.validacionCedula(persona.getCedula());
		System.out.println(t+"y");
		if(t==false) {
			return "ListarP";
		} else 
		{
			persona.setRol(3);
			pdao.guardar(persona);
			sesion.setUsuario(persona);
			loadPersonas();
			return "ListarP";
		}
	}
	
	@SuppressWarnings("static-access")
	public String Guardar1()
	{
		persona.setRol(2);
			pdao.guardar(persona);
			loadPersonas();
			return "ListarP";
	}
	
	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda (cedula)
	 * y nos retornara un String (UsuarioE que es un nombre de una pagina Xhtml
	 * @param cedula
	 * @return
	 */
	public String listadatosEditar(int cedula) 
	{
		persona = pdao.leer(cedula);
		System.out.println("Cuenca " + persona.getNombre());
		return "EditarP.xhtml";
	}
	
	/**
	 * este metodo permite agregar los usuarios a una lista para luego mostrarlos.
	 * @param 
	 * @return
	 */
	public void loadPersonas() {
		personas =  pdao.listadoPersonas();
	}

	
	/**
	 * Este metodo recibe un parametro (cedula de Usuario)
	 * y este llama al objeto pdao(pdao de la clase UsuarioDao)
	 * y se le pase el parametro cedula y recarga el metodo loadUsuarios
	 * @param cedula
	 */
	public void Borrar(int cedula) {
		pdao.borrar(cedula);
		loadPersonas();
	}
	
	/**
	 * este metodo carga el usuario dado el parametro de correo y clave de inico 
	 * luego hace un for para verificar que tipo de usuario es con el campo rol
	 * este campo rol permitira ver si el usuario que se registra es administrador, empleado o cliente
	 * y retorna un String (RestauranteR, RestauranteL, Inicio, null).
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public String listar(){
		
		persona = (Persona) pdao.listadoPersonas();
		listadoLogin = pdao.getPersonasLogin(correoI,claveI);
		
		
		for(int i=0;i<listadoLogin.size();i++){
			System.out.println(listadoLogin.get(i).getEmail());
			System.out.println(listadoLogin.get(i).getRol());
			codigo = listadoLogin.get(i).getId();
			System.out.println(codigo);
			if(listadoLogin.get(i).getRol()==1){
				System.out.println("administrador");
				return "RestauranteR";
			}else
				if(listadoLogin.get(i).getRol()==2){
					sesion.setUsuario(listadoLogin.get(i));
					return "ListarP";
				}else
					if(listadoLogin.get(i).getRol()==3) 
					{
						sesion.setUsuario(listadoLogin.get(i));
					}
					return "ListarP";
					
				
		}
		return null;
	}

	
 public String contraseñaCambiada(){
    	 
    	 if(getContraseñaA().equals(persona.getContrasena())){
    		 persona.setContrasena(getContraseñaN());
    		 pdao.guardar(this.persona);
    	 }
    	 return "dialogo_clave";
     }
 public String cambiarContraseña(int correo){
		
	 Persona persona = pdao.leer(correo);
		this.persona  = persona;
		//System.out.println(this.usuario.getClave());
		return "cambiar_contrasenia";
	}
 
    public String editarPerfil(int correo){
		Persona persona = pdao.leer(correo);
		this.persona  = persona;
		return "UsuarioE";
	}
    
    @PreDestroy
	public void close(){
		System.out.println("Cerrando");
	}
	
	/*
	 * getters and setters
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Persona> getUsuarios() {
		return personas;
	}

	public void setUsuarios(List<Persona> personas) {
		this.personas = personas;
	}

	public String getCorreoI() {
		return correoI;
	}

	public void setCorreoI(String correoI) {
		this.correoI = correoI;
	}

	public String getClaveI() {
		return claveI;
	}

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void setClaveI(String claveI) {
		this.claveI = claveI;
	}

	public String getContraseñaA() {
		return contraseñaA;
	}

	public void setContraseñaA(String contraseñaA) {
		this.contraseñaA = contraseñaA;
	}

	public String getContraseñaN() {
		return contraseñaN;
	}

	public void setContraseñaN(String contraseñaN) {
		this.contraseñaN = contraseñaN;
	}

	public PersonaDAO getPdao() {
		return pdao;
	}

	public void setPdao(PersonaDAO pdao) {
		this.pdao = pdao;
	}

}