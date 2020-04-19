package com.mic.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mic.common.dto.CommonResult;
import com.mic.common.exception.SystemErrorType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResponseUtil {
    private ResponseUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 通过流写到前端
     *
     * @param objectMapper 对象序列化
     * @param response
     * @param msg          返回信息
     * @param httpStatus   返回状态码
     * @throws IOException
     */
    public static void responseWriter(ObjectMapper objectMapper,
                                      HttpServletResponse response,
                                      String msg, int httpStatus) throws IOException {
        CommonResult result = CommonResult.builder()
                .code(String.valueOf(httpStatus)).message(msg).build();
        responseWrite(objectMapper, response, result);
    }

    /**
     * 通过流写到前端
     *
     * @param objectMapper 对象序列化
     * @param response
     * @param obj
     */
    public static void responseSucceed(ObjectMapper objectMapper,
                                       HttpServletResponse response,
                                       Object obj) throws IOException {
        CommonResult result = CommonResult.
                builder().build().success(obj);
        responseWrite(objectMapper, response, result);
    }

    /**
     * 通过流写到前端
     *
     * @param objectMapper
     * @param response
     * @param msg
     * @throws IOException
     */
    public static void responseFailed(ObjectMapper objectMapper,
                                      HttpServletResponse response,
                                      String msg) throws IOException {
        CommonResult result = CommonResult.builder()
                .code(SystemErrorType.SYSTEM_ERROR.getCode())
                .message(msg).build();
        responseWrite(objectMapper, response, result);
    }

    private static void responseWrite(ObjectMapper objectMapper,
                                      HttpServletResponse response,
                                      CommonResult result) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (
                Writer writer = response.getWriter()
        ) {
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
        }
    }

    /**
     * webflux的response返回json对象
     */
    public static Mono<Void> responseWriter(ServerWebExchange exchange,
                                            int httpStatus, String msg) throws JsonProcessingException {
        CommonResult result = CommonResult.builder()
                .code(String.valueOf(httpStatus)).message(msg).build();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.valueOf(httpStatus));
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        //todo fix bug
        ObjectMapper objectMapper=new ObjectMapper();
        DataBuffer buffer =
                dataBufferFactory.wrap(objectMapper.writeValueAsBytes(result));
        return response.writeWith(Mono.just(buffer)).doOnError((error) -> {
            DataBufferUtils.release(buffer);
        });
    }
}
