package com.mever.api.domain.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mever.api.domain.email.dto.SmsDto;
import com.mever.api.domain.email.dto.SmsResponseDTO;
import com.mever.api.domain.email.repository.SendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSmsSender {
    private static final String NAVER_SMS_API_URL = "https://apis.ncloud.com/sms/v2/services/{serviceId}/messages";
    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;
    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;
    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;
    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;
    private final SendRepository sendRepository;

    public String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String timestamp = time.toString();

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        return encodeBase64String;
    }
    public ResponseEntity sendNaver(String recipient, String message) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        Long time = System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        SmsDto.SmsRequest smsRequest = SmsDto.SmsRequest.builder()
                .type("SMS")
                .from(phone) // 발신자 번호
                .to(recipient) // 수신자 번호
                .content(message)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(smsRequest);
        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseDTO.class);
        SmsDto smsDto = SmsDto.builder()
                .msg(smsRequest.getContent())
                .email(smsRequest.getEmail())
                .phone(smsRequest.getTo())
                .type("sms")
                .build();
        return  ResponseEntity.ok(sendRepository.save(smsDto.toSendBuilder()));
//        String url = NAVER_SMS_API_URL.replace("{serviceId}", serviceId);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("X-NCP-auth-key", accessKey);
//        headers.set("X-NCP-service-secret", secretKey);
//
//        SmsDto.SmsRequest smsRequest = new SmsDto.SmsRequest();
//        smsRequest.setType("SMS");
//        smsRequest.setFrom(phone); // 발신자 번호
//        smsRequest.setTo(recipient); // 수신자 번호
//        smsRequest.setContent(message);
//
//        Mono<ResponseEntity<String>> responseMono = webClient.post()
//                .uri(url)
//                .headers(httpHeaders -> httpHeaders.addAll(headers))
//                .body(BodyInserters.fromValue(smsRequest))
//                .retrieve()
//                .toEntity(String.class);
//
//        return responseMono.map(responseEntity -> {
//            if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                SmsDto smsDto = SmsDto.builder()
//                        .msg(smsRequest.getContent())
//                        .email(smsRequest.getEmail())
//                        .phone(smsRequest.getTo())
//                        .type("sms")
//                        .build();
//                return ResponseEntity.ok(sendRepository.save(smsDto.toSendBuilder()));
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        }).block();
    }
}
