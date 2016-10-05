package nl.fa5t.test.app.Agenda;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.fa5t.test.app.Model.Entity.Agenda;
import nl.fa5t.test.app.R;

/**
 * An activity representing a single Agenda detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link AgendaListActivity}.
 */
public class AgendaDetailActivity extends AppCompatActivity {


    protected Agenda agenda = null;

    @BindView(R.id.backdrop)
    ImageView backdropImg;

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;


    @OnClick(R.id.fab)
    public void onButtonClick(View view) {

        Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @OnClick(R.id.backdrop)
    public void onBackdropClick(View view) {
        if (agenda != null) {


            SimpleTarget target = new SimpleTarget() {
                @Override
                public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
                    Intent intent = new Intent(AgendaDetailActivity.this, FullscreenActivity.class);
                    intent.putExtra("agendaObject", agenda);
                    Bundle bundle = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions
                                .makeSceneTransitionAnimation(AgendaDetailActivity.this, backdropImg, "imageTrans");
                        bundle = options.toBundle();
                        startActivity(intent, bundle);
                    } else {
                        startActivity(intent);
                    }
                }
            };

            Glide.with(this).load("https://in-finity.nl/files/agenda/photo/" + agenda.photo_dir + "/big_" + agenda.photo).into(target);

        }

    }


    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_detail);
        ButterKnife.bind(this);
        System.out.println(toolbar);
        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(AgendaDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(AgendaDetailFragment.ARG_ITEM_ID, 0));
            AgendaDetailFragment fragment = new AgendaDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.agenda_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, AgendaListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
