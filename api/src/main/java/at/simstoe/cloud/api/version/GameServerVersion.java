package at.simstoe.cloud.api.version;

import at.simstoe.cloud.api.json.Document;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 30.12.2024 - 07:00
 */

@Getter
@Accessors(fluent = true)
public enum GameServerVersion {
    TEST("","","", true);

    private final String url;
    private final String title;
    private final String version;
    private final boolean proxyAble;

    GameServerVersion(@NotNull String url,
                      @NotNull String title,
                      @NotNull String version, boolean proxyAble) {
        this.url = url;
        this.title = title;
        this.version = version;
        this.proxyAble = proxyAble;
    }

    public String getLatestVersion(String title) {
        final var document = this.request("https://papermc.io/api/v2/projects/" + title);

        if (document != null) {
            final List<String> versions = document.get("versions", TypeToken.getParameterized(List.class, String.class).getType());

            return versions.get(versions.size() - 1);
        } else {
            return "Unknown Game-Version!";
        }
    }

    private @Nullable Document request(final @NotNull String urlString) {
        try {
            var url = new URL(urlString);
            var inputStream = url.openStream();
            var inputStreamReader = new InputStreamReader(inputStream);
            var document = new Document(inputStreamReader);

            inputStreamReader.close();
            inputStream.close();

            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
