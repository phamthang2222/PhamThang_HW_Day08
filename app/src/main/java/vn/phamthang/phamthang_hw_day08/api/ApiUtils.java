package vn.phamthang.phamthang_hw_day08.api;

import vn.phamthang.phamthang_hw_day08.api.Services.IDummyServices;

public class ApiUtils {
    public static IDummyServices iDummyServices(){
        return RetrofitClient.getInstance().create(IDummyServices.class);
    }
}
