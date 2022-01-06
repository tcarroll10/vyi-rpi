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

		return getNamedParameterJdbcTemplate().query(sql, (rs, rowNum) -> Team.builder().teamId(rs.getInt("ID_TEAM"))
				.teamName(StringUtils.trimWhitespace(rs.getString("NAME_TEAM"))).build());

	}

	@Override
	public Team getSpecificTeam(Integer teamId) {
		final String sql = findSqlById("getSpecificTeam");
		final MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("teamID", teamId);

		return getNamedParameterJdbcTemplate().queryForObject(sql, paramSource, (rs, rowNum) -> Team.builder()
				.teamId(rs.getInt("ID_TEAM")).teamName(StringUtils.trimWhitespace(rs.getString("NAME_TEAM"))).build());

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

}
