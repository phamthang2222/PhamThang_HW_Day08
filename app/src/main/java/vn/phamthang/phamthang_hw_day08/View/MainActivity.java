package vn.phamthang.phamthang_hw_day08.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.phamthang.phamthang_hw_day08.R;
import vn.phamthang.phamthang_hw_day08.adapter.ProductAdapter;
import vn.phamthang.phamthang_hw_day08.iinterface.home_data.IHomeView;
import vn.phamthang.phamthang_hw_day08.iinterface.search.ISearchView;
import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;
import vn.phamthang.phamthang_hw_day08.objects.Product;
import vn.phamthang.phamthang_hw_day08.presenter.HomePresenter;
import vn.phamthang.phamthang_hw_day08.presenter.SearchPresenter;
import vn.phamthang.phamthang_hw_day08.utils.Constant;
import vn.phamthang.phamthang_hw_day08.utils.PrefManager;

public class MainActivity extends AppCompatActivity implements IHomeView, ISearchView, ProductAdapter.IOnProductItemClickListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private EditText edtSearch;
    private ImageView imgSearch;
    private HomePresenter mHomePresenter;
    private SearchPresenter mSearchPresenter;
    private ProductAdapter mAdapter;
    private ArrayList<Product> mListProduct ;

    public static final String TAG = "OnCreate: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        mHomePresenter = new HomePresenter(this);
        mSearchPresenter = new SearchPresenter(this);
        mListProduct = new ArrayList<>();
        getAllProduct();
        saveData(Constant.DATA,mListProduct);
        mAdapter = new ProductAdapter(mListProduct);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setCallback(this);
    }

    private void initView() {
        recyclerView = findViewById(R.id.rcvProduct);
        edtSearch = findViewById(R.id.edtSearch);
        imgSearch = findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(this);

    }

    private void getAllProduct() {
        mHomePresenter.getAllProduct();
    }

    @Override
    public void getProductSuccess(AllProductRespone productRespone) {
        mListProduct.clear();
        mAdapter.updateData((ArrayList<Product>) productRespone.getProducts());
        Log.d(TAG, "getProductSuccess: "+productRespone.getProducts().toString());
    }

    @Override
    public void getProductError(String error) {
        Log.d(TAG, "Error: " + error);

    }

    @Override
    public void onShowNowClick(Product product) {
        Toast.makeText(this, "" + product.getTitle(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFavClick(Product product,int pos) {
        product = mListProduct.get(pos);
        product.setFav(!product.isFav());
        mListProduct.set(pos, product);
        mAdapter.notifyItemChanged(pos);
    }

    @Override
    public void onSearchSuccess(AllProductRespone productRespone) {
        if (productRespone.getProducts().size() > 0) {
            mListProduct.clear();
            mAdapter.updateData((ArrayList<Product>) productRespone.getProducts());

        } else {
            Toast.makeText(this, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSearchFail(String fail) {
        Toast.makeText(this, "Lỗi tìm kiếm", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgSearch) {

            String txtSearch = edtSearch.getText().toString();
            mSearchPresenter.getSearchProduct(txtSearch);
        }
    }
    private void saveData(String key, ArrayList<Product> listData){
        PrefManager.saveData(key,listData);
    }
}