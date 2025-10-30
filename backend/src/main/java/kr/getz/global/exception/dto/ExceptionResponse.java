package kr.getz.global.exception.dto;

public record ExceptionResponse(String httpMethod, String path, String auctionCode, String message) {
}
