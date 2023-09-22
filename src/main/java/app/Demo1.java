package app;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo1 {
	//variables globales 
	//obtener la conexion -->DAO--> especificar la unidad de persistencia o conexion a usar
	static EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("ejercicio1");
	//establecer el manejador de las entidades
	static EntityManager em = fabrica.createEntityManager();
	
	
	//para registrar un nuevo usuario
	public static void main(String[] args) {
		//el obj con los datos a guardar
		Usuario u =new Usuario(1, "jose", "gonzales","jgonzales","123456", "1997/02/12", 1, 1);
		//proceso de grabacion o registro
		em.getTransaction().begin();
		//insert into .... values
		em.persist(u);
		//confirmar el registro
		em.getTransaction().commit();
		System.out.println("Registro ok ...");

	}
}
