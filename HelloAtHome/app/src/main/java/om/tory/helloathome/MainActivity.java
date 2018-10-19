package om.tory.helloathome;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGlSurfaceView;

    private boolean isSupportES2() {
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

        return configurationInfo.reqGlEsVersion >= 0x20000;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGlSurfaceView = findViewById(R.id.glsvMain);

        if (isSupportES2()) {
            mGlSurfaceView.setEGLContextClientVersion(2);
            mGlSurfaceView.setEGLConfigChooser(8,8,8,8,16,0);//tory ?
            mGlSurfaceView.setRenderer(new MyRenderer());
            mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        } else {
            Toast.makeText(this, "This device does NOT support ES2.0", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mGlSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGlSurfaceView.onResume();
    }
}
