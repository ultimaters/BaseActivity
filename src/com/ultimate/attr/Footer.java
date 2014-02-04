package com.ultimate.attr;

import android.widget.RadioButton;

public interface Footer {
	/**
	 * Set the visibility of footer view. This method is invalid out {@link #initView}
	 * 
	 * @param vis
	 *            if true, the footer view is visible. Otherwise, footer view
	 *            will gone.
	 */
	public void setFooterVisible(boolean vis);

	/**
	 * Set the backgroud resource(Drawable object) of footer.
	 * 
	 * @param resId
	 *            the id of drawable. set 0 to remove backgroud.
	 */
	public void setFooterBackgroudResource(int resId);

	/**
	 * Set the backgroud color of footer. But now is nouse, because I have set
	 * the backgroud resource, so set the color singly is nouse. Just use
	 * {@link #setFooterBackgroudResource(int)}
	 * 
	 * @param color
	 *            color value.
	 */
	@Deprecated
	public void setFooterBackgroudColor(int color);

	/**
	 * Set the number of footer button
	 * 
	 * @param num
	 *            between 0 and 10.
	 */
	public void setFooterButtonsNum(int num);

	/**
	 * Get the footer button given by index.
	 * 
	 * @param index
	 *            the index of footer button.(from 0)
	 * @return RadioButton
	 */
	public RadioButton getFooterButton(int index);

	/**
	 * Set the backgroud of footer button.
	 * 
	 * @param btnId
	 *            index of footer button.(from 0)
	 * @param bgResId
	 *            the resource id.(the system drawable is android.R.drawable.*)
	 */
	public void setFooterButtonBackgroud(int btnId, int bgResId);
}
