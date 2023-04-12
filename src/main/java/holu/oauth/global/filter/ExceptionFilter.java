package holu.oauth.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import holu.oauth.global.exception.BasicException;
import holu.oauth.global.exception.ErrorCode;
import holu.oauth.global.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BasicException e) {
            sendError(response, e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            sendError(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        String responseString = objectMapper.writeValueAsString(errorResponse);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(responseString);
    }
}
