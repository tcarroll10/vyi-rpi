package com.tcarroll10.vyi.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Game.Builder.class)
public class Game {

	private Integer id;
	private Date gameDt;
	private Integer homeId;
	private Integer awayId;
	private Integer winnerId;
	private Integer amtPtsHome;
	private Integer amtPtsAway;

	private Game(Builder builder) {
		this.id = builder.id;
		this.gameDt = builder.gameDt;
		this.homeId = builder.homeId;
		this.awayId = builder.awayId;
		this.winnerId = builder.winnerId;
		this.amtPtsHome = builder.amtPtsHome;
		this.amtPtsAway = builder.amtPtsAway;
	}

	/**
	 * Creates builder to build {@link Game}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Creates a builder to build {@link Game} and initialize it with the given
	 * object.
	 * 
	 * @param game to initialize the builder with
	 * @return created builder
	 */
	public static Builder builder(Game game) {
		return new Builder(game);
	}

	/**
	 * Builder to build {@link Game}.
	 */
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
	public static final class Builder {
		private Integer id;
		private Date gameDt;
		private Integer homeId;
		private Integer awayId;
		private Integer winnerId;
		private Integer amtPtsHome;
		private Integer amtPtsAway;

		private Builder() {
		}

		private Builder(Game game) {
			this.id = game.id;
			this.gameDt = game.gameDt;
			this.homeId = game.homeId;
			this.awayId = game.awayId;
			this.winnerId = game.winnerId;
			this.amtPtsHome = game.amtPtsHome;
			this.amtPtsAway = game.amtPtsAway;
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder gameDt(Date gameDt) {
			this.gameDt = gameDt;
			return this;
		}

		public Builder homeId(Integer homeId) {
			this.homeId = homeId;
			return this;
		}

		public Builder awayId(Integer awayId) {
			this.awayId = awayId;
			return this;
		}

		public Builder winnerId(Integer winnerId) {
			this.winnerId = winnerId;
			return this;
		}

		public Builder amtPtsHome(Integer amtPtsHome) {
			this.amtPtsHome = amtPtsHome;
			return this;
		}

		public Builder amtPtsAway(Integer amtPtsAway) {
			this.amtPtsAway = amtPtsAway;
			return this;
		}

		public Game build() {
			return new Game(this);
		}
	}

	public Integer getId() {
		return id;
	}

	public Date getGameDt() {
		return gameDt;
	}

	public Integer getHomeId() {
		return homeId;
	}

	public Integer getAwayId() {
		return awayId;
	}

	public Integer getWinnerId() {
		return winnerId;
	}

	public Integer getAmtPtsHome() {
		return amtPtsHome;
	}

	public Integer getAmtPtsAway() {
		return amtPtsAway;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", gameDt=" + gameDt + ", homeId=" + homeId + ", awayId=" + awayId + ", winnerId="
				+ winnerId + ", amtPtsHome=" + amtPtsHome + ", amtPtsAway=" + amtPtsAway + "]";
	}

}
