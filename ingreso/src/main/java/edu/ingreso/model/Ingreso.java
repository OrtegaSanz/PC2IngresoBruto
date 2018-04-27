package edu.ingreso.model;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Ingreso implements Serializable{
	@Id
	@GeneratedValue
	private Integer Id;
	@NotNull
	@Size(min=1,max=60,message="Debe poner el nombre de su Empresa.")
	private String nomEmpresa;
	@Positive
	@NotNull
	private double ingresoBruto;
	private double rentaNeta;
	private double impuesto;
	private double rentaMensual;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNomEmpresa() {
		return nomEmpresa;
	}
	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}
	public double getIngresoBruto() {
		return ingresoBruto;
	}
	public void setIngresoBruto(double ingresoBruto) {
		this.ingresoBruto = ingresoBruto;
	}
	public double getRentaNeta() {
		return rentaNeta;
	}
	public void setRentaNeta(double rentaNeta) {
		this.rentaNeta = rentaNeta;
	}
	public double getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
	public double getRentaMensual() {
		return rentaMensual;
	}
	public void setRentaMensual(double rentaMensual) {
		this.rentaMensual = rentaMensual;
	}
}
