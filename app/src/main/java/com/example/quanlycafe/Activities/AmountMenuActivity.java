package com.example.quanlycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlycafe.DAO.ChiTietDonDatDAO;
import com.example.quanlycafe.DAO.DonDatDAO;
import com.example.quanlycafe.DTO.ChiTietDonDatDTO;
import com.example.quanlycafe.R;
import com.google.android.material.textfield.TextInputLayout;


public class AmountMenuActivity extends AppCompatActivity {

    TextInputLayout TXTL_amountmenu_SoLuong, TXTL_amountmenu_GhiChu;
    Button BTN_amountmenu_DongY;
    int maban, mamon;
    DonDatDAO donDatDAO;
    ChiTietDonDatDAO chiTietDonDatDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_menu_layout);

        TXTL_amountmenu_SoLuong = (TextInputLayout)findViewById(R.id.txtl_amountmenu_SoLuong);
        TXTL_amountmenu_GhiChu = (TextInputLayout)findViewById(R.id.txtl_amountmenu_GhiChu);
        BTN_amountmenu_DongY = (Button)findViewById(R.id.btn_amountmenu_DongY);


        donDatDAO = new DonDatDAO(this);
        chiTietDonDatDAO = new ChiTietDonDatDAO(this);

        Intent intent = getIntent();
        maban = intent.getIntExtra("maban",0);
        mamon = intent.getIntExtra("mamon",0);

        BTN_amountmenu_DongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateAmount()){
                    return;
                }

                int madondat = (int) donDatDAO.LayMaDonTheoMaBan(maban,"false");
                boolean ktra = chiTietDonDatDAO.KiemTraMonTonTai(madondat,mamon);
                if(ktra){
                    int sluongcu = chiTietDonDatDAO.LaySLMonTheoMaDon(madondat,mamon);
                    int sluongmoi = Integer.parseInt(TXTL_amountmenu_SoLuong.getEditText().getText().toString());
                    int tongsl = sluongcu + sluongmoi;

                    String ghichumoi = TXTL_amountmenu_GhiChu.getEditText().getText().toString();

                    ChiTietDonDatDTO chiTietDonDatDTO = new ChiTietDonDatDTO();
                    chiTietDonDatDTO.setMaMon(mamon);
                    chiTietDonDatDTO.setMaDonDat(madondat);
                    chiTietDonDatDTO.setSoLuong(tongsl);
                    chiTietDonDatDTO.setGhichu((ghichumoi));

                    boolean ktracapnhat = chiTietDonDatDAO.CapNhatSL(chiTietDonDatDTO);
                    if(ktracapnhat){
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.add_sucessful),Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }else {
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.add_failed),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    int sluong = Integer.parseInt(TXTL_amountmenu_SoLuong.getEditText().getText().toString());
                    String ghichu = TXTL_amountmenu_GhiChu.getEditText().getText().toString();
                    ChiTietDonDatDTO chiTietDonDatDTO = new ChiTietDonDatDTO();
                    chiTietDonDatDTO.setMaMon(mamon);
                    chiTietDonDatDTO.setMaDonDat(madondat);
                    chiTietDonDatDTO.setSoLuong(sluong);
                    chiTietDonDatDTO.setGhichu(ghichu);

                    boolean ktracapnhat = chiTietDonDatDAO.ThemChiTietDonDat(chiTietDonDatDTO);
                    if(ktracapnhat){
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.add_sucessful),Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }else {
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.add_failed),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateAmount(){
        String val = TXTL_amountmenu_SoLuong.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            TXTL_amountmenu_SoLuong.setError(getResources().getString(R.string.not_empty));
            return false;
        }else if(!val.matches(("\\d+(?:\\.\\d+)?"))){
            TXTL_amountmenu_SoLuong.setError("Số lượng không hợp lệ");
            return false;
        }else {
            TXTL_amountmenu_SoLuong.setError(null);
            TXTL_amountmenu_SoLuong.setErrorEnabled(false);
            return true;
        }
    }
}