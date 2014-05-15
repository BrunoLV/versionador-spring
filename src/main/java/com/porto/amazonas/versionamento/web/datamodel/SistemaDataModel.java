package com.porto.amazonas.versionamento.web.datamodel;

import com.porto.amazonas.versionamento.model.Sistema;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Classe utilizada apenas nos Controllers da aplicacao para representar uma
 * lista de sistemas selecionavel.
 *
 * @author BRUNO VIANA
 */
public class SistemaDataModel extends ListDataModel<Sistema> implements SelectableDataModel<Sistema> {

    public SistemaDataModel() {
    } // fim do metodo construtor

    public SistemaDataModel(List<Sistema> sistemas) {
        super(sistemas);
    } // fim do metodo construtor com parametros

    @SuppressWarnings("unchecked")
    @Override
    public Sistema getRowData(String rowKey) {
        List<Sistema> sistemas = (List<Sistema>) getWrappedData();
        for (Sistema sistema : sistemas) {
            if (sistema.getNome().equals(rowKey)) {
                return sistema;
            } // fim do bloco if
        } // fim do bloco for
        return null;
    } // fim do metodo getRowData

    @Override
    public Object getRowKey(Sistema sistema) {
        return sistema.getNome();
    } // fim do metodo getRowKey

} // fim da classe SistemaDataModel
