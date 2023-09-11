package com.wolfhack.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "mastercard")
public class MastercardProperties {

	private Resource signIn;

	/**
	 * @return the serviceAccount
	 */
	public Resource getProperties() {
		return signIn;
	}

	/**
	 * @param serviceAccount the serviceAccount to set
	 */
	public void setProperties(Resource serviceAccount) {
		this.signIn = serviceAccount;
	}

}
