package com.jike.counttime;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    private EditText inputtext ;
    private Button gettime;
    private TextView time;
    private Button starttimer;
    private Button stoptimer;
    private int i =0;
    private Timer timer = null;
    private TimerTask task = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		inputtext = (EditText) findViewById(R.id.inputtext);
		gettime =  (Button) findViewById(R.id.gettime);
		time =  (TextView) findViewById(R.id.time);
		starttimer =  (Button) findViewById(R.id.starttimer);
		stoptimer =  (Button) findViewById(R.id.stoptimer);
		gettime.setOnClickListener(this);
		starttimer.setOnClickListener(this);
		stoptimer.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.gettime:
			time.setText(inputtext.getText().toString());
			i = Integer.parseInt((String) time.getText());
			break;
		case R.id.starttimer:
			starttimer();  
			break;
		case R.id.stoptimer:
			stoptimer();
		    break;
			
		}
	}

	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			time.setText(msg.arg1+"");
			starttimer();
		}
		
	};
	private void stoptimer() {
		// TODO Auto-generated method stub
		timer.cancel();
	}

	private void starttimer() {
		// TODO Auto-generated method stub
		timer = new Timer();
		task = new TimerTask(){

			@Override
			public void run() {
				i--;
				Message message = mHandler.obtainMessage();
				message.arg1 = i;
				mHandler.sendMessage(message);
			}

		};
		timer.schedule(task, 1000);
	}

	

	
}
