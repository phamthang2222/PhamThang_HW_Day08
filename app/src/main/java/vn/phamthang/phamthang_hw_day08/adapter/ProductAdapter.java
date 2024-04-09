package vn.phamthang.phamthang_hw_day08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vn.phamthang.phamthang_hw_day08.R;
import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;
import vn.phamthang.phamthang_hw_day08.objects.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> mListProduct;
    private Context mContext;
    private IOnProductItemClickListener callback;

    public ProductAdapter(ArrayList<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public void updateData(ArrayList<Product> ListProduct) {
        this.mListProduct = ListProduct;
        notifyDataSetChanged();
    }


    public void setCallback(IOnProductItemClickListener callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);

        holder.tvProductName.setText(product.getTitle());
        Glide.with(mContext)
                .load(product.getThumbnail())
                .into(holder.imgProduct);
        int favRes = product.isFav()? R.drawable.ic_heart_check : R.drawable.ic_heart_uncheck;
        holder.imgFavourite.setBackgroundResource(favRes);
    }

    @Override
    public int getItemCount() {
        return mListProduct != null ? mListProduct.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgProduct,imgFavourite;
        TextView tvProductName;
        LinearLayout llShopNow;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            imgFavourite = itemView.findViewById(R.id.imgFav);
            llShopNow = itemView.findViewById(R.id.llShopNow);
            llShopNow.setOnClickListener(this);
            imgFavourite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (callback != null) {
                if (v.getId() == R.id.llShopNow) {
                    Product product = mListProduct.get(getAdapterPosition());
                    callback.onShowNowClick(product);
                }
                if (v.getId() == R.id.imgFav) {
                    Product product = mListProduct.get(getAdapterPosition());
                    callback.onFavClick(product,getAdapterPosition());
                }
            }
        }
    }

    public interface IOnProductItemClickListener {
        void onShowNowClick(Product product);

        void onFavClick(Product product,int pos);
    }
}
