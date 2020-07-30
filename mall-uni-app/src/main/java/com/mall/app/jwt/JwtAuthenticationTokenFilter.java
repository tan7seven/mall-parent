package com.mall.app.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mall.app.handler.BaseContextHandler;
import com.mall.common.enums.ResultStatus;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestResult;
import com.mall.common.model.vo.ResultVO;
import com.mall.common.utils.IPUtil;
import com.sun.istack.Nullable;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: JWT登录授权过滤器
 */

@Slf4j
@CrossOrigin
@Component
public class JwtAuthenticationTokenFilter implements HandlerInterceptor {

    /**
     * 桶的最大容量，即能装载 Token 的最大数量
     */
    @Value("${rate.limit.maxCapacity:5}")
    private Integer maxCapacity;

    /**
     * 补充 Token 的时间间隔
     */
    private Duration refillDuration = Duration.ofSeconds(1);

    /**
     * 单机网关限流用一个ConcurrentHashMap来存储 bucket，
     * 如果是分布式集群限流的话，可以采用 Redis等分布式解决方案
     */
    private static final Map<String, Bucket> LOCAL_CACHE = new ConcurrentHashMap<>();

    private Bucket createNewBucket(Integer refillCount) {
        Refill refill = Refill.of(refillCount, refillDuration);
        Bandwidth limit = Bandwidth.classic(maxCapacity, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    /**
     * 获取补充令牌策略
     */
    private Integer getRefillTokens(HttpServletRequest request) {
        return 5;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        /** 跨域验证不处理 */
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        /**  限流  */
        Integer refillCount = getRefillTokens(request);
        String remoteAddr = request.getRemoteAddr();
        Bucket bucket = LOCAL_CACHE.computeIfAbsent(remoteAddr, k -> createNewBucket(refillCount));
        log.info("ip:{} ,availableTokens:{} ", remoteAddr, bucket.getAvailableTokens());
        if (!bucket.tryConsume(1)) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 当可用的令牌书为0时，进行限流返回429状态码
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            return false;
        }
        String authHeader = request.getHeader("Authorization");
        String userId = JwtTokenUtil.parseTokenGetUserId(authHeader);
        if (StringUtils.isNotBlank(userId)) {
            BaseContextHandler.setUserID(Long.valueOf(userId));
            BaseContextHandler.setToken(authHeader);
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
    }



}