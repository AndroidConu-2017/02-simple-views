package ca.campbell.week2_rw_views;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*  
 *  Sample code Week 2
 *  Demonstrates programmatic manipulation of Views defined in xml.
 *  Demonstrates simple use of an intent to invoke a second activity.
 *  Check the manifest: min sdk  18 (4.3 Jellybean) target sdk 22 (5.1.1 Lollipop)
 */

public class MainActivity extends Activity {

	private static final String TAG = (String) "RW-VIEWS";
	private static final String SECRET = (String) "guacamole";

	// one may use string here, EditText.getText() returns CharSequence
	// android uses CharSequence so that other objs like StringBuffers may be
	// specified
	// a String is-a CharSequence, CharSequence operates more generally
	private CharSequence str;
	// private String str;

	// variables for references to the View widgets 
	private TextView msgHeader, msg; 
	private EditText et; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// showData() is the method called by the View.OnClickListener()
	// defined in the xml in this case
	public void showData(View view) {
		// get the references to the view widgets
		// no reference to the header TextView, I don't manipulate it
		et = (EditText) findViewById(R.id.input);
		// get the data input
		str = et.getText().toString();

		msgHeader = (TextView) findViewById(R.id.resultheader);
		msgHeader.setVisibility(TextView.VISIBLE);

		msg = (TextView) findViewById(R.id.result);
		msg.setVisibility(TextView.VISIBLE);
		msg.setText(str);

		// need minimum api 9 for str.isEmpty() :(
		if (((String) str).isEmpty()) {
			msgHeader.setText(R.string.emptymsg);
			Log.w(TAG, "showData(): no data");
		} else {
			if (str.equals(SECRET)) {
				msgHeader.setText(R.string.successmsg);
				Log.w(TAG, "showData(): guessed correctly");
			} else {
				msgHeader.setText(R.string.tryagainmsg);
				Log.w(TAG, "showData(): bad guess");
			}
		}
	} // showData()

	public void clearData(View view) {
		if (msgHeader != null ) {
			msgHeader.setVisibility(TextView.INVISIBLE);
			msg.setVisibility(TextView.INVISIBLE);
			et.setText("");
			Log.w(TAG, "clearData(): reset fields");
		}
		Log.w(TAG, "clearData(): fields not set yet");
	} // clearData()
	
	public void showActivity(View view) {
		Log.d(TAG, "showActivity(): fire intent");
		Intent launchOtherScreen = new Intent(getApplicationContext(),
				Activity2.class);
		startActivity(launchOtherScreen);
	} // showActivity()

	public void showActivityLL(View view) {
		Log.d(TAG, "showActivityLL(): fire intent");
		Intent launchOtherActivity = new Intent(getApplicationContext(),
				WeightLinearLayout.class);
		startActivity(launchOtherActivity);
	} // showActivity()
}

