package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioService {
    public Usuario getRandomUser() {
        String url = "https://randomuser.me/api/";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // Procesar la respuesta con Gson
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject userObject = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject();
        JsonObject nameObject = userObject.getAsJsonObject("name");

        Usuario user = new Usuario();
        user.setNombre(nameObject.get("first").getAsString());
        user.setApellido(nameObject.get("last").getAsString());
        user.setEmail(userObject.get("email").getAsString());

        return user;
    }
}
