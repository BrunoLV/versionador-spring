package com.porto.amazonas.versionamento.web.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.porto.amazonas.versionamento.model.Dependencia;

/**
 * Classe utilizada apenas nos Controllers da aplica��o para representar uma
 * lista de dependencias selecionavel.
 * @author BRUNO VIANA
 */
public class DependenciaDataModel extends ListDataModel<Dependencia> implements SelectableDataModel<Dependencia> {

	public DependenciaDataModel() {
	} // fim do m�todo construtor
	
	public DependenciaDataModel(List<Dependencia> dependencias) {
		super(dependencias);
	} // fim do m�todo construtor	
	
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
	} // fim do m�todo getRowData

	@Override
	public Object getRowKey(Dependencia dependencia) {
		return dependencia.getNome();
	} // fim do m�todo getRowKey

} // fim da classe DependenciaDataModel