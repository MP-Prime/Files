package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.qa.persistence.domain.Livefile;
import com.qa.persistence.repository.LivefilesRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class LivefilesServiceIntegratedTest {
	
	@Autowired
    private LivefilesService service;

    @Autowired
    private LivefilesRepository LFrepo;

	
    private final Livefile Livefilea = new Livefile(1l, "USA continent", "Hello North America", "Generic");
	private final Livefile Livefileb = new Livefile(2l, "Kenya continent", "Hello Africa", "Generic2");
	private final Livefile Livefilec = new Livefile(3l, "UK continent", "Hello Europe", "Generic");
	private final Livefile Livefiled = new Livefile(4l, "Empty continent", "Hello Antarctica", "Generic3");
	private final Livefile Livefilee = new Livefile(5l, "China continent", "Hello Asia", "Generic2");
	private final Livefile Livefilef = new Livefile(6l, "Chine continent", "Hello Asia", "Generic2");
	private final Livefile Livefileg = new Livefile(7l, "Chine continent", "Hello Asia", "Generic2");

	private final List<Livefile> LFlist = List.of(Livefilea, Livefileb, Livefilec, Livefiled, Livefilee, Livefileg);
	private final List<Livefile> LFlist3 = List.of(Livefilea, Livefileb, Livefilec, Livefiled, Livefilee, Livefilef);
	private final List<Livefile> LFlist2 = List.of(Livefilea, Livefilec);

	@BeforeEach
    public void setuprepo() {
        this.LFrepo.saveAll(LFlist3);
    }

	// Testing create
    @Test
    public void LFIcreateT() throws Exception {
        assertThat(this.service.create(Livefilea))
        	.isEqualTo(this.Livefilea);
    }

    // Testing read
    	
	    //Read by ID
	    @Test
	    public void LFIreadByIDT() throws Exception {
	        assertThat(this.service.readByRecordId(Livefilea.getRecord_id()))
	        	.isEqualTo(Livefilea);
	    }

	    //Read all
	    @Test
	    public void LFIreadAllT() throws Exception {
	        assertThat(this.service.readAll().stream())
	        	.isEqualTo(LFlist);
	
	    }
		
	    //Read by label
	    @Test
	    public void LFIfindByLabelT() throws Exception {
	        assertThat(this.service.findByLabelSearch(Livefilea.getLabel()).stream())
	    	.isEqualTo(LFlist2);
	    }

	// Testing update    
    @Test
    public void LFIupdateT() throws Exception {
        assertThat(this.service.updateById(Livefilea.getRecord_id(), Livefilea))
                .isEqualTo(Livefilea);
    }

    // Testing delete
    @Test
    public void LFIdeleteT() throws Exception {
        assertThat(this.service.delete(Livefilef.getRecord_id())).isTrue();
    }


}
