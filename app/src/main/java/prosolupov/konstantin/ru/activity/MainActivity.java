package prosolupov.konstantin.ru.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import prosolupov.konstantin.ru.R;
import prosolupov.konstantin.ru.fragment.ListNotesFragment;
import prosolupov.konstantin.ru.fragment.ListNotesMoreFragment;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private Button createButton;
    private Toolbar myToolbar;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentById(R.id.activity_list_notes);

        fragmentManager.beginTransaction().add(R.id.activity_list_notes, new ListNotesFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_notes:
/*                Notes notes = new Notes(45, "add Notes", "Body Notes");*/
                fragmentManager.beginTransaction()
                        .replace(R.id.activity_list_notes, new ListNotesMoreFragment()).addToBackStack("main").commit();
                /*new NotesAdapter().addItemsAdapter(notes);*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
