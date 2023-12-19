package uva.tds.practica2_grupo8;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Mario Danov Ivanov
 * Clase que implementa el DatabaseManager con su interfaz
 */
public class DataBaseManager implements IDatabaseManager {

	private static final String EX_ID = "El identificador no puede ser nulo";
	
	/**
	 * Metodo que añade un recorrido
	 * @param recorrido Recorrido a añadir
	 */
	@Override
	public void addRecorrido(Recorrido recorrido) {

		if(recorrido == null) {
			throw new IllegalArgumentException();
		}
		if(getRecorrido(recorrido.getId()) != null) {
			throw new IllegalStateException("El recorrido que intentas añadir ya existe en el sistema");
		}

		Session session = getSession();

		try {
			session.beginTransaction();

			session.persist(recorrido);
			session.flush();

		} catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	/**
	 * Metodo que elimina un recorrido
	 * @param idRecorrido idRecorrido a eliminar
	 */
	@Override
	public void eliminarRecorrido(String idRecorrido) {
		
		if(idRecorrido == null) {
			throw new IllegalArgumentException(EX_ID);
		}
		if(!getBilletesDeRecorrido(idRecorrido).isEmpty()) {
			throw new IllegalStateException("El recorrido que intentas eliminar tiene un billete asociado");
		}
		Recorrido recorrido = getRecorrido(idRecorrido);
		Session session = getSession();
		try {
			session.beginTransaction();
			
			session.delete(recorrido);
			session.flush();
			
		}catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	
	/**
	 * Metodo que actualiza un recorrido
	 * @param recorrido Recorrido a actualizar
	 */
	@Override
	public void actualizarRecorrido(Recorrido recorrido) {
		
		if(recorrido == null) {
			throw new IllegalArgumentException("El recorrido no puede ser nulo");
		}
		if(getRecorrido(recorrido.getId())== null) {
			throw new  IllegalStateException("No existe un recorrido con el identificador indicado en recorrido");
		}
		List<Billete> billetesRecorrido = getBilletesDeRecorrido(recorrido.getId());
		
		if(!billetesRecorrido.isEmpty()) {
			for(Billete item : billetesRecorrido) {
				eliminarBilletes(item.getId().getLocalizador());
			}
		}
		
		eliminarRecorrido(recorrido.getId());
		addRecorrido(recorrido);
		
		if(!billetesRecorrido.isEmpty()) {
			for(Billete item : billetesRecorrido) {
				addBillete(item);
			}
		}
		
		
	}

	/**
	 * Metodo que devuelve recorrido por su id
	 * @param idRecorrido id del recorrido a devolver
	 * @return Recorrido obtenido
	 */
	@Override
	public Recorrido getRecorrido(String idRecorrido) {
		
		Recorrido recorrido = null;
		
		if(idRecorrido == null) {
			throw new IllegalArgumentException(EX_ID);
		}
		Session session = getSession();
		try {
			session.beginTransaction();
			recorrido = session.get(Recorrido.class, idRecorrido);

		} catch (Exception e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return recorrido;
	}

	/**
	 * Metodo que devuelve la lista de recorridos
	 * @return Lista de recorridos
	 */
	@Override
	public List<Recorrido> getRecorridos() {
		
		
		Session session = getSession();
		try {
			session.beginTransaction();
			List<Recorrido> list = session.createQuery("FROM Recorrido").list();
			session.flush();
			return list;
		} catch (Exception e) {
			
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}

	
	/**
	 * Metodo que devuelve la lista de recorridos en una fecha
	 * @return Lista de recorridos en una fecha
	 */
	@Override
	public List<Recorrido> getRecorridos(LocalDate fecha) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery("FROM Recorrido r where r.infoRecorrido.fecha = ?1", Recorrido.class)
					.setParameter(1, fecha);
			List<Recorrido> list = q.getResultList();
			if(list.isEmpty())
				return  Collections.emptyList();
			return list;

		} catch (Exception e) {
			
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}

	
	/**
	 * Metodo que añade un usuario al sistema
	 */
	@Override
	public void addUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException();
		}
		if(getUsuario(usuario.getNif()) != null) {
			
			throw new IllegalStateException("El usuario que intentas añadir ya existe en el sistema");
		}

		Session session = getSession();
		try {
			session.beginTransaction();

			session.persist(usuario);
			session.flush();

		} catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	/**
	 * Metodo que elimina un usuario del sistema
	 * @param idUsuario id del usuario a eliminar
	 */
	@Override
	public void eliminarUsuario(String idUsuario) {
		if(idUsuario == null) {
			throw new IllegalArgumentException(EX_ID);
		}
		
		Usuario usuario = getUsuario(idUsuario);
		Session session = getSession();
		try {
			session.beginTransaction();
			
			session.delete(usuario);
			session.flush();
			
		}catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	/**
	 * Meodo que actualiza un Usuario del sistema
	 * @param usuario usuario nuevo actualizado
	 */
	@Override
	public void actualizarUsuario(Usuario usuario) {
		
		if(usuario == null) {
			throw new IllegalArgumentException("El recorrido no puede ser nulo");
		}
		if(getUsuario(usuario.getNif())== null) {
			throw new  IllegalStateException("No existe un usuario con el nif indicado");
		}
		Session session = getSession();
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(usuario);
			session.flush();
		}catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	/**
	 * Metodo que devuelve el usuario 
	 * @param idUsuario id del usuario que buscamos
	 */
	@Override
	public Usuario getUsuario(String idUsuario) {
		
		if(idUsuario == null) {
			throw new IllegalArgumentException(EX_ID);
		}
		
		Session session = getSession();
		Usuario usuario = null;
		try {
			session.beginTransaction();

			 usuario = session.get(Usuario.class, idUsuario);
		} catch (Exception e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return usuario;
	}

	/**
	 * Metodo que añade billete al sistema
	 * @param billete Billete que buscamos añadir al sistema
	 */
	@Override
	public void addBillete(Billete billete) {
		if(billete == null) {
			throw new IllegalArgumentException("El billete no puede ser nulo");
		}

		Session session = getSession();

		try {
			session.beginTransaction();

			session.persist(billete);
			session.flush();

		} catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * Metodo que elimina billete del sistema
	 * @param localizadorBillete localizador del billetes que buscamos eliminar
	 */
	@Override
	public void eliminarBilletes(String localizadorBillete) {
		if(localizadorBillete == null) {
			throw new IllegalArgumentException("El localizador del billete no puede ser nulo");
		}
		Session session = getSession();
		try {
			session.beginTransaction();

			session.createQuery("delete from Billete b where b.id.localizador = ?1")
				.setParameter(1, localizadorBillete)
				.executeUpdate();
				
			session.flush();

		} catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * Metodo que actualiza un billete del sistema
	 * @param billete Billete del sistema a actualizar
	 */
	@Override
	public void actualizarBilletes(Billete billete) {
		if(billete == null) {
			throw new IllegalArgumentException("El localizador del billete no puede ser nulo");
		}
		if(getBilletes(billete.getId().getLocalizador()).isEmpty()) {
			throw new IllegalStateException("No existen billetes con el localizador indicado");
		}
		Session session = getSession();
		try {
			session.beginTransaction();
			
			session.createSQLQuery("update Billete b set b.ID_RECORRIDO = ?1,b.NIF_USUARIO = ?2 where b.LOCALIZADOR = ?3")
				.setParameter(1, billete.getRecorrido().getId())
				.setParameter(2, billete.getUsuario().getNif())
				.setParameter(3, billete.getId().getLocalizador())
				.executeUpdate();
			
			session.flush();
		}catch (HibernateException e) {
			
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	/**
	 * Metodo que devuelve una lista de billetes dado un localizador
	 * @param localizadorBilletes localizador de los billetes que buscamos
	 * @return lista con los billetes
	 */
	@Override
	public List<Billete> getBilletes(String localizadorBilletes) {
		
		Session session = getSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery("FROM Billete b where b.id.localizador = ?1", Billete.class)
					.setParameter(1, localizadorBilletes);
			List<Billete> list = q.getResultList();
			session.flush();
			if(list.isEmpty())
				return  Collections.emptyList();
			return list;
		} catch (Exception e) {
			
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return Collections.emptyList();
		
	}

	/**
	 * Metodo que devuelve los billetes de un recorrido
	 * @param idRecorrido id del recorrido
	 * @return lista con los billetes de recorrido
	 */
	@Override
	public List<Billete> getBilletesDeRecorrido(String idRecorrido) {
		
		Recorrido recorrido = getRecorrido(idRecorrido);
		Session session = getSession();
		try {
			session.beginTransaction();
			
			List<Billete> list = session.createQuery("FROM Billete b where b.recorrido = ?1",Billete.class)
					.setParameter(1, recorrido).list();
			if(list.isEmpty())
				return Collections.emptyList();
			return list;
		} catch (Exception e) {
			
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return Collections.emptyList();
		
	}

	/**
	 * Metodo que devuelve los billetes de un usuario
	 * @param idUsuario id del usuario que buscamos
	 * @return lista con los billetes de usuario
	 */
	@Override
	public List<Billete> getBilletesDeUsuario(String idUsuario) {
		Usuario usuario = getUsuario(idUsuario);
		Session session = getSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery("FROM Billete b WHERE b.usuario = ?1",Billete.class)
					.setParameter(1, usuario);
			List<Billete> list = q.getResultList();
			if(list.isEmpty())
				return Collections.emptyList();
			return list;
		} catch (Exception e) {
			
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}
	
	/**
	 * Metodo que consigue la sesion de la bd
	 * @return sesion de la bd
	 */
	public Session getSession() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session;
		session = factory.getCurrentSession();
		return session;
	}
	
	/**
	 * Metodo que limpia la bd
	 */
	public void clearDatabase() {
		Session session = getSession();
		session.getTransaction().begin();
		Query query = session.createSQLQuery("Truncate table Billete");
		query.executeUpdate();
		query = session.createSQLQuery("Truncate table InfoRecorrido");
		query.executeUpdate();
		query = session.createSQLQuery("Truncate table Recorrido");
		query.executeUpdate();
		query = session.createSQLQuery("Truncate table Usuario");
		query.executeUpdate();
		session.close();

	}


}
