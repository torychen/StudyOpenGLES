package om.tory.airhockey1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGlSurfaceView;
    private boolean mIsRendererSet;

    private boolean isSupportES2() {
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        if (configurationInfo != null) {
            return configurationInfo.reqGlEsVersion >= 0x20000;
        } else {
            MyUtility.Toast(this, "Fail to get configurationInfo.Quit");
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIsRendererSet = false;

        if (isSupportES2()) {
            mGlSurfaceView = new GLSurfaceView(this);
            mGlSurfaceView.setEGLContextClientVersion(2);
            mGlSurfaceView.setRenderer(new MyRenderer());
            mIsRendererSet = true;

            setContentView(mGlSurfaceView);
        } else {
            MyUtility.Toast(this, "The device does NOT support OpenGL ES2.0.");
            finish();
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsRendererSet) {

            mGlSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsRendererSet) {
            mGlSurfaceView.onResume();
        }
    }
}
