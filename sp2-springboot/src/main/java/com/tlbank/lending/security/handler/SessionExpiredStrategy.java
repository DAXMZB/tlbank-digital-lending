package com.tlbank.lending.security.handler;

import java.io.IOException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.security.util.JsonResponseWriter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Returns a JSON 401 response when a concurrent login invalidates the existing session.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SessionExpiredStrategy implements SessionInformationExpiredStrategy {

    private final JsonResponseWriter jsonResponseWriter;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        log.debug("Session expired for user due to concurrent login");
        jsonResponseWriter.write(event.getResponse(), jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED,
                ApiResponse.error(ErrorCode.UNAUTHORIZED, "Session expired due to concurrent login", null));
    }
}
