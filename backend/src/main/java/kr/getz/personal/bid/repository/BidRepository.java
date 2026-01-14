package kr.getz.personal.bid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.getz.personal.bid.domain.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
