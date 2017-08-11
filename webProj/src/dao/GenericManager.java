package dao;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.persistence.*;

public class GenericManager {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private Class<?> clazz;
	private Field[] field;
	private int primaryKeyIndex;

	
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public EntityManager getEm() {
		return em;
	}

	public GenericManager(String PersistenceUnit, Class<?> clazz){
		emf = Persistence.createEntityManagerFactory(PersistenceUnit);
		em = emf.createEntityManager();
		this.clazz = clazz;
		field = clazz.getDeclaredFields();
		Id id;
		for (int i = 0; i < field.length; i++) {
			id = field[i].getDeclaredAnnotation(Id.class);
			if ( id != null) {
				field[i].setAccessible(true);
				primaryKeyIndex = i;
				break;
			}
		}
	}
	
	public void beginTransaction(){
		em.getTransaction().begin();
	}
	
	public void commitTransaction(){
		em.getTransaction().commit();
	}
	
	public Collection<?> list() {
		System.out.println("SELECT o FROM " + clazz.getSimpleName() + " o");
		return em.createQuery("SELECT o FROM " + clazz.getSimpleName() + " o").getResultList();
	}

	public Object findByPrimaryKey(int primaryKey) throws NoSuchRow {
		Object obj = em.find(clazz, primaryKey);
		if (obj == null)
			throw new NoSuchRow();
		else
			return obj;
	}
	
	public static String hash1(String s) {
        MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
	           m.update(s.getBytes(),0,s.length());
	           return new BigInteger(1,m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	// Anderer Hash
	public byte[] hash(String password) throws NoSuchAlgorithmException {
	    MessageDigest sha256 = MessageDigest.getInstance("SHA-256");        
	    byte[] passBytes = password.getBytes();
	    byte[] passHash = sha256.digest(passBytes);
	    return passHash;
	}*/

	public void delete(int primaryKey) throws NoSuchRow {
		System.out.println("In Delete pk: "+primaryKey);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Object obj = em.find(clazz, primaryKey);
		if (obj != null)
			em.remove(obj);
		else
			throw new NoSuchRow();
		tx.commit();
	}

	public void save(Object arg) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Object obj = null;
		try {
			obj = em.find(clazz, field[primaryKeyIndex].get(arg));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		if (obj != null) {
			em.merge(arg);
		} else {
			em.persist(arg);
		}
		tx.commit();
	}
	
	public void close() {
		em.close();
	}

}
