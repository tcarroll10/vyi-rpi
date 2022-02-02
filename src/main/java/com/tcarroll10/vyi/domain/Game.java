package com.tcarroll10.vyi.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Game.Builder.class)
public class Game {

	private Date gameDt;
	private Integer seqNum;
	private Integer homeId;
	private Integer awayId;
	private Integer winnerId;
	private Integer amtPtsHome;
	private Integer amtPtsAway;

	private Game(Builder builder) {
		this.gameDt = builder.gameDt;
		this.seqNum = builder.seqNum;
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
		private Date gameDt;
		private Integer seqNum;
		private Integer homeId;
		private Integer awayId;
		private Integer winnerId;
		private Integer amtPtsHome;
		private Integer amtPtsAway;

		private Builder() {
		}

		private Builder(Game game) {
			this.gameDt = game.gameDt;
			this.seqNum = game.seqNum;
			this.homeId = game.homeId;
			this.awayId = game.awayId;
			this.winnerId = game.winnerId;
			this.amtPtsHome = game.amtPtsHome;
			this.amtPtsAway = game.amtPtsAway;
		}

		public Builder gameDt(Date gameDt) {
			this.gameDt = gameDt;
			return this;
		}

		public Builder seqNum(Integer seqNum) {
			this.seqNum = seqNum;
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

	public Date getGameDt() {
		return gameDt;
	}

	public Integer getSeqNum() {
		return seqNum;
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
		return "Game [gameDt=" + gameDt + ", seqNum=" + seqNum + ", homeId=" + homeId + ", awayId=" + awayId
				+ ", winnerId=" + winnerId + ", amtPtsHome=" + amtPtsHome + ", amtPtsAway=" + amtPtsAway + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amtPtsAway == null) ? 0 : amtPtsAway.hashCode());
		result = prime * result + ((amtPtsHome == null) ? 0 : amtPtsHome.hashCode());
		result = prime * result + ((awayId == null) ? 0 : awayId.hashCode());
		result = prime * result + ((gameDt == null) ? 0 : gameDt.hashCode());
		result = prime * result + ((homeId == null) ? 0 : homeId.hashCode());
		result = prime * result + ((seqNum == null) ? 0 : seqNum.hashCode());
		result = prime * result + ((winnerId == null) ? 0 : winnerId.hashCode());
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
		Game other = (Game) obj;
		if (amtPtsAway == null) {
			if (other.amtPtsAway != null)
				return false;
		} else if (!amtPtsAway.equals(other.amtPtsAway))
			return false;
		if (amtPtsHome == null) {
			if (other.amtPtsHome != null)
				return false;
		} else if (!amtPtsHome.equals(other.amtPtsHome))
			return false;
		if (awayId == null) {
			if (other.awayId != null)
				return false;
		} else if (!awayId.equals(other.awayId))
			return false;
		if (gameDt == null) {
			if (other.gameDt != null)
				return false;
		} else if (!gameDt.equals(other.gameDt))
			return false;
		if (homeId == null) {
			if (other.homeId != null)
				return false;
		} else if (!homeId.equals(other.homeId))
			return false;
		if (seqNum == null) {
			if (other.seqNum != null)
				return false;
		} else if (!seqNum.equals(other.seqNum))
			return false;
		if (winnerId == null) {
			if (other.winnerId != null)
				return false;
		} else if (!winnerId.equals(other.winnerId))
			return false;
		return true;
	}

}
