package com.mphasis.sreenu.productservice.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.sreenu.productservice.command.CreateProductCommand;
import com.mphasis.sreenu.productservice.model.CreateProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CommandGateway commandGateway;
	
	@PostMapping
	public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
		
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.price(createProductRestModel.getPrice())
				.quantity(createProductRestModel.getQuantity())
				.title(createProductRestModel.getTittle())
				.productId(UUID.randomUUID().toString())
				.build();
		String returnValue;
		try {
			returnValue = commandGateway.sendAndWait(createProductCommand);
		}catch(Exception ex) {
			returnValue = ex.getLocalizedMessage();	
		}
		return "HTTP Post Request "+ returnValue;
	}
	@GetMapping
	public String getProduct() {
		
		return "HTTP Get Request Handled "+ environment.getProperty("local.server.port");
	}
	@PutMapping
public String updateProduct() {
		
		return "HTTP PUT Request Handled "+ environment.getProperty("local.server.port");
	}
	@DeleteMapping
public String deleteProduct() {
	
	return "HTTP DELETE Request Handled "+ environment.getProperty("local.server.port");
}

}
