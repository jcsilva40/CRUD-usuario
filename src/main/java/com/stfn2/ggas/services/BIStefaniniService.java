package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.ConvertObj;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.*;
import com.stfn2.ggas.domain.dtos.basic.BIStefaniniBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.BIStefaniniFilterDTO;
import com.stfn2.ggas.domain.enumTypes.FormatosBIEnum;
import com.stfn2.ggas.repositories.BIStefaniniRepository;
import com.stfn2.ggas.services.componentes.bi.JasperBIComponent;
import com.stfn2.ggas.services.componentes.bi.PlayBIComponent;
import com.stfn2.ggas.services.componentes.relatorio.XLSXReportGenerator;
import com.stfn2.ggas.tools.ToolCSV;
import com.stfn2.ggas.tools.ToolStr;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.poi.ss.usermodel.Workbook;

@Service
public class BIStefaniniService extends BaseService<BIStefanini, BIStefaniniDTO, BIStefaniniBasicDTO, BIStefaniniFilterDTO, BIStefaniniRepository> {


    @Autowired
    private JasperBIComponent jasperBIComponent;
    
    @Autowired
    private XLSXReportGenerator xlsxReportGenerator;
    
    @NotNull
    private static ConvertObj<ParametroBI, ParametroBIDTO> getParametroBIParametroBIDTOConvertObj() {
        ConvertObj<ValorDefinidoFixoBI, ValorDefinidoFixoBIDTO> converterValorFixo = new ConvertObj<ValorDefinidoFixoBI, ValorDefinidoFixoBIDTO>() {
            @Override
            public void update(ValorDefinidoFixoBI d, ValorDefinidoFixoBIDTO o) {
                d.setDescricao(o.getDescricao());
                d.setValor(o.getValor());
            }
        };

        return new ConvertObj<ParametroBI, ParametroBIDTO>() {
            @Override
            public void update(ParametroBI d, ParametroBIDTO o) {
                d.setDescricao(o.getDescricao());
                d.setKey(o.getKey());
                d.setValorPadrao(o.getValorPadrao());
                d.setObrigatorio(o.getObrigatorio());
                d.setEntidadePesquisa(o.getEntidadePesquisa());
                d.setTipoEntrada(o.getTipoEntrada());
                d.setTipoPrimitivo(o.getTipoPrimitivo());
                d.setPosicao(o.getPosicao());
                d.setQuantidadeMeses(o.getQuantidadeMeses());

                Iterator<ValorDefinidoFixoBI> iteratorValorFixo = d.getValoresFixos().iterator();
                while (iteratorValorFixo.hasNext()) {
                    ValorDefinidoFixoBI valor = iteratorValorFixo.next();
                    Optional<ValorDefinidoFixoBIDTO> op = o.getValoresFixos().stream().filter(f -> f.getId().equals(valor.getId())).findFirst();
                    if (op.isPresent()) {
                        converterValorFixo.update(valor, op.get());
                    } else {
                        iteratorValorFixo.remove();
                    }
                }

                for (ValorDefinidoFixoBIDTO v : o.getValoresFixos()) {
                    if (v.isNew()) {
                        ValorDefinidoFixoBI newValor = new ValorDefinidoFixoBI();
                        converterValorFixo.update(newValor, v);
                        d.getValoresFixos().add(newValor);
                    }
                }
            }
        };
    }

    @Override
    public BIStefanini beforeSave(BIStefanini entity) {
        entity.getParametros().forEach(p -> {
            p.setBi(entity);
            p.getValoresFixos().forEach(v -> v.setParametro(p));
        });
        beforeSaveQuery(entity.getQuery());
        return entity;
    }

    private void beforeSaveQuery(BIQuery query) {
        query.getChildren().forEach(q -> {
            q.setQueryPai(query);
            beforeSaveQuery(q);
        });
    }

    @Override
    public BIStefaniniDTO entityToDTO(BIStefanini ob) {
        BIStefaniniDTO res = new BIStefaniniDTO(ob);

        return res;
    }

    @Override
    public BIStefaniniBasicDTO entityToBasicDTO(BIStefanini ob) {
        BIStefaniniBasicDTO res = new BIStefaniniBasicDTO();
        res.setId(ob.getId());
        res.setDescricao(ob.getDescricao());
        return res;
    }

