package com.spaylia.delay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Locale;

public class MainMenu extends AppCompatActivity {

	private Spinner s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		s = (Spinner) findViewById(R.id.spinnerDifficulty);

		ArrayList<String> al = new ArrayList<>();

		if(Locale.getDefault().getLanguage().equals("fr")) {
			al.add("Facile");
			al.add("Normal");
			al.add("Difficile");
			al.add("Impossible");
		} else {
			al.add("Easy");
			al.add("Normal");
			al.add("Hard");
			al.add("Insane");
		}

		ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, al);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(aa);
	}

	public void onClickPlayClassic(View v) {
		Intent i = new Intent(this, classicLevel.class);
		if(s.getSelectedItem().toString().equals("Facile") || s.getSelectedItem().toString().equals("Easy"))
			i.putExtra("Difficulty", "0");
		else if(s.getSelectedItem().toString().equals("Normal") || s.getSelectedItem().toString().equals("Normal"))
			i.putExtra("Difficulty", "1");
		else if(s.getSelectedItem().toString().equals("Difficile") || s.getSelectedItem().toString().equals("Hard"))
			i.putExtra("Difficulty", "2");
		else if(s.getSelectedItem().toString().equals("Impossible") || s.getSelectedItem().toString().equals("Insane"))
			i.putExtra("Difficulty", "3");
		startActivity(i);
	}
}