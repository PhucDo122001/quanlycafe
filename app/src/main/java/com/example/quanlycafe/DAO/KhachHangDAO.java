package com.example.quanlycafe.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.quanlycafe.DTO.KhachHangDTO;
import com.example.quanlycafe.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    SQLiteDatabase database;
    public KhachHangDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemKhachHang(KhachHangDTO khachHangDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_KHACHHANG_HOTENKH,khachHangDTO.getHOTENKH());
        contentValues.put(CreateDatabase.TBL_KHACHHANG_EMAIL,khachHangDTO.getEMAIL());
        contentValues.put(CreateDatabase.TBL_KHACHHANG_SDT,khachHangDTO.getSDT());
        contentValues.put(CreateDatabase.TBL_KHACHHANG_GIOITINH,khachHangDTO.getGIOITINH());

        long ktra = database.insert(CreateDatabase.TBL_KHACHHANG,null,contentValues);
        return ktra;
    }

    public long SuaKhachHang(KhachHangDTO khachHangDTO,int makh){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_KHACHHANG_HOTENKH,khachHangDTO.getHOTENKH());
        contentValues.put(CreateDatabase.TBL_KHACHHANG_EMAIL,khachHangDTO.getEMAIL());
        contentValues.put(CreateDatabase.TBL_KHACHHANG_SDT,khachHangDTO.getSDT());
        contentValues.put(CreateDatabase.TBL_KHACHHANG_GIOITINH,khachHangDTO.getGIOITINH());


        long ktra = database.update(CreateDatabase.TBL_KHACHHANG,contentValues,
                CreateDatabase.TBL_KHACHHANG_MAKH+" = "+makh,null);
        return ktra;
    }

    public boolean KtraTonTaiNV(){
        String query = "SELECT * FROM "+CreateDatabase.TBL_NHANVIEN;
        Cursor cursor =database.rawQuery(query,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<KhachHangDTO> LayDSKH(){
        List<KhachHangDTO> khachHangDTOS = new ArrayList<KhachHangDTO>();
        String query = "SELECT * FROM "+CreateDatabase.TBL_KHACHHANG;

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            KhachHangDTO khachHangDTO = new KhachHangDTO();
            khachHangDTO.setHOTENKH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_HOTENKH)));
            khachHangDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_EMAIL)));
            khachHangDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_GIOITINH)));
            khachHangDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_SDT)));
            khachHangDTO.setMAKH(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_MAKH)));


            khachHangDTOS.add(khachHangDTO);
            cursor.moveToNext();
        }
        return khachHangDTOS;
    }



    public KhachHangDTO LayKHTheoMa(int makh){
        KhachHangDTO khachHangDTO = new KhachHangDTO();
        String query = "SELECT * FROM "+CreateDatabase.TBL_KHACHHANG+" WHERE "+CreateDatabase.TBL_KHACHHANG_MAKH+" = "+makh;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            khachHangDTO.setHOTENKH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_HOTENKH)));
            khachHangDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_EMAIL)));
            khachHangDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_GIOITINH)));
            khachHangDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_SDT)));
            khachHangDTO.setMAKH(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_KHACHHANG_MAKH)));

            cursor.moveToNext();
        }
        return khachHangDTO;
    }



}
