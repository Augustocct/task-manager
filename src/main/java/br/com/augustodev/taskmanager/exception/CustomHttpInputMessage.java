package br.com.augustodev.taskmanager.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor
public class CustomHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;
    private InputStream body;

    public CustomHttpInputMessage(HttpHeaders headers, InputStream body) {
        this.headers = headers;
        this.body = body;
    }

    @SuppressWarnings("null")
    @Override
    public InputStream getBody() throws IOException {
        return this.body;
    }

    @SuppressWarnings("null")
    @Override
    public HttpHeaders getHeaders() {
        return this.headers;
    }
}
