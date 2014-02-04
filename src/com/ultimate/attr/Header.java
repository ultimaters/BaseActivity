package com.ultimate.attr;

import android.view.View;

public interface Header {
	
	/**
	 * Set the backgroud resource(Drawable object) of header.
	 * @param resId the id of drawable. set 0 to remove backgroud.
	 */
	public void setHeaderBackgroudResource(int resId);
	
	/**
	 * Set the backgroud color of Header.But now is nouse, because I have set
	 * the backgroud resource, so set the color singly is nouse. Just use
	 * {@link #setHeaderBackgroudResource(int)}
	 * 
	 * @param color
	 *            color value.
	 */
	@Deprecated
	public void setHeaderBackgroudColor(int color);

	/**
	 * Set the view of header left. Default, left view is not display. 
	 * @param leftView the view to set. Set null to remove leftview.
	 */
	public void setHeaderLeftView(View leftView);
	
	/**
	 * Set the view of header right. Default, right view is not display. 
	 * @param rightView the view to set.
	 */
	public void setHeaderRightView(View rightView);
	
	/**
	 * Set the text of Header Center TextView
	 * 
	 * @param centerText
	 *            text to display in header center.
	 */
	public void setHeaderCenterViewText(String centerText);

	/**
	 * Set the text and color of Header Center TextView
	 * 
	 * @param centerText
	 * @param color
	 */
	public void setHeaderCenterViewText(String centerText, int color);

	/**
	 * Set the text¡¢color and size of Header Center TextView
	 * 
	 * @param centerText
	 * @param color
	 * @param size
	 */
	public void setHeaderCenterViewText(String centerText, int color, float size);
}
