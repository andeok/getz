package kr.getz.personal;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.getz.personal.auction.repository.AuctionRepository;
import kr.getz.personal.auction.service.AuctionService;
import kr.getz.personal.bid.dto.request.BidRequest;
import kr.getz.personal.global.auth.dto.MemberAuth;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private AuctionRepository auctionRepository;

	@Test
	@DisplayName("입찰 동시성 테스트")
	void bid_test() throws InterruptedException {

		Long auctionId = 1L;
		int bidAmount = 600000;

		BidRequest bidRequest = new BidRequest(bidAmount);

		int threadCount = 500;

		ExecutorService executorService = Executors.newFixedThreadPool(32);

		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		AtomicInteger successCount = new AtomicInteger();
		AtomicInteger failCount = new AtomicInteger();

		for (int i = 0; i < threadCount; i++) {
			MemberAuth memberAuth = new MemberAuth((long)i);
			System.out.println("회원 " + i);
			executorService.submit(() -> {
				try {
					System.out.println("Executing thread: " + Thread.currentThread().getName());
					auctionService.placeBid(memberAuth, auctionId, bidRequest);
					successCount.incrementAndGet();
				} catch (Exception e) {
					failCount.incrementAndGet();
					System.out.println(
						"Thread failed: " + Thread.currentThread().getName() + ", 이유: " + e.getMessage());
				} finally {
					System.out.println("Thread finally: " + Thread.currentThread().getName());
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await(20, TimeUnit.SECONDS);
		executorService.shutdown();
		executorService.awaitTermination(20, TimeUnit.SECONDS);

		assertThat(successCount.get()).isEqualTo(1);
	}
}
