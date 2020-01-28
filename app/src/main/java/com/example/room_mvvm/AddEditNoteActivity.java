package com.example.room_mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {
	NumberPicker numberPicker;
	private EditText title, des;
	public static final String EXTRA_ID = "com.example.room_mvvm.EXTRA_ID";
	public static final String EXTRA_TITLE = "com.example.room_mvvm.EXTRA_TITLE";
	public static final String EXTRA_DESCRIPTION = "com.example.room_mvvm.EXTRA_DESCRIPTION";
	public static final String EXTRA_PRIORITY = "com.example.room_mvvm.EXTRA_PRIORITY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);


		title = findViewById(R.id.edt_title);
		des = findViewById(R.id.edt_description);
		numberPicker = findViewById(R.id.numberPicker);

		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

		Intent intent = getIntent();
		if (intent.hasExtra(EXTRA_ID)) {
			getSupportActionBar().setTitle("Edit  Note");
			title.setText(intent.getStringExtra(EXTRA_TITLE));
			des.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
			numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));

		} else {

			getSupportActionBar().setTitle("Add Note");

		}
		numberPicker.setMinValue(1);
		numberPicker.setMaxValue(10);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.add_note_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case (R.id.saveicon):

				saveNote();
				return true;
			default:
				return super.onOptionsItemSelected(item);

		}
	}

	private void saveNote() {
		String editTitle = title.getText().toString();
		String editDes = des.getText().toString();
		int prio = numberPicker.getValue();

		if (editTitle.trim().isEmpty() || editDes.trim().isEmpty()) {
			Toast.makeText(this, "Please add title or description", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent data = new Intent();
		data.putExtra(EXTRA_TITLE, editTitle);
		data.putExtra(EXTRA_DESCRIPTION, editDes);
		data.putExtra(EXTRA_PRIORITY, prio);

		int id = getIntent().getIntExtra(EXTRA_ID, -1);
		if (id != -1) {
			data.putExtra(EXTRA_ID, id);

		}
		setResult(RESULT_OK, data);
		finish();


	}
}
