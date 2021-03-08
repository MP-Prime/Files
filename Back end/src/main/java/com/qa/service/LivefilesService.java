package com.qa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.Livefile;
import com.qa.persistence.repository.LivefilesRepository;
import com.qa.service.exceptions.LivefilesNotFoundException;
import com.qa.utils.FilesUtil;

@Service
public class LivefilesService {
	
	private LivefilesRepository repo;
	

	@Autowired
	public LivefilesService(LivefilesRepository repo) {
		super();
		this.repo = repo;
	}
	
	// methods
	
	//Create
	public Livefile create(Livefile file) {
		Livefile newfile = this.repo.save(file);
		return newfile;
	}
	
	//Read
	
		//By record_id
		public Livefile readByRecordId(Long id) {
			Livefile findfile = this.repo.findById(id).orElseThrow(LivefilesNotFoundException::new);
			return findfile;
		}
	    
	    // By searching for label
	    public List<Livefile> findByLabelSearch(String label) {
	    	List<Livefile> findfiles = this.repo.findLivefileByLabel(label);
	        return findfiles;
	    }
	    
	
	    // All (not for clients)
		public List<Livefile> readAll() {
			List<Livefile> getfiles = this.repo.findAll();
			return getfiles;
		}
	
	//Update	
		
	public Livefile updateById(Long id, Livefile newfile) {
		// grabs what we need to change
		Livefile findfile = this.repo.findById(id).orElseThrow(LivefilesNotFoundException::new);
			
		// the object we need to change to
		findfile.setTitle(newfile.getTitle());
		findfile.setContent(newfile.getContent());
		findfile.setLabel(newfile.getLabel());
			
		//saves the changes
		FilesUtil.mergeNotNull(newfile, findfile);
		return this.repo.save(findfile);
	}
	
	
	//Delete
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		
		// checks to see id does not exist
		return !this.repo.existsById(id);
	}
	
}
