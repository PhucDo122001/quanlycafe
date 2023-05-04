package com.example.quanlycafe.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.quanlycafe.DTO.MonDTO;
import com.example.quanlycafe.DTO.top;
import com.example.quanlycafe.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class topDAO {
    Context context;
    SQLiteDatabase database;
    public topDAO(Context context){
        this.context = context;
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }


    public int getDoanhThu(String min, String max) {
        String sqlDoanhThu = "Select Sum("+  CreateDatabase.TBL_DONDAT_TONGTIEN  +") as doanhThu from "+CreateDatabase.TBL_DONDAT +" Where "+CreateDatabase.TBL_DONDAT_NGAYDAT+" between ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sqlDoanhThu, new String[]{min, max});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }

    //thống kê top 10
    public List<top> getTop() {
        String sqlTop = "Select MAMON ,count(MAMON) as soLuong From "+CreateDatabase.TBL_CHITIETDONDAT+" Group By MAMON Order By soLuong DESC Limit 10";
        List<top> list = new ArrayList<>();
        MonDAO monDAO = new MonDAO(context);
        Cursor cursor = database.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            top top = new top();
            MonDTO mon = monDAO.LayMonTheoMa(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MAMON"))));
            top.setTenMon(mon.getTenMon());
            top.setSoLuongMon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }


}
