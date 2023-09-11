package com.wolfhack.adapter.feign;

import com.wolfhack.model.dto.PaymentCreationDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface PaymentClient {

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	void pay(@RequestBody PaymentCreationDTO paymentCreationDTO);

}
