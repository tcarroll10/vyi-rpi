package com.tcarroll10.vyi.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tcarroll10.vyi.dao.VyiAppDao;
import com.tcarroll10.vyi.domain.Game;
import com.tcarroll10.vyi.domain.Rslt;
import com.tcarroll10.vyi.domain.Team;
import com.tcarroll10.vyi.service.VyiService;

/**
 * Service implementation UPS API application
 *
 * @author Tom Carroll [f355430]
 * @since Nov 11, 2020 12:33:15 PM
 *
 */

@Service
public class VyiServiceImpl implements VyiService {

	private static final Logger LOG = LogManager.getLogger(VyiServiceImpl.class);

	@Autowired
	private VyiAppDao vyiAppDao;

	@Value("${app.rpi.winpct.weight}")
	private Double winPctWeight;

	@Value("${app.rpi.oppWinPct.weight}")
	private Double oppWinPctWeight;

	@Value("${app.rpi.oppOppWinPct.weight}")
	private Double oppOppWinPctweight;

	public List<Team> getTeam() {

		return vyiAppDao.getTeam();

	}

	public List<Game> getGames() {

		return vyiAppDao.getGames();
	}

	public void addGameRslt(Game game) {

		vyiAppDao.addGameRslt(game);
	}

	public void updateGame(Game game) {

		vyiAppDao.updateGame(game);
	}

	public List<Team> getTeamsByDivision(String division) {

		return vyiAppDao.getTeamsByDivision(division);
	}

	public Team getSpecificTeam(Integer teamId) {

		return vyiAppDao.getSpecificTeam(teamId);
	}

	public Double getGamesPlayed(Integer teamId) {
		return vyiAppDao.numberOfGamesPlayed(teamId);
	}

	public Double getGamesWon(Integer teamId) {
		return vyiAppDao.numberOfGamesWon(teamId);
	}

	public Double getWinRate(Integer teamId) {

		LOG.info("Team ID Entered {}", teamId);

		return (vyiAppDao.numberOfGamesWon(teamId) / vyiAppDao.numberOfGamesPlayed(teamId));
	}

	public List<Team> getOpponents(Integer teamId) {

		List<Team> rslt = vyiAppDao.getOpponents(teamId);

		rslt.removeIf(b -> b.getTeamId().equals(teamId));
		return rslt;

	}

	public List<Team> getOpponentsOfOpponents(Integer teamId) {

		List<Team> opponents = getOpponents(teamId);

		LOG.info("Team {} opponents are {}", teamId, opponents.toString());

		final HashMap<Team, List<Team>> opponentsOfOpponents = new HashMap<Team, List<Team>>();

		for (Team opp : opponents) {
			opponentsOfOpponents.put(Team.builder().teamId(opp.getTeamId()).teamName(opp.getTeamName()).build(),
					new ArrayList<Team>(getOpponents(opp.getTeamId())));

		}

		LOG.info("Team {}'s opponent's opponents are {}", teamId, opponentsOfOpponents.toString());

		Set<Team> team_set = new LinkedHashSet<Team>();

		opponentsOfOpponents.forEach((k, v) -> {
			team_set.addAll(v);
			LOG.info("adding {}'s opponents {} to the set", k, v);
		});

		team_set.remove(this.getSpecificTeam(teamId));

		List<Team> rslt = new ArrayList<Team>();
		rslt.addAll(team_set);

		return rslt;

	}

	public Double avgWinRate(List<Team> input) {

		Double sum = input.stream().mapToDouble(e -> getWinRate(e.getTeamId())).sum();
		LOG.info("sum by the For method is {}", sum);

		return sum / (double) input.size();

	}

	public Double getOpponentsAvgWinRate(Integer teamId) {

		List<Team> opponents = this.getOpponents(teamId);

		return avgWinRate(opponents);

	}

	public Double getOpponentsOfOpponentsAvgWinRate(Integer teamId) {

		List<Team> opponentsOfOpponents = this.getOpponentsOfOpponents(teamId);

		return avgWinRate(opponentsOfOpponents);
	}

	public Double getRpi(Integer teamId) {

		return (winPctWeight * getWinRate(teamId) + (oppWinPctWeight * getOpponentsAvgWinRate(teamId))
				+ (oppOppWinPctweight * getOpponentsOfOpponentsAvgWinRate(teamId)));

	}

	public List<Rslt> calcRslts() {

		return this.getTeam().stream()
				.map(e -> Rslt.builder().teamId(e.getTeamId()).teamName(e.getTeamName())
						.gamesPlayed(this.getGamesPlayed(e.getTeamId())).gamesWon(this.getGamesWon(e.getTeamId()))
						.winRate(this.getWinRate(e.getTeamId())).oppnts(this.getOpponents(e.getTeamId()))
						.oppntsAvgWinRate(this.getOpponentsAvgWinRate(e.getTeamId()))
						.oppntsOfOppnts(this.getOpponentsOfOpponents(e.getTeamId()))
						.oppntsOfOppntsAvgWinRate(this.getOpponentsOfOpponentsAvgWinRate(e.getTeamId()))
						.rpi(this.getRpi(e.getTeamId())).build())
				.collect(toList());

	}

	public List<Rslt> calcRsltsForDivision(String division) {

		return this.getTeamsByDivision(division).stream()
				.map(e -> Rslt.builder().teamId(e.getTeamId()).teamName(e.getTeamName())
						.gamesPlayed(this.getGamesPlayed(e.getTeamId())).gamesWon(this.getGamesWon(e.getTeamId()))
						.winRate(this.getWinRate(e.getTeamId())).oppnts(this.getOpponents(e.getTeamId()))
						.oppntsAvgWinRate(this.getOpponentsAvgWinRate(e.getTeamId()))
						.oppntsOfOppnts(this.getOpponentsOfOpponents(e.getTeamId()))
						.oppntsOfOppntsAvgWinRate(this.getOpponentsOfOpponentsAvgWinRate(e.getTeamId()))
						.rpi(this.getRpi(e.getTeamId())).build())
				.collect(toList());

	}

	public List<Rslt> calcRpiOnly() {

		return this.getTeam().stream().map(e -> Rslt.builder().teamId(e.getTeamId()).teamName(e.getTeamName())
				.rpi(this.getRpi(e.getTeamId())).build()).collect(toList());
	}

	public Game getGame(Integer id) {
		return vyiAppDao.getGame(id);

	}

	public void deleteGame(Integer id) {
		vyiAppDao.deleteGame(id);

	}

}
