package kr.getz.auction.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.getz.auction.dto.request.AuctionWithProductRequest;
import kr.getz.auction.dto.request.BidRequest;
import kr.getz.auction.dto.response.AuctionResponse;
import kr.getz.auction.dto.response.AuctionsResponses;
import kr.getz.auction.service.AuctionService;
import kr.getz.auth.config.UserPrincipal;
import kr.getz.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auctions")
public class AuctionController {

	private final AuctionService auctionService;

	// 경매 등록
	@PostMapping
	public ResponseEntity<Void> createAuction(
		@UserPrincipal User user,
		@ModelAttribute AuctionWithProductRequest request) {

		long auctionId = auctionService.createAuction(user, request);

		return ResponseEntity.created(URI.create("/auctions/" + auctionId)).build();
	}

	// 경매 조회(목록)
	@GetMapping
	public ResponseEntity<AuctionsResponses> getAuctionList() {

		return ResponseEntity.ok(auctionService.getAuctionList());
	}

	// 경매 조회(상세)
	@GetMapping("/{id}")
	public ResponseEntity<AuctionResponse> getAuction(@PathVariable long id) {
		return ResponseEntity.ok(auctionService.getAuction(id));
	}

	// 입찰
	@PostMapping("/{auctionId}/bids")
	public ResponseEntity<AuctionResponse> placeBid(@UserPrincipal User user,
		@PathVariable long auctionId,
		@RequestBody BidRequest request) {

		return ResponseEntity.ok(auctionService.placeBid(user, request, auctionId));
	}
}
