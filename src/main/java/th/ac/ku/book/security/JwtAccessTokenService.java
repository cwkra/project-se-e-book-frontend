package th.ac.ku.book.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtAccessTokenService {

    @Value("https://book/api")
    private String audience;

    @Value("GWc3O5GJrDAL2DB9FKT8W137fL4s2udM")
    private String clientId;

    @Value("MRFAPob2f3Si61SwA4dpkr3iyGwNFtR5K8HwQrqlVgpbN7a3xB-E3c4cDHiofo9c")
    private String clientSecret;

    @Value("https://dev-dh6ugkdxqrtx8t2m.us.auth0.com/")
    private String issuer;

    @Autowired
    private RestTemplate restTemplate;

    private String token = null;

    public String requestAccessToken() {

        if (token != null)
            return token;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",
                MediaType.APPLICATION_FORM_URLENCODED.toString());

        MultiValueMap<String, String> requestBody =
                new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("audience", audience);

        HttpEntity entity = new HttpEntity(requestBody, headers);

        ResponseEntity<JwtResponse> response =
                restTemplate.exchange(issuer + "oauth/token",
                        HttpMethod.POST,
                        entity, JwtResponse.class);

        JwtResponse jwtResponse = response.getBody();
        token = jwtResponse.getAccessToken();

        return token;
    }
}
