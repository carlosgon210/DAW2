package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo5 {
	//listado de todos los usuarios
	
	public static void main(String[] args) {
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		
		//select*from tb_usuarios-->lista
		//String sql="select*from tb_usuarios";
		String jpql ="select u from Usuario u";
		List<Usuario> lstUsuario =em.createQuery(jpql ,Usuario.class).getResultList();
		//imprimir el listado
		for (Usuario u : lstUsuario) {
			System.out.println("Codigo..:"+u.getCod_usua());
			System.out.println("Nombre..:"+u.getNom_usua());
			System.out.println("Apellido...:"+u.getApe_usua());
			System.out.println("Tipo..:"+u.getObjTipo().getDescripcion());
			System.out.println("--------------");
		}
		
		
		em.close();
	}
}
