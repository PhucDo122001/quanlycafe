package com.example.quanlycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlycafe.DAO.KhachHangDAO;
import com.example.quanlycafe.DTO.KhachHangDTO;
import com.google.android.material.textfield.TextInputLayout;
import com.example.quanlycafe.R;

import java.util.regex.Pattern;

public class AddCustomer extends AppCompatActivity implements View.OnClickListener{

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$");

    ImageView img_back;
    TextView txt_title;
    TextInputLayout txtl_HoVaTen, txtl_Email, txtl_SDT;
    RadioGroup rg_GioiTinh;
    RadioButton rd_Nam,rd_Nu,rd_Khac;
    Button btn_ThemKH;
    KhachHangDAO khachHangDAO;
    String hoTen,eMail,sDT,gioiTinh;
    int makh = 0;
    long ktra = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcustomer);

        txt_title = (TextView)findViewById(R.id.txt_title);
        img_back = (ImageView)findViewById(R.id.img_back);
        txtl_HoVaTen = (TextInputLayout)findViewById(R.id.txtl_HoVaTen);
        txtl_Email = (TextInputLayout)findViewById(R.id.txtl_Email);
        txtl_SDT = (TextInputLayout)findViewById(R.id.txtl_SDT);
        rg_GioiTinh = (RadioGroup)findViewById(R.id.rg_GioiTinh);
        rd_Nam = (RadioButton)findViewById(R.id.rd_Nam);
        rd_Nu = (RadioButton)findViewById(R.id.rd_Nu);
        rd_Khac = (RadioButton)findViewById(R.id.rd_Khac);
        btn_ThemKH = (Button)findViewById(R.id.btn_ThemKH);


        khachHangDAO = new KhachHangDAO(this);

        makh = getIntent().getIntExtra("makh",0);
        if(makh != 0){
            txt_title.setText("Sửa khách hàng");
            KhachHangDTO khachHangDTO = khachHangDAO.LayKHTheoMa(makh);

            txtl_HoVaTen.getEditText().setText(khachHangDTO.getHOTENKH());
            txtl_Email.getEditText().setText(khachHangDTO.getEMAIL());
            txtl_SDT.getEditText().setText(khachHangDTO.getSDT());

            String gioitinh = khachHangDTO.getGIOITINH();
            if(gioitinh.equals("Nam")){
                rd_Nam.setChecked(true);
            }else if (gioitinh.equals("Nữ")){
                rd_Nu.setChecked(true);
            }else {
                rd_Khac.setChecked(true);
            }


            btn_ThemKH.setText("Sửa khách hàng");
        }

        btn_ThemKH.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String chucnang;
        switch (id){
            case R.id.btn_ThemKH:
                if(  !validateEmail() | !validateFullName() | !validateGender() | !validatePhone()){
                    return;
                }
                hoTen = txtl_HoVaTen.getEditText().getText().toString();

                eMail = txtl_Email.getEditText().getText().toString();
                sDT = txtl_SDT.getEditText().getText().toString();


                switch (rg_GioiTinh.getCheckedRadioButtonId()){
                    case R.id.rd_Nam: gioiTinh = "Nam"; break;
                    case R.id.rd_Nu: gioiTinh = "Nữ"; break;
                    case R.id.rd_Khac: gioiTinh = "Khác"; break;
                }

                KhachHangDTO khachHangDTO = new KhachHangDTO();
                khachHangDTO.setHOTENKH(hoTen);
                khachHangDTO.setEMAIL(eMail);
                khachHangDTO.setSDT(sDT);
                khachHangDTO.setGIOITINH(gioiTinh);


                if(makh != 0){
                    ktra = khachHangDAO.SuaKhachHang(khachHangDTO,makh);
                    chucnang = "sua";
                }else {
                    ktra = khachHangDAO.ThemKhachHang(khachHangDTO);
                    chucnang = "themkh";
                }
                Intent intent = new Intent();
                intent.putExtra("ketquaktra",ktra);
                intent.putExtra("chucnang",chucnang);
                setResult(RESULT_OK,intent);
                finish();
                break;

            case R.id.img_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
    }

    private boolean validateFullName(){
        String val = txtl_HoVaTen.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            txtl_HoVaTen.setError(getResources().getString(R.string.not_empty));
            return false;
        }else {
            txtl_HoVaTen.setError(null);
            txtl_HoVaTen.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String val = txtl_Email.getEditText().getText().toString().trim();
        String checkspaces = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if(val.isEmpty()){
            txtl_Email.setError(getResources().getString(R.string.not_empty));
            return false;
        }else if(!val.matches(checkspaces)){
            txtl_Email.setError("Email không hợp lệ!");
            return false;
        }
        else {
            txtl_Email.setError(null);
            txtl_Email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone(){
        String val = txtl_SDT.getEditText().getText().toString().trim();


        if(val.isEmpty()){
            txtl_SDT.setError(getResources().getString(R.string.not_empty));
            return false;
        }else if(val.length() != 10){
            txtl_SDT.setError("Số điện thoại không hợp lệ!");
            return false;
        }
        else {
            txtl_SDT.setError(null);
            txtl_SDT.setErrorEnabled(false);
            return true;
        }
    }



    private boolean validateGender(){
        if(rg_GioiTinh.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Hãy chọn giới tính",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


}