package com.porto.amazonas.versionamento.web.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.porto.amazonas.versionamento.model.Sistema;

/**
 * Classe utilizada apenas nos Controllers da aplica��o para representar uma
 * lista de sistemas selecionavel.
 * @author BRUNO VIANA
 */
public class SistemaDataModel extends ListDataModel<Sistema> implements SelectableDataModel<Sistema> {

	public SistemaDataModel() {
	} // fim do m�todo construtor

	public SistemaDataModel(List<Sistema> sistemas) {
		super(sistemas);
	} // fim do m�todo construtor com parametros

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
	} // fim do m�todo getRowData

	@Override
	public Object getRowKey(Sistema sistema) {
		return sistema.getNome();
	} // fim do m�todo getRowKey

} // fim da classe SistemaDataModel
