package kr.getz.personal;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.getz.personal.auction.domain.Auction;
import kr.getz.personal.auction.domain.AuctionStatus;
import kr.getz.personal.auction.repository.AuctionRepository;
import kr.getz.personal.member.domain.Member;
import kr.getz.personal.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class InitData implements CommandLineRunner {

	private final MemberRepository memberRepository;
	private final AuctionRepository auctionRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		// if (memberRepository.count() > 0) {
		// 	memberRepository.deleteAll();
		// }

		List<Member> members = new ArrayList<>();

		for (int i = 1; i <= 100; i++) {
			// 1. 이메일: user1@example.com, user2@example.com ...
			String email = "user" + i + "@example.com";

			// 2. 비밀번호: "1234"로 통일 (로그인 테스트 편의성)
			// 실제 로그인 기능을 쓰려면 반드시 암호화해서 넣어야 합니다.
			String encodedPassword = passwordEncoder.encode("1234");

			// 3. 이름: 사용자1, 사용자2 ...
			String name = "사용자" + i;

			// 4. 엔티티 생성
			Member member = Member.builder()
				.email(email)
				.password(encodedPassword)
				.name(name)
				// .role(Role.USER) // 권한 필드가 있다면 추가
				.build();

			members.add(member);
		}

		memberRepository.saveAll(members);
		log.info("✅ 회원 100명 생성 완료!");

		// if (auctionRepository.count() > 0) {
		// 	auctionRepository.deleteAll();
		// }

		List<Auction> auctionList = new ArrayList<>();
		SecureRandom random = new SecureRandom();

		// 랜덤 제목 조합용 단어
		String[] adjectives = {"급처", "S급", "미개봉", "사용감 있는", "희귀", "정품", "가품아님", "네고가능", "교환환영", "박스풀"};
		String[] items = {"맥북 프로", "아이폰 15", "갤럭시 S24", "플레이스테이션5", "닌텐도 스위치", "에어팟 프로", "버즈 프로", "RTX 4090", "기계식 키보드", "게이밍 모니터"};

		for (int i = 0; i < 50000; i++) {
			// 1. 판매자 ID (1~100)
			Long sellerId = (long) (random.nextInt(100) + 1);

			// 2. 제목 생성 (형용사 + 물품)
			String title = adjectives[random.nextInt(adjectives.length)] + " " + items[random.nextInt(items.length)];

			// 3. 가격 생성 (1,000원 ~ 1,000,000원, 100원 단위 끊기)
			int startPrice = (random.nextInt(1000) + 1) * 1000;

			// 4. 즉시 구매가 (50% 확률로 null, 아니면 시작가의 1.2~2배)
			Integer endPrice = random.nextBoolean() ? null : (int) (startPrice * (1.2 + random.nextDouble()));

			// 5. 시간 생성 (현재 ~ 최대 30일 뒤 마감)
			LocalDateTime startTime = LocalDateTime.now();
			LocalDateTime endTime = startTime.plusMinutes(random.nextInt(30 * 24 * 60) + 10); // 최소 10분 뒤

			// 6. 엔티티 생성 (Builder 패턴 가정, 생성자로 대체 가능)
			Auction auction = Auction.builder()
				.sellerId(sellerId)
				.title(title + " (" + i + "번째)") // 중복 방지용 번호
				.startPrice(startPrice)
				.endPrice(endPrice)
				.startTime(startTime)
				.endTime(endTime)
				.status(AuctionStatus.PROCEEDING) // Enum 가정
				.build();

			auctionList.add(auction);
		}

		// 대용량 저장
		auctionRepository.saveAll(auctionList);
		log.info("✅ 5만 건 데이터 생성 완료!");


	}
}
