package com.example.vito.mdtemplate.model;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * As it is not defined to store data between app launches i'll use this Data
 * holder. This is basic data storage singleton with basic caching mechanism
 * which can be usable to show cashed results for previous searches without
 * reload them from net. All heavy data like Bitmaps will be managed and locally
 * stored by Universal Image Loader library, here we just store the
 * SearchResults list.
 * 
 * @author Vito
 *
 */
public class DataHolder {

	private static DataHolder _instance;
	private String last_received_search_text = null;
	private Hashtable<String, ArrayList<SearchResult>> mCache = new Hashtable();
	private Response.Cursor mCursor = null;
	private static boolean isInitCalled = false;

	/**
	 * This function should be called to initialize DataHolder. Here we can
	 * define any required or must have configuration. Currently we just call to
	 * getInstance().
	 */
	public static void init() {
		getInstance();
		isInitCalled = true;
	}

	private DataHolder() {

	}

	private static DataHolder getInstance() {
		if (_instance == null)
			_instance = new DataHolder();
		return _instance;
	}

	public synchronized static void addNewResults(String search_text, ArrayList<SearchResult> results, Response.Cursor cursor) {
		if (!isInitCalled)
			throw new IllegalStateException("DataHolder: init() should be called first...");
		ArrayList<SearchResult> data = _instance.mCache.get(search_text);
		_instance.mCursor = cursor;
		if (data == null)
			data = new ArrayList<SearchResult>();
		_instance.last_received_search_text = search_text;
		data.addAll(results);
		_instance.mCache.put(search_text, data);
	}

	public synchronized static ArrayList<SearchResult> getResultsBySearchText(String search_text) {
		if (!isInitCalled)
			throw new IllegalStateException("DataHolder: init() should be called first...");
		if (search_text == null)
			return null;
		return _instance.mCache.get(search_text);
	}

	/**
	 * Return label value for current page. If there is no data, return -1.
	 * 
	 * @return
	 */
	public static int getCurrentPageLabel() {
		if (!isInitCalled)
			throw new IllegalStateException("DataHolder: init() should be called first...");
		if (_instance.mCursor == null)
			return -1;
		Page page = _instance.mCursor.getCurrentPage();
		return (page == null) ? -1 : page.label;
	}

	/**
	 * Return start value for next page found in cursor. If there is no cursor
	 * data, return 0. (New Search) If cusror goes out of bounds return -1.
	 * 
	 * @return
	 */
	public static int getNextPageStart() {
		if (!isInitCalled)
			throw new IllegalStateException("DataHolder: init() should be called first...");
		if (_instance.mCursor == null)
			return 0;
		Page page = _instance.mCursor.getNextPage();
		return (page == null) ? -1 : page.start;
	}

	/**
	 * Call to clear current search paging cursor. As result, getNextPageStart()
	 * will return 0 until new data received.
	 */
	public static void clearCursor() {
		if (!isInitCalled)
			throw new IllegalStateException("DataHolder: init() should be called first...");
		_instance.last_received_search_text = null;
		_instance.mCursor = null;
	}

	/**
	 * If argument is equal to recent search term returns true.
	 * 
	 * @param search_text
	 * @return
	 */
	public static boolean isCurrentSearch(String search_text) {
		if (!isInitCalled)
			throw new IllegalStateException("DataHolder: init() should be called first...");
		return (_instance.last_received_search_text == null) ? false
				: _instance.last_received_search_text.equals(search_text);
	}

	public static String getRecentSearchText() {
		if (!isInitCalled)
			throw new IllegalStateException("DataHolder: init() should be called first...");
		return _instance.last_received_search_text;
	}
}
