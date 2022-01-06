package com.tcarroll10.vyi.domain;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Rslt.Builder.class)
public class Rslt {

	private Integer teamId;
	private String teamName;
	private Double gamesPlayed;
	private Double gamesWon;
	private Double winRate;
	private List<Team> oppnts;
	private Double oppntsAvgWinRate;
	private List<Team> oppntsOfOppnts;
	private Double oppntsOfOppntsAvgWinRate;
	private Double rpi;

	private Rslt(Builder builder) {
		this.teamId = builder.teamId;
		this.teamName = builder.teamName;
		this.gamesPlayed = builder.gamesPlayed;
		this.gamesWon = builder.gamesWon;
		this.winRate = builder.winRate;
		this.oppnts = builder.oppnts;
		this.oppntsAvgWinRate = builder.oppntsAvgWinRate;
		this.oppntsOfOppnts = builder.oppntsOfOppnts;
		this.oppntsOfOppntsAvgWinRate = builder.oppntsOfOppntsAvgWinRate;
		this.rpi = builder.rpi;
	}

	/**
	 * Creates builder to build {@link Rslt}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Creates a builder to build {@link Rslt} and initialize it with the given
	 * object.
	 * 
	 * @param rslt to initialize the builder with
	 * @return created builder
	 */
	public static Builder builder(Rslt rslt) {
		return new Builder(rslt);
	}

	/**
	 * Builder to build {@link Rslt}.
	 */
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
	public static final class Builder {
		private Integer teamId;
		private String teamName;
		private Double gamesPlayed;
		private Double gamesWon;
		private Double winRate;
		private List<Team> oppnts = Collections.emptyList();
		private Double oppntsAvgWinRate;
		private List<Team> oppntsOfOppnts = Collections.emptyList();
		private Double oppntsOfOppntsAvgWinRate;
		private Double rpi;

		private Builder() {
		}

		private Builder(Rslt rslt) {
			this.teamId = rslt.teamId;
			this.teamName = rslt.teamName;
			this.gamesPlayed = rslt.gamesPlayed;
			this.gamesWon = rslt.gamesWon;
			this.winRate = rslt.winRate;
			this.oppnts = rslt.oppnts;
			this.oppntsAvgWinRate = rslt.oppntsAvgWinRate;
			this.oppntsOfOppnts = rslt.oppntsOfOppnts;
			this.oppntsOfOppntsAvgWinRate = rslt.oppntsOfOppntsAvgWinRate;
			this.rpi = rslt.rpi;
		}

		public Builder teamId(Integer teamId) {
			this.teamId = teamId;
			return this;
		}

		public Builder teamName(String teamName) {
			this.teamName = teamName;
			return this;
		}

		public Builder gamesPlayed(Double gamesPlayed) {
			this.gamesPlayed = gamesPlayed;
			return this;
		}

		public Builder gamesWon(Double gamesWon) {
			this.gamesWon = gamesWon;
			return this;
		}

		public Builder winRate(Double winRate) {
			this.winRate = winRate;
			return this;
		}

		public Builder oppnts(List<Team> oppnts) {
			this.oppnts = oppnts;
			return this;
		}

		public Builder oppntsAvgWinRate(Double oppntsAvgWinRate) {
			this.oppntsAvgWinRate = oppntsAvgWinRate;
			return this;
		}

		public Builder oppntsOfOppnts(List<Team> oppntsOfOppnts) {
			this.oppntsOfOppnts = oppntsOfOppnts;
			return this;
		}

		public Builder oppntsOfOppntsAvgWinRate(Double oppntsOfOppntsAvgWinRate) {
			this.oppntsOfOppntsAvgWinRate = oppntsOfOppntsAvgWinRate;
			return this;
		}

		public Builder rpi(Double rpi) {
			this.rpi = rpi;
			return this;
		}

		public Rslt build() {
			return new Rslt(this);
		}
	}

	@Override
	public String toString() {
		return "Rslt [teamId=" + teamId + ", teamName=" + teamName + ", gamesPlayed=" + gamesPlayed + ", gamesWon="
				+ gamesWon + ", winRate=" + winRate + ", oppnts=" + oppnts + ", oppntsAvgWinRate=" + oppntsAvgWinRate
				+ ", oppntsOfOppnts=" + oppntsOfOppnts + ", oppntsOfOppntsAvgWinRate=" + oppntsOfOppntsAvgWinRate
				+ ", rpi=" + rpi + "]";
	}

	public Integer getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public Double getGamesPlayed() {
		return gamesPlayed;
	}

	public Double getGamesWon() {
		return gamesWon;
	}

	public Double getWinRate() {
		return winRate;
	}

	public List<Team> getOppnts() {
		return oppnts;
	}

	public Double getOppntsAvgWinRate() {
		return oppntsAvgWinRate;
	}

	public List<Team> getOppntsOfOppnts() {
		return oppntsOfOppnts;
	}

	public Double getOppntsOfOppntsAvgWinRate() {
		return oppntsOfOppntsAvgWinRate;
	}

	public Double getRpi() {
		return rpi;
	}

}
