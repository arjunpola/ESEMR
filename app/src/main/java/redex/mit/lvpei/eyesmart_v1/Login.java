package redex.mit.lvpei.eyesmart_v1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.FlatUI;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjunpola on 15/07/14.
 */
public class Login extends Activity {

    EditText emailBox,pinBox;
    String email;
    int pin;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);

//        FlatUI.initDefaultValues(this);
//        FlatUI.setDefaultTheme(FlatUI.DEEP);
//        FlatUI.setDefaultTheme(R.array.deep);    // for using custom theme as default
//        getActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this,FlatUI.DEEP,false));

        setContentView(R.layout.login);
        emailBox = (EditText)findViewById(R.id.emailBox);
        pinBox = (EditText)findViewById(R.id.pinBox);

        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter.open();
    }

    public void auth(View v)
    {
        email = emailBox.getText().toString();

        if(email.equals("") || pinBox.getText().toString().equals(""))
            Toast.makeText(getBaseContext(), "Missing Values", Toast.LENGTH_SHORT).show();
        else {

            //Following code is responsible authenticating the user. Firstly it looks up in the local database for username.
            //If found then it matches the password and shows next screen based on the result of matching.
            //If username is not found in local database then it connects to a server(temporarily a personal computer) and authenticates.
            //Class 'Auth' extends AsyncTask and is used to connect to the server in background and authenticate.
            //Once authenticated the username and password is saved in the local database and 'Home' Activity is brought to front

            //Uncomment the following code to not bypass the login process.

//            pin = Integer.parseInt(pinBox.getText().toString());
//            int storedPin=loginDataBaseAdapter.getSingleEntry(email);
//            Toast.makeText(getBaseContext(),String.valueOf(storedPin),Toast.LENGTH_LONG).show();
//            if(storedPin == -1 || storedPin != pin)
//            {
//                new Auth().execute(email,String.valueOf(pin));
//            }
//            else
//            {
//                //Toast.makeText(this,"LSUCCESS",Toast.LENGTH_LONG).show();
//                Intent i = new Intent(this,Home.class);
//                startActivity(i);
//                finish();
//            }
                Intent i = new Intent(this,Home.class);
                startActivity(i);
                finish();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.clear_login:
                loginDataBaseAdapter.clearLogin();
        }

        return super.onOptionsItemSelected(item);
    }

    public class Auth extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            String ip = "172.16.79.165:8080";
            HttpPost httppost = new HttpPost("http://"+ip+"/SmartEMR/tlogin.php");
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("email", params[0]));
                nameValuePairs.add(new BasicNameValuePair("pin",String.valueOf(params[1])));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String line = reader.readLine();
                Log.v("Line", line);

                if(line.equals("SUCCESS")) {
                    Log.v("Response", "SUCCESS");
                    return "SUCCESS";
                }
                else {
                    Log.v("Response", "FAIL");
                    return "FAIL";
                }

            }
            catch (ClientProtocolException e) {
                    Log.v("Error",e.getMessage());
                // TODO Auto-generated catch block
            } catch (IOException e) {
                Log.v("IO Exception",e.getMessage());
                // TODO Auto-generated catch block
            }
            catch (Exception e){
                Log.v("Exception",e.getMessage());
            }

            return "FAIL1";
        }

        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("SUCCESS")) {
                loginDataBaseAdapter.insertEntry(email,pin);
                Intent i = new Intent(Login.this,Home.class);
                startActivity(i);
                finish();
            }

            if (result.equals("FAIL"))

                Toast.makeText(getBaseContext(),"FAIL",Toast.LENGTH_LONG).show();
        }
    }

}
