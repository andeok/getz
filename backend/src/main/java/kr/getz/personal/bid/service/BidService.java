package kr.getz.personal.bid.service;

import org.springframework.stereotype.Service;

import kr.getz.personal.bid.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BidService {

	private final BidRepository bidRepository;


}
