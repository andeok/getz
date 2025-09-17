package kr.getz.global.oci.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.auth.InstancePrincipalsAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;

@Configuration
public class OciConfig {

	@Value("${oci.profile}")
	private String profile;

	@Value("${oci.namespace}")
	private String namespace;

	@Value("${oci.compartmentId}")
	private String compartmentId;

	@Bean
	public AuthenticationDetailsProvider authenticationDetailsProvider() throws IOException {
		// 'INSTANCE_PRINCIPAL' 프로필일 경우 InstancePrincipalsAuthenticationDetailsProvider를 사용
		if ("INSTANCE_PRINCIPAL".equalsIgnoreCase(profile)) {
			return (AuthenticationDetailsProvider)InstancePrincipalsAuthenticationDetailsProvider.builder().build();
		}
		// 그 외의 경우 (예: 'DEFAULT') 기존의 로컬 config 파일 방식 사용
		else {
			final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault(profile);
			return new ConfigFileAuthenticationDetailsProvider(configFile);
		}
	}

	@Bean
	public ObjectStorage objectStorageClient(AuthenticationDetailsProvider provider) {
		ObjectStorage client = ObjectStorageClient.builder()
			.build(provider);

		return client;
	}
}
