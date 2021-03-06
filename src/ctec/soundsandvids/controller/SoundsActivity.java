package ctec.soundsandvids.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class SoundsActivity extends Activity implements Runnable
{
	private Button startButt;
	private Button pauseButt;
	private Button stopButt;
	private Button vidButt;
	private MediaPlayer soundPlayer;
	private SeekBar soundSeekBar;
	private Thread soundThread;
	private ArrayList<Integer> songList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sounds);
		
		startButt = (Button) findViewById(R.id.playButton);
		pauseButt = (Button) findViewById(R.id.pauseButton);
		stopButt = (Button) findViewById(R.id.stopButton);
		vidButt = (Button) findViewById(R.id.videoButton);
		soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
		soundPlayer = MediaPlayer.create(this.getBaseContext(), (int) (Math.random() * songList.size()));
		songList = new ArrayList<Integer>();
		
		playSongs();
		setupListeners();
		
		soundThread = new Thread(this);
		soundThread.start();
		
	}
	
	private void playSongs()
	{
		songList.add(R.raw.spirit_tracks);
		songList.add(R.raw.aiur_child);
		songList.add(R.raw.mega_man);
		
	}

	private void setupListeners()
	{
		startButt.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				soundPlayer.start();
				
			}
		});
		
		pauseButt.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				soundPlayer.pause();
				
			}
		});
		
		stopButt.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				soundPlayer.stop();
				soundPlayer = MediaPlayer.create(getBaseContext(), (int) (Math.random() * songList.size()));
				
			}
		});
		
		vidButt.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View currentView)
			{
				Intent myIntent = new Intent(currentView.getContext(), VideoActivity.class);
				startActivityForResult(myIntent, 0);
				
			}
		});
		
		soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if (fromUser)
				{
					soundPlayer.seekTo(progress);
				}
				
			}
		});
	}
	
	@Override
	public void run()
	{
		int currentPosition = 0;
		int soundTotal = soundPlayer.getDuration();
		
		soundSeekBar.setMax(soundTotal);
		
		while (soundPlayer != null && currentPosition < soundTotal)
		{
			try
			{
				Thread.sleep(50);
				currentPosition = soundPlayer.getCurrentPosition();
			}
			catch (InterruptedException soundException)
			{
				return;
			}
			catch (Exception otherException)
			{
				return;
			}
			
			soundSeekBar.setProgress(currentPosition);
		}
		
	}
}
