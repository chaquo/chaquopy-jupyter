package com.chaquo.python.jupyter;

import android.app.*;
import com.chaquo.python.utils.*;

public class JupyterActivity extends PythonConsoleActivity {

    @Override protected Class<? extends JupyterActivity.Task> getTaskClass() {
        return JupyterActivity.Task.class;
    }

    // Maintain state unless the main loop has been terminated. Requires
    // the activity to be in its own task (see AndroidManifest).
    @Override public void onBackPressed() {
        if (task.getState() == Thread.State.RUNNABLE) {
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }
    }

    // =============================================================================================

    public static class Task extends PythonConsoleActivity.Task {
        public Task(Application app) {
            super(app);
        }

        @Override public void run() {
            py.getModule("chaquopy.jupyter.notebook").callAttr("main");
        }
    }

}
