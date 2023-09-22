package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

public class Demo6 {
	//listado de todos los usuarios
	
	public static void main(String[] args) {
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		
		//select*from tb_usuarios-->lista
		//String sql="select*from tb_usuarios";
		
		String jpql ="select p from Producto p";
		List<Producto> lstProductos =em.createQuery(jpql ,Producto.class).getResultList();
		//imprimir el listado
		for (Producto p: lstProductos) {
			System.out.println("Codigo..:"+p.getId_prod());
			System.out.println("Nombre..:"+p.getDes_prod());
			//System.out.println("Tipo..:"+u.getObjTipo().getDescripcion());
			System.out.println("Categoria: "+ p.getObjCategoria().getDescripcion());
			System.out.println("Proveedor: "+ p.getObjProverdor().getNombre_rs());
			System.out.println("--------------");
		}
		
		
		em.close();
	}
}
