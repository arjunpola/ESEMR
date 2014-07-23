package redex.mit.lvpei.eyesmart_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arjunpola on 17/07/14.
 */
public class Register extends Activity {

    public static final String ARG_ITEM_ID = "item_id";

    Spinner l1,l2;
    String[] states,dists;
    LoginDataBaseAdapter adapter;
    ArrayAdapter<String> adapter1,adapter2;
    HashMap<String,Integer> stateCode,pinCode;
    HashMap<Integer,List<String>> distCode;
    JSONArray jstates,jdistricts,jpincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        stateCode = new HashMap<String,Integer>();
        distCode = new HashMap<Integer, List<String>>();

        adapter = new LoginDataBaseAdapter(getApplicationContext());
        adapter.open();

//        initLists();
//        states = getResources().getStringArray(R.array.states_list);
//        Arrays.sort(states);

        try {
              JSONObject  obj = new JSONObject(loadJSONFromAsset("states.json"));
              jstates = obj.getJSONArray("states");
//
               states = new String[jstates.length()];
               for(int i=0;i< jstates.length();i++) {
                stateCode.put(jstates.getJSONArray(i).getString(2),jstates.getJSONArray(i).getInt(0));
                states[i] = jstates.getJSONArray(i).getString(2);
               }
               Arrays.sort(states);
               Log.v("State",states[0]);

           obj = new JSONObject(loadJSONFromAsset("dist.json"));
           jdistricts = obj.getJSONArray("dist");
           for(int i=0;i<jdistricts.length();i++)
           {
               if(distCode.get(jdistricts.getJSONArray(i).getInt(1)) == null)
               {
                   List<String> d = new ArrayList<String>();
                   d.add(jdistricts.getJSONArray(i).getString(2));
                   distCode.put(jdistricts.getJSONArray(i).getInt(1),d);

               }
               else{
                   List<String> d = distCode.get(jdistricts.getJSONArray(i).getInt(1));
                   if(d != null)
                       d.add(jdistricts.getJSONArray(i).getString(2));
               }
           }

            obj = new JSONObject(loadJSONFromAsset("mandals.json"));

            Log.v("Dist",distCode.get(1).get(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,states);

        l1 = (Spinner) findViewById(R.id.state_list);
        l2 = (Spinner) findViewById(R.id.dist_list);

        l1.setAdapter(adapter1);

        l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s = adapter1.getItem(position);
                Toast.makeText(getBaseContext(), stateCode.get(s).toString(), Toast.LENGTH_LONG).show();
                List<String> d = distCode.get(stateCode.get(s));
                dists = d.toArray(new String[d.size()]);
                Arrays.sort(dists);
                adapter2 = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,dists);
                l2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                String s = adapter2.getItem(position);
//                Toast.makeText(getBaseContext(), stateCode.get(s).toString(), Toast.LENGTH_LONG).show();
//                List<String> d = distCode.get(stateCode.get(s));
//                dists = d.toArray(new String[d.size()]);
//                Arrays.sort(dists);
//                adapter2 = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,dists);
//                l2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void initLists()
    {
//        clookup = new HashMap<String, Integer>();
//        clookup.put("Andhra_Pradesh", R.array.Andhra_Pradesh);
//        clookup.put("Karnataka",R.array.Karnataka);
    }

    public String loadJSONFromAsset(String file) {
        String json = null;
        try {

            InputStream is = getAssets().open(file);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

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

   public void registerPatient(View v)
   {
       Intent i = new Intent(this,ViewPatients.class);
       startActivity(i);
   }
}
