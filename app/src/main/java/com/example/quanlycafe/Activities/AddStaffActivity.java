package com.example.quanlycafe.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlycafe.DAO.NhanVienDAO;
import com.google.android.material.textfield.TextInputLayout;
import com.example.quanlycafe.DTO.NhanVienDTO;
import com.example.quanlycafe.R;

import java.util.regex.Pattern;

public class AddStaffActivity extends AppCompatActivity implements View.OnClickListener{

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$");

    ImageView IMG_addstaff_back;
    TextView TXT_addstaff_title;
    TextInputLayout TXTL_addstaff_HoVaTen, TXTL_addstaff_TenDN, TXTL_addstaff_Email, TXTL_addstaff_SDT, TXTL_addstaff_MatKhau;
    RadioGroup RG_addstaff_GioiTinh,rg_addstaff_Quyen;
    RadioButton RD_addstaff_Nam,RD_addstaff_Nu,RD_addstaff_Khac,rd_addstaff_QuanLy,rd_addstaff_NhanVien;
    DatePicker DT_addstaff_NgaySinh;
    Button BTN_addstaff_ThemNV;
    NhanVienDAO nhanVienDAO;
    String hoTen,tenDN,eMail,sDT,matKhau,gioiTinh,ngaySinh;
    int manv = 0,quyen = 0;
    long ktra = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstaff_layout);

        TXT_addstaff_title = (TextView)findViewById(R.id.txt_addstaff_title);
        IMG_addstaff_back = (ImageView)findViewById(R.id.img_addstaff_back);
        TXTL_addstaff_HoVaTen = (TextInputLayout)findViewById(R.id.txtl_addstaff_HoVaTen);
        TXTL_addstaff_TenDN = (TextInputLayout)findViewById(R.id.txtl_addstaff_TenDN);
        TXTL_addstaff_Email = (TextInputLayout)findViewById(R.id.txtl_addstaff_Email);
        TXTL_addstaff_SDT = (TextInputLayout)findViewById(R.id.txtl_addstaff_SDT);
        TXTL_addstaff_MatKhau = (TextInputLayout)findViewById(R.id.txtl_addstaff_MatKhau);
        RG_addstaff_GioiTinh = (RadioGroup)findViewById(R.id.rg_addstaff_GioiTinh);
        rg_addstaff_Quyen = (RadioGroup)findViewById(R.id.rg_addstaff_Quyen);
        RD_addstaff_Nam = (RadioButton)findViewById(R.id.rd_addstaff_Nam);
        RD_addstaff_Nu = (RadioButton)findViewById(R.id.rd_addstaff_Nu);
        RD_addstaff_Khac = (RadioButton)findViewById(R.id.rd_addstaff_Khac);
        rd_addstaff_QuanLy = (RadioButton)findViewById(R.id.rd_addstaff_QuanLy);
        rd_addstaff_NhanVien = (RadioButton)findViewById(R.id.rd_addstaff_NhanVien);
        DT_addstaff_NgaySinh = (DatePicker)findViewById(R.id.dt_addstaff_NgaySinh);
        BTN_addstaff_ThemNV = (Button)findViewById(R.id.btn_addstaff_ThemNV);


        nhanVienDAO = new NhanVienDAO(this);

        manv = getIntent().getIntExtra("manv",0);   //lấy manv từ display staff
        if(manv != 0){
            TXT_addstaff_title.setText("Sửa nhân viên");
            NhanVienDTO nhanVienDTO = nhanVienDAO.LayNVTheoMa(manv);

            TXTL_addstaff_HoVaTen.getEditText().setText(nhanVienDTO.getHOTENNV());
            TXTL_addstaff_TenDN.getEditText().setText(nhanVienDTO.getTENDN());
            TXTL_addstaff_Email.getEditText().setText(nhanVienDTO.getEMAIL());
            TXTL_addstaff_SDT.getEditText().setText(nhanVienDTO.getSDT());
            TXTL_addstaff_MatKhau.getEditText().setText(nhanVienDTO.getMATKHAU());

            String gioitinh = nhanVienDTO.getGIOITINH();
            if(gioitinh.equals("Nam")){
                RD_addstaff_Nam.setChecked(true);
            }else if (gioitinh.equals("Nữ")){
                RD_addstaff_Nu.setChecked(true);
            }else {
                RD_addstaff_Khac.setChecked(true);
            }

            if(nhanVienDTO.getMAQUYEN() == 1){
                rd_addstaff_QuanLy.setChecked(true);
            }else {
                rd_addstaff_NhanVien.setChecked(true);
            }

            String date = nhanVienDTO.getNGAYSINH();
            String[] items = date.split("/");
            int day = Integer.parseInt(items[0]);
            int month = Integer.parseInt(items[1]) - 1;
            int year = Integer.parseInt(items[2]);
            DT_addstaff_NgaySinh.updateDate(year,month,day);
            BTN_addstaff_ThemNV.setText("Sửa nhân viên");
        }

        BTN_addstaff_ThemNV.setOnClickListener(this);
        IMG_addstaff_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        int id = v.getId();
