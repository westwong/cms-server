package com.k2future.cms.config.oauth2;


import com.yunque.commons.util.Assert;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author West
 * @date create in 2019/9/16
 */
public class Oauth2UserUtil {

    private static final String defaultRolePrefix = "ROLE_";

    private static OAuth2Authentication currSecurityUser() {
        return  (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long currUserId(){
        try {
            Map details = (Map) currSecurityUser().getUserAuthentication().getDetails();
            Map principal = (Map) details.get("principal");
            return Long.valueOf(principal.get("id").toString());
        } catch (Exception e) {
            Assert.throwE("please login");
        }
        return  null;
    }
    public static String currUsername(){
        try {
            Map details = (Map) currSecurityUser().getUserAuthentication().getDetails();
            Map principal = (Map) details.get("principal");
            return principal.get("username").toString();
        } catch (Exception e) {
            Assert.throwE("please login");
        }
        return  null;
    }

    public static boolean currUserHasRole(String role) {
        role = defaultRolePrefix + role;
        for (GrantedAuthority authority : currSecurityUser().getAuthorities()) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public static boolean currHasNotRole(String role) {
       return !currUserHasRole(role);
    }

    public  static List<String> currRoles() {
        List<String> roles = new ArrayList<>(3);
        for (GrantedAuthority authority : currSecurityUser().getAuthorities()) {
            String role = authority.getAuthority();
            roles.add(role.replaceAll(defaultRolePrefix,""));
        }
        return roles;
    }

    public static List<String> currScopes(){
        OAuth2Authentication oAuth2Authentication = currSecurityUser();
        return new ArrayList<>(oAuth2Authentication.getOAuth2Request().getScope());
    }

}
