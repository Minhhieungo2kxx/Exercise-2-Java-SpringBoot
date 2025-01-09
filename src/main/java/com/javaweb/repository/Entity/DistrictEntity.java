package com.javaweb.repository.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "district")
public class DistrictEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@OneToMany(mappedBy = "district",fetch = FetchType.LAZY)
	List<BuidingEntity> buidings=new ArrayList <>();
	
	
	public Long getId() {
		return id;
	}

	public List<BuidingEntity> getBuidings() {
		return buidings;
	}

	public void setBuidings(List<BuidingEntity> buidings) {
		this.buidings = buidings;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
