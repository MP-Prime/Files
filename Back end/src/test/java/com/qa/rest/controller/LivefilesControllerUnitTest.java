package com.qa.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.persistence.domain.Livefile;
import com.qa.service.LivefilesService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class LivefilesControllerUnitTest {
	

    @Autowired
    private LivefilesController LFcontroller;

    @MockBean
    private LivefilesService service;

    private final Livefile Livefilea = new Livefile(1l, "USA continent", "Hello North America", "Generic");
	private final Livefile Livefileb = new Livefile(2l,"Kenya continent", "Hello Africa", "Generic2");
	private final Livefile Livefilec = new Livefile(3l, "UK continent", "Hello Europe", "Generic");
	private final Livefile Livefiled = new Livefile(4l, "Empty continent", "Hello Antarctica", "Generic3");
	private final Livefile Livefilee = new Livefile(5l, "China continent", "Hello Asia", "Generic2");
	
	private final List<Livefile> LFlist = List.of(Livefilea, Livefileb, Livefilec, Livefiled, Livefilee);
	
	// Test create
	@Test
    public void LFcreateT() throws Exception {
        when(this.service.create(Livefilea)).thenReturn(Livefilea);
        
        assertThat(new ResponseEntity<Livefile> (Livefilea, HttpStatus.CREATED))
        .isEqualTo(this.LFcontroller.create(Livefilea));
        
        verify(this.service, atLeastOnce()).create(Livefilea);
    }
	
	// Test read
		// Read by ID
		@Test
	    public void LFreadByIDT() throws Exception {
	        when(this.service.readByRecordId(Livefilea.getRecord_id())).thenReturn(Livefilea);
	        
	        assertThat(new ResponseEntity<Livefile>(Livefilea, HttpStatus.OK))
	        .isEqualTo(this.LFcontroller.readbyrecordid(Livefilea.getRecord_id()));
	        
	        verify(this.service, atLeastOnce()).readByRecordId(Livefilea.getRecord_id());
	    }
		
		// Read all
		@Test
		public void LFreadAllT() throws Exception {
	        when(this.service.readAll()).thenReturn(LFlist.stream().collect(Collectors.toList()));
	        assertThat(this.LFcontroller.readall().getBody().isEmpty()).isFalse();
	        verify(this.service, atLeastOnce()).readAll();
		}
		
		// Read by label
		@Test
		public void LFreadByLabelT() throws Exception {
	    	when(this.service.findByLabelSearch(Livefilea.getLabel()))
        	.thenReturn(LFlist.stream().collect(Collectors.toList()));
	    	
	    	assertThat(new ResponseEntity<List<Livefile>>(LFlist.stream().collect(Collectors.toList()), HttpStatus.OK))
	    	.isEqualTo(this.LFcontroller.searchbylabel(Livefilea.getLabel()));

	    	
	    	verify(this.service, atLeastOnce()).findByLabelSearch(Livefilea.getLabel());
		}
		
	// Test update
	@Test
	public void LFupdateT() throws Exception {
		when(this.service.updateById(Livefilea.getRecord_id(), Livefilea))
		.thenReturn(Livefilea);
	    
		assertThat(new ResponseEntity<Livefile>(Livefilea, HttpStatus.ACCEPTED))
	    .isEqualTo(this.LFcontroller.update(Livefilea.getRecord_id(), Livefilea));
	    verify(this.service, atLeastOnce()).updateById(Livefilea.getRecord_id(), Livefilea);
	}
	
	// Test delete
	@Test
	public void LFdeleteT() throws Exception {
		this.LFcontroller.delete(Livefilea.getRecord_id());
	    verify(this.service, atLeastOnce()).delete(Livefilea.getRecord_id());
	}		
}
