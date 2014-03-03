package pl.gaue.ibansender;

//import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IbanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iban_activity);

		EditText iban = (EditText) findViewById(R.id.ibanNumberField);
		iban.setText("PL42184000072213320008111319");
		
	}

	public void onSend(View v){
		//Toast.makeText(this,"ButtonSend", Toast.LENGTH_SHORT).show();
		EditText iban = (EditText) findViewById(R.id.ibanNumberField);
		String ibanNum = iban.getText().toString();
		
		//Toast.makeText(this, "IBAN: " + ibanNum, Toast.LENGTH_LONG).show();
		
		if(!NumberIBAN.verifyIban(ibanNum)){
			iban.setError("Incorrect Iban");
			return;
		}
		
		Log.d("SendMyNumber", "Uruchomienie aplikacji do wysylania SMSow.");
        
        Intent iSms = new Intent(Intent.ACTION_VIEW);
        iSms.putExtra("address", ""); 
        iSms.putExtra("sms_body", "Moj numer iban: " + ibanNum ); 
        iSms.setType("vnd.android-dir/mms-sms");
        startActivity(iSms); 
		
		
	}

}
