package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;

/**
 * 
 * @author Altap
 *
 */
@Service
public class MatchServiceImpl implements MatchService{
	
	private MatchRepository matchRepository;
	
	@Autowired
	public MatchServiceImpl(MatchRepository matchRepository) {
		super();
		this.matchRepository = matchRepository;
	}

	@Override
	public boolean saveMatch(Match match) throws MatchAlreadyExistsException {
		final Optional<Match> matchObj = matchRepository.findById(match.getId());
		if (matchObj.isPresent()) {
			throw new MatchAlreadyExistsException("Could not save match. Match is already exists");
		}
		matchRepository.save(match);
		return true;
	}

	@Override
	public boolean updateMatch(Match updateMatch) throws MatchNotFoundException {
		final Optional<Match> matchOpt = matchRepository.findById(updateMatch.getId());
		
		if (matchOpt.isPresent()) {
			Match match = matchOpt.get();
			match.setMatchStarted(updateMatch.isMatchStarted());
			matchRepository.save(match);
			return true;
		}
		else {
			throw new MatchNotFoundException("Could not update match. Match not found");
		}
	}

	@Override
	public boolean deleteMatchById(int id) throws MatchNotFoundException {
		final Optional<Match> matchOpt = matchRepository.findById(id);
		
		if (matchOpt.isPresent()) {
			Match match = matchOpt.get();
			matchRepository.delete(match);
			return true;
		}
		else {
			throw new MatchNotFoundException("Could not delete match. Match not found");
		}
	}

	@Override
	public Match getMatchById(int id) throws MatchNotFoundException {
		final Optional<Match> matchOpt = matchRepository.findById(id);
		if (matchOpt.isPresent()) {
			return matchOpt.get();
		}
		else {
			throw new MatchNotFoundException("Match not found");
		}
	}

	@Override
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	@Override
	public List<Match> getMyMatches(String userId) {
		return matchRepository.findByUserId(userId);
	}

}
