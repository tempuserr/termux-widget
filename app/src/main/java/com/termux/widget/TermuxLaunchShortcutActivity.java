package com.termux.widget;

import android.app.Activity;

/**
 * An activity to launch a shortcut. We want to a launch a service directly, but a shortcut
 * cannot be used to launch a service, only activities, so have to go through this activity.
 */
public class TermuxLaunchShortcutActivity extends Activity {

	private static final String LOG_TAG = "TermuxLaunchShortcutActivity";

	@Override
	protected void onResume() {
		super.onResume();

		// Set log level for the app
		TermuxWidgetApplication.setLogConfig(this, false);

		String[] arguments = ShortcutFile.splitArguments(
				getIntent().getStringExtra(ShortcutFile.EXTRA_SHORTCUT_ARGUMENTS));
		TermuxWidgetProvider.handleTermuxShortcutExecutionIntent(this, getIntent(), LOG_TAG, arguments);
		finish();
	}

}
