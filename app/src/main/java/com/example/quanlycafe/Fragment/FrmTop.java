package com.example.quanlycafe.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.example.quanlycafe.Adapter.AdapterTop;
import com.example.quanlycafe.DAO.topDAO;
import com.example.quanlycafe.DTO.top;
import com.example.quanlycafe.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class FrmTop extends Fragment {
    Button btnTuNgay , btnDenNgay , btnTopSP;
    EditText edTuNgay , edDenNgay;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
    int mYear , mMonth , mDay;
    ListView listView;
    ArrayList<top> listTop;
    AdapterTop adapterTop;


    public FrmTop() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frm_top, container, false);

        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnTopSP = view.findViewById(R.id.btnTopSP);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        listView  = view.findViewById(R.id.lvTop);
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
        btnTopSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topDAO topDAO = new topDAO(getActivity());
                listTop = (ArrayList<top>)topDAO.getTop();
                adapterTop = new AdapterTop(getActivity() , R.layout.fragment_frm_top , listTop);
                listView.setAdapter(adapterTop);
            }
        });
//        topDAO topDAO = new topDAO(getActivity());
//        listTop = (ArrayList<top>)topDAO.getTop();
//        adapterTop = new AdapterTop(getActivity() , this , listTop);
//        listView.setAdapter(adapterTop);
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