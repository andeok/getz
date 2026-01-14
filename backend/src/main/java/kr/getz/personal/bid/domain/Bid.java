package kr.getz.personal.bid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_auction_amount", columnNames = {"auctionId", "bidAmount"})
	}
)
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long auctionId;

	private Long bidderId;

	private Integer bidAmount;

	@Builder
	public Bid(Long auctionId, Long bidderId, Integer bidAmount) {
		this.auctionId = auctionId;
		this.bidderId = bidderId;
		this.bidAmount = bidAmount;
	}
}
