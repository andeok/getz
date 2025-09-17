package kr.getz.auction.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import kr.getz.auction.domain.Auction;
import kr.getz.product.domain.ProductImage;

public record AuctionResponse(
	LocalDateTime startTime,
	LocalDateTime endTime,
	String title,
	String description,
	String seller,
	int startPrice,
	int currentPrice,
	int buyNowPrice,
	List<BidResponse> bidHistory
) {
	public static AuctionResponse from(Auction auction) {
		return new AuctionResponse(
			auction.getStartTime(),
			auction.getEndTime(),
			auction.getProduct().getTitle(),
			auction.getProduct().getDescription(),
			auction.getProduct().getUser().getNickname(),
			auction.getStartPrice(),
			auction.getCurrentPrice(),
			auction.getBuyNowPrice(),
			auction.getBids().stream()
				.map(BidResponse::from)
				.toList()
		);
	}
}
