package pl.gauee.wishlist.apk.remote;

import java.util.ArrayList;
import java.util.List;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishItemInList;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import pl.gauee.wishlist.utils.persistance.rest.RestWishUser;

public class RemoteAccess {

	private static RemoteAccess instance = new RemoteAccess();

	private RemoteAccess() {
		// TODO Auto-generated constructor stub
	}

	public static RemoteAccess getInstance() {
		return instance;
	}

	public List<WishList> geWishLists() {
		List<WishList> list = new ArrayList<WishList>();

		list.add(new WishList("WishLista1"));
		list.add(new WishList("WishLista2"));
		list.add(new WishList("WishLista3"));
		list.add(new WishList("WishLista4"));
		list.add(new WishList("WishLista5"));
		list.add(new WishList("WishLista6"));
		list.add(new WishList("WishLista7"));

		return list;
	}

	public WishList getWishList() {
		WishList list = new WishList("Przykładowa lista");
		WishItem item;
		for (int i = 0; i < 10; ++i) {
			item = new WishItem("Artykuł" + i, "Opis"+i, 10.00+i,
					(i % 3 == 0 ? null : "photoUrl"), null);
			item.setId(i);
			list.getListItems().add(item);
		}
		return list;
	}
	
	public RestWishUser getMe(){
		final String requestUrl = R.string.baseUrlToWebApp+PageUtils.restUserMe;
		return HttpRequestService.getService().getHttpGet(requestUrl, RestWishUser.class);
	}

}
