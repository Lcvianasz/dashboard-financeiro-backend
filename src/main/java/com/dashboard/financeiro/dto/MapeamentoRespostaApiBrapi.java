package com.dashboard.financeiro.dto;

import lombok.Data;
import java.util.List;

@Data
public class MapeamentoRespostaApiBrapi {

    private List<Stock> results;

    @Data
    public static class Stock {
        private String symbol;
        private Double regularMarketPrice;
        private Double regularMarketChangePercent;
        private String longName;
        private SummaryProfile summaryProfile;
    }

    @Data
    public static class SummaryProfile {
        private String sector;
        private String industry;
        private String website;
    }
}
