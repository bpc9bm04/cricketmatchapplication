package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;

/**
 * 
 * @author Altap
 *
 */
public interface MatchService {

	boolean saveMatch(Match match) throws MatchAlreadyExistsException;

	boolean updateMatch(Match match) throws MatchNotFoundException;

	boolean deleteMatchById(int id) throws MatchNotFoundException;

	Match getMatchById(int id) throws MatchNotFoundException;

	List<Match> getAllMatches();

	List<Match> getMyMatches(String userId);
}
