package hue.edu.sim.activity;

import android.app.Activity;
import android.app.Application;
import hue.edu.sim.manager.XmppConnectionManager;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * �������˳�Ӧ��.
 * 
 * @author shimiso
 */
public class EimApplication extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();

	// ���Activity��������
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// ��������Activity��finish
	public void exit() {
		XmppConnectionManager.getInstance().disconnect();
		for (Activity activity : activityList) {
			activity.finish();
		}
	}
}
