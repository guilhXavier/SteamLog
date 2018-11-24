package c.gg.steamlog.Services;

import c.gg.steamlog.ModelSteam.GetAppDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SteamSpyService {

    String BASE_URL = "steamspy.com/api.php?request=";

    @GET("")
    Call<GetAppDetails> getAppDetails(@Query("request") String request, @Query("appid") long appid);

}
