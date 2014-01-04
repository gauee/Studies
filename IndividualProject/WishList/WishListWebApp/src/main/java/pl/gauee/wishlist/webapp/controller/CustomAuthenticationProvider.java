/*
 * To change this template, choose Toolss | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.gauee.wishlist.utils.HashUtils;
import pl.gauee.wishlist.utils.remote.RemoteAccessApi;

/**
 *
 * @author gauee
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier(value = "proxyBean")
    RemoteAccessApi remoteAccessApi;
    private final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
//    private final String testUserName = "gauee";
//    private final String testUserPass = "0e238030db298bbe7fcb89275fe2a789f358b690ca7581479bf1c34d4d0ff49d";

    @Override
    public Authentication authenticate(Authentication authentication) {
        logger.info(authentication.toString());
        if (authentication.isAuthenticated()) {
            return authentication;
        }
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        HashUtils encoder = HashUtils.getInstance();

        String passwordHash = encoder.hashSHA256(password);
        logger.info("Name: " + username);
        logger.info("Pass: " + password);
        logger.info("PassHash: " + passwordHash);

        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (remoteAccessApi.authenticateUser(username, passwordHash)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, passwordHash, grantedAuths);
            return token;
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
