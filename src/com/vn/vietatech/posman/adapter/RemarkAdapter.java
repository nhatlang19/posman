package com.vn.vietatech.posman.adapter;

import java.util.ArrayList;

import com.vn.vietatech.api.ItemAPI;
import com.vn.vietatech.model.Item;
import com.vn.vietatech.model.Remark;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class RemarkAdapter  extends ArrayAdapter<Remark>{
    private Context context;
    private Item selectedItem;
    
    private ArrayList<Remark> values;

    public RemarkAdapter(Context context, int textViewResourceId,
    		Item item) {
        super(context, textViewResourceId);
		this.context = context;
		this.selectedItem = item;
		
		if(selectedItem.getRemarks().size() != 0) {
			values = selectedItem.getRemarks();
		} else {
			try {
				values = new ItemAPI(this.context).getRemarkByItem(item.getItemCode());
				item.setRemarks(values);
			} catch (Exception e) {
				Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
	}

	public int getCount() {
		return values.size();
	}

	public Remark getItem(int position) {
		return values.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		
		if (convertView != null){
			label = (TextView) convertView;
        } else {
//        	LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//        	label = (TextView) inflater.inflate(
//                    android.R.layout.simple_dropdown_item_1line, parent, false
//            );
        	
        	label.setPadding(5, 15, 5, 15);
        }
		
		label.setText(values.get(position).getName());
		return label;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		if (convertView != null){
			label = (TextView) convertView;
        } else {
        	LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        	label = (TextView) inflater.inflate(
                    android.R.layout.simple_dropdown_item_1line, parent, false
            );
        }
		label.setText(values.get(position).getName());
		return label;
	}
}
