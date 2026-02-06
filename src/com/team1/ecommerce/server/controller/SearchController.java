package com.team1.ecommerce.server.controller;

import com.team1.ecommerce.server.service.ProductService;
import com.team1.ecommerce.shared.model.Product;

import java.util.List;

/**
 * Server‑side controller for handling search requests.
 *
 * <p>This class is intentionally simple: it delegates the heavy lifting to
 * {@link ProductService} and focuses on converting the result to XML that
 * matches the project protocol.</p>
 */
public class SearchController {

    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Performs a search and returns an XML response string.
     *
     * @param nameQuery   product name fragment (may be empty)
     * @param categoryOpt optional category (may be null/empty)
     * @return XML response:
     *
     * &lt;response&gt;
     *   &lt;status&gt;SUCCESS&lt;/status&gt;
     *   &lt;products&gt;
     *     &lt;product id="..."&gt;...&lt;/product&gt;
     *   &lt;/products&gt;
     * &lt;/response&gt;
     */
    public String searchToXml(String nameQuery, String categoryOpt) {

        List<Product> products = productService.search(nameQuery, categoryOpt);

        StringBuilder xml = new StringBuilder();
        xml.append("<response>");
        xml.append("<status>SUCCESS</status>");
        xml.append("<message>").append("Search completed").append("</message>");

        xml.append("<products>");
        for (Product p : products) {
            xml.append("<product id=\"").append(escape(p.getId())).append("\">");
            xml.append("<name>").append(escape(p.getName())).append("</name>");
            xml.append("<price>").append(p.getPrice()).append("</price>");
            xml.append("<stock>").append(p.getStock()).append("</stock>");
            if (p.getCategory() != null) {
                xml.append("<category>").append(escape(p.getCategory())).append("</category>");
            }
            xml.append("</product>");
        }
        xml.append("</products>");

        xml.append("</response>");
        return xml.toString();
    }

    /**
     * Escapes characters that are unsafe inside XML text nodes/attributes.
     */
    private String escape(String text) {
        if (text == null) {
            return "";
        }
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}

