package com.ultimate.attr;

import android.view.View;

public interface Content {
	/**
	 * Set the content view. The view can inflate from XML layout file, or
	 * construct by View, Widget.<br>
	 * 
	 * @Usage From XML:<br>
	 *        <code>
	 * LinearLayout content = (LinearLayout) LinearLayout.inflate(R.layout.content, null);<br>
	 * setContent(content); 
	 * </code><br>
	 * <br>
	 *        From View or Widget:<br>
	 *        <code>
	 * setContent(new ContentView());  //ContentView is custom view, extends View.
	 * </code>
	 * @param contentView
	 *            the view to set as content
	 */
	public void setContent(View contentView);
}
