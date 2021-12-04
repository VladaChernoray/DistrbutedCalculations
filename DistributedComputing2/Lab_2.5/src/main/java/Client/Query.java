package Client;

import java.io.Serializable;

public class Query implements Serializable {
    public QueryType queryType;
    public String productCountry, productName, sectionName;
    public int productPrice, productID, sectionID, newSectionID, sectionNumber;
}
