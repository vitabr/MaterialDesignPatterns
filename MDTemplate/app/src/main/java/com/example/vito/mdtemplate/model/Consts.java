package com.example.vito.mdtemplate.model;

public class Consts {

	public final static boolean DEBUG = true;

	public static final String INTENT_GET_PAGE = "com.vito.myheritage.Get_Page_By_Num";
	public static final String EXTRAS_PAGE_NUM = "com.vito.page_num";
	public static final String EXTRAS_SEARCH_TEXT = "com.vito.search_text";
	public static final String EXTRAS_RESULT_JSON = "com.vito.result_json";
	public static final String EXTRAS_RESULT_ERROR_CODE = "com.vito.result_error_code";

	// Using static final much more efficient than enum's(less object memory
	// allocation)
	public static final int ANIM_ROTATE = 0;
	public static final int ANIM_FADE_OUT = 1;
	public static final int ANIM_FADE_IN_SLIDE_RIGHT = 2;
	public static final int ANIM_FADE_IN_ZOOM_IN = 3;
	public static final int ANIM_FADE_IN_SLIDE_DOWN = 4;
	public static final int ANIM_ZOOM_IN_ALPHA_BOUNCE_LOOP = 5;

}
