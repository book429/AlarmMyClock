package com.notification.alarmclock;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Created by Book(NClock) on 2020/03/27.
 */

public class AlarmReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {

		// Get id & message from intent.
		int notificationId = intent.getIntExtra("notificationId", 0);
		String message = intent.getStringExtra("todo");

		// When notification is tapped, call MainActivity.
		Intent mainIntent = new Intent(context, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

		NotificationManager myNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		// Prepare notification.
		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(android.R.drawable.ic_dialog_info)
				.setContentTitle("It's Time!")
				.setContentText(message)
				.setWhen(System.currentTimeMillis())
				.setAutoCancel(true)
				.setContentIntent(contentIntent)
				.setPriority(Notification.PRIORITY_MAX)
				.setDefaults(Notification.DEFAULT_ALL);

		// Notify
		myNotificationManager.notify(notificationId, builder.build());

	}
}


//for over API 26...!!!===

//public class AlarmReceiver extends BroadcastReceiver {
//	//    private boolean alreadyDisplayedNotification = false;
//	@RequiresApi(api = Build.VERSION_CODES.O)
//	@Override
//	public void onReceive(Context context, Intent intent) {
//
//		// Get id & message from intent.
//		int notificationId = intent.getIntExtra("notificationId", 0);
//		String CHANNEL_ID = "my_channel_01";
//		int importance = NotificationManager.IMPORTANCE_HIGH;
//		NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "name", importance);
//
//		String temperatureDesc = intent.getStringExtra("temperatureDesc");
//		String comfort = intent.getStringExtra("comfort");
//
//		// When notification is tapped, call MainActivity.
//		Intent mainIntent = new Intent(context, DashboardActivity.class);
//		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
//
//		NotificationManager myNotificationManager =
//				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//		myNotificationManager.createNotificationChannel(mChannel);
//
//		// Prepare notification.
//
////        if (!alreadyDisplayedNotification){
//		Notification.Builder builder = new Notification.Builder(context);
//		builder.setSmallIcon(R.drawable.icon)
//				.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon))
//				.setContentTitle("Hey, Today's weather seems to be"+temperatureDesc+" with "+comfort+".")
//				.setContentText("Click here to know more.")
//				.setWhen(System.currentTimeMillis())
//				.setAutoCancel(true)
//				.setContentIntent(contentIntent)
//				.setPriority(Notification.PRIORITY_MAX)
//				.setDefaults(Notification.DEFAULT_ALL)
//				.setChannelId(CHANNEL_ID).build();
//
//		// Notify
//		myNotificationManager.notify(notificationId, builder.build());
//
////            alreadyDisplayedNotification = true;
//
////        }
//	}
//}
