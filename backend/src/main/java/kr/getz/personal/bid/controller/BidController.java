package kr.getz.personal.bid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.getz.personal.bid.service.BidService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bid")
@RequiredArgsConstructor
public class BidController {

	private final BidService bidService;

}
