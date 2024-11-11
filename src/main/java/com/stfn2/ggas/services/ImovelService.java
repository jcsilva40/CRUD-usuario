package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.ConvertObj;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.ImovelDTO;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.PontoConsumoMedidorInstalacaoDTO;
import com.stfn2.ggas.domain.dtos.basic.ImovelBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ImovelFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum;
import com.stfn2.ggas.domain.enumTypes.PontoConsumoSituacaoEnum;
import com.stfn2.ggas.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ImovelService extends BaseService<Imovel, ImovelDTO, ImovelBasicDTO, ImovelFilterDTO, ImovelRepository> {

    @Autowired
    private SegmentoService segmentoService;

    @Lazy
    @Autowired
    private RotaService rotaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CepService cepService;

    @Autowired
    private PontoConsumoService pontoConsumoService;


    @Override
    public Imovel beforeSave(Imovel entity) {
        entity.getPontoConsumos().forEach(item -> item.setImovel(entity));
        entity.setQuantidadePontosConsumo(entity.getPontoConsumos().size());
        // entity.getUnidadesConsumidoras().forEach(item -> item.setQuantidadeEconomica(entity.getQuantidadePontosConsumo()));
        // entity.getUnidadesConsumidoras().forEach(item -> item.setImovel(entity));
        // entity.getClienteImovel().forEach(item -> item.setImovel(entity));
        entity.setCep(entity.getCep().replace("-", ""));
        return super.beforeSave(entity);
    }

    @Override
    public List<ImovelBasicDTO> findAll() {

        return super.findAll();
    }

    @Override
    public ImovelDTO entityToDTO(Imovel entity) {
        ImovelDTO dto = new ImovelDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setImovelSituacao(entity.getImovelSituacao());
        if(entity.getRota() != null){
        dto.setRotaId(entity.getRota().getId());
        }
        dto.setNip(entity.getNip());
        dto.setDataEntrega(entity.getDataEntrega());
        dto.setZonaBloqueio(entity.getZonaBloqueio());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataTesteEstanque(entity.getDataTesteEstanque());
        dto.setNumeroUdas(entity.getNumeroUdas());

        if(entity.getImovelTipoMedicao() != null){
            dto.setImovelTipoMedicao(entity.getImovelTipoMedicao());
        }
        if(entity.getImovelTipoMedicaoGLP() != null){
            dto.setImovelTipoMedicaoGLP(entity.getImovelTipoMedicaoGLP());
        }
        if(entity.getImovelOrigem() != null) {
            dto.setImovelOrigem(entity.getImovelOrigem());
        }



        if (entity.getImovelPai() != null) {
            dto.setImovelPaiId(entity.getImovelPai().getId());
        }

        dto.setCondominio(entity.getCondominio());

        // Endereco

        dto.setCep  (entity.getCep());
        dto.setLogradouro(entity.getLogradouro());
        dto.setBairro(entity.getBairro());
        dto.setMunicipio(entity.getMunicipio());
        dto.setUf(entity.getUf());
        dto.setHabilitado(entity.getHabilitado());
        dto.setNumero(entity.getNumero());
        dto.setComplementoEndereco(entity.getComplementoEndereco());

        Iterator<PontoConsumo> iteratorPontoConsumo = entity.getPontoConsumos().iterator();
        if(iteratorPontoConsumo.hasNext()){
            PontoConsumo pc = iteratorPontoConsumo.next();
            dto.setSegmentoId(pc.getSegmento().getId());
        }
/*w
        // Converter ClienteImovel
        entity.getClienteImovel().forEach(entityItem -> {
            ClienteImovelDTO clienteImovelDTO = new ClienteImovelDTO();
            clienteImovelDTO.setId(entityItem.getId());
            clienteImovelDTO.setClienteId(entityItem.getCliente().getId());
            clienteImovelDTO.setClienteDescricao(entityItem.getCliente().getDescricao());
            clienteImovelDTO.setImovelId(entityItem.getImovel().getId());
            clienteImovelDTO.setRelacionamento(entityItem.getRelacionamento());
            clienteImovelDTO.setHabilitado(entityItem.getHabilitado());
            clienteImovelDTO.setInicioRelacionamento(entityItem.getInicioRelacionamento());
            clienteImovelDTO.setClienteImovelFimRelacionamentoMotivoEnum(entityItem.getClienteImovelFimRelacionamentoMotivoEnum());
            clienteImovelDTO.setFimRelacionamento(entityItem.getFimRelacionamento());

            dto.getClienteImovel().add(clienteImovelDTO);
        });
*/
        // Converter PontoConsumos
        entity.getPontoConsumos().forEach(entityItem -> {
            PontoConsumoDTO pontoConsumoDTO = new PontoConsumoDTO();
            pontoConsumoDTO.setId(entityItem.getId());
            pontoConsumoDTO.setDescricao(entityItem.getDescricao());
            pontoConsumoDTO.setHabilitado(entityItem.getHabilitado());
            if(entityItem.getRota() != null) {
                pontoConsumoDTO.setRotaId(entityItem.getRota().getId());
            }
            pontoConsumoDTO.setSegmentoId(entityItem.getSegmento().getId());
            pontoConsumoDTO.setSequenciaLeitura(entityItem.getSequenciaLeitura());
            pontoConsumoDTO.setCil(entityItem.getCil());
            pontoConsumoDTO.setHabilitado(entityItem.getHabilitado());
            pontoConsumoDTO.setPontoConsumoSituacaoId(entityItem.getPontoConsumoSituacao().getId());
            pontoConsumoDTO.setRamoAtividadeId(entityItem.getSegmento().getRamosAtividade().isEmpty() ? null : entityItem.getSegmento().getRamosAtividade().get(0).getId());
            pontoConsumoDTO.setCep(entityItem.getCep());
            pontoConsumoDTO.setObservacao(entityItem.getObservacao());
            pontoConsumoDTO.setLogradouro(entityItem.getLogradouro());
            pontoConsumoDTO.setBairro(entityItem.getBairro());
            pontoConsumoDTO.setUf(entityItem.getUf());
            pontoConsumoDTO.setLocalidade(entityItem.getLocalidade());
            pontoConsumoDTO.setNumero(entityItem.getNumero());
            pontoConsumoDTO.setComplemento(entityItem.getComplemento());
            pontoConsumoDTO.setMensagemFatura(entityItem.getMensagemFatura());

            if (entityItem.getMedidorInstalacao() != null) {
                pontoConsumoDTO.setMedidorInstalacaoId(entityItem.getMedidorInstalacao().getId());
            }


            List<ContratoPontoConsumo> contratos = repository.findByPontoConsumoId(entityItem.getId());
            Optional<ContratoPontoConsumo> cpc = contratos.stream()
                    .findFirst();

            if(cpc.isPresent()) {
                 ContratoPontoConsumo cpcNew = cpc.get();
                 pontoConsumoDTO.setContratoId(cpcNew.getContrato().getId());
                 ContratoStatusEnum contratoStatusEnum = pontoConsumoService.pontoConsumoContratoStatus(pontoConsumoDTO.getId());
                 pontoConsumoDTO.setContratoStatus(contratoStatusEnum);
            }else{
                pontoConsumoDTO.setContratoId(null);
                pontoConsumoDTO.setContratoStatus(ContratoStatusEnum.INEXISTENTE);
            }
            entityItem.getMedidores().forEach(medidorInstalacao -> {
                if(medidorInstalacao.getHabilitado()){
                PontoConsumoMedidorInstalacaoDTO instalacao = new PontoConsumoMedidorInstalacaoDTO();
                instalacao.setId(medidorInstalacao.getId());
                instalacao.setData(medidorInstalacao.getData());
                instalacao.setMedidorDescricao(medidorInstalacao.getMedidor().getDescricao());
                instalacao.setMedidorId(medidorInstalacao.getMedidor().getId());
                instalacao.setPontoConsumoDescricao(medidorInstalacao.getPontoConsumo().getDescricao());
                instalacao.setPontoConsumoCil(medidorInstalacao.getPontoConsumo().getCil());

                pontoConsumoDTO.getListaInstalacoes().add(instalacao);


            }});
            dto.getPontoConsumos().add(pontoConsumoDTO);
        });

        return dto;
    }

    @Override
    public Imovel dtoToEntity(ImovelDTO dto) {

        boolean isNew = false;
        Imovel imovel = getById(dto.getId());
        if(imovel == null){
            isNew = true;
            imovel = new Imovel();
        }

        imovel.setDescricao(dto.getDescricao());
        imovel.setImovelSituacao(dto.getImovelSituacao());

        if(dto.getImovelOrigem() != null){
            imovel.setImovelOrigem(dto.getImovelOrigem());
        }
        if(dto.getImovelTipoMedicao() != null){
            imovel.setImovelTipoMedicao(dto.getImovelTipoMedicao());
        }
        if(dto.getImovelTipoMedicaoGLP() != null){
            imovel.setImovelTipoMedicaoGLP(dto.getImovelTipoMedicaoGLP());
        }


        if(dto.getDataEntrega() != null) {
            imovel.setDataEntrega(dto.getDataEntrega());
        }
        if(dto.getDataCriacao() != null){
            imovel.setDataCriacao(dto.getDataCriacao());
        }
        if(dto.getDataTesteEstanque() != null){
            imovel.setDataTesteEstanque(dto.getDataTesteEstanque());
        }
        if(dto.getRotaId() != null) {
            Rota rota = rotaService.getById(dto.getRotaId());
            imovel.setRota(rota);
        }
        imovel.setNip(dto.getNip());
        imovel.setNumeroUdas(dto.getNumeroUdas());
        imovel.setZonaBloqueio(dto.getZonaBloqueio());
        if (dto.getImovelPaiId() != null) imovel.setImovelPai(getById(dto.getImovelPaiId()));
        imovel.setCondominio(dto.getCondominio());
        imovel.setHabilitado(dto.getHabilitado());


        //Endereco
        imovel.setCep(dto.getCep());
        imovel.setLogradouro(dto.getLogradouro());
        imovel.setBairro(dto.getBairro());
        imovel.setMunicipio(dto.getMunicipio());
        imovel.setUf(dto.getUf());
        imovel.setNumero(dto.getNumero());
        imovel.setComplementoEndereco(dto.getComplementoEndereco());
/*
        // Aba ClienteImovel

        ConvertObj<ClienteImovel, ClienteImovelDTO> convertObjCliente = new ConvertObj<ClienteImovel, ClienteImovelDTO>() {
            @Override
            public void update(ClienteImovel d, ClienteImovelDTO o) {
                Cliente cl = clienteService.getById(o.getClienteId());
                d.setRelacionamento(o.getRelacionamento());
                d.setInicioRelacionamento(o.getInicioRelacionamento());
                d.setFimRelacionamento(o.getFimRelacionamento());
                d.setClienteImovelFimRelacionamentoMotivoEnum(o.getClienteImovelFimRelacionamentoMotivoEnum());
                d.setHabilitado(o.getHabilitado());
                d.setCliente(cl);
            }
        };

        for(ClienteImovelDTO clDto : dto.getClienteImovel()){
            boolean exists = imovel.getClienteImovel().stream()
                    .anyMatch(n -> n.getId() != null && clDto.getId() != null && clDto.getId().equals(n.getId()));
            if(!exists){
                ClienteImovel newCliente = new ClienteImovel();
                newCliente.setImovel(imovel);  // Associar o ClienteImovel ao Imovel
                convertObjCliente.update(newCliente, clDto);
                imovel.getClienteImovel().add(newCliente);
            } else {
                imovel.getClienteImovel().stream()
                        .filter(n -> clDto.getId() != null && clDto.getId().equals(n.getId()))
                        .findFirst()
                        .ifPresent(existingCliente -> convertObjCliente.update(existingCliente, clDto));
            }
        }
*/
        // Aba PontoConsumo

        Segmento sg = segmentoService.getById(dto.getSegmentoId());

        boolean finalIsNew = isNew;
        Imovel finalImovel = imovel;
        ConvertObj<PontoConsumo, PontoConsumoDTO> convertPC = new ConvertObj<PontoConsumo, PontoConsumoDTO>() {
            @Override
            public void update(PontoConsumo d, PontoConsumoDTO o) {
                d.setDescricao(o.getDescricao());
                d.setHabilitado(o.getHabilitado());
                d.setUltimaAlteracao(LocalDateTime.now());
                d.setVersao(d.getVersao() + 1);
                d.setSequenciaLeitura(o.getSequenciaLeitura());
                d.setObservacao(o.getObservacao());

                if(dto.getRotaId() != null) {
                    Rota rota = rotaService.getById(dto.getRotaId());
                    d.setRota(rota);
                }
                d.setSegmento(sg);
                d.setCil(o.getCil());
                if(finalIsNew){
                   if(d.getComplementoCil() == null || d.getComplementoCil().isEmpty()) {
                       d.setComplementoCil("101");
                   }
                   if(d.getQuadraFace() == null) {
                       d.setQuadraFace(81714L);
                   }
                }else {
                    d.setComplementoCil(o.getComplementoCil());
                    d.setQuadraFace(finalImovel.getIdQuadraFace());
                }
                d.setCodigoLegado(o.getCil());



                d.setPontoConsumoSituacao(PontoConsumoSituacaoEnum.toEnum(o.getPontoConsumoSituacaoId()));
                d.setRamoAtividade(d.getSegmento().getRamosAtividade().getFirst());


                d.setCep(dto.getCep());
                d.setLogradouro(dto.getLogradouro());
                d.setBairro(dto.getBairro());
                d.setUf(dto.getUf());
                d.setLocalidade(dto.getMunicipio());
                d.setNumero(dto.getNumero());
                d.setComplemento(o.getComplemento());
                d.setMensagemFatura(o.getMensagemFatura());

                d.setCepTabela(cepService.findByCep(dto.getCep()));
                d.setCil(o.getCil());

              /*
                d.setMensagemFatura("teste");
                d.setFatorCorrecao(1);
                d.setCil("1235");
                d.setSequenciaLeitura(1);
                */
            }
        };
        if(isNew){
            imovel.getPontoConsumos().add(createArcomPc(dto));
        }else {

            for (PontoConsumoDTO i : dto.getPontoConsumos()) {
                boolean exists = imovel.getPontoConsumos().stream()
                        .anyMatch(n -> n.getId() != null && i.getId() != null && i.getId().equals(n.getId()));
                if (!exists) {
                    PontoConsumo newPC = new PontoConsumo();
                    newPC.setImovel(imovel);
                    newPC.setQuadraFace(imovel.getIdQuadraFace());
                    convertPC.update(newPC, i);
                    imovel.getPontoConsumos().add(newPC);
                } else {
                    imovel.getPontoConsumos().stream()
                            .filter(n -> i.getId() != null && i.getId().equals(n.getId()))
                            .findFirst()
                            .ifPresent(existingPC -> convertPC.update(existingPC, i));
                }
            }
        }

        return imovel;
    }

    @Override
    public void validDto(ImovelDTO dto) {
        if (!hasContent(dto.getDescricao())) addErro("descricao", "Campo descrição é obrigatório!");
        if (isNull(dto.getImovelSituacao())) addErro("imovelSituacao", "Campo imóvel situação é obrigatório!");
        if (!hasContent(dto.getCep())) addErro("cep", "Campo cep é obrigatório!");
        if (!hasContent(dto.getNumero())) addErro("numero", "Campo Numero é obrigatório!");

      //  if (isNull(dto.getPontoConsumos()) || dto.getPontoConsumos().isEmpty()) addErro("pontoConsumos","Pelo menos um ponto consumo deve ser informado!");;

    }

    @Override
    public ImovelBasicDTO entityToBasicDTO(Imovel entity) {
        ImovelBasicDTO dto = super.entityToBasicDTO(entity);
        dto.setDataEntrega(entity.getDataEntrega());
        dto.setQuantidadePontosConsumo(entity.getPontoConsumos().size());

        Iterator<PontoConsumo> pc = entity.getPontoConsumos().iterator();
        if(pc.hasNext()){
            PontoConsumo p = pc.next();
            dto.setEndereco(String.format("%s %s - %s, %s - %s, %s", p.getLogradouro(), p.getNumero(), p.getBairro(), p.getLocalidade(), p.getUf(), p.getCep()));

        }
        if(entity.getRota() != null) {
            dto.setGrupoRota(String.format("%s / %s", entity.getRota().getFaturamentoGrupo().getDescricao(), entity.getRota().getDescricao()));
        }


        return dto;

    }
    
    public Long findImovelIdByPontoConsumoId(Long pontoConsumoId){
        return repository.findImovelByPontoConsumoId(pontoConsumoId).getId();
    }

    public Boolean existCil(String cil) {
        return pontoConsumoService.existsByCil(cil);
    }

    public Long savePontoConsumo(Long imovelId, PontoConsumoDTO dto){
        Imovel imovel = getById(imovelId);
        Segmento sg = imovel.getPontoConsumos().getFirst().getSegmento();

        boolean cilExists = pontoConsumoService.existsByCil(dto.getCil());

       if (cilExists) {
          throw new IllegalArgumentException("Já existe um Ponto Consumo com o CIL fornecido.");
       }

        ConvertObj<PontoConsumo, PontoConsumoDTO> convertPC = new ConvertObj<PontoConsumo, PontoConsumoDTO>() {
            @Override
            public void update(PontoConsumo d, PontoConsumoDTO o) {
                d.setId(o.getId());
                d.setDescricao(o.getDescricao());
                d.setHabilitado(o.getHabilitado());
                d.setUltimaAlteracao(LocalDateTime.now());
                d.setVersao(d.getVersao() + 1);
                d.setSequenciaLeitura(o.getSequenciaLeitura());
                d.setObservacao(o.getObservacao());
                if(imovel.getRota() != null) {
                    d.setRota(imovel.getRota());
                }
                d.setSegmento(sg);
                d.setCil(o.getCil());
                if(o.getComplementoCil().isEmpty()){
                    d.setComplementoCil("101");
                }else {
                    d.setComplementoCil(o.getComplementoCil());
                }
                d.setCodigoLegado(o.getCil());

                d.setPontoConsumoSituacao(PontoConsumoSituacaoEnum.toEnum(o.getPontoConsumoSituacaoId()));
                d.setRamoAtividade(d.getSegmento().getRamosAtividade().getFirst());


                d.setCep(imovel.getCep());
                d.setLogradouro(imovel.getLogradouro());
                d.setBairro(imovel.getBairro());
                d.setUf(imovel.getUf());
                d.setLocalidade(imovel.getMunicipio());
                d.setNumero(imovel.getNumero());
                d.setComplemento(o.getComplemento());
                d.setMensagemFatura(o.getMensagemFatura());
                if(d.getQuadraFace() == null) {
                    d.setQuadraFace(81714L);
                }

                d.setCepTabela(cepService.findByCep(imovel.getCep()));
                d.setCil(o.getCil());

            }
        };

      /*  if(dto.getId() != null) {
            imovel.getPontoConsumos().stream()
                    .filter(n -> dto.getId() != null && dto.getId().equals(n.getId()))
                    .findFirst()
                    .ifPresent(existingPC -> convertPC.update(existingPC, dto));
            save(imovel);

            return dto.getId();
        }else{*/
            PontoConsumo newPC = new PontoConsumo();
            newPC.setImovel(imovel);
            newPC.setComplementoCil("101");
            convertPC.update(newPC, dto);
            pontoConsumoService.save(newPC);
            return newPC.getId();
     //   }
    }


    public PontoConsumo createArcomPc(ImovelDTO imovel){
        PontoConsumo arcom = new PontoConsumo();
        Segmento sg = segmentoService.getById(imovel.getSegmentoId());
        if(imovel.getRotaId() != null) {
            Rota rota = rotaService.getById(imovel.getRotaId());
            arcom.setRota(rota);
        }
        arcom.setDescricao("ARCOM");
        arcom.setHabilitado(true);


        arcom.setUltimaAlteracao(LocalDateTime.now());
        arcom.setVersao(1);
        arcom.setSequenciaLeitura(1);
        arcom.setObservacao("");
        arcom.setComplementoCil("101");
        arcom.setSegmento(sg);

        arcom.setCil("");
        arcom.setCodigoLegado("");

        arcom.setPontoConsumoSituacao(PontoConsumoSituacaoEnum.toEnum(1L));
        arcom.setRamoAtividade(sg.getRamosAtividade().getFirst());

        arcom.setQuadraFace(81714L);
        arcom.setCep(imovel.getCep());
        arcom.setLogradouro(imovel.getLogradouro());
        arcom.setBairro(imovel.getBairro());
        arcom.setUf(imovel.getUf());
        arcom.setLocalidade(imovel.getMunicipio());
        arcom.setNumero(imovel.getNumero());
        arcom.setComplemento("");
        arcom.setMensagemFatura("");

        arcom.setCepTabela(cepService.findByCep(imovel.getCep()));

        return arcom;
    }

    public List<ImovelDTO> obterListaImoveisDisponiveis() {
        return repository.obterListaImoveisDisponiveis();
    }
}