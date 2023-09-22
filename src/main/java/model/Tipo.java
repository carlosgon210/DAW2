package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	@Entity
	@Table(name="tb_tipos")
	@Data
	//de prefernecia no agregar constructores
public class Tipo {
	@Id
	private int idtipo;
	private String descripcion;

}
