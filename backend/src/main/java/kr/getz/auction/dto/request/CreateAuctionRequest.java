package kr.getz.auction.dto.request;

import java.time.LocalDateTime;

import kr.getz.auction.domain.Auction;
import kr.getz.product.domain.Product;
import kr.getz.user.domain.User;

public record CreateAuctionRequest(
	String title,
	String description,
	int startPrice,
	int endPrice,
	LocalDateTime startTime,
	LocalDateTime endTime,
	int bidIncrement
) {

	public Auction toAuction(Product product) {
		return new Auction(title, description, startPrice, endPrice, startTime, endTime, bidIncrement, product);
	}

}
