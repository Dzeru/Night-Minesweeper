package com.dzeru.nightminesweeper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.mustache.jmustache.LocalizationMessageInterceptor;

import java.util.Locale;

@Configuration
public class LocalizationConfig implements WebMvcConfigurer
{
    @Autowired
    MessageSource messageSource;

    @Override
    public void addInterceptors(InterceptorRegistry reg)
    {
        reg.addInterceptor(localeChangeInterceptor());
        reg.addInterceptor(localizationMessageInterceptor());
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor()
    {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    LocaleResolver localeResolver()
    {
        SessionLocaleResolver clr = new SessionLocaleResolver();
        clr.setDefaultLocale(Locale.ENGLISH);
        return clr;
    }

    @Bean
    LocalizationMessageInterceptor localizationMessageInterceptor()
    {
        LocalizationMessageInterceptor lmi = new LocalizationMessageInterceptor();
        lmi.setLocaleResolver(localeResolver());
        lmi.setMessageSource(messageSource);
        return lmi;
    }
}
