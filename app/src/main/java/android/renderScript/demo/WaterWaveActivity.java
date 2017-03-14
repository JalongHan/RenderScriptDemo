package android.renderScript.demo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

@SuppressLint("ParserError")
public class WaterWaveActivity extends Activity {
	WaterWaveView mWaterWaveView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWaterWaveView = new WaterWaveView(this);
        setContentView(mWaterWaveView);
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mWaterWaveView.resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mWaterWaveView.pause();
	}
}
