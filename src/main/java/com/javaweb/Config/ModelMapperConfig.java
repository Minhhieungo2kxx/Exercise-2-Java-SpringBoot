package com.javaweb.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.Entity.BuidingEntity;

@Configuration
public class ModelMapperConfig {
     @Bean
     public ModelMapper modelMapper() {
    	 return new ModelMapper();
     }
    
     
}
