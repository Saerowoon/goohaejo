package com.example.goohaejo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegiPostGuham extends AppCompatActivity {

    EditText et_product,et_city,et_price,et_fee;
    ImageView iv_regiPostGuhamPicture,iv_regiPostGuham,iv_back;

    String product, city, price, fee;
    String uriString = "";

    private static final int MY_PERMISSION_CAMERA = 1111;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_post_guham);

        et_product = findViewById(R.id.et_product);

        et_city = findViewById(R.id.et_city);

        et_price = findViewById(R.id.et_price);

        et_fee = findViewById(R.id.et_fee);


        iv_regiPostGuhamPicture = findViewById(R.id.iv_regiPostGuhamPicture);
        iv_regiPostGuhamPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegiPostGuham.this);

                builder.setTitle("사진을 등록합니다.").setMessage("무슨 작업을 할까요?");

                builder.setPositiveButton("카메라", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "카메라", Toast.LENGTH_SHORT).show();
                        checkPermission();
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                            File photoFile = null;
                            try{
                                photoFile = createImageFile();
                            }catch (Exception e){

                            }
                            Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                                    "com.example.goohaejo.fileprovider",
                                    photoFile);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            //Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.example.mainapplication.filepovider",photoFile);
                            //putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                            startActivityForResult(cameraIntent, 1000);
                        }
                    }
                });
                builder.setNegativeButton("갤러리", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "갤러리", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        //intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 2000);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });




        iv_regiPostGuham = findViewById(R.id.iv_regiPostGuham);
        iv_regiPostGuham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //어디에서 어디로
                Intent intent = new Intent(RegiPostGuham.this,MainActivity.class);
                product = et_product.getText().toString();
                city = et_city.getText().toString();
                fee = et_fee.getText().toString();
                price = et_price.getText().toString();

                    //다른 정보가 있다면 키값 + 데이터
                	intent.putExtra("title",product);
                    intent.putExtra("region",city);
                    intent.putExtra("fee",fee);
                    intent.putExtra("img",uriString);
                	//인텐트 실행
                	startActivity(intent);
            }
        });

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //어디에서 어디로
                Intent intent = new Intent(RegiPostGuham.this ,MainActivity.class);
                	//인텐트 실행
                	startActivity(intent);
            }
        });
    }
    //사진 가져오기
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File file = new File(mCurrentPhotoPath);

            Uri uri = Uri.fromFile(file);
            mediaScanIntent.setData(uri);
            this.sendBroadcast(mediaScanIntent);
            uriString = uri.toString();



            //가져온 사진
            Glide.with(this).load(uri).centerCrop().into(iv_regiPostGuhamPicture);



        }

        if (requestCode == 2000 && resultCode == RESULT_OK) {

            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Uri selectedImageUri = data.getData();
                uriString = selectedImageUri.toString();



                Glide.with(this).load(selectedImageUri).centerCrop().into(iv_regiPostGuhamPicture);

            } catch (Exception e) {

            }


        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg",storageDir
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //허가 받기
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))) {
                new AlertDialog.Builder(this).setTitle("알림").setMessage("저장소 권한이 거부되었습니다.").setNeutralButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package: " + getPackageName()));
                        startActivity(intent);
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setCancelable(false).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
        }
    }

    @Override
    //허가 결과
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == 0) {
                Toast.makeText(this, "카메라 권한 승인완료", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "카메라 권한 승인 거절", Toast.LENGTH_SHORT).show();
            }
        }
    }


}