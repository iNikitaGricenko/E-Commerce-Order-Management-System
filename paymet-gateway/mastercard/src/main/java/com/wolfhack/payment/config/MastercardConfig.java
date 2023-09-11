package com.wolfhack.payment.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MastercardConfig {

	@JsonProperty("consumerKey")
	private String consumerKey;

	@JsonProperty("signingKeyFilePath")
	private String signingKeyFilePath;

	@JsonProperty("encryptionCertPath")
	private String encryptionCertPath;

	@JsonProperty("signingKeyAlias")
	private String signingKeyAlias;

	@JsonProperty("signingKeyPassword")
	private String signingKeyPassword;

	@JsonProperty("decryptionKeyFilePath")
	private String decryptionKeyFilePath;

	@JsonProperty("decryptionKeyAlias")
	private String decryptionKeyAlias;

	@JsonProperty("decryptionKeyPassword")
	private String decryptionKeyPassword;



}
