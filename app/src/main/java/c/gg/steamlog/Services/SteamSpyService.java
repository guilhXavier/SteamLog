package c.gg.steamlog.Services;

import c.gg.steamlog.ModelSteam.GetAppDetailsRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SteamSpyService {

    String BASE_URL_STEAM_SPY = "https://steamspy.com/";

    @GET("api.php?")
    Call<GetAppDetailsRequest> getAppDetails(@Query("request") String request, @Query("appid") long appid);

}
