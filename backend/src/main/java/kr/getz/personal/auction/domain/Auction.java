package kr.getz.personal.auction.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.getz.personal.BaseTimeEntity;
import kr.getz.personal.global.exception.ErrorCode;
import kr.getz.personal.global.exception.custom.BadRequestException;
import kr.getz.personal.global.exception.custom.IllegalArgumentException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Auction extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long sellerId;

	private String title;

	private String description;

	private Integer startPrice;
	private Integer endPrice;
	private Integer currentPrice;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@Enumerated(EnumType.STRING)
	private AuctionStatus status;

	@Builder
	public Auction(Long sellerId, String title, String description, Integer startPrice, Integer endPrice,
		Integer currentPrice, LocalDateTime startTime, LocalDateTime endTime, AuctionStatus status) {
		this.sellerId = sellerId;
		this.title = title;
		this.description = description;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.currentPrice = currentPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public void placeBid(Integer bidAmount) {
		System.out.println("this.currentPrice : " + this.currentPrice);
		if (this.currentPrice == null) {
			this.currentPrice = bidAmount;
			return;
		}

		if (this.currentPrice >= bidAmount) {
			throw new BadRequestException(ErrorCode.LOWER_THAN_CURRENT_PRICE);
		}
	}
}

