package com.ebank.jwt.util;

import com.ebank.jwt.config.EbankApiProperties;
import com.ebank.jwt.system.mapper.MyUserDetailsServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("ebankService")
public class MyRBACService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private EbankApiProperties ebankApiProperties;

    @Resource
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    /**
     * verify the user have permission
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = ((UserDetails)principal);
            List<GrantedAuthority> authorityList =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI());
            return userDetails.getAuthorities().contains(authorityList.get(0))
                    || ebankApiProperties.getIgnoreUrl().contains(request.getRequestURI());
        }
        return false;
    }

    public MyUserDetails findByUserName(String username) {
        return myUserDetailsServiceMapper.findByUserName(username);
    }

    public List<String> findRoleByUserName(String username) {
        return myUserDetailsServiceMapper.findRoleByUserName(username);
    }
}
