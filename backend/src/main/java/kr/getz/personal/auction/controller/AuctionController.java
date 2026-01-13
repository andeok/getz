package kr.getz.personal.auction.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.getz.personal.auction.domain.Auction;
import kr.getz.personal.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

	private final AuctionService auctionService;

	@GetMapping
	public ResponseEntity<Slice<Auction>> getAuctions(
		@RequestParam(required = false) Long lastId,
		@RequestParam(defaultValue = "100") int size
	) {
		Pageable pageable = PageRequest.of(0, size);
		return ResponseEntity.ok(auctionService.getAuctions(lastId, pageable));
	}

	@GetMapping("/{auctionId}")
	public ResponseEntity<Auction> getAuction(@PathVariable Long auctionId) {
		return ResponseEntity.ok(auctionService.getAuction(auctionId));
	}
}
