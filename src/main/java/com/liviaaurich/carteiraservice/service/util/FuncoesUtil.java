package com.liviaaurich.carteiraservice.service.util;

import com.liviaaurich.carteiraservice.web.rest.errors.ParametrizedMessageException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

@UtilityClass
public final class FuncoesUtil {

    public static void verificarDuplicidade(Integer numRegistros, String mensagem) {
        if(numRegistros > 0) {
            throw new ParametrizedMessageException(mensagem, ConstantsUtil.ERROR_TITLE);
        }
    }

    public static String formatarCampoNumerico(String arg) {
        if(StringUtils.isNotEmpty(arg)) {
            return arg.replaceAll("\\D", Strings.EMPTY);
        }
        return arg;
    }

}
