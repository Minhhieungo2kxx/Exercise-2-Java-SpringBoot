package com.javaweb.Model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseDTO {
     private String error;
     private List<String> detai=new ArrayList<>();
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getDetai() {
		return detai;
	}
	public void setDetai(List<String> detai) {
		this.detai = detai;
	}
     
     
     
    
}
