package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.listeners.OnClickBoughtItemListener;
import pl.gauee.wishlist.apk.listeners.OnClickShowPhotoListener;
import pl.gauee.wishlist.apk.listeners.OnClickTakePhotoListener;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;

public class ItemDetailsActivity extends Activity {
	
	private RestWishItem item;
    public static final int CAMERA_REQUEST = 1888; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_details);
		item = (RestWishItem) getIntent().getSerializableExtra("wishItem");
		if(item != null){
			EditText itemName = (EditText)findViewById(R.id.itemdetails_name);
			EditText itemDescribe = (EditText)findViewById(R.id.itemdetails_describe);
			EditText itemPrice = (EditText)findViewById(R.id.itemdetails_price);
			itemName.setText(item.getName());
			itemDescribe.setText(item.getDescription());
			itemPrice.setText(item.getPrice()+"");
			
			ImageView buy = (ImageView) findViewById(R.id.itemdetails_buy);
			buy.setOnClickListener(new OnClickBoughtItemListener(this, item.getId()));
			
			ImageView photo = (ImageView) findViewById(R.id.itemdetails_photo);
			if(item.getPhotoUrl()==null){
				photo.setOnClickListener(new OnClickTakePhotoListener(this,item.getId()) {
					
					@Override
					public void startCamera() {
						Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
		                startActivityForResult(cameraIntent, CAMERA_REQUEST);
					}
				});
			}else{
				photo.setOnClickListener(new OnClickShowPhotoListener(this,item.getId()));
			}
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_details, menu);
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            ImageView imageView = (ImageView) findViewById(R.id.itemdetails_photo);
			
            imageView.setImageBitmap(photo);
        }  
    } 

}
