package edu.ingreso.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import edu.ingreso.model.Ingreso;

public interface IngresoRepository extends Repository<Ingreso,Integer>{
	void save(Ingreso ingreso);
	//Listar
	List<Ingreso> findAll();
	//Edit
	Ingreso findById(Integer id);
	//Delete
	void delete(Ingreso ingreso);
}
