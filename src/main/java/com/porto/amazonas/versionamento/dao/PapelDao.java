package com.porto.amazonas.versionamento.dao;

import com.porto.amazonas.versionamento.model.EnumPapel;
import com.porto.amazonas.versionamento.model.Papel;

public interface PapelDao extends BaseDao<Papel> {

    Papel buscarPapelPorDescricao(EnumPapel enumPapel);

}
