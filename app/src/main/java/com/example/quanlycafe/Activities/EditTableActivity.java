package com.example.quanlycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlycafe.DAO.BanAnDAO;
import com.google.android.material.textfield.TextInputLayout;
import com.example.quanlycafe.R;

public class EditTableActivity extends AppCompatActivity {

    TextInputLayout TXTL_edittable_tenban;
    Button BTN_edittable_SuaBan;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittable_layout);

        TXTL_edittable_tenban = (TextInputLayout)findViewById(R.id.txtl_edittable_tenban);
        BTN_edittable_SuaBan = (Button)findViewById(R.id.btn_edittable_SuaBan);

        banAnDAO = new BanAnDAO(this);
        int maban = getIntent().getIntExtra("maban",0);

        BTN_edittable_SuaBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenban = TXTL_edittable_tenban.getEditText().getText().toString();

                if(tenban != null || tenban.equals("")){
                    boolean ktra = banAnDAO.CapNhatTenBan(maban,tenban);
                    Intent intent = new Intent();
                    intent.putExtra("ketquasua",ktra);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}