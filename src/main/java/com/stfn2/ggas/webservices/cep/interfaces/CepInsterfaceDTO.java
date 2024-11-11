package com.stfn2.ggas.webservices.cep.interfaces;

public interface CepInsterfaceDTO {

    public String getCidade();

    public void setCidade(String cidade);

    public String getUf();

    public void setUf(String uf) ;

    public String getCep();

    public void setCep(String cep);

    public String getLogradouro();

    public void setLogradouro(String logradouro);

    public String getComplemento();

    public void setComplemento(String complemento);

    public String getBairro();

    public void setBairro(String bairro);

    public String getGia();

    public void setGia(String gia);

    public String getIbge();

    public void setIbge(String ibge);

    public String getDdd();

    public void setDdd(String ddd);

    public String getSiafi();

    public void setSiafi(String siafi);


    default void copy(CepInsterfaceDTO origem){
        setCidade(origem.getCidade());
        setUf(origem.getUf());
        setCep(origem.getCep());
        setLogradouro(origem.getLogradouro());
        setComplemento(origem.getComplemento());
        setBairro(origem.getBairro());
        setGia(origem.getGia());
        setIbge(origem.getIbge());
        setDdd(origem.getDdd());
        setSiafi(origem.getSiafi());
    }
}
