package c.gg.steamlog.ModelSteam;

import java.util.List;

public class Friends {

    private String steamid;

    private String relationship;

    private Integer friendSince;

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Integer getFriendSince() {
        return friendSince;
    }

    public void setFriendSince(Integer friendSince) {
        this.friendSince = friendSince;
    }

}