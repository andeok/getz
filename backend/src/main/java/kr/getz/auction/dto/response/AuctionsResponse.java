package kr.getz.auction.dto.response;

import java.time.LocalDateTime;

import kr.getz.auction.domain.Auction;

public record AuctionsResponse(
	long auctionId,
	LocalDateTime startTime,
	LocalDateTime endTime,
	String title,
	long sellerId,
	String sellerNickname,
	int startPrice,
	int currentPrice,
	Integer endPrice,
	long bidCount
) {

	public static AuctionsResponse from(Auction auction) {
		return new AuctionsResponse(
			auction.getId(),
			auction.getStartTime(),
			auction.getEndTime(),
			auction.getTitle(),
			auction.getProduct().getUser().getId(),
			auction.getProduct().getUser().getNickname(),
			auction.getStartPrice(),
			auction.getCurrentPrice(),
			auction.getEndPrice(),
			auction.getBids().size()
		);

	}
}
