package com.example.quanlycafe.Fragment;

import androidx.fragment.app.Fragment;

import com.example.quanlycafe.R;


public class ThongKeSanPhamFragment extends Fragment {

//    ListView lv;
//    RecyclerView rv_topSP;
//    Button btnTuNgay, btnDenNgay, btnTK;
//    MonDAO dao;
//    DonDatDAO thongKeDAO;
//    ArrayList<MonDTO> list;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.activity_top10mon, container, false);
//
//        lv = view.findViewById(R.id.lvTop10);
//        btnTuNgay = view.findViewById(R.id.btnTuNgay);
//        btnDenNgay = view.findViewById(R.id.btnDenNgay);
//        btnTK = view.findViewById(R.id.btnTK);
//
//        //
//        final Calendar cldr = Calendar.getInstance();
//        final int day = cldr.get(Calendar.DAY_OF_MONTH);
//        final int month = cldr.get(Calendar.MONTH);
//        final int year = cldr.get(Calendar.YEAR);
//        //
//
//        btnTuNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        if(monthOfYear < 9 && dayOfMonth < 10){
//                            btnTuNgay.setText(year+"/"+"0"+(monthOfYear+1)+"/"+"0"+dayOfMonth);
//                        } else if(monthOfYear < 9){
//                            btnTuNgay.setText(year+"/"+"0"+(monthOfYear+1)+"/"+dayOfMonth);
//                        }else if(dayOfMonth < 10){
//                            btnTuNgay.setText(year+"/"+(monthOfYear+1)+"/"+"0"+dayOfMonth);
//                        }else{
//                            btnTuNgay.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
//                        }
//                    }
//                }, year, month, day);
//                datePicker.show();
//            }
//        });
//
//        btnDenNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        if(monthOfYear < 9 && dayOfMonth < 10){
//                            btnDenNgay.setText(year+"/"+"0"+(monthOfYear+1)+"/"+"0"+dayOfMonth);
//                        } else if(monthOfYear < 9){
//                            btnDenNgay.setText(year+"/"+"0"+(monthOfYear+1)+"/"+dayOfMonth);
//                        }else if(dayOfMonth < 10){
//                            btnDenNgay.setText(year+"/"+(monthOfYear+1)+"/"+"0"+dayOfMonth);
//                        }else{
//                            btnDenNgay.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
//                        }
//                    }
//                }, year, month, day);
//                datePicker.show();
//            }
//        });
//
//
//        btnTK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //set tong doanh so
//                thongKeDAO = new DonDatDAO(getContext());
//                list = new ArrayList<MonDTO>();
//                list = thongKeDAO.getTop10();
//                adapter = new Top10Adapter(getActivity(), list);
//                lv.setAdapter(adapter);
//            }
//        });
//        return view;
//    }
}
