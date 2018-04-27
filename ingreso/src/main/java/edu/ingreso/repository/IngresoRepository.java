package edu.ingreso.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import edu.ingreso.model.Ingreso;

public interface IngresoRepository extends Repository<Ingreso,Integer>{
	void save(Ingreso ingreso);
	List<Ingreso> findAll();
}
