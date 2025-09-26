package com.sdlcpro.txdemo.utils.common;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public record NullSafeMapper() {



    // ---------- String ----------
    public static String safeString(String value) {
        return value != null ? value : "N/A";
    }

    public static String safeString(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Byte ----------
    public static Byte safeByte(Byte value) {
        return value != null ? value : (byte) 0;
    }

    public static Byte safeByte(Byte value, Byte defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Short ----------
    public static Short safeShort(Short value) {
        return value != null ? value : (short) 0;
    }

    public static Short safeShort(Short value, Short defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Integer ----------
    public static Integer safeInteger(Integer value) {
        return value != null ? value : 0;
    }

    public static Integer safeInteger(Integer value, Integer defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Long ----------
    public static Long safeLong(Long value) {
        return value != null ? value : 0L;
    }

    public static Long safeLong(Long value, Long defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Float ----------
    public static Float safeFloat(Float value) {
        return value != null ? value : 0.0f;
    }

    public static Float safeFloat(Float value, Float defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Double ----------
    public static Double safeDouble(Double value) {
        return value != null ? value : 0.0;
    }

    public static Double safeDouble(Double value, Double defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Boolean ----------
    public static Boolean safeBoolean(Boolean value) {
        return value != null ? value : Boolean.FALSE;
    }

    public static Boolean safeBoolean(Boolean value, Boolean defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- Character ----------
    public static Character safeCharacter(Character value) {
        return value != null ? value : '\u0000'; // null char
    }

    public static Character safeCharacter(Character value, Character defaultValue) {
        return value != null ? value : defaultValue;
    }

    // ---------- List (Immutable Snapshot) ----------
    public static <T> List<T> safeList(List<T> list) {
        return list != null
                ? List.copyOf(list)
                : Collections.emptyList();
    }

    public static <T> List<T> safeList(List<T> list, List<T> defaultValue) {
        return list != null
                ? List.copyOf(list)
                : List.copyOf(defaultValue);
    }

    // ---------- List (Concurrent Copy) ----------
    public static <T> List<T> safeConcurrentList(List<T> list) {
        return list != null
                ? new CopyOnWriteArrayList<>(list)
                : new CopyOnWriteArrayList<>();
    }

    public static <T> List<T> safeConcurrentList(List<T> list, List<T> defaultValue) {
        return list != null
                ? new CopyOnWriteArrayList<>(list)
                : new CopyOnWriteArrayList<>(defaultValue);
    }
}

/**
 * UserDto dto = new UserDto();
 *
 * dto.setName(NullSafeMapper.safeString(user.getName()));
 * dto.setAge(NullSafeMapper.safeInteger(user.getAge(), -1));
 * dto.setSalary(NullSafeMapper.safeDouble(user.getSalary()));
 * dto.setActive(NullSafeMapper.safeBoolean(user.getActive(), true));
 * dto.setGrade(NullSafeMapper.safeCharacter(user.getGrade(), 'A'));
 * dto.setTags(NullSafeMapper.safeList(user.getTags()));
 */