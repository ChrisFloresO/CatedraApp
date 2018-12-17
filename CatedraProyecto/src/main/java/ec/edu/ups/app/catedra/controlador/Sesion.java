package ec.edu.ups.app.catedra.controlador;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ec.edu.ups.app.catedra.modelo.Persona;


@SuppressWarnings("serial")
@Named
@SessionScoped
public class Sesion implements Serializable {

	private String username;
	private Persona persona;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Persona getUsuario() {
		return persona;
	}

	public void setUsuario(Persona persona) {
		this.persona = persona;
	}
}
