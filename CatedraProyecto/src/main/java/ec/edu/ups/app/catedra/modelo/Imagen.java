package ec.edu.ups.app.catedra.modelo;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


@Entity
public class Imagen {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="img_id")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pub_id")
	private Publicacion publicacion;
	
	@Lob
	@Column(name="img_archivo")
	private byte[] imagen;
	
	//Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	//Metodo to string

	@Override
	public String toString() {
		return "Imagen [id=" + id + ", publicacion=" + publicacion + ", imagen=" + Arrays.toString(imagen) + "]";
	}

	
	
}
