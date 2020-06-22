package com.manav.graphql.pack.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manav.graphql.pack.Dao.EmployeeAccountsRepository;
import com.manav.graphql.pack.Dao.EmployeeRepository;
import com.manav.graphql.pack.chilpojo.EmployeeAccounts;
import com.manav.graphql.pack.pojo.Employee;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class GraphQlController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeAccountsRepository employeeAccountRepository;

	@Value("classpath:employee.graphqls")
	private Resource schemaResource;
	
	private GraphQL graphQl;

	@PostConstruct
	public void loadResources() throws IOException {
		
		File file = schemaResource.getFile();
		
		TypeDefinitionRegistry typeDefinationRegistry = new SchemaParser().parse(file);
		
		RuntimeWiring runtimeWiring = createRuntimeWiring();
		
		GraphQLSchema graphQlSchema = new SchemaGenerator().makeExecutableSchema(typeDefinationRegistry, runtimeWiring);
		
		graphQl= GraphQL.newGraphQL(graphQlSchema).build();
	}

	private RuntimeWiring createRuntimeWiring() {

		DataFetcher<List<Employee>> fetchOne = data -> (List<Employee>)employeeRepository.findAll();

		DataFetcher<Employee> fetchTwo = data -> employeeRepository.findByName(data.getArgument("empId"));

		return RuntimeWiring.newRuntimeWiring().type("Query", typeWriter -> 
			typeWriter.dataFetcher("getAllEmployee", fetchOne).dataFetcher("findEmployee", fetchTwo)
		).build();

	}
	
	@PostMapping("/getAll")
	public ResponseEntity<Object> getAllEmp(@RequestBody String query)
	{
		ExecutionResult ex = graphQl.execute(query);
		return new ResponseEntity<>(ex, HttpStatus.OK);
	}
	
	@PostMapping("/getbyId")
	public ResponseEntity<Object> getbyId(@RequestBody String query)
	{
		ExecutionResult ex = graphQl.execute(query);
		return new ResponseEntity<>(ex, HttpStatus.OK);
	}

	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody List<Employee> employees) {
		for (Employee employee : employees) {
			employeeRepository.save(employee);
		}

		return "record inserted successfully" + employees.size();
	}

	@GetMapping("/getEmployees")
	@ApiOperation(value="Find all Employee", notes="click on send and get all employee data", response=Employee.class)
	public List<Employee> getEmployee() {
		return (List<Employee>) employeeRepository.findAll();
	}
	
	@PostMapping("/addEmployeeAccount")
	public String addEmployeeAccount(@RequestBody List<EmployeeAccounts> employees) {
		for (EmployeeAccounts employee : employees) {
			employeeAccountRepository.save(employee);
		}

		return "record inserted successfully" + employees.size();
	}

	@GetMapping("/getEmployeesAccounts")
	@ApiOperation(value="Find all Employee Accounts", notes="click on send and get all employee data", response=EmployeeAccounts.class)
	public List<EmployeeAccounts> getEmployeeAccounts() {
		return (List<EmployeeAccounts>) employeeAccountRepository.findAll();
	}


}
