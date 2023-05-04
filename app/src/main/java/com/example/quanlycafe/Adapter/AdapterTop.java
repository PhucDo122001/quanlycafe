package com.example.quanlycafe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlycafe.DTO.top;
import com.example.quanlycafe.R;

import java.util.ArrayList;

public class AdapterTop extends ArrayAdapter<top> {
    private Context context;
    int frmTop;
    ArrayList<top> listtop;
    TextView tvSach , tvSoLuong;
    ImageView imgView;

    public AdapterTop(@NonNull Context context, int frmTop, ArrayList<top> listtop) {
        super(context, 0 , listtop);
        this.context = context;
        this.frmTop = frmTop;
        this.listtop = listtop;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_top , null);

        }
        final top item = listtop.get(position);
        if(item != null){
            tvSach = view.findViewById(R.id.tvSachTop);
            tvSach.setText("Tên món: " + item.getTenMon());
            tvSoLuong = view.findViewById(R.id.tvSoLuongTop);
            tvSoLuong.setText("Số lượng đơn đặt: " + item.getSoLuongMon());
        }

        return view;
    }
}