//        String chucnang;
//        switch (id){
//            case R.id.btn_addstaff_ThemNV:
//                if( !validateAge() | !validateEmail() | !validateFullName() | !validateGender() | !validatePassWord() |
//                !validatePermission() | !validatePhone() | !validateUserName()){
//                    return;
//                }
//                //Lấy dữ liệu từ view
//                hoTen = TXTL_addstaff_HoVaTen.getEditText().getText().toString();
//                tenDN = TXTL_addstaff_TenDN.getEditText().getText().toString();
//                eMail = TXTL_addstaff_Email.getEditText().getText().toString();
//                sDT = TXTL_addstaff_SDT.getEditText().getText().toString();
//                matKhau = TXTL_addstaff_MatKhau.getEditText().getText().toString();
//
//                switch (RG_addstaff_GioiTinh.getCheckedRadioButtonId()){
//                    case R.id.rd_addstaff_Nam: gioiTinh = "Nam"; break;
//                    case R.id.rd_addstaff_Nu: gioiTinh = "Nữ"; break;
//                    case R.id.rd_addstaff_Khac: gioiTinh = "Khác"; break;
//                }
//                switch (rg_addstaff_Quyen.getCheckedRadioButtonId()){
//                    case R.id.rd_addstaff_QuanLy: quyen = 1; break;
//                    case R.id.rd_addstaff_NhanVien: quyen = 2; break;
//                }
//
//                ngaySinh = DT_addstaff_NgaySinh.getDayOfMonth() + "/" + (DT_addstaff_NgaySinh.getMonth() + 1)
//                        +"/"+DT_addstaff_NgaySinh.getYear();
//
//                //truyền dữ liệu vào obj nhanvienDTO
//                NhanVienDTO nhanVienDTO = new NhanVienDTO();
//                nhanVienDTO.setHOTENNV(hoTen);
//                nhanVienDTO.setTENDN(tenDN);
//                nhanVienDTO.setEMAIL(eMail);
//                nhanVienDTO.setSDT(sDT);
//                nhanVienDTO.setMATKHAU(matKhau);
//                nhanVienDTO.setGIOITINH(gioiTinh);
//                nhanVienDTO.setNGAYSINH(ngaySinh);
//                nhanVienDTO.setMAQUYEN(quyen);
//
//                if(manv != 0){
//                    ktra = nhanVienDAO.SuaNhanVien(nhanVienDTO,manv);
//                    chucnang = "sua";
//                }else {
//                    ktra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
//                    chucnang = "themnv";
//                }
//                //Thêm, sửa nv dựa theo obj nhanvienDTO
//                Intent intent = new Intent();
//                intent.putExtra("ketquaktra",ktra);
//                intent.putExtra("chucnang",chucnang);
//                setResult(RESULT_OK,intent);
//                finish();
//                break;
//
//            case R.id.img_addstaff_back:
//                finish();
//                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
//                break;
//        }
    }

    //region validate fields
