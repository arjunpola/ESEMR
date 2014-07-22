package redex.mit.lvpei.eyesmart_v1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by arjunpola on 17/07/14.
 */

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getActionBar();
        ab.hide();

        setContentView(R.layout.home);

    }

    public void register(View v)
    {
        Intent i = new Intent(this,Register.class);
        startActivity(i);
    }

    public void viewPatients(View v)
    {
        Intent i = new Intent(this,ViewPatients.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.view_patients:
                Intent i = new Intent(this,ViewPatients.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
