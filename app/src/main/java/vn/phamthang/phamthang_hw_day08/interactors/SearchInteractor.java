package vn.phamthang.phamthang_hw_day08.interactors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.phamthang.phamthang_hw_day08.api.ApiUtils;
import vn.phamthang.phamthang_hw_day08.api.Services.IDummyServices;
import vn.phamthang.phamthang_hw_day08.iinterface.search.ISearchPresenter;
import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;

public class SearchInteractor {
    private ISearchPresenter mSearchPresenter;
    private IDummyServices mDummyServices;

    public SearchInteractor(ISearchPresenter mSearchPresenter) {
        this.mSearchPresenter = mSearchPresenter;
        mDummyServices = ApiUtils.iDummyServices();
    }

    public void getSearchProduct(String txtSearch) {
        mDummyServices.getSearchProduct(txtSearch).enqueue(new Callback<AllProductRespone>() {
            @Override
            public void onResponse(Call<AllProductRespone> call, Response<AllProductRespone> response) {
                if (response.isSuccessful()) {
                    if (mSearchPresenter != null) {
                        mSearchPresenter.onSearchSuccess(response.body());
                    } else {
                        mSearchPresenter.onSearchFail(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductRespone> call, Throwable throwable) {
                if (mSearchPresenter != null) {
                    mSearchPresenter.onSearchFail(throwable.getMessage());
                }
            }
        });
    }
}
