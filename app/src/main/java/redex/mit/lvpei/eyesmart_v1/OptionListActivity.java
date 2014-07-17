package redex.mit.lvpei.eyesmart_v1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * An activity representing a list of Options. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link OptionDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link OptionListFragment} and the item details
 * (if present) is a {@link OptionDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link OptionListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class OptionListActivity extends Activity
        implements OptionListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    RegisterFormFragment register_fragment;
    PatientListFragment patient_list_fragment;
    public List<Patient> patients;
    LoginDataBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_list);
       adapter = new LoginDataBaseAdapter(getApplicationContext());
        adapter.open();
        patients = adapter.GetAllPatient();

        if (findViewById(R.id.option_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((OptionListFragment) getFragmentManager()
                    .findFragmentById(R.id.option_list))
                    .setActivateOnItemClick(true);
        }

    }

    /**
     * Callback method from {@link OptionListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
//            Toast.makeText(this,id,Toast.LENGTH_LONG).show();
            int fItem = Integer.parseInt(id);
            Bundle arguments = new Bundle();

            switch (fItem)
            {
                case 1:
                    register_fragment = new RegisterFormFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.option_detail_container, register_fragment)
                            .commit();
                    break;
                case 2:
                     patient_list_fragment = new PatientListFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.option_detail_container,patient_list_fragment)
                            .commit();
                    break;
                case 3:
                    arguments.putString(OptionDetailFragment.ARG_ITEM_ID, id);
                    OptionDetailFragment fragment1 = new OptionDetailFragment();
                    fragment1.setArguments(arguments);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.option_detail_container, fragment1)
                            .commit();
                    break;
            }


        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, OptionDetailActivity.class);
            detailIntent.putExtra(OptionDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    //Register Patient Handler
    public void registerPatient(View v)
    {

      Patient pObj = new Patient();
//      EditText editText = (EditText)register_fragment.getView().findViewById(R.id.firstName);
//      RadioGroup radioSexGroup = (RadioGroup) register_fragment.getView().findViewById(R.id.gender);
//
//    int selecteditem = radioSexGroup.getCheckedRadioButtonId();
//    RadioButton button =(RadioButton) register_fragment.getView().findViewById(selecteditem);


        pObj.setfName(((EditText)register_fragment.getView().findViewById(R.id.firstName)).getText().toString());
        pObj.setlName(((EditText) register_fragment.getView().findViewById(R.id.lastname)).getText().toString());

       pObj.setDob(((EditText) register_fragment.getView().findViewById(R.id.dob)).getText().toString());
      pObj.setEmail(((EditText) register_fragment.getView().findViewById(R.id.email)).getText().toString());
       pObj.setPhone(((EditText) register_fragment.getView().findViewById(R.id.phone)).getText().toString());
       pObj.setCountry(((EditText) register_fragment.getView().findViewById(R.id.country)).getText().toString());
       pObj.setState(((Spinner) register_fragment.getView().findViewById(R.id.state_list)).getSelectedItem().toString());
        pObj.setDist(((Spinner) register_fragment.getView().findViewById(R.id.dist_list)).getSelectedItem().toString());

       pObj.setPin(((EditText)register_fragment.getView().findViewById(R.id.pincode)).getText().toString());
       pObj.setAddress(((EditText) register_fragment.getView().findViewById(R.id.add)).getText().toString());
       pObj.setReferal(((EditText) register_fragment.getView().findViewById(R.id.ref_doc)).getText().toString());
        pObj.setRefAddress(((EditText) register_fragment.getView().findViewById(R.id.ref_doc_address)).getText().toString());
        pObj.setVisionTech(((EditText) register_fragment.getView().findViewById(R.id.vision_tech)).getText().toString());
       //pObj.setGender("MALE");

//        DateFormat format = new SimpleDateFormat("DD-MM-YY HH:MM:SS");
//        String st = format.format(new Date(0));

        pObj.setCheckinTime("01-10-2014 10:10:10");

       long entries= adapter.InsertPatientEntry(pObj);


       //Toast.makeText(this,"Time "+ st ,Toast.LENGTH_LONG).show();
    }
       // Log.v("Script",str);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trial,menu);
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
        }
        return super.onOptionsItemSelected(item);
    }
}
