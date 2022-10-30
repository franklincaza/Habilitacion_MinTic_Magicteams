package com.app.web.models.dao;

import java.util.List;

import com.app.web.models.entity.Usuario;

public interface IusuarioDao {

	public List<Usuario> findALL();
	
	public void save(Usuario usuario);
	
	public Usuario findOne (Long id);
	
	public void delete (Long id);
	
}
