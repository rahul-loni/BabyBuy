package com.example.baby_buy;

import static com.example.baby_buy.DB.DBmain.TABLENAME;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baby_buy.DB.DBmain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class AddItems extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    EditText txt_name,txt_price,txt_dis;
    Button addItemsBtn;
    ImageView imageView;
    int id=0;
    public static final int CAMERA_REQUEST=100;
    public static final int STORAGE_REQUEST=101;
    String[] cameraPermission;
    String[] storagePermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        dBmain=new DBmain(this);
        findid();
        insertData();
        imagePicker();
    }

    private void imagePicker() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                int image=0;
                if (image==0){
                    if (!checkCamerapermision()){
                        requestCameraPermission();
                    }else {
                        pickFromGallery();
                    }

                    }else if (image==1){
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }else {
                        pickFromGallery();
                    }
                }
            }
        });
    }

    private void pickFromGallery() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission,CAMERA_REQUEST);
    }

    private boolean checkCamerapermision() {
        boolean result= ContextCompat.checkSelfPermission
                (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result2=ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        return result && result2;

    }

    private void insertData() {
        addItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("image",ImageViewToByte(imageView));
                cv.put("name", txt_name.getText().toString());
                cv.put("price", txt_price.getText().toString());
                cv.put("dis", txt_dis.getText().toString());
                sqLiteDatabase=dBmain.getWritableDatabase();
                Long recinsert=sqLiteDatabase.insert(TABLENAME,null,cv);
                if (recinsert!=null){
                    Toast.makeText(AddItems.this, "inserted successfully ", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    txt_name.setText("");
                    txt_price.setText("");
                    txt_dis.setText("");
                }
            }
        });
    }

    private byte[] ImageViewToByte(ImageView imageView) {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[]bytes=stream.toByteArray();
        return bytes;
    }

    private void findid() {
        txt_name=findViewById(R.id.addName);
        txt_price=findViewById(R.id.addPrice);
        txt_dis=findViewById(R.id.addDis);
        addItemsBtn=findViewById(R.id.btn_addItems);
        imageView=findViewById(R.id.addImage);
    }
}