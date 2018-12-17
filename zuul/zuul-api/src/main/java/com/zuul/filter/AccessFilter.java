//package com.zuul.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//import java.util.stream.Stream;
//
//@Component
//public class AccessFilter extends ZuulFilter {
//
//    /**
//     * 忽略的url
//     */
//    @Value("${ignore.authUrls}")
//    private String ignoreUrls;
//
//    /**
//     * jwt 秘钥
//     */
//    @Value("${jwt.jwtSecretKey}")
//    private String jwtSecretKey;
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        // 判断是否需要过滤  不需要过滤 直接放通
//        if (!isNeedFilter(request.getRequestURI())) {
//            ctx.setSendZuulResponse(true);
//            return null;
//        }
//
//        String token = request.getHeader("Authorization");
//
//        // 没有token直接返回
//        if (StringUtils.isEmpty(token)) {
//            ctx.setResponseStatusCode(401);
//            ctx.setSendZuulResponse(false);
//            return null;
//        }
//
//        Claims claims = null;
//
//        // 校验token是否正确
//        try {
//            claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            throw e;
//        }
//
//        if (Objects.isNull(claims)) {
//            ctx.setResponseStatusCode(401);
//            ctx.setSendZuulResponse(false);
//            try {
//                ctx.getResponse().getWriter().write("you are not login...");
//                ctx.getResponse().getWriter().close();
//            } catch (Exception e) {
//            }
//            return null;
//        }
//
//        ctx.addZuulRequestHeader("userId", (Integer) claims.get("id") + "");
//
//
//        return null;
//    }
//
//
//    /**
//     * 判断是否需要过滤
//     *
//     * @param uri 请求的地址
//     * @return 需要过滤返回true  不需要过滤返回false
//     */
//    private boolean isNeedFilter(String uri) {
//
//
//        // 没有忽略的url 直接返回
//        if (StringUtils.isEmpty(ignoreUrls)) {
//            return true;
//        }
//
//        return !Stream.of(ignoreUrls.split(",")).filter(ignoreUrl -> uri.contains(ignoreUrl)).findAny().isPresent();
//    }
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//
//}