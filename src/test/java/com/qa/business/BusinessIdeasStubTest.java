package com.qa.business;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.qa.service.IdeasServiceStub;
import com.qa.service.listService;

public class BusinessIdeasStubTest {

	@Test
	public void testingStubs1() {
		listService listServiceStub = new IdeasServiceStub();
		businessClass businessClass = new businessClass(listServiceStub);
		
		List<String> filteredIdeas = businessClass.retrieveChickenIdeas("");

		assertEquals(2, filteredIdeas.size());
	}

}
