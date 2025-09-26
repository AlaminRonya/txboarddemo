package com.sdlcpro.txdemo.common;

import com.sdlcpro.txdemo.config.BeanServiceUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Arrays;
import java.util.Locale;

public interface I18nService {
    default String i18n(String code, Object... params) {
        final MessageSource ms = BeanServiceUtils.getBean(MessageSource.class);
        final Locale locale = LocaleContextHolder.getLocale();

        Object[] p = Arrays.stream(params)
                .map(param -> {
                    try {
                        return (param instanceof String str) ? ms.getMessage(str, null, locale) : ms.getMessage(param.toString(), null, locale);
                    } catch (Exception ex) {
                        return param;
                    }
                })
                .toArray();

        return ms.getMessage(code, p, locale);
    }
}