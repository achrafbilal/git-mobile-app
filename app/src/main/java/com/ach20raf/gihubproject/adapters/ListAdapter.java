package com.ach20raf.gihubproject.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ach20raf.gihubproject.R;
import com.ach20raf.gihubproject.models.GitUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<GitUser> {
	public static final String TAG="ListAdapterTag";
	public ListAdapter(@NonNull Context context, @NonNull ArrayList<GitUser> objects) {
		super(context,R.layout.git_user_item, objects);
		Log.d(TAG, "ListAdapter: ");
	}
	
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		Log.d(TAG, "getView: ");
		GitUser user=getItem(position);
		if(convertView==null)
		{
			convertView= LayoutInflater.from(getContext()).inflate(R.layout.git_user_item,parent,false);
		}
		Log.d(TAG, "getView: "+position);
		ImageView imageView=convertView.findViewById(R.id.avatar_img);
		TextView txt_login=convertView.findViewById(R.id.txt_login);
		TextView txt_id=convertView.findViewById(R.id.txt_id);
		Picasso.get()
				.load(user.getAvatar_url())
				.resize(80, 80)
				.centerCrop()
				.into(imageView);
		txt_login.setText(user.getLogin());
		txt_id.setText(user.getId()+"");
		return convertView;
	}
}
