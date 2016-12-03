package eu.execom.todolistgrouptwo;

import android.app.Application;

import com.facebook.stetho.Stetho;

import org.androidannotations.annotations.EApplication;

/**
 * Created by Jovana Protic on 20-Nov-16.
 */
@EApplication
public class TodoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
