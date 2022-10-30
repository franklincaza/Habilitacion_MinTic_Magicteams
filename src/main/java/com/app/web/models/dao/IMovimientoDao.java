package com.app.web.models.dao;

import java.util.List;

import com.app.web.models.entity.Movimiento;



public interface IMovimientoDao {

	public List<Movimiento> findALL();
	
	public void save(Movimiento movimiento);
	
	public Movimiento findOne (Long id);
	
	public void delete (Long id);

}
