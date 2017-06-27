package altarix.ru.smartnotes.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import altarix.ru.smartnotes.R;
import altarix.ru.smartnotes.fragment.ListNotesFragment;
import altarix.ru.smartnotes.model.Notes;

public class MainActivity extends AppCompatActivity {

    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentById(R.id.activity_list_notes);

        fragmentManager.beginTransaction().add(R.id.activity_list_notes, new ListNotesFragment()).commit();



    }
}
