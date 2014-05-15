package com.porto.amazonas.versionamento.web.datamodel;

import com.porto.amazonas.versionamento.model.Dependencia;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Classe utilizada apenas nos Controllers da aplicacao para representar uma
 * lista de dependencias selecionavel.
 *
 * @author BRUNO VIANA
 */
public class DependenciaDataModel extends ListDataModel<Dependencia> implements SelectableDataModel<Dependencia> {

    public DependenciaDataModel() {
    } // fim do metodo construtor

    public DependenciaDataModel(List<Dependencia> dependencias) {
        super(dependencias);
    } // fim do metodo construtor

    @SuppressWarnings("unchecked")
    @Override
    public Dependencia getRowData(String rowKey) {
        List<Dependencia> dependencias = (List<Dependencia>) getWrappedData();
        for (Dependencia dependencia : dependencias) {
            if (dependencia.getNome().equals(rowKey)) {
                return dependencia;
            } // fim do bloco if
        } // fim do bloco for
        return null;
    } // fim do metodo getRowData

    @Override
    public Object getRowKey(Dependencia dependencia) {
        return dependencia.getNome();
    } // fim do metodo getRowKey

} // fim da classe DependenciaDataModel