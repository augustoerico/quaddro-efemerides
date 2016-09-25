package br.com.erico.efemerides;

/**
 * Created by Mobile on 22/09/2016.
 */
public class Efemeride {

    private int idEfemeride;
    private int mes;
    private int dia;
    private String descricao;

    public Efemeride() {
    }

    public Efemeride(int mes, int dia, String descricao) {
        this.mes = mes;
        this.dia = dia;
        this.descricao = descricao;
    }

    public Efemeride(int idEfemeride, int dia, int mes, String descricao) {
        this.idEfemeride = idEfemeride;
        this.dia = dia;
        this.mes = mes;
        this.descricao = descricao;
    }

    public int getIdEfemeride() {
        return idEfemeride;
    }

    public void setIdEfemeride(int idEfemeride) {
        this.idEfemeride = idEfemeride;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