//    private boolean validateFullName(){
//        String val = TXTL_addstaff_HoVaTen.getEditText().getText().toString().trim();
//
//        if(val.isEmpty()){
//            TXTL_addstaff_HoVaTen.setError(getResources().getString(R.string.not_empty));
//            return false;
//        }else {
//            TXTL_addstaff_HoVaTen.setError(null);
//            TXTL_addstaff_HoVaTen.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateUserName(){
//        String val = TXTL_addstaff_TenDN.getEditText().getText().toString().trim();
//        String checkspaces = "\\A\\w{1,50}\\z";
//
//        if(val.isEmpty()){
//            TXTL_addstaff_TenDN.setError(getResources().getString(R.string.not_empty));
//            return false;
//        }else if(val.length()>50){
//            TXTL_addstaff_TenDN.setError("Phải nhỏ hơn 50 ký tự");
//            return false;
//        }else if(!val.matches(checkspaces)){
//            TXTL_addstaff_TenDN.setError("Không được cách chữ!");
//            return false;
//        }
//        else {
//            TXTL_addstaff_TenDN.setError(null);
//            TXTL_addstaff_TenDN.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateEmail(){
//        String val = TXTL_addstaff_Email.getEditText().getText().toString().trim();
//        String checkspaces = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
//
//        if(val.isEmpty()){
//            TXTL_addstaff_Email.setError(getResources().getString(R.string.not_empty));
//            return false;
//        }else if(!val.matches(checkspaces)){
//            TXTL_addstaff_Email.setError("Email không hợp lệ!");
//            return false;
//        }
//        else {
//            TXTL_addstaff_Email.setError(null);
//            TXTL_addstaff_Email.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validatePhone(){
//        String val = TXTL_addstaff_SDT.getEditText().getText().toString().trim();
//
//
//        if(val.isEmpty()){
//            TXTL_addstaff_SDT.setError(getResources().getString(R.string.not_empty));
//            return false;
//        }else if(val.length() != 10){
//            TXTL_addstaff_SDT.setError("Số điện thoại không hợp lệ!");
//            return false;
//        }
//        else {
//            TXTL_addstaff_SDT.setError(null);
//            TXTL_addstaff_SDT.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validatePassWord(){
//        String val = TXTL_addstaff_MatKhau.getEditText().getText().toString().trim();
//
//        if(val.isEmpty()){
//            TXTL_addstaff_MatKhau.setError(getResources().getString(R.string.not_empty));
//            return false;
//        }else if(!PASSWORD_PATTERN.matcher(val).matches()){
//            TXTL_addstaff_MatKhau.setError("Mật khẩu ít nhất 6 ký tự!");
//            return false;
//        }
//        else {
//            TXTL_addstaff_MatKhau.setError(null);
//            TXTL_addstaff_MatKhau.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateGender(){
//        if(RG_addstaff_GioiTinh.getCheckedRadioButtonId() == -1){
//            Toast.makeText(this,"Hãy chọn giới tính",Toast.LENGTH_SHORT).show();
//            return false;
//        }else {
//            return true;
//        }
//    }
//
//    private boolean validatePermission(){
//        if(rg_addstaff_Quyen.getCheckedRadioButtonId() == -1){
//            Toast.makeText(this,"Hãy chọn quyền",Toast.LENGTH_SHORT).show();
//            return false;
//        }else {
//            return true;
//        }
//    }
//
//    private boolean validateAge(){
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        int userAge = DT_addstaff_NgaySinh.getYear();
//        int isAgeValid = currentYear - userAge;
//
//        if(isAgeValid < 10){
//            Toast.makeText(this,"Bạn không đủ tuổi đăng ký!",Toast.LENGTH_SHORT).show();
//            return false;
//        }else {
//            return true;
//        }
//    }

}