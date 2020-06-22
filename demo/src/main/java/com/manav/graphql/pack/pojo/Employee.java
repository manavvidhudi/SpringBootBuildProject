package com.manav.graphql.pack.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ApiModel(description = "description about the model Emloyee")
public class Employee implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes="this is the mandatory and not required property")
	public Integer emplId;
	
	@ApiModelProperty(notes="name should be in 8 charated and more than 8")
	public String name;
	public String fullName;
	public String age;
	public String grade;
	

}
