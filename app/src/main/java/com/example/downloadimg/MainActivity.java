package com.example.downloadimg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
        ImageView imageView;
        class Download extends AsyncTask<String,Void,Bitmap>{

            @Override
            protected Bitmap doInBackground(String... urls) {
                try {
                    URL url =new URL(urls[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                    httpURLConnection.connect();
                    InputStream inputStream =httpURLConnection.getInputStream();
                    Bitmap mybitmap = BitmapFactory.decodeStream(inputStream);
                    return mybitmap;


                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();
                }
                return null;
            }
        }

    public void DownloadImg(View view){
        Log.i("Tapped-->","Button Is tapped");
            Download downloadImage = new  Download();
               Bitmap myimage;
               try {
                   myimage = downloadImage.execute("http://www.nasa.gov/sites/default/files/images/nasaLogo-570x450.png").get();
                    imageView.setImageBitmap(myimage);
               }//downloadImage.execute();
               catch (ExecutionException e) {
                   e.printStackTrace();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
                       }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            imageView = findViewById(R.id.imageView);

        }
}
