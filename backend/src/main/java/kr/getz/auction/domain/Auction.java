package kr.getz.auction.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import kr.getz.BaseEntity;
import kr.getz.bid.domain.Bid;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import kr.getz.product.domain.Product;
import kr.getz.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Auction extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int startPrice;
	private int currentPrice;

	// 최종 낙찰 금액
	private Integer finalPrice;

	// 즉시 구매가격
	private Integer buyNowPrice;

	private int bidIncrement; // 호가
	private int bidCount;

	@Column(name = "start_at")
	private LocalDateTime startTime;

	@Column(name = "end_at")
	private LocalDateTime endTime;

	@Enumerated(EnumType.STRING)
	private AuctionStatus status; // 경매 상태

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "winner_id")
	private User winner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Bid> bids = new ArrayList<>();

	public Auction(int startPrice, int buyNowPrice, LocalDateTime startTime,
		LocalDateTime endTime, int bidIncrement, Product product) {
		this.startPrice = startPrice;
		this.buyNowPrice = buyNowPrice;
		this.currentPrice = startPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bidIncrement = bidIncrement;
		this.product = product;
		
		//TODO : OPEN으로 변경 기능 추가 예정
		this.status = AuctionStatus.READY;
	}

	public void addBid(Bid bid) {
		validateBidWindow();
		validateBidPrice(bid);

		this.bids.add(bid);
		bid.setAuction(this);

		this.currentPrice = bid.getBidPrice();
	}

	private void validateBidPrice(Bid newBid) {
		if (newBid.getBidPrice() <= this.currentPrice) {
			throw new AuctionException(ExceptionCode.BID_PRICE_ERROR);
		}
		if (newBid.getBidPrice() - this.currentPrice < this.bidIncrement) {
			throw new AuctionException(ExceptionCode.BID_PRICE_ERROR);
		}
	}

	private void validateBidWindow() {
		LocalDateTime now = LocalDateTime.now();
		if (now.isBefore(this.startTime) || !now.isBefore(this.endTime) || this.status != AuctionStatus.OPEN) {
			throw new AuctionException(ExceptionCode.BID_TIME_ERROR);
		}
	}
}
