package com.example.quanlycafe.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlycafe.DAO.TabAdapter;
import com.example.quanlycafe.MainActivity;
import com.example.quanlycafe.R;
import com.google.android.material.tabs.TabLayout;


public class ThongKeFragment extends Fragment {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    Toolbar tb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Thống kê");
        setHasOptionsMenu(true);
        //set togle bar cho fragment menu
        tb = view.findViewById(R.id.tg_bar);
        TextView toolbar_title = view.findViewById(R.id.toolbar_title);
        toolbar_title.setText("");
        toolbar_title.setTypeface(Typeface.DEFAULT_BOLD);
        //

        viewPager = view.findViewById(R.id.viewPager_thongke);
        tabLayout = view.findViewById(R.id.tabLayout_thongke);

        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new DisplayTKFragment(), "Doanh số");

        // Thống kê số lần đồ uống đc gọi trong các đơn đặt
//        adapter.addFragment(new FrmTop(), "Top sản phẩm bán chạy");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
