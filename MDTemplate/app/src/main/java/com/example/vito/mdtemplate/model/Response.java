package com.example.vito.mdtemplate.model;

import java.util.ArrayList;

public class Response {

	public static class ResponseData {
		public SearchResults results;
		public Cursor cursor;
	}
	
	public static class SearchResults extends ArrayList<SearchResult>{
	}
	public static class Pages extends ArrayList<Page>{
		
	}
	public static class Cursor{
		public Pages pages;
		public int currentPageIndex;

		public Page getCurrentPage() {
			for (Page page : pages) {
				if(page.label == currentPageIndex+1)
					return page;
			}
			return null;
		}	
		public Page getNextPage(){
			for (Page page : pages) {
				if(page.label == currentPageIndex+2)
					return page;
			}
			return null;
		}
	}
	
	public String responseDetails;
	public int responseStatus;
	public ResponseData responseData;
	
	public ArrayList<SearchResult> getSearchResults(){
		if(responseData == null)
			return null;
		return responseData.results;
	}
	
	public Cursor getCursor(){
		if(responseData == null)
			return null;
		return responseData.cursor;
	}
}
