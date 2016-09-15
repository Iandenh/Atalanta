package nl.fa5t.test.app.Agenda;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.Bind;
import nl.fa5t.test.app.Helper;
import nl.fa5t.test.app.Model.Entity.Agenda;
import nl.fa5t.test.app.Model.Table.AgendasTable;
import nl.fa5t.test.app.R;
import nl.fa5t.test.app.Agenda.dummy.DummyContent;

/**
 * A fragment representing a single Agenda detail screen.
 * This fragment is either contained in a {@link AgendaListActivity}
 * in two-pane mode (on tablets) or a {@link AgendaDetailActivity}
 * on handsets.
 */
public class AgendaDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Agenda mItem;
    protected View rootView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AgendaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            new LoadAgendaTask(this.getActivity()).execute(getArguments().getInt(ARG_ITEM_ID));


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.agenda_detail, container, false);

        return rootView;
    }

    private class LoadAgendaTask extends AsyncTask<Integer, Void, Agenda> {
        private Activity activity;
        public LoadAgendaTask(Activity activity) {
            this.activity = activity;
        }

        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected Agenda doInBackground(Integer... id) {
            AgendasTable agendasTable = new AgendasTable();
            return agendasTable.get(id[0], Agenda.class);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(Agenda result) {

            mItem = result;
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.party.title);
            }
            ((TextView) rootView.findViewById(R.id.desc)).setText(mItem.desc);
            ((TextView) rootView.findViewById(R.id.body)).setText(mItem.body);
        }
    }
}
