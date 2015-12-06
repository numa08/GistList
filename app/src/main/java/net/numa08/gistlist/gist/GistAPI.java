package net.numa08.gistlist.gist;

import net.numa08.gistlist.realm.models.Gist;

import java.util.List;

public interface GistAPI {
    String API_ENDPOINT = "https://api.github.com";
    String LIST_GISTS = API_ENDPOINT + "/gists";
    String PUBLIC_LIST = LIST_GISTS + "/public";

    List<Gist> getPublic() throws APIException;
}
