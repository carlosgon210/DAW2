package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuarios")
@Data
@NoArgsConstructor
public class Usuario {
	@Id
	private int cod_usua;
	private String nom_usua;
	private String ape_usua;
	private String usr_usua;
	private String cla_usua;
	private String fna_usua;
	private int idtipo;
	private int est_usua;
	
	
	
	public Usuario(int cod_usua, String nom_usua, String ape_usua, String usr_usua, String cla_usua, String fna_usua,
			int idtipo, int est_usua) {
		super();
		this.cod_usua = cod_usua;
		this.nom_usua = nom_usua;
		this.ape_usua = ape_usua;
		this.usr_usua = usr_usua;
		this.cla_usua = cla_usua;
		this.fna_usua = fna_usua;
		this.idtipo = idtipo;
		this.est_usua = est_usua;
	}



	@ManyToOne
	@JoinColumn(name="idtipo", insertable=false, updatable=false)
	///objeto tipo
	private Tipo objTipo; 
	
	
	
}
