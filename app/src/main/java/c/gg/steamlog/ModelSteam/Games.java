package c.gg.steamlog.ModelSteam;

public class Games
{
    private long playtimeForever;

    private int appid;

    public long getPlaytimeForever ()
    {
        return playtimeForever;
    }

    public void setPlaytimeForever (long playtimeForever)
    {
        this.playtimeForever = playtimeForever;
    }

    public int getAppid ()
    {
        return appid;
    }

    public void setAppid (int appid)
    {
        this.appid = appid;
    }
}
