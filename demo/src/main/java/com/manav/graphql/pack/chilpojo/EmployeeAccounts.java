package com.manav.graphql.pack.chilpojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.manav.graphql.pack.pojo.Employee;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployeeAccounts implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	
	private String accountName;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL )
	@JoinColumn(name="emplId")
    private Employee employee;
	
}
