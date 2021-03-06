package framework.core;

import framework.dispose.Disposable;

/**
 * Created by vicboma on 11/07/14.
 * Create a task asynchronously executable.
 */
public class AsyncTask<T extends Executable> implements Disposable {
    private static final String PROCESSOR_THREAD = "processor-thread";
    private Thread thread;
    private T executable;

    public AsyncTask(final T executable) {
        this.executable = executable;
        thread = new Thread(executable, PROCESSOR_THREAD + executable.toString());
        thread.setPriority(Thread.NORM_PRIORITY);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        executable.stop();
        thread.stop();
        dispose();
    }

    @Override
    public void dispose() {
        executable = null;
        thread = null;
    }
}
