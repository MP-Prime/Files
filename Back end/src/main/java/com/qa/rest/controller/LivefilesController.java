package com.qa.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.Livefile;
import com.qa.service.LivefilesService;

@RestController
@CrossOrigin
@RequestMapping("/livefiles")
public class LivefilesController {
	
	// passes things to the service
	private LivefilesService service;
	
	@Autowired
	public LivefilesController(LivefilesService service) {
		super();
		this.service = service;
	}
	
	// Create
	@PostMapping("/create")
	public ResponseEntity<Livefile> create(@RequestBody Livefile file){
		Livefile createdObject = this.service.create(file);
		return new ResponseEntity<> (createdObject, HttpStatus.CREATED);
	}
	
	// Read/View/Select
	
		//Read by record_id
		@GetMapping("/read/id/{record_id}")
		public ResponseEntity<Livefile> readbyrecordid(@PathVariable Long record_id){
			Livefile readobject = this.service.readByRecordId(record_id);
			return ResponseEntity.ok(readobject);
		}
		
		//Search by user
		@GetMapping("/read/label/{label}")
		public ResponseEntity<List<Livefile>> searchbylabel(@PathVariable String label){
			List<Livefile> readobject = this.service.findByLabelSearch(label);
				return ResponseEntity.ok(readobject);
		}		
		
		//Read all (not for clients)
		@GetMapping("/read")
		public ResponseEntity<List<Livefile>> readall(){
			List<Livefile> readobject = this.service.readAll();
			return ResponseEntity.ok(readobject);
		}		
	
	// Update/Edit
	@PutMapping("/update/{record_id}")
	public ResponseEntity<Livefile> update(@PathVariable Long record_id, @RequestBody Livefile file){
		Livefile updatedObject = this.service.updateById(record_id, file);
		return new ResponseEntity<>(updatedObject, HttpStatus.ACCEPTED); // Error 202
	}
	
	// Delete
	@DeleteMapping("/delete/{record_id}")
	public ResponseEntity<Livefile> delete(@PathVariable Long record_id){
		if(this.service.delete(record_id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Error 204
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 503
		}
		
	}

}
