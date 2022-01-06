package com.tcarroll10.vyi.dao;

import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * Abstract Dao that provides Spring JDBC support and a mechanism to lookup a
 * sql string from a xml configuration file. Note that this class provides
 * getters for both a JdbcTemplate and a NamedParamenterJdbcTemplate.
 *
 *
 */
public abstract class AbstractJdbcDao extends NamedParameterJdbcDaoSupport {

	public AbstractJdbcDao(@Autowired final @Qualifier("vyi-db") DataSource dataSource,
			final Map<String, String> sqlMap) {
		super.setDataSource(dataSource);
		Objects.requireNonNull(dataSource, "DataSource cannot be null");
		Objects.requireNonNull(sqlMap, "sqlMap cannot be null");
		this.sqlMap = sqlMap;
	}

	private final Map<String, String> sqlMap;

	/**
	 * Retrieves the SQL text defined in *-sql-config.xml file based on the sql id
	 * name. Each dao holds the corresponding sql id, which is injected by spring
	 *
	 * @param sqlId sql text identifier
	 * @return the sql string
	 */
	public String findSqlById(final String sqlId) {
		return sqlMap.get(sqlId);
	}

	public Map<String, String> getSqlMap() {
		return sqlMap;
	}
}
