package com.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.springboot.app.commons.users.models.entity.User;
import com.springboot.app.oauth.services.IUserService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUserService userService;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		
		if(authentication.getDetails() instanceof WebAuthenticationDetails) {
			return;
		}
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String message = "Success Login: " + userDetails.getUsername();
		log.info(message);

		User user = userService.findByUsername(authentication.getName());
		
		if(user.getAttempts() != null && user.getAttempts() > 0) {
			user.setAttempts(0);
			userService.update(user, user.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Login error: " + exception.getMessage();
		log.error(message);

		try {
			
			StringBuilder errors = new StringBuilder();
			errors.append(message);
			
			User user = userService.findByUsername(authentication.getName());
			if (user.getAttempts() == null) {
				user.setAttempts(0);
			}
			
			log.info("Attempts: " + user.getAttempts());
			
			user.setAttempts(user.getAttempts()+1);
			
			log.info("Attempts: " + user.getAttempts());
			errors.append("Attempts: " + user.getAttempts());
			
			if(user.getAttempts() >= 3) {
				String errorMaxAttempts = String.format("User %s disabled by maximum attempts.", user.getUsername());
				log.error(errorMaxAttempts);
				errors.append(" - " + errorMaxAttempts);
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());
			
		} catch (FeignException e) {
			log.error(String.format("User %s not found", authentication.getName()));
		}

	}

}
