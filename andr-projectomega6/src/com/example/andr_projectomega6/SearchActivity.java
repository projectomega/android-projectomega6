package com.example.andr_projectomega6;

import com.example.andr_projectomega6.DataSource;
import com.example.andr_projectomega6.QuoteDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseAnalytics;

public class SearchActivity extends Activity{
	private ListView mListView;
	public class QuoteAdapter extends BaseAdapter {
		private Context mContext;
		private LayoutInflater mInflator;
		private DataSource mDataSource;
		
		public QuoteAdapter(Context c) {
	        mContext = c;
	        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        mDataSource = new DataSource();
	}
        @Override
        public int getCount() {
        	return mDataSource.getDataSourceLength();
        }
 
        @Override
        public Object getItem(int position) {
            return position;
        }
 
        @Override
        public long getItemId(int position) {
            return position;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	ImageView thumbnail;
        	TextView quote;
         
                if(convertView == null) {
        		convertView = mInflator.inflate(R.layout.list_item_layout, parent, false);
        	}
         
        	thumbnail = (ImageView) convertView.findViewById(R.id.list_thumb);
        	thumbnail.setImageResource(mDataSource.getmPhotoPool().get(position));
        	quote = (TextView) convertView.findViewById(R.id.list_text);
        	quote.setText(mDataSource.getmQuotePool().get(position));
        	return convertView;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
    		  Parse.initialize(this, "P3LHQiqqiFlDSRGZvZk7MIrDXGoQrkCtVnl4V45y", "3CYEN7tfx1SwtH0RPvvaWPWcByReQeJxP4nLw197");
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_search);
		mListView = (ListView) findViewById(R.id.quotes_list);
		if (mListView == null) { Log.w("", "ListView is null"); }
		mListView.setAdapter(new QuoteAdapter(this));


/*		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.quotes_list, new PlaceholderFragment()).commit();
		}*/
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			 
			@Override
			public void onItemClick(AdapterView arg0, View arg1, int position,
								long arg3) {
				Intent i = new Intent(SearchActivity.this, QuoteDetail.class);
				i.putExtra("position", position);
				startActivity(i);
				}
			});
        Button userProfile  = (Button) findViewById(R.id.user);
 
        // Listening to register new account link
        userProfile.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
