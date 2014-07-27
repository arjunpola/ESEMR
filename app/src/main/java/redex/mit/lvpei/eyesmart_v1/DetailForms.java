package redex.mit.lvpei.eyesmart_v1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import redex.mit.lvpei.eyesmart_v1.R;

public class DetailForms extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    PagerTabStrip pagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forms);

        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_forms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {

            switch (sectionNumber)
            {
                case 1:
                    CaseSheetFragment fragment = new CaseSheetFragment();
                    Bundle args = new Bundle();
                    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                    fragment.setArguments(args);
                    return fragment;
                case 2:
                    PlaceholderFragment fragment2 = new PlaceholderFragment();
                    Bundle args2 = new Bundle();
                    args2.putInt(ARG_SECTION_NUMBER, sectionNumber);
                    fragment2.setArguments(args2);
                    return fragment2;
                case 3:
                    PlaceholderFragment fragment3 = new PlaceholderFragment();
                    Bundle args3 = new Bundle();
                    args3.putInt(ARG_SECTION_NUMBER, sectionNumber);
                    fragment3.setArguments(args3);
                    return fragment3;
            }

            return null;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            Bundle b = getArguments();
            switch (b.getInt(ARG_SECTION_NUMBER))
            {
                case 1:
                    View rootView = inflater.inflate(R.layout.case_sheet, container, false);
                    return rootView;

                case 2:
                    View rootView2 = inflater.inflate(R.layout.new_refraction, container, false);
                    return rootView2;

                case 3:
                    View rootView3 = inflater.inflate(R.layout.new_ocular, container, false);
                    return rootView3;
            }
            View rootView = null;
            return rootView;
        }
    }

    public static class CaseSheetFragment extends Fragment{

        private Spinner complaint,eye_complaint,duration_complaint;
        private Spinner disease_past_illness,duration_past_illness;
        private Spinner relation,disease_family_history;
        private Spinner eye_sl;

        private EditText chief_complaints,present_illness,past_illness,family_history,procedure,date_sl,outcome_sl,allergies,current_treatment,personal_history,general_exam,systematic_exam;
        private RadioGroup nut_status;
        private RadioButton nut_status_checked;
        private Button save_button;
        private View rootView;

        protected void getValuesOfFields(){
            //getting the value from form fields and storing in respective variables
            //input ---
            //output - variable initialisation


            // get values for chief complaints
            complaint = (Spinner)rootView.findViewById(R.id.spinner);
            eye_complaint = (Spinner)rootView.findViewById(R.id.spinner2);
            duration_complaint = (Spinner)rootView.findViewById(R.id.spinner3);
            chief_complaints = (EditText)rootView.findViewById(R.id.edit_text);

            //get values for history of present illness
            present_illness = (EditText)rootView.findViewById(R.id.edit_text2);

            //get values of past Illness
            disease_past_illness = (Spinner)rootView.findViewById(R.id.spinner4);
            duration_past_illness = (Spinner)rootView.findViewById(R.id.spinner5);
            past_illness = (EditText)rootView.findViewById(R.id.edit_text3);

            //get values for family history
            relation = (Spinner)rootView.findViewById(R.id.spinner6);
            disease_family_history = (Spinner)rootView.findViewById(R.id.spinner7);
            family_history = (EditText)rootView.findViewById(R.id.edit_text4);

            //get values for surgeries/lasers
            procedure = (EditText)rootView.findViewById(R.id.edit_text5);
            eye_sl = (Spinner)rootView.findViewById(R.id.spinner8);
            date_sl = (EditText)rootView.findViewById(R.id.edit_text6);
            outcome_sl = (EditText)rootView.findViewById(R.id.edit_text7);

            //get values from allergies
            allergies = (EditText)rootView.findViewById(R.id.edit_text8);

            //get values from current treatment
            current_treatment = (EditText)rootView.findViewById(R.id.edit_text9);

            //get values from personal history
            personal_history = (EditText)rootView.findViewById(R.id.edit_text10);

            //get values from nutritional status
            nut_status = (RadioGroup)rootView.findViewById(R.id.radio_group);

            //get values from general examination
            general_exam = (EditText)rootView.findViewById(R.id.edit_text11);

            //get values from systematic examination
            systematic_exam = (EditText)rootView.findViewById(R.id.edit_text12);

            //Save button
            save_button = (Button)rootView.findViewById(R.id.save);
        }

        protected boolean validate_chief_complaints()
        //@author - Nagesh
        {
            int pos = complaint.getSelectedItemPosition();
            if(!(pos > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Complaint",Toast.LENGTH_LONG).show();
                return false;
            }

            pos = eye_complaint.getSelectedItemPosition();
            if(!(pos > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Eye",Toast.LENGTH_LONG).show();
                return false;
            }

            pos = duration_complaint.getSelectedItemPosition();
            if(!(pos > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Duration",Toast.LENGTH_LONG).show();
                return false;
            }

            if(chief_complaints.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter Chief Complaint Details",Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        protected  boolean validate_history_present_illness()
        //@author - Nagesh
        {
            if(present_illness.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter History of Present Illness Details",Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        protected boolean validate_history_past_illness()
        //@author - Nagesh
        {
            if(!(disease_past_illness.getSelectedItemPosition() > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Past Illness Disease",Toast.LENGTH_LONG).show();
                return false;
            }
            if(!(duration_past_illness.getSelectedItemPosition() > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Past Illness Duration",Toast.LENGTH_LONG).show();
                return false;
            }
            if(past_illness.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter History of Past Illness Details",Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        protected boolean validate_family_history()
        //@author - Nagesh
        {
            if(!(relation.getSelectedItemPosition() > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Relation",Toast.LENGTH_LONG).show();
                return false;
            }
            if(!(disease_family_history.getSelectedItemPosition() > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Family Disease",Toast.LENGTH_LONG).show();
                return false;
            }
            if(family_history.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter Family History Details",Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        protected boolean validate_date(String date1)
        //@author - Nagesh
        {
            ArrayList<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>()
            {
                {
                    add(new SimpleDateFormat("M/dd/yyyy"));
                    add(new SimpleDateFormat("dd.M.yyyy"));
                    add(new SimpleDateFormat("dd.MM.yyyy"));
                    add(new SimpleDateFormat("dd-MM-yyyy"));
                    add(new SimpleDateFormat("dd/MM/yyyy"));
                }
            };
            Date date=null;
            for (SimpleDateFormat format : dateFormats)
            {
                try
                {
                    format.setLenient(false);
                    date = format.parse(date1);
                }
                catch (ParseException e)
                {
                    Log.d("Date parse", "Date parsing Exception");
                }
                if (date != null)
                {
                    return true;
                }
            }
            return false;
        }

        protected boolean validate_surgeries()
        //@author - Nagesh
        {
            if(procedure.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter Procedure in Surgeries",Toast.LENGTH_LONG).show();
                return false;
            }
            if(!(eye_sl.getSelectedItemPosition() > 0))
            {
                Toast.makeText(getActivity().getBaseContext(),"Select Eye in Surgeries",Toast.LENGTH_LONG).show();
                return  false;
            }
            if(date_sl.getText().toString().equals("") || !validate_date(date_sl.getText().toString()))
            {
                Toast.makeText(getActivity().getBaseContext(),"Date in validate surgeries is empty or wrong",Toast.LENGTH_LONG).show();
                return false;
            }
            if((outcome_sl.getText().toString().equals("")))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter Outcome of Surgeries",Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        protected boolean validate_allergies()
        //@author - Nagesh
        {
            if (allergies.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter allergies",Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        protected boolean validate_current_treatment()
        //@author - Nagesh
        {
            if(current_treatment.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter current treatment",Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        protected boolean validate_personal_history()
        //@author - Nagesh
        {
            if(personal_history.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter Personal History",Toast.LENGTH_LONG).show();
                return  false;
            }
            return true;
        }

        protected boolean validate_nutritional_status()
        //@author - Nagesh
        {
            int sid = nut_status.getCheckedRadioButtonId();
            if( sid < 0 )
            {

                Toast.makeText(getActivity().getBaseContext(),"Select Nutritional Status",Toast.LENGTH_LONG).show();
                return  false;
            }
            return true;
        }

        protected boolean validate_general_examination()
        //@author - Nagesh
        {
            if(general_exam.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter General Examination",Toast.LENGTH_LONG).show();
                return  false;
            }
            return true;
        }

        protected boolean validate_systematic_examination()
        //@author - Nagesh
        {
            if(systematic_exam.getText().toString().equals(""))
            {
                Toast.makeText(getActivity().getBaseContext(),"Enter Systematic Examination",Toast.LENGTH_LONG).show();
                return  false;
            }
            return true;
        }

        protected boolean validate_form()
        //@author - Nagesh
        //validates the contents of form data
        //input --
        //output - returns true if form input data is correct else return false
        {
            boolean isValidate = true;

            //validate Chief Complaints
            if(!(validate_chief_complaints()))
            {
                isValidate = false;
                return isValidate;
            }

            //validate History of Present Illness
            if(!(validate_history_present_illness()))
            {
                isValidate = false;
                return isValidate;
            }

            //validate History of Past Illness
            if(!(validate_history_past_illness()))
            {
                isValidate = false;
                return isValidate;
            }

            //validate Family History
            if(!(validate_family_history()))
            {
                isValidate = false;
                return isValidate;
            }

            //validate Surgeries
            if(!(validate_surgeries()))
            {
                isValidate = false;
                return isValidate;
            }

            //validate allergies
            if(!validate_allergies())
            {
                isValidate = false;
                return isValidate;
            }

            //validate current treatment
            if(!validate_current_treatment())
            {
                isValidate = false;
                return isValidate;
            }

            //validate personal history
            if(!validate_personal_history())
            {
                isValidate = false;
                return isValidate;
            }

            //validate nutritional status
            if(!validate_nutritional_status())
            {
                isValidate = false;
                return isValidate;
            }

            //validate general examination
            if(!validate_general_examination())
            {
                isValidate = false;
                return isValidate;
            }

            //validate systematic examination
            if(!validate_systematic_examination())
            {
                isValidate = false;
                return isValidate;
            }
            return isValidate;
        }

        protected void save_to_database()
        //@author - Nagesh
        // save data to database
        {
            Toast.makeText(getActivity().getBaseContext(),"Saving to Database",Toast.LENGTH_LONG).show();
        }

        protected void clear_fields()
        //@author - Nagesh
        //clears all the fields after successful saving
        {
            complaint.setSelection(0);
            eye_complaint.setSelection(0);
            duration_complaint.setSelection(0);
            chief_complaints.setText("");

            present_illness.setText("");

            disease_past_illness.setSelection(0);
            duration_past_illness.setSelection(0);
            past_illness.setText("");

            procedure.setText("");
            eye_sl.setSelection(0);
            date_sl.setText("");
            outcome_sl.setText("");

            allergies.setText("");

            current_treatment.setText("");

            personal_history.setText("");

            int sid = nut_status.getCheckedRadioButtonId();
            RadioButton rd = (RadioButton)rootView.findViewById(sid);
            rd.setChecked(false);

            general_exam.setText("");

            systematic_exam.setText("");
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.case_sheet, container, false);

            Toast.makeText(getActivity().getBaseContext(),"CaseSheet Fragement",Toast.LENGTH_LONG).show();
            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();

            getValuesOfFields();
            save_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validate_form())
                    {
                        save_to_database();
                        clear_fields();
                    }
                }
            });
        }
    }

}
