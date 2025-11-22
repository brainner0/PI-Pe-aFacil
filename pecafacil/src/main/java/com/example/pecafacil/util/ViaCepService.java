package com.example.pecafacil.util;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private static final String URL = "https://viacep.com.br/ws/%s/json/";

    public Endereco buscarEndereco(String cep) {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.getForObject(String.format(URL, cep), Endereco.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Data
    public static class Endereco {
        private String logradouro;
        private String complemento;
        private String bairro;
        private String localidade;
        private String uf;
        private String erro;
    }
}
