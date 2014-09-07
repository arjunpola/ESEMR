package redex.mit.lvpei.eyesmart_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by arjunpola on 17/07/14.
 */
public class ViewPatients extends Activity {

    public List<Patient> patients;
    LoginDataBaseAdapter adapter;
    LayoutInflater inflater;
    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get all the patient data and save it in 'patients' variable.
        adapter = new LoginDataBaseAdapter(getApplicationContext());
        adapter.open();
        patients = adapter.GetAllPatient();

        //Get the inflater to dynamically create list of patients.
        inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        ScrollView sv = (ScrollView) inflater.inflate(R.layout.view_patients,null);
        TableLayout tl = (TableLayout)sv.findViewById(R.id.tableLayout);

        //Populate the patients.
        if(patients != null) {
//            Toast.makeText(this,"Patient Count is "+patients.size(),Toast.LENGTH_SHORT).show();
            for (int i = 0; i < patients.size(); i++) {

                LinearLayout row = (LinearLayout) inflater.inflate(R.layout.record, null);
                ((TextView) row.findViewById(R.id.name)).setText(patients.get(i).getfName());
                ((TextView) row.findViewById(R.id.gen)).setText(patients.get(i).getGender());
                ((TextView) row.findViewById(R.id.dob)).setText(patients.get(i).getDob());
                ((TextView) row.findViewById(R.id.vt)).setText(patients.get(i).getVisionTech());

                tl.addView(row);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) view.findViewById(R.id.name);
                        //Toast.makeText(getBaseContext(),tv.getText(),Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getBaseContext(),DetailForms.class);
                        i.putExtra("NAME",tv.getText());
                        startActivity(i);
                    }
                });
            }
        }
        else // Used to display dummy patient information. Remove Else{...} after Debug.
        {
//            LinearLayout row = (LinearLayout) inflater.inflate(R.layout.record, null);
//            ((TextView) row.findViewById(R.id.name)).setText("Arjun");
//            ((TextView) row.findViewById(R.id.gen)).setText("Male");
//            ((TextView) row.findViewById(R.id.dob)).setText("22");
//            ((TextView) row.findViewById(R.id.vt)).setText("Rahul");
//            ((TextView) row.findViewById(R.id.ckt)).setText("5:10 PM");
//            tl.addView(row);
//
//            row.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    TextView tv = (TextView) view.findViewById(R.id.name);
//                    //Toast.makeText(getBaseContext(),tv.getText(),Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(getBaseContext(),DetailForms.class);
//                    i.putExtra("NAME",tv.getText());
//                    startActivity(i);
//                }
//            });

            Toast.makeText(this,"No Patients Entered Today!",Toast.LENGTH_LONG).show();
        }

        setContentView(sv);
    }
}
