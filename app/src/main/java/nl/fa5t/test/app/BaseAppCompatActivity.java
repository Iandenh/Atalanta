package nl.fa5t.test.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import nl.fa5t.test.app.Agenda.AgendaListActivity;
import nl.fa5t.test.app.Gallery.GalleryActivity;

/**
 * Created by ian on 5-10-16.
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    protected void navigationStart() {
        // Initializing Toolbar and setting it as the actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                //drawerLayout.closeDrawers();
                Intent intent;

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    case R.id.nav_agenda:
                        intent = new Intent(BaseAppCompatActivity.this, AgendaListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_photo:
                        intent = new Intent(BaseAppCompatActivity.this, GalleryActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_settings:
                        return true;
                    default:
                        return true;

                }
            }
        });

    }
}
