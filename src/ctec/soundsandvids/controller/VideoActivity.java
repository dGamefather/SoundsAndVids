package ctec.soundsandvids.controller;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity
{
	private VideoView myPlayer;
	private Button returnButt;
	private MediaController myVideoControl;
	private Uri videoLocate;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		myPlayer = (VideoView) findViewById(R.id.videoView);
		returnButt = (Button) findViewById(R.id.homeButton);
		videoLocate = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sawyer_annoy);
		myVideoControl = new MediaController(this);
		//Prepare the video
		setupMedia();
		setupListeners();	
	}
	
	private void setupMedia()
	{
		myPlayer.setMediaController(myVideoControl);
		myPlayer.setVideoURI(videoLocate);
	}
	
	private void setupListeners()
	{
		returnButt.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View currentView)
			{
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
		
	}
}
