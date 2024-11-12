package Individ2;

import java.util.TreeMap;

public class Chocolate {
    private String countryExporter;
    private String companyExporter;
    private String brand;
    private int indexExpertise;
    private int yearExpertise;
    private int percentCacao;
    private String countryBrand; // страна, выпускающая шоколад
    private float grade;

    public Chocolate() {
        this.countryExporter = "";
        this.companyExporter = "";
        this.brand = "";
        this.indexExpertise = 0;
        this.yearExpertise = 0;
        this.percentCacao = 0;
        this.countryBrand = "";
        this.grade = 0;
    }

    public Chocolate(String countryExporter, String companyExporter, String brand, int indexExpertise,
                     int yearExpertise, int percentCacao, String countryBrand, float grade) {
        setCountryExporter(countryExporter);
        setCompanyExporter(companyExporter);
        setBrand(brand);
        setIndexExpertise(indexExpertise);
        setYearExpertise(yearExpertise);
        setPercentCacao(percentCacao);
        setCountryBrand(countryBrand);
        setGrade(grade);
    }

    public String getContinent(TreeMap<String, String> countryContinent) throws IllegalArgumentException {
        if (countryExporter == null || countryExporter.isEmpty()) {
            throw new IllegalArgumentException("Отсутствует название страны!");
        }

        String continent = countryContinent.get(countryExporter);
        if (continent == null || continent.isEmpty())
            throw new IllegalArgumentException("Не найден континент для " + countryExporter);

        return continent;
    }

    public void setCountryExporter(String countryExporter) { this.countryExporter = countryExporter; }
    public void setCompanyExporter(String companyExporter) { this.companyExporter = companyExporter; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setIndexExpertise(int indexExpertise) { this.indexExpertise = indexExpertise; }
    public void setYearExpertise(int yearExpertise) { this.yearExpertise = yearExpertise; }
    public void setPercentCacao(int percentCacao) { this.percentCacao = percentCacao; }
    public void setCountryBrand(String countryBrand) { this.countryBrand = countryBrand; }
    public void setGrade(float grade) { this.grade = grade; }

    public String getCountryExporter() { return countryExporter; }
    public String getCompanyExporter() { return companyExporter; }
    public String getBrand() { return brand; }
    public int getIndexExpertise() { return indexExpertise; }
    public int getYearExpertise() { return yearExpertise; }
    public int getPercentCacao() { return percentCacao; }
    public String getCountryBrand() { return countryBrand; }
    public float getGrade() { return grade; }
}
