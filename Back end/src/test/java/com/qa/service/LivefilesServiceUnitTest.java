package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.persistence.domain.Livefile;
import com.qa.persistence.repository.LivefilesRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class LivefilesServiceUnitTest {
	@Autowired
    private LivefilesService service;

	@MockBean
    private LivefilesRepository LFrepo;
	
	private final Livefile Livefilea = new Livefile("USA continent", "Hello North America", "Generic");
	private final Livefile Livefileb = new Livefile("Kenya continent", "Hello Africa", "Generic2");
	private final Livefile Livefilec = new Livefile("UK continent", "Hello Europe", "Generic");
	private final Livefile Livefiled = new Livefile("Empty continent", "Hello Antarctica", "Generic3");
	private final Livefile Livefilee = new Livefile("China continent", "Hello Asia", "Generic2");
	
	private final List<Livefile> LFlist = List.of(Livefilea, Livefileb, Livefilec, Livefiled, Livefilee);
    
	// Test Create
	@Test
    public void LFcreateT() throws Exception {
        when(this.LFrepo.save(Livefilea)).thenReturn(Livefilea);
        assertThat(this.service.create(Livefilea)).isEqualTo(Livefilea);
        verify(this.LFrepo, atLeastOnce()).save(Livefilea);
    }
	
	// Test Read
		//Read by ID
		@Test
	    public void LFreadByIDT() throws Exception {
			when(this.LFrepo.findById(Livefilea.getRecord_id())).thenReturn(Optional.of(Livefilea));
	        assertThat(this.service.readByRecordId(Livefilea.getRecord_id())).isEqualTo(Livefilea);
	        verify(this.LFrepo, atLeastOnce()).findById(Livefilea.getRecord_id());
	    }
		
		//Read all
		@Test
		public void LFreadAllT() throws Exception {
	        when(this.LFrepo.findAll()).thenReturn(LFlist);
	        assertThat(this.service.readAll().isEmpty()).isFalse();
	        verify(this.LFrepo, atLeastOnce()).findAll();
		}
		
		//Read all
		@Test
		public void LFreadByLabelT() throws Exception {
	        when(this.LFrepo.findLivefileByLabel(Livefilea.getLabel())).thenReturn(LFlist);
	        assertThat(this.LFrepo.findLivefileByLabel(Livefilea.getLabel())).asList().isEqualTo(LFlist);
	        verify(this.LFrepo, atLeastOnce()).findLivefileByLabel(Livefilea.getLabel());
		}

	// Test Update
	@Test
	public void LFupdateT() throws Exception {
        when(this.LFrepo.findById(Livefilea.getRecord_id())).thenReturn(Optional.of(Livefilea));
        when(this.LFrepo.save(Livefilea)).thenReturn(Livefilea);
        assertThat(this.service.updateById(Livefilea.getRecord_id(), Livefilea))
                .isEqualTo(Livefilea);
        verify(this.LFrepo, atLeastOnce()).findById(Livefilea.getRecord_id());
        verify(this.LFrepo, atLeastOnce()).save(Livefilea);
	}
	
	// Test Delete
	@Test
	public void LFdeleteT() throws Exception {
        when(this.LFrepo.existsById(Livefilea.getRecord_id())).thenReturn(false);
        assertThat(this.service.delete(Livefilea.getRecord_id())).isEqualTo(true);
        verify(this.LFrepo, atLeastOnce()).existsById(Livefilea.getRecord_id());
	}	

}
