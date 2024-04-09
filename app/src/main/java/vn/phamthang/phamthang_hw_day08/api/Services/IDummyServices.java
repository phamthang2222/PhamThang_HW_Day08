package vn.phamthang.phamthang_hw_day08.api.Services;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;
import vn.phamthang.phamthang_hw_day08.utils.Constant;

public interface IDummyServices {

    @GET(Constant.GET_ALL_PRODUCT_API)
    Call<AllProductRespone> getAllProduct();

    @GET("products/search")
    Call<AllProductRespone> getSearchProduct(@Query("q") String txtSearch);
}
