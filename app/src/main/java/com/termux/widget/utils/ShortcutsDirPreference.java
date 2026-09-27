package com.termux.widget.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.termux.shared.termux.TermuxConstants;

import java.io.File;

public final class ShortcutsDirPreference {

    private static final String PREFERENCES_FILE_NAME = "termux_widget_custom_prefs";
    private static final String KEY_CUSTOM_SHORTCUTS_DIR_PATH = "custom_shortcuts_dir_path";

    private ShortcutsDirPreference() {}

    @NonNull
    private static SharedPreferences getPreferences(@NonNull Context context) {
        return context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    public static String getCustomShortcutsDirPathRaw(@NonNull Context context) {
        return getPreferences(context).getString(KEY_CUSTOM_SHORTCUTS_DIR_PATH, null);
    }

    public static void setCustomShortcutsDirPath(@NonNull Context context, @Nullable String path) {
        String trimmed = (path == null) ? null : path.trim();
        getPreferences(context).edit()
                .putString(KEY_CUSTOM_SHORTCUTS_DIR_PATH, (trimmed == null || trimmed.isEmpty()) ? null : trimmed)
                .apply();
    }

    @NonNull
    public static File getEffectiveShortcutsDir(@NonNull Context context) {
        String customPath = getCustomShortcutsDirPathRaw(context);
        if (customPath != null && !customPath.isEmpty()) {
            return new File(customPath);
        }
        return TermuxConstants.TERMUX_SHORTCUT_SCRIPTS_DIR;
    }
}
