package com.javaweb.repository.ipl;

import java.util.List;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.Builder.Buildingsearchbuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuidingEntity;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
//@Primary
public class BuildingRepositoryJPAiTestmpl implements BuildingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuidingEntity> FindAll(Buildingsearchbuilder buildingsearchbuilder) {

		String sql="Select * from building b where b.name like '%building%' ";
		Query query= entityManager.createNativeQuery(sql,BuidingEntity.class);
		return query.getResultList();
	}

}
