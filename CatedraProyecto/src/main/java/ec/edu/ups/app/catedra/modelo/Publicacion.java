package ec.edu.ups.app.catedra.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
		@Column(name="pub_id",length=10)
		private int id;
		
		@Size(min=1,max=280)
		@Column(name="pub_texto")
		private String texto;
		
		@NotNull
		@Size(min=1,max=2)
		@Column(name="pub_tipo")
		private int tipo;
		
		@OneToMany(mappedBy="publicacion")
		private List<Imagen> Imagenes;
		
		@Column(name = "pub_fecha")
		private Date fecha;
		
		
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

		public List<Imagen> getImagenes() {
			return Imagenes;
		}

		public void setImagenes(List<Imagen> imagenes) {
			Imagenes = imagenes;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		@Override
		public String toString() {
			return "Publicacion [id=" + id + ", texto=" + texto + ", tipo=" + tipo + ", Imagenes=" + Imagenes
					+ ", fecha=" + fecha + "]";
		}
		
		//Metodo to String

	
		
		

		
		
	


}
