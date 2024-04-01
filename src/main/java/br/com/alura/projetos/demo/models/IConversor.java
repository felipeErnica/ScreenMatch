package br.com.alura.projetos.demo.models;

public interface IConversor {
    public <T> T getData(String json, Class<T> classe);
}
