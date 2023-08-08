package com.cinema.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationResponse {
private String jwtToken;

public String getJwtToken() {
	return jwtToken;
}

public void setJwtToken(String jwtToken) {
	this.jwtToken = jwtToken;
}

public static Builder builder() {
    return new Builder();
}

public static class Builder {
    private String jwtToken;

    private Builder() {
        // Private constructor to prevent direct instantiation
    }

    public Builder jwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }

    public AuthenticationResponse build() {
        AuthenticationResponse response = new AuthenticationResponse();
        response.jwtToken = this.jwtToken;
        return response;
    }
}

}
