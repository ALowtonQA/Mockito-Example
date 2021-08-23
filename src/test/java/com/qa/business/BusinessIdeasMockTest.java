package com.qa.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.qa.service.listService;

@RunWith(MockitoJUnitRunner.class)
public class BusinessIdeasMockTest {
	// These are tests for businessClass 
	
	@Mock
	listService listServiceMock;
	
	@InjectMocks
	businessClass businessClass;
	
	@Test
	public void testingMocks1() {
		List<String> ideas = Arrays.asList("Chicken soda", "Leather gumshield", "Chicken flavored icecream");

		when(listServiceMock.retrieveIdeas("Something")).thenReturn(ideas);

		List<String> filteredIdeas = businessClass.retrieveChickenIdeas("Something");
		
		assertEquals(2, filteredIdeas.size());
	}
	
	@Test
	public void testingMocks2() {
		List<String> ideas1 = Arrays.asList("Chicken soda", "Leather gumshield", "Chicken flavored icecream");
		List<String> ideas2 = Arrays.asList("Chicken soda");
		
		when(listServiceMock.retrieveIdeas("Nothing")).thenReturn(ideas1).thenReturn(ideas2);
		
		List<String> filteredIdeas = businessClass.retrieveChickenIdeas("Nothing");
		assertEquals(2, filteredIdeas.size());
		
		filteredIdeas = businessClass.retrieveChickenIdeas("Nothing");
		assertEquals(1, filteredIdeas.size());
	}
	
	
	@Test
	public void testingMocks3() {
		List<String> ideas1 = Arrays.asList("Chicken soda", "Leather gumshield", "Chicken flavored icecream");
		
		given(listServiceMock.retrieveIdeas("")).willReturn(ideas1);

		List<String> filteredIdeas = businessClass.retrieveChickenIdeas("");
		
		assertThat(filteredIdeas.size(), is(2));
		verify(listServiceMock, times(1)).retrieveIdeas(anyString());
	}
	
	@Test
	public void testingMocks4() {
		List<String> allIdeas = Arrays.asList("Chicken soda", 
				"Leather gumshield", "Chicken flavored icecream");
		
		when(listServiceMock.retrieveIdeas(anyString())).thenReturn(allIdeas);

		businessClass.deleteIdeasNotRelatedToChicken("Piers");
		
		verify(listServiceMock, never()).deleteIdea("Chicken soda");
		verify(listServiceMock, never()).deleteIdea("Chicken flavored icecream");
		verify(listServiceMock, times(1)).deleteIdea("Leather gumshield");
	}
}

