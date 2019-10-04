package com.stackroute.favouriteservice.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;

public class MatchServiceImplTest {



	@Mock
	private transient MatchRepository matchRepository;

	@InjectMocks
	private transient MatchServiceImpl matchServiceImpl;

	private transient Match match;

	transient Optional<Match> options;

	/**
	 * set up mock objects.
	 */
	@org.junit.Before
	public void setupMocks() {
		match = new Match();
		match.setTeamOne("India");
		match.setId(1);
		match.setTeamTwo("Australia");
		match.setMatchDate("2018-03-03");
		match.setUserId("1");
		MockitoAnnotations.initMocks(this);
		options = Optional.of(match);

	}

	/**
	 * test mock creations
	 */
	@Test
	public void testMockCreation() {
		assertNotNull("match object is null but expected that it should initialize in setup mock method", match);
	}
	
	/**
	 * test save match method.
	 * @throws MatchAlreadyExistsException
	 */
	@Test
	public void testSaveMatchSuccess() throws MatchAlreadyExistsException{
		when(matchRepository.save(match)).thenReturn(match);
		boolean saveFlag = matchServiceImpl.saveMatch(match);
		assertTrue("Match not saved in database. Match service returning false", saveFlag);
		verify(matchRepository, times(1)).save(match);
	}
	
	/**
	 * test save match method for failure scenario.
	 * @throws MatchAlreadyExistsException
	 */
	@Test(expected = MatchAlreadyExistsException.class)
	public void testSaveMatchFailure() throws MatchAlreadyExistsException{
		when(matchRepository.findById(1)).thenReturn(options);
		when(matchRepository.save(match)).thenReturn(match);
		matchServiceImpl.saveMatch(match);
		
		assertThatExceptionOfType(MatchAlreadyExistsException.class);
		
	}
	
	/**
	 * test update match method.
	 * @throws MatchNotFoundException
	 */
	@Test
	public void testUpdateMatchSuccess() throws MatchNotFoundException{
		when(matchRepository.findById(1)).thenReturn(options);
		when(matchRepository.save(match)).thenReturn(match);
		match.setMatchStarted(true);
		final boolean isUpdate  = matchServiceImpl.updateMatch(match);
		assertEquals("updating match failed", true, isUpdate);
		verify(matchRepository, times(1)).findById(match.getId());
		verify(matchRepository, times(1)).save(match);
	}
	
	/**
	 * test delete match method.
	 * @throws MatchNotFoundException
	 */
	@Test
	public void testDeleteMatchById() throws MatchNotFoundException{
		when(matchRepository.findById(1)).thenReturn(options);
		doNothing().when(matchRepository).delete(match);
		
		boolean flag  = matchServiceImpl.deleteMatchById(1);
		assertTrue("deleting match failed", flag);
		verify(matchRepository, times(1)).findById(match.getId());
		verify(matchRepository, times(1)).delete(match);
	}
	
	/**
	 * test get match by id method
	 * @throws MatchNotFoundException
	 */
	@Test
	public void testGetMatchById() throws MatchNotFoundException{
		when(matchRepository.findById(1)).thenReturn(options);
		
		final Match fetchMatch = matchServiceImpl.getMatchById(1);
		assertEquals("fetching match by id failed", fetchMatch, match);
		verify(matchRepository, times(1)).findById(match.getId());
	}
	
	/**
	 * test get all match method
	 * @throws MatchNotFoundException
	 */
	@Test
	public void testGetAllMatches() throws MatchNotFoundException{
		final ArrayList<Match> matchList = new ArrayList<Match>(1);
		when(matchRepository.findAll()).thenReturn(matchList);
		
		final List<Match> matchList1 = matchServiceImpl.getAllMatches();
		assertEquals("fetching all matches failed", matchList, matchList1);
		verify(matchRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetMyMatches() throws MatchNotFoundException{
		final ArrayList<Match> matchList = new ArrayList<Match>(1);
		when(matchRepository.findByUserId("1")).thenReturn(matchList);
		
		final List<Match> matchList1 = matchServiceImpl.getMyMatches("1");
		assertEquals("fetching all matches failed", matchList, matchList1);
		verify(matchRepository, times(1)).findByUserId("1");
	}
	

}
