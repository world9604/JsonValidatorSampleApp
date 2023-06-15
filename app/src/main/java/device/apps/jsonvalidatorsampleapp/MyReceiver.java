package device.apps.jsonvalidatorsampleapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MyReceiver extends BroadcastReceiver {
    /**------------------------------[EmkitAgent][Amazon]----------------------------**/
    private static final String ACTION_CONFIG_RESULT = "device.apps.emkitagent.ACTION_CONFIG_RESULT";
    private static final String CATEGORY_CONFIG_RESULT = "device.apps.emkitagent.CATEGORY_CONFIG_RESULT";
    private static final String EXTRA_CONFIG_RESULT = "result";
    private static final String EXTRA_CONFIG_RESULT_REPORTS = "reports";
    private static final String TAG = "taein";

    /**------------------------------[EmInstaller][Amazon]----------------------------**/
    public static final String ACTION_WORK_RESULT = "ex.dev.tool.eminstaller.ACTION_WORK_RESULT";
    public static final String CATEGORY_WORK_RESULT = "ex.dev.tool.eminstaller.CATEGORY_WORK_RESULT";
    public static final String EXTRA_WORK_RESULT = "result";
    public static final String EXTRA_WORK_RESULT_REASON = "reason";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive action : " + action);
        boolean result = true;
        String report = "";
        String msg = "";

        switch (action) {
            case ACTION_CONFIG_RESULT :
                result = intent.getBooleanExtra(EXTRA_CONFIG_RESULT, true);
                report = intent.getStringExtra(EXTRA_CONFIG_RESULT_REPORTS);
                msg = "result : " + result + ", report : " + report;
                startActivity(context, msg);
                break;
            case ACTION_WORK_RESULT :
                result = intent.getBooleanExtra(EXTRA_WORK_RESULT, true);
                report = intent.getStringExtra(EXTRA_WORK_RESULT_REASON);
                msg = "result : " + result + ", report : " + report;
                startActivity(context, msg);
                break;
        }
    }

    private void startActivity(Context context, String msg) {
        HandlerThread t = new HandlerThread("");
        t.start();
        new Handler(t.getLooper()).post(() -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("msg", msg);
            context.startActivity(intent);
        });
    }
}