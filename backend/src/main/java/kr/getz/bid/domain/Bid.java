package kr.getz.bid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.getz.BaseEntity;
import kr.getz.auction.domain.Auction;
import kr.getz.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Bid extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int bidPrice; // 입찰 금액

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="auction_id")
	private Auction auction;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bidder_id")
	private User bidder;

	public Bid(int bidPrice, Auction auction, User bidder) {
		this.bidPrice = bidPrice;
		this.auction = auction;
		this.bidder = bidder;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
}
