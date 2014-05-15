package com.porto.amazonas.versionamento.web.datamodel;

import com.porto.amazonas.versionamento.model.Versao;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Classe utilizada apenas nos Controllers da aplicacao para representar uma
 * lista de versoes selecionavel.
 *
 * @author BRUNO VIANA
 */
public class VersaoDataModel extends ListDataModel<Versao> implements SelectableDataModel<Versao> {

    public VersaoDataModel() {
    } // fim do metodo construtor

    public VersaoDataModel(List<Versao> versoes) {
        super(versoes);
    } // fim do metodo construtor

    @SuppressWarnings("unchecked")
    @Override
    public Versao getRowData(String rowKey) {
        List<Versao> versoes = (List<Versao>) getWrappedData();
        for (Versao versao : versoes) {
            if (versao.getNome().equals(rowKey)) {
                return versao;
            } // fim do bloco if
        } // fim do bloco for
        return null;
    } // fim do metodo getRowData

    @Override
    public Object getRowKey(Versao versao) {
        return versao.getNome();
    } // fim do metodo getRowKey

} // fim da classe VersaoDataModel