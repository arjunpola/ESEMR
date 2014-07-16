package redex.mit.lvpei.eyesmart_v1;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;

import redex.mit.lvpei.eyesmart_v1.dummy.Content;

/**
 * Created by arjunpola on 15/07/14.
 */
public class RegisterFormFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RegisterFormFragment() {
    }

    Spinner l1,l2;
    String[] states;
    ArrayAdapter<String> adapter1,adapter2;
    HashMap<String,Integer> clookup;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity a = getActivity();
        if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        states = getResources().getStringArray(R.array.states_list);
        Arrays.sort(states);


//        String[] locations = getResources().getStringArray(R.array.locationList);
//        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,locations);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_form, container, false);

        initLists();

        adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,states);

        l1 = (Spinner) rootView.findViewById(R.id.state_list);
        l2 = (Spinner) rootView.findViewById(R.id.dist_list);

        l1.setAdapter(adapter1);

        l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),adapter1.getItem(position),Toast.LENGTH_LONG).show();
                String s = adapter1.getItem(position);
                s = s.replaceAll(" ","_");
                String[] dists = getResources().getStringArray(clookup.get(s));
                Arrays.sort(dists);
                adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,dists);
                l2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }

    public void initLists()
    {
        clookup = new HashMap<String, Integer>();
        clookup.put("AP", R.array.AP);
        clookup.put("KA",R.array.KA);
    }
}
