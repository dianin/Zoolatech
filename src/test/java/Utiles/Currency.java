package Utiles;

public enum Currency {
    USD("USD", "$"),
    EUR("EUR", "€"),
    CAD("CAD", "$"),
    AUD("AUD", "$"),
    GBP("GBP", "£");

    private String value;
    private String symbol;

    Currency (String value, String symbol)
    {
        this.value = value;
        this.symbol = symbol;
    }

    public String getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }
}