    @Override
    public BIStefanini dtoToEntity(BIStefaniniDTO dto) {
        BIStefanini e = null;
        if (!dto.isNew()) {
            e = getById(dto.getId());
        }
        if (e == null) {
            e = new BIStefanini();
        }
        e.setDescricao(dto.getDescricao());

        ConvertObj<ParametroBI, ParametroBIDTO> converterParametro = getParametroBIParametroBIDTOConvertObj();


        ConvertObj<BIQuery, BIQueryDTO> converterQuery = new ConvertObj<BIQuery, BIQueryDTO>() {
            @Override
            public void update(BIQuery d, BIQueryDTO o) {
                d.setDescricao(o.getDescricao());
                d.setPrincipal(false);
                d.setQuery(o.getQuery());
                d.setTipoQuery(o.getTipoQuery());
                d.setNomeField(o.getNomeField());

                if (o.getConteudoJasper() != null && !o.getConteudoJasper().isEmpty()) {
                    BIJasper jasper = d.getJasper();
                    if (jasper == null) {
                        jasper = new BIJasper();
                        d.setJasper(jasper);
                    }
                    jasper.setConteudo(o.getConteudoJasper());
                }

                Iterator<BIQuery> iteratorChildren = d.getChildren().iterator();

                while (iteratorChildren.hasNext()) {
                    BIQuery query = iteratorChildren.next();
                    Optional<BIQueryDTO> opQuery = o.getChildren().stream().filter(f -> f.getId().equals(query.getId())).findFirst();
                    if (opQuery.isPresent()) {
                        update(query, opQuery.get());
                    } else {
                        iteratorChildren.remove();
                    }
                }

                for (BIQueryDTO dtoQuery : o.getChildren()) {
                    if (dtoQuery.isNew()) {
                        BIQuery newQuery = new BIQuery();
                        update(newQuery, dtoQuery);
                        d.getChildren().add(newQuery);
                    }
                }
            }
        };

        Iterator<ParametroBI> iteratorParametro = e.getParametros().iterator();
        while (iteratorParametro.hasNext()) {
            ParametroBI parametroBI = iteratorParametro.next();
            Optional<ParametroBIDTO> op = dto.getParametros().stream().filter(f -> f.getId().equals(parametroBI.getId())).findFirst();
            if (op.isPresent()) {
                converterParametro.update(parametroBI, op.get());
            } else {
                iteratorParametro.remove();
            }
        }

        for (ParametroBIDTO pDto : dto.getParametros()) {
            if (pDto.isNew()) {
                ParametroBI newParametro = new ParametroBI();
                converterParametro.update(newParametro, pDto);
                e.getParametros().add(newParametro);
            }
        }

        BIQuery queryMain = e.getQuery();
        if (queryMain == null) {
            e.setQuery(new BIQuery());
        }

        converterQuery.update(e.getQuery(), dto.getQuery());
        e.getQuery().setPrincipal(true);

        return e;
    }

    public DownloadDTO executarBI(Long id, HashMap<String, Object> data, FormatosBIEnum formatoEnum) {
        BIStefanini bi = getById(id);
        PlayBIComponent play = new PlayBIComponent(this);
        List<HashMap<String, Object>> dataResult = play.executarBI(bi, data);

        byte[] conteudoRelatorio = "".getBytes();

        if (!Objects.isNull(formatoEnum)) switch (formatoEnum) {
            case XLSX -> {
                Workbook workbook = xlsxReportGenerator.mapBIToXlsx(dataResult);
                conteudoRelatorio = xlsxReportGenerator.workBookToByte(workbook);
            }
            case JSON -> conteudoRelatorio = ToolStr.toJson(dataResult).getBytes();
            case PDF -> conteudoRelatorio = jasperBIComponent.buildJasper(dataResult, bi);
            case CSV -> conteudoRelatorio = ToolCSV.mapToCsv(dataResult).getBytes();
            default -> {
            }
        }

        return new DownloadDTO(bi.getDescricao(), formatoEnum.toLowerCase(), conteudoRelatorio);
    }
}