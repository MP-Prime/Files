package com.qa.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.Livefile;
import com.qa.persistence.repository.LivefilesRepository;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:fileschema.sql", "classpath:filedata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")

public class LivefilesControllerIntegratedTest {
	
	@Autowired
	private MockMvc mockFileMVC;

	@Autowired
	private ObjectMapper objmapper;

    private final Livefile Livefilea = new Livefile(1l, "USA continent", "Hello North America", "Generic");
	private final Livefile Livefileb = new Livefile(2l,"Kenya continent", "Hello Africa", "Generic2");
	private final Livefile Livefilec = new Livefile(3l, "UK continent", "Hello Europe", "Generic");
	private final Livefile Livefiled = new Livefile(4l, "Empty continent", "Hello Antarctica", "Generic3");
	private final Livefile Livefilee = new Livefile(5l, "China continent", "Hello Asia", "Generic2");
	private final Livefile Livefilef = new Livefile(6l, "Chine continent", "Hello Asia", "Generic2");
	
	private final List<Livefile> LFlist = List.of(Livefilea, Livefileb, Livefilec, Livefiled, Livefilee);
	private final List<Livefile> LFlist3 = List.of(Livefilea, Livefileb, Livefilec, Livefiled, Livefilee, Livefilef);
	
	private final List<Livefile> LFlist2 = List.of(Livefilea, Livefilec);
	
	// Testing create
    @Test
    public void LFIcreateT() throws Exception {
    	this.mockFileMVC.perform(post("/livefiles/create")
			.accept(MediaType.APPLICATION_JSON)
            	.contentType(MediaType.APPLICATION_JSON).content(this.objmapper.writeValueAsString(Livefilef)))
            	.andExpect(status().isCreated())
                .andExpect(content().json(this.objmapper.writeValueAsString(Livefilef)));
    }

    // Testing read
    
    	// Read by ID
	    @Test
	  	public void LFIreadByIDT() throws Exception {
	        this.mockFileMVC.perform(get("/livefiles/read/id/" + Livefilea.getRecord_id())
	        		.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().json(this.objmapper.writeValueAsString(Livefilea)));
	    }

	    // Read all
	    @Test
	    public void LFIreadAllT() throws Exception {
	        this.mockFileMVC.perform(get("/livefiles/read")
	        	.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andExpect(content().json(this.objmapper.writeValueAsString(LFlist3.stream().collect(Collectors.toList()))));
	    }

	    // Read by label
	    @Test
	    public void LFIfindByLabelT() throws Exception {
	        this.mockFileMVC.perform(get("/livefiles/read/label/" + Livefilea.getLabel())
	        		.accept(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
		        	.andExpect(content().json(this.objmapper.writeValueAsString(LFlist.stream().filter(e -> e.getLabel().equals(Livefilea.getLabel())).collect(Collectors.toList()))));                
	                
	    }

	// Update by ID   
    @Test
    public void LFIupdateT() throws Exception {
        this.mockFileMVC.perform(put("/livefiles/update/" + Livefilea.getRecord_id()).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objmapper.writeValueAsString(Livefilea)))
                .andExpect(status().isAccepted())
                .andExpect(content().json(this.objmapper.writeValueAsString(Livefilea)));
    }

    // Delete by ID
    @Test
    public void LFIdeleteT() throws Exception {
        this.mockFileMVC.perform(delete("/livefiles/delete/" + Livefilef.getRecord_id()))
        .andExpect(status().isNoContent());
    }




}
