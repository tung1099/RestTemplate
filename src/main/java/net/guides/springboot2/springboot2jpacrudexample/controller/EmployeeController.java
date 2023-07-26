package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import net.guides.springboot2.springboot2jpacrudexample.service.employees.EmployeeService;
import net.guides.springboot2.springboot2jpacrudexample.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class EmployeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public Iterable<Employee> getAllEmployees() {
		log.info("\n--------------------------------------------------------------------------->\n");
		log.info("[GET] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
		return employeeService.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		log.info("\n--------------------------------------------------------------------------->\n");
		log.info("[GET] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
		log.info(" body ['{}']", GsonUtil.toPrettyJson(employeeId));
		Employee employee = employeeService.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		log.info("\n--------------------------------------------------------------------------->\n");
		log.info("[POST] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
		log.info(" body ['{}']", GsonUtil.toPrettyJson(employee));
		return employeeService.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		log.info("\n--------------------------------------------------------------------------->\n");
		log.info("[PUT] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
		log.info(" body ['{}']", GsonUtil.toPrettyJson(employeeDetails));
		Employee employee = employeeService.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeService.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		log.info("\n--------------------------------------------------------------------------->\n");
		log.info("[DELETE] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
		log.info(" body ['{}']", GsonUtil.toPrettyJson(employeeId));
		Employee employee = employeeService.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeService.remove(employeeId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
