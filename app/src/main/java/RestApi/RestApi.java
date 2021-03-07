package RestApi;

import Models.Result;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/kayitol.php")
    Call<Result> getResultt(@Field("kullaniciadi") String kullaniciadi,@Field("sifre") String sifre,@Field("mail") String mail);

    @FormUrlEncoded
    @POST("/kayitolaktif.php")
    Call<Result>getaktif(@Field("kullaniciadi") String kullaniciadi,@Field("kod") String kod);

}
