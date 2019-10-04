package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Match;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MatchRepositoryTest {



	@Autowired
	private transient MatchRepository matchRepository;

	/**
	 * @param matchRepository
	 *            the matchRepository to set
	 */
	public void setMatchRepository(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	/**
	 * create Match object
	 * 
	 * @return
	 */
	Match createMatch() {
		Match match = new Match();
		match.setId(1);
		match.setMatchStarted(false);
		match.setTeamOne("India");
		match.setTeamTwo("Australia");
		match.setMatchDate("2018-03-03");
		match.setUnique_id(1);
		
		return match;

	}

	/**
	 * test save match method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveMatch() throws Exception {
		matchRepository.save(createMatch());
		final Match saveMatch = matchRepository.getOne(1);
		assertThat(saveMatch.getId()).isEqualTo(1);
	}

	/**
	 * test update method
	 * @throws Exception
	 */
	@Test
	public void testUpdateMatch() throws Exception {
		Match match = matchRepository.save(createMatch());
		final Match updatedMatch = matchRepository.getOne(match.getId());
		assertEquals(updatedMatch.getTeamOne(), "India");

		updatedMatch.setMatchStarted(true);
		matchRepository.save(updatedMatch);
		final Match updateDMatch = matchRepository.getOne(updatedMatch.getId());
		assertEquals(updateDMatch.isMatchStarted(), true);
	}

	/**
	 * test delete method
	 * @throws Exception
	 */
	@Test
	public void testDeleteMatch() throws Exception {
		Match saveMatch = matchRepository.save(createMatch());
		final Match deleteMatch = matchRepository.getOne(saveMatch.getId());
		assertThat(deleteMatch.getId()).isEqualTo(saveMatch.getId());
		matchRepository.delete(deleteMatch);
		//assertEquals(Optional.empty(), matchRepository.findById(saveMatch.getId()));
	}

	/**
	 * test get method
	 * @throws Exception
	 */
	@Test
	public void testGetMatch() throws Exception {
		matchRepository.save(createMatch());
		final Match saveMatch= matchRepository.getOne(1);
		assertThat(saveMatch.getId()).isEqualTo(1);
	}

	/**
	 * test fetch all method
	 * @throws Exception
	 */
	@Test
	public void testGetAllMatches() throws Exception {
		matchRepository.save(createMatch());
		Match match2 = createMatch();
		match2.setId(2);
		match2.setTeamOne("India");
		match2.setTeamTwo("Australia");
		List<Match> matchList = matchRepository.findAll();
	}
	
	@Test
	public void testGetMyMatches() {
		Match match1 = createMatch();
		match1.setUserId("1");
		matchRepository.save(match1);
		Match match2 = createMatch();
		match2.setUserId("1");
		matchRepository.save(match2);
		
		List<Match> matches = matchRepository.findByUserId("1");
		
		assertEquals("India", matches.get(0).getTeamOne());
	}

}
