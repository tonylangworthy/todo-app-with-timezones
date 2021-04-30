package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationEvents {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEvents.class);

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        logger.info("success event: " + success.getTimestamp());
    }

    @EventListener
    public void onLogout(LogoutSuccessEvent logout) {
        logger.info("logout event: " + logout.getTimestamp());
    }

    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent failure) {
        logger.info("bad credentials event: " + failure.getTimestamp());
    }

    @EventListener
    public void onCredentialsExpired(AuthenticationFailureCredentialsExpiredEvent failure) {
        logger.info("expired credentials event: " + failure.getTimestamp());
    }

    @EventListener
    public void onLocked(AuthenticationFailureLockedEvent locked) {
        logger.info("locked event: " + locked.getTimestamp());
    }

    @EventListener
    public void onDisabled(AuthenticationFailureDisabledEvent disabled) {
        logger.info("disabled event: " + disabled.getTimestamp());
    }

    @EventListener
    public void onExpired(AuthenticationFailureExpiredEvent disabled) {
        logger.info("expired event: " + disabled.getTimestamp());
    }

    @EventListener
    public void onProxyUntrusted(AuthenticationFailureProxyUntrustedEvent disabled) {
        logger.info("proxy untrusted event: " + disabled.getTimestamp());
    }

    @EventListener
    public void onProviderNotFound(AuthenticationFailureProviderNotFoundEvent disabled) {
        logger.info("provider not found event: " + disabled.getTimestamp());
    }


}