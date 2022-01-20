package com.tcarroll10.vyi.service;

import java.util.List;

import com.tcarroll10.vyi.domain.Rslt;
import com.tcarroll10.vyi.domain.Team;

/**
 * Defines the Service contract
 *
 * @author Tom Carroll [f355430]
 * @since Nov 11, 2020 12:33:15 PM
 *
 */
public interface VyiService {

	public List<Team> getTeam();

	public Team getSpecificTeam(Integer teamId);

	public List<Team> getTeamsByDivision(String division);

	public Double getGamesPlayed(Integer teamID);

	public Double getGamesWon(Integer teamID);

	public Double getWinRate(Integer teamId);

	public List<Team> getOpponents(Integer teamId);

	public List<Rslt> calcRslts();

	public Double getOpponentsAvgWinRate(Integer teamId);

	public List<Team> getOpponentsOfOpponents(Integer teamId);

	public Double getOpponentsOfOpponentsAvgWinRate(Integer teamId);

	public Double getRpi(Integer teamId);

	public List<Rslt> calcRpiOnly();

}
