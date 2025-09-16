package kr.getz.global.oci.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.requests.DeleteObjectRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.PutObjectResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OciService {

	private final ObjectStorage objectStorageClient;

	@Value("${oci.namespace}")
	private String namespace;

	@Value("${oci.compartmentId}")
	private String compartmentId;

	@Value("${oci.bucketName}")
	private String bucketName;

	@Value("${oci.region}")
	private String region;


	public String uploadFile(MultipartFile file) throws IOException {

		String originalFilename = file.getOriginalFilename();
		String fileExtension = getFileExtension(originalFilename);
		String generatedFileName = UUID.randomUUID().toString().replace("-", "") + "." + fileExtension;

		try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
			PutObjectRequest request = PutObjectRequest.builder()
				.namespaceName(namespace)
				.bucketName(bucketName)
				.objectName(generatedFileName)
				.contentType(file.getContentType())
				.contentLength(file.getSize())
				.putObjectBody(inputStream)
				.build();

			objectStorageClient.putObject(request);

			// 필요하다면 업로드된 파일의 URL을 생성하여 반환할 수도 있습니다.
			// 예: https://objectstorage.{region}.oraclecloud.com/n/{namespace}/b/{bucketName}/o/{objectName}

			String fileUrl = String.format("https://objectstorage.%s.oraclecloud.com/n/%s/b/%s/o/%s",
				region,
				namespace,
				bucketName,
				generatedFileName);

			return fileUrl;
		}

	}

	private String getFileExtension(String filename) {
		if (filename == null || filename.isEmpty()) {
			return "";
		}
		int dotIndex = filename.lastIndexOf('.');
		return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
	}

	public void deleteFile(String objectName) {
		if (objectName == null || objectName.isBlank()) {
			log.warn("삭제할 파일 이름이 비어있습니다.");
			return;
		}

		log.info("Deleting file from OCI Object Storage: {}", objectName);

		try {
			DeleteObjectRequest request = DeleteObjectRequest.builder()
				.namespaceName(namespace)
				.bucketName(bucketName)
				.objectName(objectName)
				.build();

			// 삭제 요청 실행
			objectStorageClient.deleteObject(request);

			log.info("File '{}' deleted successfully.", objectName);
		} catch (Exception e) {
			// BmcException 등을 구체적으로 처리할 수 있습니다.
			log.error("OCI에서 파일 삭제 중 오류가 발생했습니다: " + objectName, e);
			// 필요하다면 여기서 사용자 정의 예외를 던질 수 있습니다.
			throw new RuntimeException("파일 삭제에 실패했습니다.", e);
		}

	}
}
