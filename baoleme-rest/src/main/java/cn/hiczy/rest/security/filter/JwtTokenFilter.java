package cn.hiczy.rest.security.filter;

import cn.hiczy.rest.security.utils.JwtTokenUtil;
import cn.hiczy.vo.CommonResult;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService myUserService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(jwtTokenUtil.getHeader());
        try {
            if (authHeader != null && authHeader != "") {
                String username = jwtTokenUtil.getUsernameFromToken(authHeader);
                //验证令牌
                jwtTokenUtil.validateToken(authHeader);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.myUserService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ExpiredJwtException e) {
            //如果令牌过期的处理
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(CommonResult.failure("登录信息已过期，请重新登录")));
        }

    }


}
