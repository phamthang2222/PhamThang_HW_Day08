package vn.phamthang.phamthang_hw_day08.presenter;



import vn.phamthang.phamthang_hw_day08.iinterface.home_data.IHomePresenter;
import vn.phamthang.phamthang_hw_day08.iinterface.home_data.IHomeView;
import vn.phamthang.phamthang_hw_day08.interactors.HomeInteractor;
import vn.phamthang.phamthang_hw_day08.objects.AllProductRespone;

public class HomePresenter implements IHomePresenter {

    private IHomeView mHomeView;
    private HomeInteractor mHomeInteractor;

    public HomePresenter(IHomeView mHomeView) {
        this.mHomeView = mHomeView;
        mHomeInteractor = new HomeInteractor(this);
    }

    public void getAllProduct() {
        mHomeInteractor.getAllProduct();
    }

    @Override
    public void getProductSuccess(AllProductRespone productRespone) {
        if (mHomeView!=null){
            mHomeView.getProductSuccess(productRespone);
        }
    }

    @Override
    public void getProductError(String error) {
        if (mHomeView!=null){
            mHomeView.getProductError(error);
        }
    }
}
