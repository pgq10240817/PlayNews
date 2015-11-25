package android.yhpl.core.http.parser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.yhpl.core.http.res.BaseGson;

public abstract class BaseDecorator {

	private static NumberFormat mAmountFmt;
	private static SimpleDateFormat mDateFmt;

	public static String getAmountStr(double amount) {
		if (mAmountFmt == null) {
			mAmountFmt = new DecimalFormat(",##0.00");
		}
		return mAmountFmt.format(amount);
	}

	public static String getDateStr(long milliseconds) {
		if (mDateFmt == null) {
			mDateFmt = new SimpleDateFormat("yyyy-MM-dd");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		Date date = calendar.getTime();
		return mDateFmt.format(date);
	}

	public static String getDateStr(String timeStr) {
		if (mDateFmt == null) {
			mDateFmt = new SimpleDateFormat("yyyy-MM-dd");
		}
		Date date = new Date();
		try {
			date = mDateFmt.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(date);
		// Date date = calendar.getTime();
		return mDateFmt.format(date);
	}

	public BaseGson onDecoratorAll(String jsonStr) {
		return null;

	}

}
