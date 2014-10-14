package example.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

//import android.view.Menu;

public class CompassActivity extends Activity implements SensorEventListener {

	TextView tvResult;
	ImageView ivCompass;
	SensorManager manager;
	float currentDegree = 0f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compass_layout);
		tvResult = (TextView) findViewById(R.id.tvResult);
		ivCompass = (ImageView) findViewById(R.id.ivCompass);
		manager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
	}
	@Override
	protected void onPause() {
		super.onPause();
		manager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float degree = Math.round(event.values[0]);
		tvResult.setText("Heading: " + Float.toString(degree) + " degrees");
		RotateAnimation rotate = new RotateAnimation(currentDegree, -degree,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(210);
		rotate.setFillAfter(true);
		ivCompass.startAnimation(rotate);
		currentDegree = -degree;
	}
}
