package com.example.wmseasyexpert.screen;
import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class ScreenType {

    private static HashMap<String, Integer> types = new HashMap<>();

    public static final String OPTIONS = "options";
    public static final String INPUT   = "input";
    public static final String INFO    = "info";
    public static final String MENU    = "menu";

    public static final int value_UNDEFINED = 0;
    public static final int value_MENU = 1;
    public static final int value_INFO = 2;
    public static final int value_INPUT = 3;
    public static final int value_HIDDEN = 4;
    public static final int value_CONTROL = 5;
    public static final int value_OPTIONS = 6;
    public static final int value_MULTI_OPTIONS = 7;

    static {
        types.put("info", value_INFO);
        types.put("input", value_INPUT);
        types.put("hidden", value_HIDDEN);
        types.put("control", value_CONTROL);
        types.put("options", value_OPTIONS);
        types.put("multi_options", value_MULTI_OPTIONS);
        types.put("menu", value_MENU);
    }

    public static int getTypeNo(final String type) {
        if (types.containsKey(type)) {
            return Objects.requireNonNull(types.get(type));
        }
        return value_UNDEFINED;
    }
}

