package com.tcarroll10.vyi.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.tcarroll10.vyi.domain.Game;
import com.tcarroll10.vyi.domain.Team;

/**
 * VYI DAO, showing a constructor into which Spring will inject two specific
 * spring beans: datasource and ups-sql-map
 *
 * @author Tom Carroll [f355430]
 * @since 9/27/2019 2:06 PM
 *
 */

@Repository
public class VyiJdbcDao extends AbstractJdbcDao implements VyiAppDao {

	Logger LOG = LoggerFactory.getLogger(VyiJdbcDao.class);

	public VyiJdbcDao(@Autowired final @Qualifier("vyi-db") DataSource dataSource,
			@Value("#{vyiSqlMap}") Map<String, String> sqlMap) {
		super(dataSource, sqlMap);

	}

	@Override
	public List<Team> getTeam() {
		final String sql = findSqlById("getTeam");

		return getNamedParameterJdbcTemplate().query(sql,
				(rs, rowNum) -> Team.builder().teamId(rs.getInt("ID_TEAM"))
						.teamName(StringUtils.trimWhitespace(rs.getString("NAME_TEAM")))
						.division(StringUtils.trimWhitespace(rs.getString("NAME_DIVISION"))).build());

	}

	@Override
	public List<Game> getGames() {

		final String sql = findSqlById("getGames");

		return getNamedParameterJdbcTemplate().query(sql,
				(rs, rowNum) -> Game.builder().id(rs.getInt("ID")).gameDt(rs.getDate("DT_GAME"))
						.homeId(rs.getInt("ID_HOME")).awayId(rs.getInt("ID_AWAY")).winnerId(rs.getInt("ID_WINNER"))
						.amtPtsHome(rs.getInt("amt_pts_home_team")).amtPtsAway(rs.getInt("amt_pts_away_team")).build());

	}

	@Override
	public List<Team> getTeamsByDivision(String division) {

		final String sql = findSqlById("getTeamsByDivision");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("division", division);

		return getNamedParameterJdbcTemplate().query(sql, paramSource,
				(rs, rowNum) -> Team.builder().teamId(rs.getInt("ID_TEAM"))
						.teamName(StringUtils.trimWhitespace(rs.getString("NAME_TEAM")))
						.division(StringUtils.trimWhitespace(rs.getString("NAME_DIVISION"))).build());
	}

	@Override
	public Team getSpecificTeam(Integer teamId) {
		final String sql = findSqlById("getSpecificTeam");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("teamID", teamId);

		return getNamedParameterJdbcTemplate().queryForObject(sql, paramSource,
				(rs, rowNum) -> Team.builder().teamId(rs.getInt("ID_TEAM"))
						.teamName(StringUtils.trimWhitespace(rs.getString("NAME_TEAM")))
						.division(StringUtils.trimWhitespace(rs.getString("NAME_DIVISION"))).build());

	}

	public Double numberOfGamesPlayed(Integer teamId) {
		final String sql = findSqlById("getGamesPlayed");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("teamID", teamId);

		return getNamedParameterJdbcTemplate().queryForObject(sql, paramSource, Double.class);

	}

	public Double numberOfGamesWon(Integer teamId) {
		final String sql = findSqlById("getGamesWon");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("teamID", teamId);

		return getNamedParameterJdbcTemplate().queryForObject(sql, paramSource, Double.class);

	}

	public List<Team> getOpponents(Integer teamId) {
		final String sql = findSqlById("getOpponents");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("teamID", teamId);

		return getNamedParameterJdbcTemplate().query(sql, paramSource, (rs, rowNum) -> Team.builder()
				.teamId(rs.getInt("ID_TEAM")).teamName(StringUtils.trimWhitespace(rs.getString("NAME_TEAM"))).build());

	}

	public void addGameRslt(Game game) {
		final String sql = findSqlById("insertGame");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("gameDt", game.getGameDt());
		paramSource.addValue("homeId", game.getHomeId());
		paramSource.addValue("awayId", game.getAwayId());
		paramSource.addValue("winnerId", game.getWinnerId());
		paramSource.addValue("amtPtsHome", game.getAmtPtsHome());
		paramSource.addValue("amtPtsAway", game.getAmtPtsAway());

		getNamedParameterJdbcTemplate().update(sql, paramSource);
	}

	public void updateGame(Game game) {

		final String sql = findSqlById("updateGame");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", game.getId());
		paramSource.addValue("gameDt", game.getGameDt());
		paramSource.addValue("homeId", game.getHomeId());
		paramSource.addValue("awayId", game.getAwayId());
		paramSource.addValue("winnerId", game.getWinnerId());
		paramSource.addValue("amtPtsHome", game.getAmtPtsHome());
		paramSource.addValue("amtPtsAway", game.getAmtPtsAway());

		getNamedParameterJdbcTemplate().update(sql, paramSource);

	}

	public Game getGame(Integer id) {
		final String sql = findSqlById("getGame");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		return getNamedParameterJdbcTemplate().queryForObject(sql, paramSource,
				(rs, rowNum) -> Game.builder().id(rs.getInt("id")).gameDt(rs.getDate("DT_GAME"))
						.homeId(rs.getInt("id_home")).awayId(rs.getInt("id_away")).winnerId(rs.getInt("id_winner"))
						.amtPtsHome(rs.getInt("amt_pts_home_team")).amtPtsAway(rs.getInt("amt_pts_away_team")).build());

	}

	public void deleteGame(Integer id) {
		final String sql = findSqlById("deleteGame");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		getNamedParameterJdbcTemplate().update(sql, paramSource);

	}

}
