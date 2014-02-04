package com.ultimate.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ultimate.app.AppManager;

public class UIHelper {

	private static String SURE = "确定";
	private static String APP_ERROR = "应用程序错误";
	private static String SUBMIT_REPORT = "提交报告";
	private static CharSequence APP_ERROR_MESSAGE = "很抱歉，应用程序出现错误，即将退出。\n请提交错误报告，我们会尽快修复这个问题!";

	/**
	 * The convinient way to show Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showToastMessage(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Show notice dialog when you want to quit
	 * 
	 * @param context
	 */
	public static void showNoticeDialog(final Context context) {
		new AlertDialog.Builder(context).setTitle("温馨提示").setMessage("你要退出吗?")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						AppManager.getAppManager().exitApplication(context);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}

	/**
	 * 打开浏览器
	 * 
	 * @param context
	 * @param url
	 */
	public static void openBrowser(Context context, String url) {
		try {
			Uri uri = Uri.parse(url);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			context.startActivity(it);
		} catch (Exception e) {
			e.printStackTrace();
			showToastMessage(context, "无法浏览此网页");
		}
	}

	/**
	 * 发送App异常崩溃报告
	 * 
	 * @param cont
	 * @param crashReport
	 */
	public static void sendAppCrashReport(final Context cont,
			final String crashReport) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(APP_ERROR);
		builder.setMessage(APP_ERROR_MESSAGE);
		builder.setPositiveButton(SUBMIT_REPORT,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 发送异常报告
						Intent i = new Intent(Intent.ACTION_SEND);
						// i.setType("text/plain"); //模拟器
						i.setType("message/rfc822"); // 真机
						i.putExtra(Intent.EXTRA_EMAIL,
								new String[] { "jxsmallmouse@163.com" });
						i.putExtra(Intent.EXTRA_SUBJECT,
								"开源中国Android客户端 - 错误报告");
						i.putExtra(Intent.EXTRA_TEXT, crashReport);
						cont.startActivity(Intent.createChooser(i, "发送错误报告"));
						// 退出
						AppManager.getAppManager().exitApplication(cont);
					}
				});
		builder.setNegativeButton(SURE,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出
						AppManager.getAppManager().exitApplication(cont);
					}
				});
		builder.show();
	}

}
