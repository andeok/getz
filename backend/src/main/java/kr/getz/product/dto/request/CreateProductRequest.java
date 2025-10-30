package kr.getz.product.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.getz.product.domain.Product;
import kr.getz.user.domain.User;

public record CreateProductRequest(
	String title,
	String description,
	List<MultipartFile> images
) {

	public Product toProduct(User user){
		return new Product(title, description, user);
	}
}
