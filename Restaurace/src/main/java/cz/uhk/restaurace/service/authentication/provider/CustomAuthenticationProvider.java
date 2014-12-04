package cz.uhk.restaurace.service.authentication.provider;

import cz.uhk.restaurace.service.UserService;
import cz.uhk.restaurace.service.authentication.CustomAuthentication;
import cz.uhk.restaurace.service.authentication.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by dann on 20.11.2014.
 * Custom authentication provider which takes care of storing additional data in CustomAuthentication object right
 * after logging in
 */
@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, org.springframework.security.core.userdetails.UserDetails user) {
        CustomAuthentication result = null;
        UserDetails userProfile = (UserDetails) principal;
        if (principal != null) {
            result = new CustomAuthentication(userProfile.getUsername(), userProfile.getPassword(), userProfile.getRoles(), userProfile);
        }
        return result;
    }
}
