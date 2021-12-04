package Client;

import java.io.Serializable;

public enum QueryType implements Serializable {
    addProduct,
    deleteProduct,
    addSection,
    deleteSection,
    changeSectionForProduct,
    getProductsInCurrentSection,
    getSections,
    getProducts
}
