package com.ach20raf.gihubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ach20raf.gihubproject.adapters.ListAdapter;
import com.ach20raf.gihubproject.databinding.ActivityMainBinding;
import com.ach20raf.gihubproject.models.GitUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainTag";
	private static final String API_URL="https://api.github.com/users";
	ActivityMainBinding binding;
	ListView listView;
	OkHttpClient client = new OkHttpClient();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding=ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		listView = findViewById(R.id.list_view);
		try {
			
			JSONArray users_json = new JSONArray(getData());
			ArrayList<GitUser> users=new ArrayList<>();
			for (int i = 0; i < users_json.length(); i++) {
				GitUser user=new GitUser();
				JSONObject obj=users_json.getJSONObject(i);
				user.setAvatar_url(obj.getString("avatar_url"));
				user.setLogin(obj.getString("login"));
				user.setId(obj.getInt("id"));
				users.add(user);
			}
			ListAdapter adapter=new ListAdapter(MainActivity.this,users);
			binding.listView.setAdapter(adapter);
			binding.listView.setClickable(true);
			binding.listView.setOnItemClickListener
	             (
	                 (parent,view,position,id)->{
	                    Toast.makeText(getApplicationContext(),String.format("User with login '%s' clicked ",users.get(position).getLogin()),Toast.LENGTH_SHORT).show();
	                 }
	             );
			Log.d(TAG, "onCreate: " + users);
		} catch (IOException e) {
			Log.d(TAG, "onCreate Error IO: " + e.getLocalizedMessage());
		} catch (JSONException e) {
			Log.d(TAG, "onCreate Error JSON: " + e.getLocalizedMessage());
		}
	}
	
	protected String getData() throws IOException {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				                                 .permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Request request = new Request.Builder()
				                  .url(API_URL)
				                  .build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
}