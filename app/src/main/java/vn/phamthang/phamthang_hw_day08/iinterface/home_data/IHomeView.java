package vn.phamthang.phamthang_hw_day08.iinterface.home_data;


import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;

public interface IHomeView {
    void getProductSuccess(AllProductRespone productRespone);
    void getProductError(String error);
}
