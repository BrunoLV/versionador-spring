package com.porto.amazonas.versionamento.web.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.porto.amazonas.versionamento.model.Versao;

/**
 * Classe utilizada apenas nos Controllers da aplica��o para representar uma
 * lista de vers�es selecionavel.
 * @author BRUNO VIANA
 */
public class VersaoDataModel extends ListDataModel<Versao> implements SelectableDataModel<Versao> {

	public VersaoDataModel() {
	} // fim do m�todo construtor
	
	public VersaoDataModel(List<Versao> versoes) {
		super(versoes);
	} // fim do m�todo construtor
	
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
	} // fim do m�todo getRowData

	@Override
	public Object getRowKey(Versao versao) {
		return versao.getNome();
	} // fim do m�todo getRowKey

} // fim da classe VersaoDataModel