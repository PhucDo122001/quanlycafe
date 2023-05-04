package com.example.quanlycafe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.quanlycafe.DTO.KhachHangDTO;
import com.example.quanlycafe.R;

import java.util.List;

public class AdapterDisplayCustomer extends BaseAdapter {

    Context context;
    int layout;
    List<KhachHangDTO> khachHangDTOS;
    ViewHolder viewHolder;

    public AdapterDisplayCustomer(Context context, int layout, List<KhachHangDTO> khachHangDTOS){
        this.context = context;
        this.layout = layout;
        this.khachHangDTOS = khachHangDTOS;
    }

    @Override
    public int getCount() {
        return khachHangDTOS.size();
    }

    @Override
    public Object getItem(int position) {
        return khachHangDTOS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return khachHangDTOS.get(position).getMAKH();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.img_HinhNV = (ImageView)view.findViewById(R.id.img_HinhNV);
            viewHolder.txt_TenNV = (TextView)view.findViewById(R.id.txt_TenNV);
            viewHolder.txt_SDT = (TextView)view.findViewById(R.id.txt_SDT);
            viewHolder.txt_Email = (TextView)view.findViewById(R.id.txt_Email);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        KhachHangDTO khachHangDTO = khachHangDTOS.get(position);

        viewHolder.txt_TenNV.setText(khachHangDTO.getHOTENKH());
        viewHolder.txt_SDT.setText(khachHangDTO.getSDT());
        viewHolder.txt_Email.setText(khachHangDTO.getEMAIL());

        return view;
    }

    public class ViewHolder{
        ImageView img_HinhNV;
        TextView txt_TenNV,txt_SDT, txt_Email;
    }
}
