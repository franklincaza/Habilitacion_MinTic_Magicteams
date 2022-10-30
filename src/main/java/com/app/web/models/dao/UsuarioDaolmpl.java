package com.app.web.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.transaction.TransactionScoped;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.web.models.entity.Usuario;


@SuppressWarnings("unused")
@Repository
public class UsuarioDaolmpl implements IusuarioDao {

	@PersistenceContext
	private EntityManager em;
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findALL() {
		// TODO Auto-generated method stub
		return em.createQuery("from Usuario").getResultList();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		if (usuario.getId() != null && usuario.getId() > 0) {
			em.merge(usuario);
		} else {
			em.persist(usuario);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		return em.find(Usuario.class, id);
	}

	@Override
	@Transactional 
	public void delete(Long id) {
		em.remove (findOne(id));
		
		
	}
	
	
	
	
}
