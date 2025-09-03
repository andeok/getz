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
	Integer buyNowPrice,
	long bidCount,
	int likeCount,
	String imageUrl
) {

	public static AuctionsResponse from(Auction auction) {
		return new AuctionsResponse(
			auction.getId(),
			auction.getStartTime(),
			auction.getEndTime(),
			auction.getProduct().getTitle(),
			auction.getProduct().getUser().getId(),
			auction.getProduct().getUser().getNickname(),
			auction.getStartPrice(),
			auction.getCurrentPrice(),
			auction.getBuyNowPrice(),
			auction.getBidCount(),
			0,// TODO : 좋아요 기능 현재 미구현
			getImageUrl(auction)
		);
	}

	public static String  getImageUrl(Auction auction) {
		return auction.getProduct().getProductImages().size() > 0 ? auction.getProduct().getProductImages().get(0).getImageUrl() : null;
	}
}
