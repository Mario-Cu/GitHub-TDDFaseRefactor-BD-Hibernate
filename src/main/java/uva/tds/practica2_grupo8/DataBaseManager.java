package uva.tds.practica2_grupo8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DataBaseManager implements IDatabaseManager {

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
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	@Override
	public void eliminarRecorrido(String idRecorrido) {
		
		if(idRecorrido == null) {
			throw new IllegalArgumentException("El identificador no puede ser nulo");
		}
		if(getBilletesDeRecorrido(idRecorrido) == null) {
			throw new IllegalStateException("El recorrido que intentas eliminar tiene un billete asociado");
		}
		
		Session session = getSession();
		Recorrido recorrido = getRecorrido(idRecorrido);
		try {
			session.beginTransaction();
			
			session.delete(recorrido);
			session.flush();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void actualizarRecorrido(Recorrido recorrido) {
		
		if(recorrido == null) {
			throw new IllegalArgumentException("El recorrido no puede ser nulo");
		}
		if(getRecorrido(recorrido.getId())== null) {
			throw new  IllegalStateException("No existe un recorrido con el identificador indicado en recorrido");
		}
		Session session = getSession();
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(recorrido);
			session.flush();
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public Recorrido getRecorrido(String idRecorrido) {
		
		if(idRecorrido == null) {
			throw new IllegalArgumentException("El identificador no puede ser nulo");
		}
		Session session = getSession();
		
		try {
			session.beginTransaction();

			Recorrido recorrido = session.get(Recorrido.class, idRecorrido);
			return recorrido;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public List<Recorrido> getRecorridos() {
		
		
		Session session = getSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery("select * from RECORRIDO", Recorrido.class);
			List<Recorrido> list = q.getResultList();
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	//CAMBIAR 
	public List<Recorrido> getRecorridos(LocalDate fecha) {
		
		return null;
	}

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
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	@Override
	public void eliminarUsuario(String idUsuario) {
		if(idUsuario == null) {
			throw new IllegalArgumentException("El identificador no puede ser nulo");
		}
		
		Session session = getSession();
		Usuario usuario = getUsuario(idUsuario);
		try {
			session.beginTransaction();
			
			session.delete(usuario);
			session.flush();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

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
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	@Override
	public Usuario getUsuario(String idUsuario) {
		
		if(idUsuario == null) {
			throw new IllegalArgumentException("El identificador no puede ser nulo");
		}
		
		Session session = getSession();
		
		try {
			session.beginTransaction();

			Usuario usuario = session.get(Usuario.class, idUsuario);
			return usuario;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

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
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void eliminarBilletes(String localizadorBillete) {
		if(localizadorBillete == null) {
			throw new IllegalArgumentException("El localizador del billete no puede ser nulo");
		}
		Session session = getSession();
		try {
			session.beginTransaction();

			session.createNativeQuery("delete from BILLETE where id.localizador = :localizador")
				.setParameter("localizador", localizadorBillete)
				.executeUpdate();
				
			session.flush();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void actualizarBilletes(Billete billete) {
		if(billete == null) {
			throw new IllegalArgumentException("El localizador del billete no puede ser nulo");
		}
		if(getBilletes(billete.getId().getLocalizador()) == null) {
			throw new IllegalStateException("No existen billetes con el localizador indicado");
		}
		Session session = getSession();
		try {
			session.beginTransaction();
			
			session.createNativeQuery("update BILLETE set ID_RECORRIDO = ?, set = NIF_USUARIO = ? where id.localizador = ?")
				.setParameter(0, billete.getRecorrido().getId())
				.setParameter(1, billete.getUsuario().getNif())
				.setParameter(2, billete.getId().getLocalizador())
				.executeUpdate();
			
			session.flush();
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Billete> getBilletes(String localizadorBilletes) {
		
		Session session = getSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery("select * from BILLETE b where b.id.localizador = ?", Billete.class)
					.setParameter(0, localizadorBilletes);
			List<Billete> list = q.getResultList();
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;
		
	}

	@Override
	public List<Billete> getBilletesDeRecorrido(String idRecorrido) {

		Session session = getSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery("select * from BILLETE b where b.ID_RECORRIDO = ?", Billete.class)
					.setParameter(0, idRecorrido);
			List<Billete> list = q.getResultList();
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;
		
	}

	@Override
	public List<Billete> getBilletesDeUsuario(String idUsuario) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery("select * from BILLETE b where b.NIF_USUARIO = ?", Billete.class)
					.setParameter(0, idUsuario);
			List<Billete> list = q.getResultList();
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;
	}
	
	private Session getSession() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session;
		try {
			session = factory.getCurrentSession();
			return session;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


}
