package redex.mit.lvpei.eyesmart_v1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Nagesh-PC on 15-Jul-14.
 */
public class SelectionFile extends Activity{
    ImageButton imageButton1;
    ImageButton imageButton2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.selection);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        imageButton1 = (ImageButton) findViewById(R.id.vision_Center);
        imageButton2 = (ImageButton) findViewById(R.id.corporate);

        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(SelectionFile.this, Login.class));
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectionFile.this, Login.class));
            }
        });
    }

}
