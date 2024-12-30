package at.simstoe.cloud.api.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.*;
import java.lang.reflect.Type;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 30.12.2024 - 07:09
 */

@Getter
public final class Document {
    @Accessors(fluent = true)
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonObject jsonObject;

    public Document(final File file) {
        this.read(file);
    }

    public Document(final Reader reader) {
        this.read(reader);
    }

    public Document(final JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Document(final String json) {
        this.jsonObject = this.gson.fromJson(json, JsonObject.class);
    }

    public Document(final Object object) {
        this.jsonObject(object);
    }

    public Document read(final File file) {
        try (final var fileReader = new FileReader(file)) {
            this.jsonObject = JsonParser.parseReader(fileReader).getAsJsonObject();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return this;
    }

    public Document read(final Reader reader) {
        this.jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

        return this;
    }

    public Document write(final File file) {
        try (final var fileWriter = new FileWriter(file)) {
            fileWriter.write(this.gson.toJson(this.jsonObject));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return this;
    }

    public <T> T get(final String key, Class<T> clazz) {
        return this.gson.fromJson(this.jsonObject.get(key), clazz);
    }

    public <T> T get(final String key, final Type type) {
        return this.gson.fromJson(this.jsonObject.get(key), type);
    }

    public <T> T get(final Class<T> clazz) {
        return this.gson.fromJson(this.jsonObject, clazz);
    }

    public <T> T get(final Type type) {
        return this.gson.fromJson(this.jsonObject, type);
    }

    public Document jsonObject(final JsonObject object) {
        this.jsonObject = this.gson.toJsonTree(object).getAsJsonObject();
        return this;
    }

    public Document jsonObject(final Object object) {
        this.jsonObject = this.gson.toJsonTree(object).getAsJsonObject();
        return this;
    }
}
