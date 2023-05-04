package com.example.quanlycafe.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quanlycafe.Activities.DetailStatisticActivity;
import com.example.quanlycafe.Adapter.AdapterDisplayStatistic;
import com.example.quanlycafe.DAO.DonDatDAO;
import com.example.quanlycafe.DTO.DonDatDTO;
import com.example.quanlycafe.MainActivity;
import com.example.quanlycafe.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class DisplayTKFragment extends Fragment {
// doanh thu
Button btnTuNgay , btnDenNgay , btnDoanhThu;
    EditText edTuNgay , edDenNgay;
    TextView tvDoanhThu;
    List<DonDatDTO> donDatDTOS;
    DonDatDAO donDatDAO;
    AdapterDisplayStatistic adapterDisplayStatistic;
    ListView lvStatistic;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
    int mYear , mMonth , mDay;
    ArrayList<DonDatDTO> listHD;
    RecyclerView rv_tk;
    int madon, manv, maban;
    String ngaydat, tongtien;
    private double TongDS;
    Toolbar tb;
    public DisplayTKFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_display_thong_ke, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Thống kê");
        setHasOptionsMenu(true);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        lvStatistic = (ListView)view.findViewById(R.id.lvStatistic);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);

        donDatDAO = new DonDatDAO(getActivity());

        donDatDTOS = donDatDAO.LayDSDonDat();
        adapterDisplayStatistic = new AdapterDisplayStatistic(getActivity(), R.layout.custom_layout_displaystatistic,donDatDTOS);
        lvStatistic.setAdapter(adapterDisplayStatistic);
        adapterDisplayStatistic.notifyDataSetChanged();


        lvStatistic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                madon = donDatDTOS.get(position).getMaDonDat();
                manv = donDatDTOS.get(position).getMaNV();
                maban = donDatDTOS.get(position).getMaBan();
                ngaydat = donDatDTOS.get(position).getNgayDat();
                tongtien = donDatDTOS.get(position).getTongTien();

                Intent intent = new Intent(getActivity(), DetailStatisticActivity.class);
                intent.putExtra("madon",madon);
                intent.putExtra("manv",manv);
                intent.putExtra("maban",maban);
                intent.putExtra("ngaydat",ngaydat);
                intent.putExtra("tongtien",tongtien);
                startActivity(intent);
            }
        });

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity() , 0 , mDateTuNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity() , 0 , mDateDenNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                DonDatDAO phieuMuonDAO = new DonDatDAO(getContext());
                tvDoanhThu.setText("Doanh thu : "+phieuMuonDAO.getDoanhThu(tuNgay , denNgay) +" VNĐ");
//                donDatDTOS = donDatDAO.LayDSDonDat();
//                adapterDisplayStatistic = new AdapterDisplayStatistic(getActivity(), R.layout.custom_layout_displaystatistic,donDatDTOS);
//                lvStatistic.setAdapter(adapterDisplayStatistic);
//                adapterDisplayStatistic.notifyDataSetChanged();
            }
        });
        return view;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            edTuNgay.setText(date1.format(calendar.getTime()));

        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            edDenNgay.setText(date1.format(calendar.getTime()));

        }
    };
}