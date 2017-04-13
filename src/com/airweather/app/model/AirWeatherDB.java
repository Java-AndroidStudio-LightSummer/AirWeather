package com.airweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.airweather.app.db.AirWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AirWeatherDB {
	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME = "air_weather";

	/**
	 * ���ݿ�汾
	 */
	public static final int VERSION = 1;

	private static AirWeatherDB airWeatherDB;
	private SQLiteDatabase db;

	/**
	 * �����췽��˽�л�
	 */

	private AirWeatherDB(Context context) {
		AirWeatherOpenHelper dbHelper = new AirWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * ��ȡAirWeatherDB ��ʵ��
	 */

	public synchronized static AirWeatherDB getInstance(Context context) {
		if (airWeatherDB == null) {
			airWeatherDB = new AirWeatherDB(context);
		}

		return airWeatherDB;
	}

	/**
	 * ��Provinceʵ���洢�����ݿ�
	 */

	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());

			db.insert("Province", null, values);
		}
	}

	/**
	 * �����ݿ��ȡȫ�����е�ʡ����Ϣ
	 */

	public List<Province> loadProvince() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		while(cursor.moveToNext()){
			Province province = new Province();
			
			province.setId(cursor.getColumnIndex("id"));
			province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
			province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
			
			list.add(province);
		}
		
		return list;
	}

}
