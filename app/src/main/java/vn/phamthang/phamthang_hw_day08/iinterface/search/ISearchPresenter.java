package vn.phamthang.phamthang_hw_day08.iinterface.search;

import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;

public interface ISearchPresenter {
    void onSearchSuccess(AllProductRespone productRespone);
    void onSearchFail(String fail);
}
