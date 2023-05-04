package com.example.quanlycafe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlycafe.DAO.MonDAO;
import com.example.quanlycafe.DTO.MonDTO;
import com.example.quanlycafe.R;

import java.util.List;

public class Top10Adapter extends BaseAdapter {
    Context context;
    MonDAO monDAO;
    List<MonDTO> list;

    public Top10Adapter(Context context, List<MonDTO> list) {
        this.context = context;
        monDAO = new MonDAO(context);
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewOfItem{
        TextView tvMaS,tvTenS,tvSLM;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewOfItem viewOfItem;
        if (convertView==null){
            viewOfItem = new ViewOfItem();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_top10,null);

            viewOfItem.tvMaS = convertView.findViewById(R.id.tvMaS);
            viewOfItem.tvTenS = convertView.findViewById(R.id.tvTenS);
            viewOfItem.tvSLM = convertView.findViewById(R.id.tvSLM);

            convertView.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) convertView.getTag();
        }
        viewOfItem.tvMaS.setText(String.valueOf(list.get(position).getMaMon()));
        viewOfItem.tvTenS.setText(list.get(position).getTenMon());
        viewOfItem.tvSLM.setText(String.valueOf(list.get(position).getHinhAnh()));


        return convertView;
    }
}
