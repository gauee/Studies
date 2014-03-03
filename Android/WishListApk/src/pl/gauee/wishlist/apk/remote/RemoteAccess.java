package pl.gauee.wishlist.apk.remote;

import pl.gauee.wishlist.apk.store.SharePrefManagement;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.rest.RestUserLists;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import pl.gauee.wishlist.utils.persistance.rest.RestWishListWithItems;
import pl.gauee.wishlist.utils.persistance.rest.RestWishUser;
import android.content.Context;

public abstract class RemoteAccess<T> {

	private final String baseHttpUrl = "http://192.168.43.219:8080/wishListWebApp/";
//	private final String baseHttpUrl = "http://192.168.1.6:8080/wishListWebApp/";
	private final Context context;

	public RemoteAccess(Context context) {
		this.context = context;
	}

	public void getMyList(long listId) {
		final String requestUrl = baseHttpUrl + PageUtils.restUserMyOneList
				+ "?listId=" + listId;
		executeInAsynTask(requestUrl, RestWishListWithItems.class);
	}

	public void updateUser(RestWishUser userToUpdate) {
		// TODO Auto-generated method stub
		String requestUrl = baseHttpUrl + PageUtils.restUserUpdate;
		executeInPost(userToUpdate, requestUrl);

	}

	public void getUserAuthorized(String login, String pass) {
		String requestUrl = baseHttpUrl + PageUtils.restUserAuth;

		RemoteAsyncTask<Boolean> rat = new RemoteAsyncTask<Boolean>(login,
				pass, context, requestUrl) {
			@Override
			public void executeOnFinished(Boolean result) {
				// TODO Auto-generated method stub
				onReceivedResult((T) (result == null ? new Boolean(false)
						: result));
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
		String requestUrl = baseHttpUrl + PageUtils.restUserMyLists;
		executeInAsynTask(requestUrl, RestUserLists.class);
	}

	public void getMe() {
		String requestUrl = baseHttpUrl + PageUtils.restUserMe;
		executeInAsynTask(requestUrl, RestWishUser.class);
	}

	public void createNewList(RestWishList newList) {
		String requestUrl = baseHttpUrl + PageUtils.restUserAddNewList
				+ "?listName=" + newList.getName();
		executeInAsynTask(requestUrl, Boolean.class);
	}

	public void changeBuyStatusFor(long itemId) {
		String requestUrl = baseHttpUrl + PageUtils.restUserChangeItemStatus
				+ "?itemId=" + itemId;
		executeInAsynTask(requestUrl, Boolean.class);
	}

	public void deleteList(long listId) {
		String requestUrl = baseHttpUrl + PageUtils.restListDelete + "?listId="
				+ listId;
		executeInAsynTask(requestUrl, Boolean.class);
	}

	public void deleteItem(long itemId) {
		String requestUrl = baseHttpUrl + PageUtils.restListItemDelete
				+ "?itemId=" + itemId;
		executeInAsynTask(requestUrl, Boolean.class);
	}

	public void updateItem(RestWishItem updatedItem) {
		final String requestUrl = baseHttpUrl + PageUtils.restListItemUpdate;
		// executeInAsynTask(requestUrl, Boolean.class);
		executeInPost(updatedItem, requestUrl);
	}

	private void executeInPost(Object object, final String requestUrl) {
		String login = new SharePrefManagement(context).getUserName();
		String pass = new SharePrefManagement(context).getUserPass();
		new RemoteAsyncTaskPost<Boolean>(login, pass, context, requestUrl,
				object) {
			@Override
			public void executeOnFinished(Boolean result) {
				// TODO Auto-generated method stub
				onReceivedResult((T) result);
			}

			@Override
			public Class<Boolean> getClassType() {
				return Boolean.class;
			}
		}.execute();
	}

	private <A> void executeInAsynTask(final String requestUrl,
			final Class<A> classType) {
		String login = new SharePrefManagement(context).getUserName();
		String pass = new SharePrefManagement(context).getUserPass();
		new RemoteAsyncTask<A>(login, pass, context, requestUrl) {
			@Override
			public void executeOnFinished(A result) {
				// TODO Auto-generated method stub
				onReceivedResult((T) result);
			}

			@Override
			public Class<A> getClassType() {
				return classType;
			}
		}.execute();
	}

	public abstract void onReceivedResult(T result);

}
