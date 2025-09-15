package kr.getz.auction.dto.request;

import java.time.LocalDateTime;

import kr.getz.auction.domain.Auction;
import kr.getz.product.domain.Product;
import kr.getz.user.domain.User;

public record CreateAuctionRequest(
	int startPrice,
	int buyNowPrice,
	LocalDateTime startTime,
	LocalDateTime endTime,
	int bidIncrement
) {

	public Auction toAuction(Product product) {
		return new Auction(startPrice, buyNowPrice, startTime, endTime, bidIncrement, product);
	}

}
