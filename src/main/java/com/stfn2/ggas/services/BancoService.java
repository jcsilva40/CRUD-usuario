package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Agencia;
import com.stfn2.ggas.domain.Banco;
import com.stfn2.ggas.domain.ContaBancaria;
import com.stfn2.ggas.domain.dtos.BancoDTO;
import com.stfn2.ggas.domain.dtos.filter.BancoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.BancoBasicDTO;
import com.stfn2.ggas.repositories.BancoRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BancoService extends BaseService<Banco, BancoDTO, BancoBasicDTO, BancoFilterDTO, BancoRepository> {

    @Autowired
    private AgenciaService agenciaService;
    
    @Autowired
    private ContaBancariaService contaBancariaService;
    
    @Override
    public BancoDTO entityToDTO(Banco banco) {
        BancoDTO bancoDTO = new BancoDTO();
        bancoDTO.setBancoId(banco.getId());
        bancoDTO.setBancoAbreviado(banco.getAbreviado());
        bancoDTO.setBancoNome(banco.getNome());
        bancoDTO.setCodigoBanco(banco.getCodigoBanco());
        Agencia agencia = agenciaService.getAgenciaPorBancoId(banco.getId());
        if(!Objects.isNull(agencia)){
            bancoDTO.setAgenciaId(agencia.getId());
            bancoDTO.setAgenciaNome(agencia.getNome());
            bancoDTO.setAgenciaCodigo(agencia.getCodigo());
            ContaBancaria contaBancaria = contaBancariaService.getContaBancariaPorAgenciaId(agencia.getId());
            if(!Objects.isNull(contaBancaria)){
                bancoDTO.setContaId(contaBancaria.getId());
                bancoDTO.setContaDescricao(contaBancaria.getDescricao());
                bancoDTO.setNumeroConta(contaBancaria.getNumeroConta());
                bancoDTO.setDigitoVerificador(contaBancaria.getNumeroDigito());
            }
        }
        
        return bancoDTO;
    }
    
    @Override
    public BancoDTO createOrUpdate(BancoDTO bancoDTO) {
        Banco b = new Banco();
        if (bancoDTO.getBancoId() == null){
            Banco banco = new Banco();
            banco.setId(bancoDTO.getBancoId());
            banco.setAbreviado(bancoDTO.getBancoAbreviado());
            banco.setNome(bancoDTO.getBancoNome());
            banco.setCodigoBanco(bancoDTO.getCodigoBanco());
            super.save(banco);

            Agencia agencia = new Agencia();
            agencia.setId(bancoDTO.getAgenciaId());
            agencia.setNome(bancoDTO.getAgenciaNome());
            agencia.setCodigo(bancoDTO.getAgenciaCodigo());
            agencia.setBanco(banco);
            agenciaService.save(agencia);

            ContaBancaria contaBancaria = new ContaBancaria();
            contaBancaria.setId(bancoDTO.getContaId());
            contaBancaria.setDescricao(bancoDTO.getContaDescricao());
            contaBancaria.setNumeroConta(bancoDTO.getNumeroConta());
            contaBancaria.setNumeroDigito(bancoDTO.getDigitoVerificador());
            contaBancaria.setAgencia(agencia);
            contaBancariaService.save(contaBancaria);
            return entityToDTO(banco);
        }else{
            Banco banco = getById(bancoDTO.getId());
            banco.setId(bancoDTO.getBancoId());
            banco.setAbreviado(bancoDTO.getBancoAbreviado());
            banco.setNome(bancoDTO.getBancoNome());
            banco.setCodigoBanco(bancoDTO.getCodigoBanco());
            super.save(banco);

            Agencia agencia = agenciaService.getAgenciaPorBancoId(banco.getId());
            agencia.setNome(bancoDTO.getAgenciaNome());
            agencia.setCodigo(bancoDTO.getAgenciaCodigo());

            ContaBancaria contaBancaria = contaBancariaService.getContaBancariaPorAgenciaId(agencia.getId());
            contaBancaria.setDescricao(bancoDTO.getContaDescricao());
            contaBancaria.setNumeroConta(bancoDTO.getNumeroConta());
            contaBancaria.setNumeroDigito(bancoDTO.getDigitoVerificador());

            return entityToDTO(banco);
        }
    }
}