package com.example.quanlycafe.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlycafe.DTO.LoaiMonDTO;
import com.example.quanlycafe.R;


import java.util.List;

public class AdapterDisplayCategory extends BaseAdapter {

    Context context;
    int layout;
    List<LoaiMonDTO> loaiMonDTOList ;
    ViewHolder viewHolder;

    public AdapterDisplayCategory(Context context, int layout, List<LoaiMonDTO> loaiMonDTOList){
        this.context = context;
        this.layout = layout;
        this.loaiMonDTOList = loaiMonDTOList;
    }

    @Override
    public int getCount() {
        return loaiMonDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiMonDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loaiMonDTOList.get(position).getMaLoai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.img_customcategory_HinhLoai = (ImageView)view.findViewById(R.id.img_customcategory_HinhLoai);
            viewHolder.txt_customcategory_TenLoai = (TextView)view.findViewById(R.id.txt_customcategory_TenLoai);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiMonDTO loaiMonDTO = loaiMonDTOList.get(position);

        viewHolder.txt_customcategory_TenLoai.setText(loaiMonDTO.getTenLoai());

        byte[] categoryimage = loaiMonDTO.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(categoryimage,0,categoryimage.length);
        viewHolder.img_customcategory_HinhLoai.setImageBitmap(bitmap);

        return view;
    }

    public class ViewHolder{
        TextView txt_customcategory_TenLoai;
        ImageView img_customcategory_HinhLoai;
    }
}
