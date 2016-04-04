package com.example.musicplayerbasic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private Button play, stop, pause;
	private SeekBar seekbar;
	private MediaPlayer player;
	private int duration;
	
	Handler handler = new Handler();
	Runnable run = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(player!=null && player.isPlaying())
			{
				seekbar.setProgress(player.getCurrentPosition());
			}
			handler.postDelayed(this, 1000);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play = (Button)findViewById(R.id.play);
		pause = (Button)findViewById(R.id.pause);
		stop = (Button)findViewById(R.id.stop);
		seekbar = (SeekBar)findViewById(R.id.seekBar1);
		player = MediaPlayer.create(this, R.raw.abc);
		duration = player.getDuration();
		seekbar.setMax(duration);
		
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(player!=null && !player.isPlaying())
				{
					if(seekbar.getProgress() == 0)
					{
					//play.setEnabled(false);//greyed out effect
					player.start();
					handler.postDelayed(run, 1000);
					}
					else
					{
						player.seekTo(seekbar.getProgress());
						player.start();
						handler.postDelayed(run, 1000);
					}
				}

			}
		});
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(player!=null && player.isPlaying())
				{
					player.stop();
					player.reset();
					player.release();
					player = MediaPlayer.create(MainActivity.this, R.raw.abc);
					seekbar.setProgress(0);
				}
			}
		});
		pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(player!=null && player.isPlaying())
				{
					player.pause();
					handler.postDelayed(run, 100);
				}
			}
		});
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekbar, int duration, boolean isUser) {
				// TODO Auto-generated method stub
				if(isUser && player!=null && player.isPlaying())
				{
					player.seekTo(duration);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
