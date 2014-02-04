package com.ultimate.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ultimate.app.AppManager;

public class UIHelper {

	private static String SURE = "ȷ��";
	private static String APP_ERROR = "Ӧ�ó������";
	private static String SUBMIT_REPORT = "�ύ����";
	private static CharSequence APP_ERROR_MESSAGE = "�ܱ�Ǹ��Ӧ�ó�����ִ��󣬼����˳���\n���ύ���󱨸棬���ǻᾡ���޸��������!";

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
		new AlertDialog.Builder(context).setTitle("��ܰ��ʾ").setMessage("��Ҫ�˳���?")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						AppManager.getAppManager().exitApplication(context);
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}

	/**
	 * �������
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
			showToastMessage(context, "�޷��������ҳ");
		}
	}

	/**
	 * ����App�쳣��������
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
						// �����쳣����
						Intent i = new Intent(Intent.ACTION_SEND);
						// i.setType("text/plain"); //ģ����
						i.setType("message/rfc822"); // ���
						i.putExtra(Intent.EXTRA_EMAIL,
								new String[] { "jxsmallmouse@163.com" });
						i.putExtra(Intent.EXTRA_SUBJECT,
								"��Դ�й�Android�ͻ��� - ���󱨸�");
						i.putExtra(Intent.EXTRA_TEXT, crashReport);
						cont.startActivity(Intent.createChooser(i, "���ʹ��󱨸�"));
						// �˳�
						AppManager.getAppManager().exitApplication(cont);
					}
				});
		builder.setNegativeButton(SURE,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// �˳�
						AppManager.getAppManager().exitApplication(cont);
					}
				});
		builder.show();
	}

}
