package com.wolfhack.payment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.developer.utils.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MastercardProperties.class)
public class CommunityPassPaymentConfig {

	private final MastercardProperties mastercardProperties;

	@Bean("mastercardPrivateKey")
	public PrivateKey signInKey(MastercardConfig mastercardConfig) throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
		String filePath = mastercardConfig.getSigningKeyFilePath();
		String keyAlias = mastercardConfig.getSigningKeyAlias();
		String keyPassword = mastercardConfig.getSigningKeyPassword();

		return AuthenticationUtils.loadSigningKey(filePath, keyAlias, keyPassword);
	}

	@Bean("mastercardCertificate")
	public Certificate getMastercardCertificate(MastercardConfig mastercardConfig) throws FileNotFoundException, CertificateException {
		return EncryptionUtils.loadEncryptionCertificate(mastercardConfig.getEncryptionCertPath());
	}

	@Bean("encryptionInterceptor")
	public FieldLevelEncryptionConfig fieldLevelEncryptionConfig(Certificate certificate) throws EncryptionException {
		return FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
				.withEncryptionCertificate(certificate)
				.withEncryptionPath("$", "$")
				.withEncryptedValueFieldName("encryptedData")
				.withEncryptedKeyFieldName("encryptedKey")
				.withOaepPaddingDigestAlgorithmFieldName("oaepHashingAlgorithm")
				.withOaepPaddingDigestAlgorithm("SHA-256")
				.withEncryptionKeyFingerprintFieldName("publicKeyFingerprint")
				.withIvFieldName("iv")
				.withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.HEX)
				.build();
	}

	@Bean("decryptionInterceptor")
	public FieldLevelEncryptionConfig decryptionInterceptor(MastercardConfig mastercardConfig)
			throws EncryptionException, KeyStoreException, NoSuchProviderException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
		String decryptionKeyFilePath = mastercardConfig.getDecryptionKeyFilePath();
		String decryptionKeyPassword = mastercardConfig.getDecryptionKeyPassword();
		String decryptionKeyAlias = mastercardConfig.getDecryptionKeyAlias();

		KeyStore keyStore = KeyStore.getInstance("PKCS12", "SunJSSE");
		FileInputStream decryptionKeyFile = new FileInputStream(decryptionKeyFilePath);
		keyStore.load(decryptionKeyFile, decryptionKeyPassword.toCharArray());

		PrivateKey keyAliasProdMc4Key = (PrivateKey) keyStore.getKey(decryptionKeyAlias, decryptionKeyPassword.toCharArray());

		return FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
				.withDecryptionPath("$", "$")
				.withDecryptionKey(keyAliasProdMc4Key)
				.withOaepPaddingDigestAlgorithm("SHA-256")
				.withEncryptedValueFieldName("encryptedValue")
				.withEncryptedKeyFieldName("encryptedKey")
				.withIvFieldName("iv")
				.withEncryptionCertificateFingerprintFieldName("publicKeyFingerprint")
				.withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.BASE64)
				.build();
	}

	@Bean
	public MastercardConfig mastercardConfig() throws Exception {
		Resource properties = mastercardProperties.getProperties();

		if (properties != null) {
			try (InputStream inputStream = properties.getInputStream()) {
				return new ObjectMapper().readValue(inputStream, MastercardConfig.class);
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
		}
		throw new Exception("Mastercard configuration is not available");
	}

}
