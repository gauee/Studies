package pl.gauee.wishlist.apk.remote;

import android.widget.ImageView;

public class IconResolver {

	public static ImageView setIM4ItemBuying(ImageView imgView,boolean isBought) {
		return (isBought?getBoughtItemImageView(imgView):getNotBoughtItemImageView(imgView));
	}

	private static ImageView getBoughtItemImageView(ImageView imgView) {
		// TODO Auto-generated method stub
		imgView.setImageResource(pl.gauee.wishlist.apk.R.drawable.bought);
		return imgView;
	}

	private static ImageView getNotBoughtItemImageView(ImageView imgView) {
		// TODO Auto-generated method stub
		imgView.setImageResource(pl.gauee.wishlist.apk.R.drawable.boughtnot);
		return imgView;
	}
	
}
