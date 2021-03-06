package com.tcarroll10.vyi.dao;

import java.util.List;

import com.tcarroll10.vyi.domain.Game;
import com.tcarroll10.vyi.domain.Team;

/**
 * Defines the DAO contract for VYI RPI service
 *
 * @author Tom Carroll [f355430]
 * @since Sept 22, 2020 12:33:15 PM
 *
 */

public interface VyiAppDao {

	public List<Team> getTeam();

	public List<Team> getTeamsByDivision(String division);

	public Team getSpecificTeam(Integer teamId);

	public Double numberOfGamesPlayed(Integer teamId);

	public Double numberOfGamesWon(Integer teamId);

	public List<Team> getOpponents(Integer teamId);

	public void addGameRslt(Game game);

	public List<Game> getGames();

	public Game getGame(Integer id);

	public void deleteGame(Integer id);

	public void updateGame(Game game);

}