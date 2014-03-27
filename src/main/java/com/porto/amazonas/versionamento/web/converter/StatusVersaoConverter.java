package com.porto.amazonas.versionamento.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Classe utilizada para fazer o papel de converter da informação status.
 * @author BRUNO VIANA
 */
@FacesConverter("statusVersaoConverter")
public class StatusVersaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String string) {
		Boolean retorno = null;
		if (string.equals("Sim") || string.equals("sim") || string.equals("SIM")) {
			retorno = Boolean.TRUE;
		} else {
			retorno = Boolean.FALSE;
		} // fim do bloco if/else
		return retorno;
	} // fim do método getAsObject

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		String retorno = null;
		if (object instanceof Boolean) {
			Boolean status = (Boolean) object;
			if (status == Boolean.TRUE) {
				retorno = "Sim";
			} else {
				retorno = "Não";
			} // fim do bloco if/else
		} // fim do bloco if
		return retorno;
	} // fim do método getAsString

} // fim da classe StatusVersaoConverter
