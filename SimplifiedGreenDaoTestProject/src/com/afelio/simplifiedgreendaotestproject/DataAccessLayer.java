package com.afelio.simplifiedgreendaotestproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.afelio.simplifiedgreendaotestproject.entities.DaoMaster;
import com.afelio.simplifiedgreendaotestproject.entities.DaoMaster.DevOpenHelper;
import com.afelio.simplifiedgreendaotestproject.entities.DaoSession;

public class DataAccessLayer implements IDAL{

	Context	       context;
	
	SQLiteDatabase	db;
	DaoMaster	   daoMaster;
	DaoSession	   daoSession;
	
	public DataAccessLayer(Context ctx) {
		context = ctx;
	}
	
	void open(){

		//Change the name of the database
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "YourProjectDatabase", null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
	}
	
	void close(){
		try {
			daoMaster.getDatabase().close();
			db.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
//	
//	@Override
//	public List<Note> loadAllNotes() {
//		return daoSession.getNoteDao().queryBuilder().list();
//	}
	
}
