/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author jhonatan-silva
 */
public class Funcionarios {

    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String papel;

    public Funcionarios() {
    }

    
    public Funcionarios(int id, String nome, String cpf, String senha, String papel) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.papel = papel;
    }

    public Funcionarios(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the papel
     */
    public String getPapel() {
        return papel;
    }

    /**
     * @param papel the papel to set
     */
    public void setPapel(String papel) {
        this.papel = papel;
    }

}
