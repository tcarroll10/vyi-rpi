package com.tcarroll10.vyi.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Team.Builder.class)
public class Team {

	private final Integer teamId;
	private String teamName;
	private String division;

	private Team(Builder builder) {
		this.teamId = builder.teamId;
		this.teamName = builder.teamName;
		this.division = builder.division;
	}

	/**
	 * Creates builder to build {@link Team}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Creates a builder to build {@link Team} and initialize it with the given
	 * object.
	 * 
	 * @param team to initialize the builder with
	 * @return created builder
	 */
	public static Builder builder(Team team) {
		return new Builder(team);
	}

	/**
	 * Builder to build {@link Team}.
	 */
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
	public static final class Builder {
		private Integer teamId;
		private String teamName;
		private String division;

		private Builder() {
		}

		private Builder(Team team) {
			this.teamId = team.teamId;
			this.teamName = team.teamName;
			this.division = team.division;
		}

		public Builder teamId(Integer teamId) {
			this.teamId = teamId;
			return this;
		}

		public Builder teamName(String teamName) {
			this.teamName = teamName;
			return this;
		}

		public Builder division(String division) {
			this.division = division;
			return this;
		}

		public Team build() {
			return new Team(this);
		}
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", division=" + division + "]";
	}

	public Integer getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getDivision() {
		return division;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (teamId == null) {
			if (other.teamId != null)
				return false;
		} else if (!teamId.equals(other.teamId))
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}

}
