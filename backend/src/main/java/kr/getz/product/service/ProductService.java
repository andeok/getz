package kr.getz.product.service;

import org.springframework.stereotype.Service;

import kr.getz.auction.dto.request.CreateAuctionRequest;
import kr.getz.product.repository.ProductRepository;
import kr.getz.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public long createProduct(User user, CreateAuctionRequest request) {
		return 0;
	}
}
