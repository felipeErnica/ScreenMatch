package br.com.alura.projetos.demo.tools;

public interface IConversor {
    public <T> T getData(String json, Class<T> classe);
}
