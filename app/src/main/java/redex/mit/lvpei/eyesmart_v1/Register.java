package redex.mit.lvpei.eyesmart_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by arjunpola on 17/07/14.
 */
public class Register extends Activity {

    public static final String ARG_ITEM_ID = "item_id";

    Spinner l1,l2;
    String[] states,dists;
    ArrayAdapter<String> adapter1,adapter2;
    HashMap<String,Integer> clookup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        initLists();
        states = getResources().getStringArray(R.array.states_list);
        Arrays.sort(states);

        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,states);

        l1 = (Spinner) findViewById(R.id.state_list);
        l2 = (Spinner) findViewById(R.id.dist_list);

        l1.setAdapter(adapter1);

        l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),adapter1.getItem(position),Toast.LENGTH_LONG).show();
                String s = adapter1.getItem(position);
                s = s.replaceAll(" ","_");
                dists = getResources().getStringArray(clookup.get(s));
                adapter2 = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,dists);
                Arrays.sort(dists);
                l2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void initLists()
    {
        clookup = new HashMap<String, Integer>();
        clookup.put("AP", R.array.AP);
        clookup.put("KA",R.array.KA);
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
            case R.id.case_sheet:
                Intent i = new Intent(this,CaseSheet.class);
                startActivity(i);
                break;

            case R.id.view_patients:
                Intent j = new Intent(this,ViewPatients.class);
                startActivity(j);
        }
        return super.onOptionsItemSelected(item);
    }
}
