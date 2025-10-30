package kr.getz.auction.dto.request;

import kr.getz.product.dto.request.CreateProductRequest;

public record AuctionWithProductRequest(
	CreateAuctionRequest auction,
	CreateProductRequest product
) {
}
