package ec.edu.ups.app.catedra.modelo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="tbl_publicacion")
@NamedQuery(name="Publicacion.findAll", query="SELECT pu FROM Publicacion pu") 
public class Publicacion {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="pub_id")
		private int id;
		
		@Size(min=1,max=280)
		@Column(name="pub_texto")
		private String texto;
		
		@NotNull
		@Column(name="pub_tipo")
		private int tipo;
		
		@Lob
		@Column(name="pub_imagen")
		private byte[] imagen;
		
		@Column(name = "pub_fecha")
		private String fecha;
		

		//Getters and Setters	
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}
		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}	

		public byte[] getImagen() {
			return imagen;
		}

		public void setImagen(byte[] imagen) {
			this.imagen = imagen;
		}

		@Override
		public String toString() {
			return "Publicacion [id=" + id + ", texto=" + texto + ", tipo=" + tipo + ", imagen="
					+ Arrays.toString(imagen) + ", fecha=" + fecha + "]";
		}

}
