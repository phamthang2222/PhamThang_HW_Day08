package vn.phamthang.phamthang_hw_day08.presenter;

import vn.phamthang.phamthang_hw_day08.iinterface.search.ISearchPresenter;
import vn.phamthang.phamthang_hw_day08.iinterface.search.ISearchView;
import vn.phamthang.phamthang_hw_day08.interactors.SearchInteractor;
import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;

public class SearchPresenter implements ISearchPresenter {
    private ISearchView mSearchView;
    private SearchInteractor mSearchInteractor;

    public SearchPresenter(ISearchView mSearchView) {
        this.mSearchView = mSearchView;
        mSearchInteractor = new SearchInteractor(this);
    }
    public void getSearchProduct(String txtSearch){
        mSearchInteractor.getSearchProduct(txtSearch);
    }
    @Override
    public void onSearchSuccess(AllProductRespone productRespone) {
        if(mSearchView!= null){
            mSearchView.onSearchSuccess(productRespone);
        }
    }

    @Override
    public void onSearchFail(String fail) {
        if(mSearchView != null){
            mSearchView.onSearchFail(fail);
        }
    }
}
