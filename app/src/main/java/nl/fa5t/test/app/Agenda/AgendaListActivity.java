package nl.fa5t.test.app.Agenda;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import nl.fa5t.test.app.BaseAppCompatActivity;
import nl.fa5t.test.app.Model.Entity.Agenda;
import nl.fa5t.test.app.Model.Table.AgendasTable;
import nl.fa5t.test.app.R;

/**
 * An activity representing a list of Agendas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link AgendaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class AgendaListActivity extends BaseAppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    protected ArrayList<Agenda> agendas;
    private class LoadAgendaTask extends AsyncTask<String, Void, ArrayList<Agenda>> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected ArrayList<Agenda> doInBackground(String... urls) {
            AgendasTable agendasTable = new AgendasTable();
            return agendasTable.getAll(Agenda.class);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(ArrayList<Agenda> result) {
            agendas = result;

            View recyclerView = findViewById(R.id.agenda_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView);

            if (findViewById(R.id.agenda_detail_container) != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true;
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_list);
        navigationStart();
        new LoadAgendaTask().execute();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new AgendaRecyclerViewAdapter(agendas));
    }

    public class AgendaRecyclerViewAdapter
            extends RecyclerView.Adapter<AgendaRecyclerViewAdapter.ViewHolder> {

        private final ArrayList<Agenda> mValues;

        public AgendaRecyclerViewAdapter(ArrayList<Agenda> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.agenda_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            System.out.println(holder.mItem.id);
            holder.mtitleView.setText(mValues.get(position).party.title);

            String price = String.format( "%.2f", mValues.get(position).price );
            String price_height = String.format( "%.2f", mValues.get(position).price_height );
            if (!price.equals("0.00")) {
                if (!price_height.equals("0.00")) {
                    price = "€" + price + " - €" + price_height;
                } else {
                    price = "€" + price;
                }

            } else {
                price = "Gratis";
            }
            holder.mPriceView.setText(price);

            SimpleDateFormat format = new SimpleDateFormat("EEEE d MMMM");
            String dateString = format.format(mValues.get(position).date);
            holder.mdateView.setText(dateString);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(AgendaDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        AgendaDetailFragment fragment = new AgendaDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.agenda_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, AgendaDetailActivity.class);
                        intent.putExtra(AgendaDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mPriceView;
            public final TextView mtitleView;
            public final TextView mdateView;
            public Agenda mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mPriceView = (TextView) view.findViewById(R.id.price);
                mtitleView = (TextView) view.findViewById(R.id.title);
                mdateView = (TextView) view.findViewById(R.id.date);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mtitleView.getText() + "'";
            }
        }
    }
}
