package com.example.intentsample;

import com.example.intentsample.R;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// アプリ内アクティビティ起動
	public void buttonActivity(View button){
		Intent intent = new Intent(
				this, SubActivity.class);
		startActivity(intent);
	}
	
	// 他アプリ起動
	public void buttonOtherActivity(View button){
		try{
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setClassName(
				"com.example.ths00000_mysound", // パッケージ名
				"com.example.ths00000_mysound.MainActivity"); // クラス名
			startActivity(intent);
		}
		catch(Exception ex){
			Toast.makeText(this,
				ex.getMessage(),
				Toast.LENGTH_SHORT).show();
		}
	}
	
	// ブラウザ起動
	public void buttonBrowser(View button){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(
			Uri.parse("http://www.google.co.jp"));
		startActivity(intent);
	}
	
	// メール起動
	public void buttonMail(View buton){
		Intent intent = new Intent(
			Intent.ACTION_VIEW,
			Uri.parse("mailto:koba@a.com"));
		startActivity(intent);
		//Intent intent = new Intent();
		//intent.setAction(ACTION_VIEW),
		//intent.setData(Uri.parse("mailto:..."));
	}
	
	// 電話起動
	public void buttonTel(View button){
		try{
			Intent intent = new Intent(
				Intent.ACTION_CALL,
				Uri.parse("tel:117"));
			startActivity(intent);
		}
		catch(Exception ex){
			Toast.makeText(this,
				ex.getMessage(),
				Toast.LENGTH_SHORT).show();
		}
	}
	
	// アルバム起動
	public void buttonAlbum(View button){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, MY_ALBUM);
	}
	private final int MY_ALBUM = 10;
	private final int MY_CAMERA = 11;
	// カメラ起動
	public void buttonCamera(View button){
		Intent intent = new Intent();
		intent.setAction(
			MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, MY_CAMERA);
	}
	@Override
	protected void onActivityResult( // 戻ってきたときの処理
			int requestCode, int resultCode, Intent data) {
		if(requestCode != MY_ALBUM
				&& requestCode != MY_CAMERA)return;
		if(resultCode != Activity.RESULT_OK)return;
		Uri uri = data.getData(); // 写真のURI
		// イメージビューに設定
		ImageView v = (ImageView)findViewById(
				R.id.imageView1);
		//v.setImageURI(uri);
		Bitmap bitmap = ImageUtils.decodeUri(
				this, uri);
		v.setImageBitmap(bitmap);
		
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
	

	
	

}
