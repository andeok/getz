package kr.getz.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.getz.auction.domain.Auction;
import kr.getz.auction.dto.response.AuctionsResponse;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

	@Query("""
		SELECT new kr.getz.auction.dto.response.AuctionsResponse(
		  a.startTime, a.endTime, a.title,
		  p.user.id, p.user.nickname,
		  a.startPrice, a.currentPrice, a.endPrice,
		  COUNT(b)
		)
		FROM Auction a
		JOIN a.product p
		JOIN p.user
		LEFT JOIN a.bids b
		GROUP BY a.id, a.startTime, a.endTime, a.title,
		         p.user.id, p.user.nickname,
		         a.startPrice, a.currentPrice, a.endPrice
		""")
	List<AuctionsResponse> findAllAsAuctionsResponseWithBidCount();

	@Query("SELECT DISTINCT a FROM Auction a JOIN FETCH a.product p JOIN FETCH p.user LEFT JOIN FETCH a.bids b WHERE a.id = :id")
	Optional<Auction> findById(@Param("id") long id);

	@Query("SELECT a FROM Auction a WHERE a.id = :id")
	Optional<Auction> findByIdForUpdate(@Param("id") long id);

}
