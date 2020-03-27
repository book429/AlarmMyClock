package com.notification.alarmclock;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	private int notificationId = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set Onclick Listener.
		findViewById(R.id.setBtn).setOnClickListener(this);
		findViewById(R.id.cancelBtn).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		EditText editText = findViewById(R.id.editText);
		TextView textView = findViewById(R.id.setTime);
		TimePicker timePicker = findViewById(R.id.timePicker);
		DatePicker datePicker = findViewById(R.id.dataPicker);

		// Set notificationId & text.
		Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
		intent.putExtra("notificationId", notificationId);
		intent.putExtra("todo", editText.getText().toString());

		// getBroadcast(context, requestCode, intent, flags)
		PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
				intent, PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

		switch (view.getId()) {
			case R.id.setBtn:
				int year = datePicker.getYear();
				int month = datePicker.getMonth();
				int day = datePicker.getDayOfMonth();
				int hour = timePicker.getCurrentHour();
				int minute = timePicker.getCurrentMinute();

				// Create date and time.
				Calendar startTime = Calendar.getInstance();
				startTime.set(Calendar.DAY_OF_YEAR,year);
				startTime.set(Calendar.DAY_OF_MONTH,month);
				startTime.set(Calendar.DATE,day);

				startTime.set(Calendar.HOUR_OF_DAY, hour);
				startTime.set(Calendar.MINUTE, minute);
				startTime.set(Calendar.SECOND, 0);
				long alarmStartTime = startTime.getTimeInMillis();

				// Set alarm.
				// set(type, milliseconds, intent)
				alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

				Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
				textView.setText(year+":"+month+":"+day+":"+hour+":"+minute);
				break;

			case R.id.cancelBtn:
				alarm.cancel(alarmIntent);
				Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
				break;
		}

	}
}
