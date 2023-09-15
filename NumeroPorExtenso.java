import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumeroPorExtenso {
    private static final String[] UNIDADES = { "", "um", "dois", "três", "quatro", "cinco",
            "seis", "sete", "oito", "nove", "dez", "onze", "doze", "treze", "quatorze", "quinze",
            "dezesseis", "dezessete", "dezoito", "dezenove" };

    private static final String[] DEZENAS = { "", "", "vinte", "trinta", "quarenta", "cinquenta",
            "sessenta", "setenta", "oitenta", "noventa" };

    private static final String[] CENTENAS = { "", "cento", "duzentos", "trezentos",
            "quatrocentos", "quinhentos", "seiscentos", "setecentos",
            "oitocentos", "novecentos" };

    public static String converter(BigDecimal valor) {
        StringBuilder sb = new StringBuilder();

        int parteInteira = valor.intValue();
        int parteDecimal = valor.subtract(new BigDecimal(parteInteira)).multiply(new BigDecimal(100)).intValue();

        if (parteInteira > 0) {
            sb.append(converter(parteInteira));
            sb.append(" reais");
        }

        if (parteDecimal > 0) {
            if (sb.length() > 0) {
                sb.append(" e ");
            }
            sb.append(converter(parteDecimal));
            sb.append(" centavos");
        }

        return sb.toString();
    }

    private static String converter(int valor) {
        if (valor < 20) {
            return UNIDADES[valor];
        } else if (valor < 100) {
            return DEZENAS[valor / 10] + ((valor % 10 != 0) ? (" e ") : "") + UNIDADES[valor % 10];
        } else if (valor < 1000) {
            return CENTENAS[valor / 100] + ((valor % 100 != 0) ? (" e ") : "") + converter(valor % 100);
        } else if (valor < 1000000) {
            return converter(valor / 1000) + ((valor / 1000 > 1) ? (" mil") : (" mil")) + ((valor % 1000 != 0) ? (" ") : "") + converter(valor % 1000);
        }

        return "";
    }
}
