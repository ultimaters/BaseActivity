package com.ultimate.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Locale;

import com.ultimate.util.UIHelper;

import org.apache.commons.httpclient.HttpException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

/**
 * Ӧ�ó����쳣�ࣺ���ڲ����쳣����ʾ������Ϣ
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class AppException extends Exception implements UncaughtExceptionHandler {

	private final static boolean Debug = false;// �Ƿ񱣴������־

	/** �����쳣���� */
	public final static byte TYPE_NETWORK = 0x01;
	public final static byte TYPE_SOCKET = 0x02;
	public final static byte TYPE_HTTP_CODE = 0x03;
	public final static byte TYPE_HTTP_ERROR = 0x04;
	public final static byte TYPE_XML = 0x05;
	public final static byte TYPE_IO = 0x06;
	public final static byte TYPE_RUN = 0x07;

	private static final String HTTP_STATUS_CODE_ERROR = "�����쳣�������룺%d";

	private static final String HTTP_EXCEPTION_ERROR = "�����쳣������ʱ";

	private static final String SOCKET_EXCEPTION_ERROR = "�����쳣����ȡ���ݳ�ʱ";

	private static final String NETWORK_NOT_CONNECTED = "��������ʧ�ܣ�������������";

	private static final String XML_PARSER_FAILED = "���ݽ����쳣";

	private static final String IO_EXCEPTION_ERROR = "�ļ����쳣";

	private static final String APP_RUN_CODE_ERROR = "Ӧ�ó�������ʱ�쳣";

	private byte type;
	private int code;

	/** ϵͳĬ�ϵ�UncaughtException������ */
	private Thread.UncaughtExceptionHandler mDefaultHandler;

	private AppException() {
		this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	private AppException(byte type, int code, Exception excp) {
		super(excp);
		this.type = type;
		this.code = code;
		if (Debug) {
			this.saveErrorLog(excp);
		}
	}

	public int getCode() {
		return this.code;
	}

	public int getType() {
		return this.type;
	}

	/**
	 * ��ʾ�ѺõĴ�����Ϣ
	 * 
	 * @param ctx
	 */
	public void makeToast(Context ctx) {
		switch (this.getType()) {
		case TYPE_HTTP_CODE:
			String err = String.format(Locale.CHINA, HTTP_STATUS_CODE_ERROR, this.getCode());
			Toast.makeText(ctx, err, Toast.LENGTH_SHORT).show();
			break;
		case TYPE_HTTP_ERROR:
			Toast.makeText(ctx, HTTP_EXCEPTION_ERROR,
					Toast.LENGTH_SHORT).show();
			break;
		case TYPE_SOCKET:
			Toast.makeText(ctx, SOCKET_EXCEPTION_ERROR,
					Toast.LENGTH_SHORT).show();
			break;
		case TYPE_NETWORK:
			Toast.makeText(ctx, NETWORK_NOT_CONNECTED,
					Toast.LENGTH_SHORT).show();
			break;
		case TYPE_XML:
			Toast.makeText(ctx, XML_PARSER_FAILED, Toast.LENGTH_SHORT)
					.show();
			break;
		case TYPE_IO:
			Toast.makeText(ctx, IO_EXCEPTION_ERROR, Toast.LENGTH_SHORT)
					.show();
			break;
		case TYPE_RUN:
			Toast.makeText(ctx, APP_RUN_CODE_ERROR, Toast.LENGTH_SHORT)
					.show();
			break;
		}
	}

	/**
	 * �����쳣��־
	 * 
	 * @param excp
	 */
	public void saveErrorLog(Exception excp) {
		String errorlog = "errorlog.txt";
		String savePath = "";
		String logFilePath = "";
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			// �ж��Ƿ������SD��
			String storageState = Environment.getExternalStorageState();
			if (storageState.equals(Environment.MEDIA_MOUNTED)) {
				savePath = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/OSChina/Log/";
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				logFilePath = savePath + errorlog;
			}
			// û�й���SD�����޷�д�ļ�
			if (logFilePath == "") {
				return;
			}
			File logFile = new File(logFilePath);
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			fw = new FileWriter(logFile, true);
			pw = new PrintWriter(fw);
			pw.println("--------------------" + (new Date().toLocaleString())
					+ "---------------------");
			excp.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static AppException http(int code) {
		return new AppException(TYPE_HTTP_CODE, code, null);
	}

	public static AppException http(Exception e) {
		return new AppException(TYPE_HTTP_ERROR, 0, e);
	}

	public static AppException socket(Exception e) {
		return new AppException(TYPE_SOCKET, 0, e);
	}

	public static AppException io(Exception e) {
		if (e instanceof UnknownHostException || e instanceof ConnectException) {
			return new AppException(TYPE_NETWORK, 0, e);
		} else if (e instanceof IOException) {
			return new AppException(TYPE_IO, 0, e);
		}
		return run(e);
	}

	public static AppException xml(Exception e) {
		return new AppException(TYPE_XML, 0, e);
	}

	public static AppException network(Exception e) {
		if (e instanceof UnknownHostException || e instanceof ConnectException) {
			return new AppException(TYPE_NETWORK, 0, e);
		} else if (e instanceof HttpException) {
			return http(e);
		} else if (e instanceof SocketException) {
			return socket(e);
		}
		return http(e);
	}

	public static AppException run(Exception e) {
		return new AppException(TYPE_RUN, 0, e);
	}

	/**
	 * ��ȡAPP�쳣�����������
	 * 
	 * @param context
	 * @return
	 */
	public static AppException getAppExceptionHandler() {
		return new AppException();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {

		if (!handleException(ex) && mDefaultHandler != null) {
			mDefaultHandler.uncaughtException(thread, ex);
		}

	}

	/**
	 * �Զ����쳣����:�ռ�������Ϣ&���ʹ��󱨸�
	 * 
	 * @param ex
	 * @return true:�����˸��쳣��Ϣ;���򷵻�false
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}

		final Context context = AppManager.getAppManager().getCurrentActivity();

		if (context == null) {
			return false;
		}

		final String crashReport = getCrashReport(context, ex);
		// ��ʾ�쳣��Ϣ&���ͱ���
		new Thread() {
			public void run() {
				Looper.prepare();
				UIHelper.sendAppCrashReport(context, crashReport);
				Looper.loop();
			}

		}.start();
		return true;
	}

	/**
	 * ��ȡAPP�����쳣����
	 * 
	 * @param ex
	 * @return
	 */
	private String getCrashReport(Context context, Throwable ex) {
		PackageInfo pinfo = ((AppContext) context.getApplicationContext())
				.getPackageInfo();
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Version: " + pinfo.versionName + "("
				+ pinfo.versionCode + ")\n");
		exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE
				+ "(" + android.os.Build.MODEL + ")\n");
		exceptionStr.append("Exception: " + ex.getMessage() + "\n");
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString() + "\n");
		}
		return exceptionStr.toString();
	}
}
