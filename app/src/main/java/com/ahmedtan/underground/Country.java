package com.ahmedtan.underground;

import java.io.Serializable;

public class Country implements Serializable {
    private String Country;
    private String CountryCode;
    private String NewConfirmed;
    private String TotalConfirmed;
    private String NewDeaths;
    private String TotalDeaths;
    private String NewRecovered;
    private String TotalRecovered;
    private String Active;
    private String Critical;
    private String CasesPOM;
    private String flagUrl;


    public Country(String country, String countryCode, String newConfirmed, String totalConfirmed, String newDeaths, String totalDeaths, String newRecovered, String totalRecovered, String active, String critical, String casesPOM, String flagUrl) {
        Country = country;
        CountryCode = countryCode;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Active = active;
        Critical = critical;
        CasesPOM = casesPOM;
        this.setFlagUrl(flagUrl);
    }



    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }



    public String getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(String newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public String getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        NewDeaths = newDeaths;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public String getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(String newRecovered) {
        NewRecovered = newRecovered;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }


    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getCritical() {
        return Critical;
    }

    public void setCritical(String critical) {
        Critical = critical;
    }

    public String getCasesPOM() {
        return CasesPOM;
    }

    public void setCasesPOM(String casesPOM) {
        CasesPOM = casesPOM;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    @Override
    public String toString() {
        return "Country{" +
                "Country='" + Country + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", NewConfirmed='" + NewConfirmed + '\'' +
                ", TotalConfirmed='" + TotalConfirmed + '\'' +
                ", NewDeaths='" + NewDeaths + '\'' +
                ", TotalDeaths='" + TotalDeaths + '\'' +
                ", NewRecovered='" + NewRecovered + '\'' +
                ", TotalRecovered='" + TotalRecovered + '\'' +
                ", Active='" + Active + '\'' +
                ", Critical='" + Critical + '\'' +
                ", CasesPOM='" + CasesPOM + '\'' +
                ", flagUrl='" + flagUrl + '\'' +
                '}';
    }
}
