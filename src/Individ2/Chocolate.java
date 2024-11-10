package Individ2;

public class Chocolate {
    String country_exporter;
    String company_exporter;
    String brand;
    int index_expertise;
    int year_expertise;
    int percent_cacao;
    String country_brand; // страна выпускающая шоколад
    float grade;

    public Chocolate() {
        this.country_exporter = "";
        this.company_exporter = "";
        this.brand = "";
        this.index_expertise = 0;
        this.year_expertise = 0;
        this.percent_cacao = 0;
        this.country_brand = "";
        this.grade = 0;
    }

    public Chocolate(String country_exporter, String company_exporter, String brand, int index_expertise,
                     int year_expertise, int percent_cacao, String country_brand, float grade) {
        setCountryExporter(country_exporter);
        setCompanyExporter(company_exporter);
        setBrand(brand);
        setIndexExpertise(index_expertise);
        setYearExpertise(year_expertise);
        setPercentCacao(percent_cacao);
        setCountryBrand(country_brand);
        setGrade(grade);
    }

    public void setCountryExporter(String country_exporter) { this.country_exporter = country_exporter; }
    public void setCompanyExporter(String company_exporter) { this.company_exporter = company_exporter; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setIndexExpertise(int index_expertise) { this.index_expertise = index_expertise; }
    public void setYearExpertise(int year_expertise) { this.year_expertise = year_expertise; }
    public void setPercentCacao(int percent_cacao) { this.percent_cacao = percent_cacao; }
    public void setCountryBrand(String country_brand) { this.country_brand = country_brand; }
    public void setGrade(float grade) { this.grade = grade; }

    public String getCountryExporter() { return country_exporter; }
    public String getCompanyExporter() { return company_exporter; }
    public String getBrand() { return brand; }
    public int getIndexExpertise() { return index_expertise; }
    public int getYearExpertise() { return year_expertise; }
    public int getPercentCacao() { return percent_cacao; }
    public String getCountryBrand() { return country_brand; }
    public float getGrade() { return grade; }
}
