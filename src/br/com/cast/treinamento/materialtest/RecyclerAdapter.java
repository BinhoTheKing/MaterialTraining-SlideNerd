package br.com.cast.treinamento.materialtest;

import java.util.List;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.cast.treinamento.materialtest.RecyclerAdapter.MyViewHolder;

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

//	private Activity context;
	private LayoutInflater inflater;
	private List<ItemData> items;

	public RecyclerAdapter(Activity context, List<ItemData> items) {
//		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.items = items;
	}

	public void delete(int position) {
		items.remove(position);
		notifyItemRemoved(position);
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		ItemData current = items.get(position);
		holder.itemTextView.setText(current.title);
		holder.itemImageView.setImageResource(current.iconId);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.custom_recycler_item, parent,
				false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	class MyViewHolder extends RecyclerView.ViewHolder implements
			OnClickListener {

		ImageView itemImageView;
		TextView itemTextView;

		public MyViewHolder(View itemView) {
			super(itemView);
			itemImageView = (ImageView) itemView.findViewById(R.id.itemIcon);
			itemTextView = (TextView) itemView.findViewById(R.id.itemText);
			itemView.setOnClickListener(this);

		}

		@Override
		public void onClick(View v) {
//			delete(getAdapterPosition());
		}

	}

}
