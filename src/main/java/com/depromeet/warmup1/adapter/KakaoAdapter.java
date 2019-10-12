package com.depromeet.warmup1.adapter;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.depromeet.warmup1.dto.KakaoUserDto;
import com.depromeet.warmup1.exception.ApiFailedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoAdapter {
	private final RestTemplate restTemplate;

	public KakaoUserDto getUserInfo(String kakaoToken) {
		final URI requestUrl = UriComponentsBuilder.fromHttpUrl("https://kapi.kakao.com/v2/user/me").build(true)
				.toUri();

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + kakaoToken);

		final HttpEntity httpEntity = new HttpEntity(httpHeaders);
		final ResponseEntity<KakaoUserDto> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET,
				httpEntity, KakaoUserDto.class);

		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new ApiFailedException("Failed to get User Info from kakao", HttpStatus.SERVICE_UNAVAILABLE);
		}
		return responseEntity.getBody();
	}

}