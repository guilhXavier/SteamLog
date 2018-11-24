package c.gg.steamlog.ModelSteam;

import java.util.List;

public class Response {
    private List<Games> games;
    private String game_count;

    private String result;
    private String player_count;

    private List<Players> players;

    public List<Games> getGames ()
    {
        return games;
    }

    public void setGames (List<Games> games)
    {
        this.games = games;
    }

    public String getGame_Count ()
    {
        return game_count;
    }

    public void setGame_Count (String game_count)
    {
        this.game_count = game_count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPlayer_count() {
        return player_count;
    }

    public void setPlayer_count(String player_count) {
        this.player_count = player_count;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }
}
