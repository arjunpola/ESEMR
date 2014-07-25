package redex.mit.lvpei.eyesmart_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
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
    String[] states,dists,mandals;
    LoginDataBaseAdapter adapter;
    ArrayAdapter<String> adapter1,adapter2,adapter3;
    HashMap<String,Integer> stateCode,distMap;
    HashMap<String,String>pincode;
    HashMap<Integer,List<String>> distCode;
    HashMap<Integer,List<JSONArray>> mandalsMap;
    JSONArray jstates,jdistricts,jmandals;
    AutoCompleteTextView location;
    EditText pinBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        stateCode = new HashMap<String,Integer>();
        distMap = new HashMap<String, Integer>();
        distCode = new HashMap<Integer, List<String>>();
        mandalsMap = new HashMap<Integer, List<JSONArray>>();
        pincode = new HashMap<String,String>();


        adapter = new LoginDataBaseAdapter(getApplicationContext());
        adapter.open();

        location = (AutoCompleteTextView)findViewById(R.id.location);
        pinBox = (EditText) findViewById(R.id.pincode);

        //Following is used to load the states from json file. It maintains 2 variables of the states. One is the 'states' -
        //a string array that is used for dropdown in sorted order and the other is 'stateCode' which is HashMap with String Key and
        // mapping to an Integer value. The integer represents the state code which is then used to lookup for districts.

        try {
              JSONObject  obj = new JSONObject(loadJSONFromAsset("states.json"));
              jstates = obj.getJSONArray("states");

               states = new String[jstates.length()];
               for(int i=0;i< jstates.length();i++) {
                stateCode.put(jstates.getJSONArray(i).getString(2),jstates.getJSONArray(i).getInt(0));
                states[i] = jstates.getJSONArray(i).getString(2);
               }
               Arrays.sort(states);

               Log.v("State",states[0]);

         //Following is used to load districs from json file. It maintains 3 variables of districts. One is 'dists' - a string array
         //that is used for dropdown in sorted order. 'distCode' is an HashMap that has Integer key which represents the state code and
         //a List of string that stores districs of corresponding state. 'distMap' is an HashMap with String Key and an Integer value that
         //is used to get the district code from the name of the district.

           obj = new JSONObject(loadJSONFromAsset("dist.json"));
           jdistricts = obj.getJSONArray("dist");
           for(int i=0;i<jdistricts.length();i++)
           {
               distMap.put(jdistricts.getJSONArray(i).getString(2),jdistricts.getJSONArray(i).getInt(0));
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

            //The following is used to load mandals from json file. It maintains two variables. 'mandalsMap' is a HashMap with an Integer Key
            //that represents the distric code and JSONArray value that stores json information of the mandals in that district. 'mandals' is
            // a string arry that is used as the datasource for Location field which provides AutoComplete feature.

            obj = new JSONObject(loadJSONFromAsset("mandals.json"));
            jmandals = obj.getJSONArray("mandals");
            for(int i=0;i<jmandals.length();i++)
            {
                JSONArray ja = jmandals.getJSONArray(i);
                if(mandalsMap.get(ja.getInt(1)) == null)
                {
                    List<JSONArray> jal = new ArrayList<JSONArray>();
                    jal.add(ja);
                    mandalsMap.put(ja.getInt(1),jal);
                }
                else {
                    mandalsMap.get(ja.getInt(1)).add(ja);
                }
            }

//            Log.v("Dist",mandals.get(1).toString());

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

                String s = adapter2.getItem(position);
                //Toast.makeText(getBaseContext(), distMap.get(s).toString(), Toast.LENGTH_LONG).show();
                List<JSONArray> mds = mandalsMap.get(distMap.get(s));
//                Log.v("Mandals",mds.toString());
                mandals = new String[mds.size()];
                for (int i=0;i<mds.size();i++) {
                    try {
                        mandals[i] = mds.get(i).getString(2);
                        pincode.put(mds.get(i).getString(2),mds.get(i).getString(3));
                    } catch (JSONException e) {

                    }
                }
                Log.v("MANDALS",mandals[0]+" "+pincode.get(mandals[0]));

                adapter3 = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_dropdown_item_1line,mandals);
                location.setAdapter(adapter3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Toast.makeText(getBaseContext(),adapter3.getItem(position), Toast.LENGTH_LONG).show();
                pinBox.setText(pincode.get(adapter3.getItem(position)));
                pinBox.setClickable(false);
            }
        });

        location.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                AutoCompleteTextView aTV = (AutoCompleteTextView)v;
                if(aTV.getText().equals(""))
                    Toast.makeText(getBaseContext(),"NULL",Toast.LENGTH_LONG);

                return true;
            }
        });


    }

    //This class is used to read the json file from /src/assets folder.
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
