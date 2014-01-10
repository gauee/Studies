package pl.gauee.wishlist.apk.remote;

import pl.gauee.wishlist.apk.store.SharePrefManagement;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.rest.RestUserLists;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import pl.gauee.wishlist.utils.persistance.rest.RestWishListWithItems;
import pl.gauee.wishlist.utils.persistance.rest.RestWishUser;
import android.content.Context;

public abstract class RemoteAccess<T> {

	private final String baseHttpUrl = "http://192.168.1.2:8080/wishListWebApp/";
	private final Context context;

	public RemoteAccess(Context context) {
		// TODO Auto -generated constructor stub
		this.context = context;
	}

	public void getMyList(long listId) {
		final String requestUrl = baseHttpUrl + PageUtils.restUserMyOneList+"?listId="+listId;

		final String login = new SharePrefManagement(context).getUserName();
		final String pass = new SharePrefManagement(context).getUserPass();

		new RemoteAsyncTask<RestWishListWithItems>(login, pass, context,
				requestUrl) {
			@Override
			public void executeOnFinished(RestWishListWithItems result) {
				onReceivedResult((T) result);
			}

			@Override
			public Class<RestWishListWithItems> getClassType() {
				return RestWishListWithItems.class;
			}
		}.execute();

	}

	public void getUserAuthorized(String login, String pass) {
		String requestUrl = baseHttpUrl + PageUtils.restUserAuth;
		RemoteAsyncTask<Boolean> rat = new RemoteAsyncTask<Boolean>(login,
				pass, context, requestUrl) {
			@Override
			public void executeOnFinished(Boolean result) {
				// TODO Auto-generated method stub
				onReceivedResult((T)(result==null?new Boolean(false):result));
			}

			@Override
			public Class<Boolean> getClassType() {
				// TODO Auto-generated method stub
				return Boolean.class;
			}
		};
		rat.execute();
	}

	public void getUserLists() {
		final String requestUrl = baseHttpUrl + PageUtils.restUserMyLists;

		String login = new SharePrefManagement(context).getUserName();
		String pass = new SharePrefManagement(context).getUserPass();

		new RemoteAsyncTask<RestUserLists>(login, pass, context, requestUrl) {
			@Override
			public void executeOnFinished(RestUserLists result) {
				// TODO Auto-generated method stub
				onReceivedResult((T) result);
			}

			@Override
			public Class<RestUserLists> getClassType() {
				return RestUserLists.class;
			}
		}.execute();
	}

	public void getMe() {
		final String requestUrl = baseHttpUrl + PageUtils.restUserMe;

		String login = new SharePrefManagement(context).getUserName();
		String pass = new SharePrefManagement(context).getUserPass();

		RemoteAsyncTask<RestWishUser> rat = new RemoteAsyncTask<RestWishUser>(
				login, pass, context, requestUrl) {
			@Override
			public Class<RestWishUser> getClassType() {
				return RestWishUser.class;
			}

			@Override
			public void executeOnFinished(RestWishUser result) {
				// TODO Auto-generated method stub
				onReceivedResult((T) result);
			}
		};
		rat.execute();

	}


	public void createNewList(RestWishList newList) {
		final String requestUrl = baseHttpUrl + PageUtils.restUserAddNewList + "?listName="+newList.getName();

		String login = new SharePrefManagement(context).getUserName();
		String pass = new SharePrefManagement(context).getUserPass();

		
		new RemoteAsyncTask<Boolean>(
				login,
				pass,
				context,
				requestUrl
				) {
			@Override
			public void executeOnFinished(Boolean result) {
				// TODO Auto-generated method stub
				onReceivedResult((T)result);
			}
			
			@Override
			public Class<Boolean> getClassType() {
				return Boolean.class;
			}
		}.execute();
	}
	


	public void changeBuyStatusFor(long itemId) {
		final String requestUrl = baseHttpUrl + PageUtils.restUserChangeItemStatus + "?itemId="+itemId;

		String login = new SharePrefManagement(context).getUserName();
		String pass = new SharePrefManagement(context).getUserPass();
		new RemoteAsyncTask<Boolean>(
				login,
				pass,
				context,
				requestUrl
				) {
			@Override
			public void executeOnFinished(Boolean result) {
				// TODO Auto-generated method stub
				onReceivedResult((T)result);
			}
			
			@Override
			public Class<Boolean> getClassType() {
				return Boolean.class;
			}
		}.execute();
		
	}
	
	public abstract void onReceivedResult(T result);

}
