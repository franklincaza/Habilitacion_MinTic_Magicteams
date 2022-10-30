package com.app.web.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.web.models.entity.Movimiento;


@SuppressWarnings("unused")
@Repository
public class MovimientoDaolmpl implements IMovimientoDao {

	@PersistenceContext
	private EntityManager em;
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Movimiento> findALL() {
		// TODO Auto-generated method stub
		return em.createQuery("from Movimiento").getResultList();
	}

	@Override
	@Transactional
	public void save(Movimiento movimiento) {
		if (movimiento.getId() != null && movimiento.getId() > 0) {
			em.merge(movimiento);
		} else {
			em.persist(movimiento);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Movimiento findOne(Long id) {
		return em.find(Movimiento.class, id);
	}

	@Override
	@Transactional 
	public void delete(Long id) {
		em.remove (findOne(id));
		
		
	}
	
	
	
	
	
}
