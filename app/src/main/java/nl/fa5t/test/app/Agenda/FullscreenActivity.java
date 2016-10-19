package nl.fa5t.test.app.Agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.fa5t.test.app.Model.Agenda;
import nl.fa5t.test.app.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    protected ImageView mContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Agenda agenda = intent.getParcelableExtra("agendaObject");

        String url = "https://in-finity.nl/files/agenda/photo/" + agenda.photo_dir + "/big_" + agenda.photo;
        Glide.with(this).load(url).into(mContentView);

        // Set up the user interaction to manually show or hide the sy     }););
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
