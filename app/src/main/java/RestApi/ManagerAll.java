package RestApi;

import Models.Result;
import retrofit2.Call;

public class ManagerAll  extends  BaseManager{

    public Call<Result> getresultt(String kullaniciadi,String sifre,String mail){
        Call<Result> resultt=getrestapi().getResultt(kullaniciadi,sifre,mail);
        return resultt;
    }
    public Call<Result>getaktiff(String kullaniciadi,String kod){
        Call<Result>resultCall=getrestapi().getaktif(kullaniciadi,kod);
        return resultCall;


    }
}
