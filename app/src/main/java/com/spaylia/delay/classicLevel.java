package com.spaylia.delay;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class classicLevel extends AppCompatActivity {

	private classicMap cm;
	private Player p;

	private ImageView[][] iv;
	private int nbLevel;

	private ImageButton ibUp, ibDown, ibRight, ibLeft;

	private String value;

	private GridLayout gl;

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classic_level);

		value = getIntent().getExtras().getString("Difficulty");

		ibUp = (ImageButton)findViewById(R.id.imageButtonUp);
		ibDown = (ImageButton)findViewById(R.id.imageButtonDown);
		ibRight = (ImageButton)findViewById(R.id.imageButtonRight);
		ibLeft = (ImageButton)findViewById(R.id.imageButtonLeft);

		gl = (GridLayout)findViewById(R.id.levelGridLayout);

		tv = (TextView)findViewById(R.id.textViewScore);

		nbLevel = 1;
		cm = new classicMap();
		init();
	}

	public void init() {
		gl.removeAllViews();

		String sLanguage = "";
		if(Locale.getDefault().getLanguage().equals("fr")) {
			sLanguage = "Niveau ";
		} else {
			sLanguage = "Level ";
		}
		tv.setText(sLanguage + nbLevel);
		tv.setTextColor(Color.BLACK);
		tv.setTextSize((float)30.0);

		if(nbLevel != 1)
			cm.setMap(nbLevel);
		iv = new ImageView[cm.getNbRow()][cm.getNbColumn()];

		gl.setRowCount(cm.getNbRow());
		gl.setColumnCount(cm.getNbColumn());

		for (int i = 0; i < cm.getNbRow(); i++)
			for (int ii = 0; ii < cm.getNbColumn(); ii++) {

				iv[i][ii] = new ImageView(this);
				iv[i][ii].setImageResource(R.drawable.path);

				if(cm.getMap()[i][ii] == 1) {
					iv[i][ii] = new ImageView(this);
					iv[i][ii].setImageResource(R.drawable.wall);
				} else if(cm.getMap()[i][ii] == 2) {
					p = new Player(i, ii, this, cm);
					iv[i][ii] = new ImageView(this);
					iv[i][ii].setImageResource(R.drawable.player);
				} else if(cm.getMap()[i][ii] == 3) {
					iv[i][ii] = new ImageView(this);
					iv[i][ii].setImageResource(R.drawable.stop);
				}
				gl.addView(iv[i][ii]);
			}

		RelativeLayout.LayoutParams overviewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		overviewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		gl.setLayoutParams(overviewParams);
	}

	public void onClickDifficulty(View v) {
		switch(value) {
			case "0" : p.move(v, 1000, 0);
				break;
			case "1" : p.move(v, 250, 1);
				break;
			case "2" : p.move(v, 500, 2);
				break;
			case "3" : p.move(v, 250, 3);
				break;
		}
	}

	public void loose() {
		String sLanguage = "";
		if(Locale.getDefault().getLanguage().equals("fr")) {
			sLanguage = "Perdu :(";
		} else {
			sLanguage = "Lose :(";
		}

		Toast.makeText(this, sLanguage, Toast.LENGTH_SHORT).show();
		p.setFirstTime(true);
		p.setTDelta(0);

		for (int i = 0; i < cm.getNbRow(); i++)
			for (int ii = 0; ii < cm.getNbColumn(); ii++) {
				if (cm.getMap()[i][ii] == 2) {
					iv[p.getX()][p.getY()].setImageResource(R.drawable.path);
					p.setX(i);
					p.setY(ii);
					iv[i][ii].setImageResource(R.drawable.player);
				}
			}
	}

	public void win() {
		p.setMoveable(false);

		String sLanguage = "";
		if(Locale.getDefault().getLanguage().equals("fr")) {
			sLanguage = "Gagné !";
		} else {
			sLanguage = "Win !";
		}

		Toast.makeText(this, sLanguage, Toast.LENGTH_SHORT).show();
		p.setFirstTime(true);
		p.setTDelta(0);
		nbLevel++;

		for (int i = 0; i < cm.getNbRow(); i++)
			for (int ii = 0; ii < cm.getNbColumn(); ii++) {
				if (cm.getMap()[i][ii] == 2) {
					iv[p.getX()][p.getY()].setImageResource(R.drawable.path);
					p.setX(i);
					p.setY(ii);
					iv[i][ii].setImageResource(R.drawable.player);
				}
			}

		init();
		p.setMoveable(true);
	}

	public ImageButton getIbUp() {
		return ibUp;
	}

	public ImageButton getIbDown() {
		return ibDown;
	}

	public ImageButton getIbLeft() {
		return ibLeft;
	}

	public ImageButton getIbRight() {
		return ibRight;
	}

	public ImageView getIv(int x, int y) {
		return iv[x][y];
	}
}