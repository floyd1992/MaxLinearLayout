package com.jyqqhw.maxlinearlayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wj on 16-9-27.
 */
public class FIrst9 extends Activity {

	private ListView listView;
	private List<Map<String, Object>> lists = new ArrayList<>();
	private Button b1, b2, b3, b4, b5;
	private MyAdapter adapter;
	private boolean more;
	private MaxLinearLayout linearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_9);
		init();
		initBtn();
	}

	private void init(){
		listView = (ListView) findViewById(R.id.list);
		for (int i=0; i< 12;i++){
			Map map = new HashMap();
			map.put("text", "吃饭睡觉打豆豆 "+i);
			map.put("image", R.mipmap.ic_launcher);
			lists.add(map);
		}
		adapter = new MyAdapter(this, lists, R.layout.list_item);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(FIrst9.this, "list on click item "+position, Toast.LENGTH_SHORT).show();
				Log.i("wj","list on click item "+position);
			}
		});

		linearLayout = (MaxLinearLayout) findViewById(R.id.linear_layout);
		linearLayout.setMaxHeight(435);

		listView.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				Log.i("wj","first="+firstVisibleItem+" ,visibleCount="+
						visibleItemCount+" , total="+totalItemCount);
				if(!view.canScrollVertically(-1)){
					linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
				}else{
					linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING);
				}
//				if(0 < firstVisibleItem || (0==firstVisibleItem&&view.getTop()>0) ){
//					linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING);
//				}else{
//					linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
//				}
			}
		});
	}

	private void initBtn(){
		b1 = (Button) findViewById(R.id.bug_1);
		b2 = (Button) findViewById(R.id.bug_2);
		b3 = (Button) findViewById(R.id.bug_3);
		b4 = (Button) findViewById(R.id.bug_4);
		b5 = (Button) findViewById(R.id.bug_5);

		b1.setOnClickListener(clickListener);
		b2.setOnClickListener(clickListener);
		b3.setOnClickListener(clickListener);
		b4.setOnClickListener(clickListener);
		b5.setOnClickListener(clickListener);
	}

	private View.OnClickListener clickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.bug_1:
					int visible = listView.getVisibility() == View.VISIBLE? View.GONE: View.VISIBLE;
					listView.setVisibility(visible);
					break;
				case R.id.bug_2:
					changeDataSet(more = !more);
					break;
				case R.id.bug_3:
					if(lists.size()>=0){
						lists.remove(0);
						if(null != adapter){
							adapter.notifyDataSetChanged();
						}
					}
					break;

				case R.id.bug_4:
					LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) listView.getLayoutParams();
					if(lp.height<500){
						lp.height += 20;
					}else{
						lp.height = 100;
					}
					listView.setLayoutParams(lp);
					break;

				case R.id.bug_5:
					int h = linearLayout.getMaxHeight();
					if(h<600){
						h += 20;
					}else{
						h = 100;
					}
					linearLayout.setMaxHeight(h);
					break;
				default:
					break;
			}
		}
	};

	private void changeDataSet(boolean more){
		if(null == lists){
			return;
		}
		lists.clear();
		int len = 26;
		if(!more){
			len = 6;
		}
		for (int i=0; i< len;i++){
			Map map = new HashMap();
			map.put("text", "吃饭睡觉打豆豆 "+i);
			map.put("image", R.mipmap.ic_launcher);
			lists.add(map);
		}
		if(null != adapter){
			adapter.notifyDataSetChanged();
		}
		linearLayout.setMaxHeight(435);
	}




	class MyAdapter extends BaseAdapter {

		private List<Map<String, Object>> lists;
		private int  resId;
		private LayoutInflater inflater;

		public MyAdapter(Context context, List<Map<String, Object>> lists, int resId){
			this.lists = lists;
			this.resId = resId;
			inflater = LayoutInflater.from(context);
		}


		@Override
		public int getCount() {
			return lists==null?0:lists.size();
		}

		@Override
		public Object getItem(int position) {
			return lists.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if(convertView == null){
				holder = new Holder();
				convertView = inflater.inflate(resId, null);
				holder.imageView = (ImageView) convertView.findViewById(R.id.image);
				holder.textView = (TextView) convertView.findViewById(R.id.text);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			Map<String, Object> map = lists.get(position);
			holder.imageView.setImageResource(( (Integer) map.get("image")).intValue() );
			holder.textView.setText((String) map.get("text"));
			return convertView;
		}


		class Holder{
			TextView textView;
			ImageView imageView;
		}
	}
}
