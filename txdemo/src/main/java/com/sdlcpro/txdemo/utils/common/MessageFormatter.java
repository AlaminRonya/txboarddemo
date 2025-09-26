package com.sdlcpro.txdemo.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IllegalFormatException;

public final class MessageFormatter {
    private static final Logger logger = LoggerFactory.getLogger(MessageFormatter.class);

    private MessageFormatter() {
    }

    public static String format(String format, Object... args) {
        if (format == null) {
            logger.warn("Format string is null.");
            return "Formatted String is null";
        }
        try {
            return String.format(format, args);
        } catch (IllegalFormatException e) {
            logger.error("Formatting failed for format: '{}', with arguments: {}", format, args, e);
            return "Formatting error: " + e.getMessage();
        }
    }
}