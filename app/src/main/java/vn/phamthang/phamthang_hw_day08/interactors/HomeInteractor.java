package vn.phamthang.phamthang_hw_day08.interactors;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.phamthang.phamthang_hw_day08.api.ApiUtils;
import vn.phamthang.phamthang_hw_day08.api.Services.IDummyServices;
import vn.phamthang.phamthang_hw_day08.iinterface.home_data.IHomePresenter;
import vn.phamthang.phamthang_hw_day08.iinterface.search.ISearchPresenter;
import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;

public class HomeInteractor {
    private IHomePresenter mHomePresenter;
    private IDummyServices mDummyServices;

    public HomeInteractor(IHomePresenter iHomePresenter) {
        this.mHomePresenter = iHomePresenter;
        mDummyServices = ApiUtils.iDummyServices();
    }

    public void getAllProduct() {
        mDummyServices.getAllProduct().enqueue(new Callback<AllProductRespone>() {
            @Override
            public void onResponse(Call<AllProductRespone> call, Response<AllProductRespone> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (mHomePresenter != null) {
                        mHomePresenter.getProductSuccess(response.body());
                    }else {
                        mHomePresenter.getProductError(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductRespone> call, Throwable throwable) {
                if (mHomePresenter != null) {
                    mHomePresenter.getProductError(throwable.getMessage());
                }
            }
        });
    }
}
