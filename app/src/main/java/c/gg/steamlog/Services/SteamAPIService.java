package c.gg.steamlog.Services;

import c.gg.steamlog.ModelSteam.GetAppList;
import c.gg.steamlog.ModelSteam.GetFriendList;
import c.gg.steamlog.ModelSteam.GetNumberOfConcurrentPlayers;
import c.gg.steamlog.ModelSteam.GetOwnedGames;
import c.gg.steamlog.ModelSteam.GetPlayerSummaries;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SteamAPIService {

    String BASE_URL_STEAM_API = "http://api.steampowered.com/";

    @GET("IPlayerService/GetOwnedGames/v0001/")
    Call<GetOwnedGames> ownedGames(@Query("key") String chave, @Query("steamid") String id);

    @GET("ISteamUserStats/GetNumberOfCurrentPlayers/v1/")
    Call<GetNumberOfConcurrentPlayers> getCCU(@Query("key") String chave, @Query("appid") String id);

    @GET("ISteamUser/GetFriendList/v0001/")
    Call<GetFriendList> listFriends(@Query("key") String chave, @Query("steamid") String id, @Query("relationship") String relacao);

    @GET("ISteamUser/GetPlayerSummaries/v0002/")
    Call<GetPlayerSummaries> summary(@Query("key") String chave, @Query("steamids") String id);

    @GET("ISteamApps/GetAppList/v2/")
    Call<GetAppList> appList();




}
