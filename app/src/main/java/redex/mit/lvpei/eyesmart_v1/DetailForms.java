package redex.mit.lvpei.eyesmart_v1;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
        public static PlaceholderFragment newInstance(int sectionNumber) {

            switch (sectionNumber)
            {
                case 1:
                    PlaceholderFragment fragment = new PlaceholderFragment();
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

}
