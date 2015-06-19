
package botask;

import com.hp.hpl.jena.query.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Carlos Villegas and Felipe Londono
 */
public class Test01 {

    public static void main(String[] args) {

        DBConnect db = new DBConnect();
        Scanner keyboard = new Scanner(System.in);

        ArrayList<String> listaContinentesDesc = db.look4Continent();
        String continente = "";
        for (String listaContinentesDesc1 : listaContinentesDesc) {
            System.out.println("Do you live in " +
                    listaContinentesDesc1 + " ? y/n");
            String myResponse = keyboard.nextLine();
            if (db.found(myResponse, listaContinentesDesc1)) {
                continente = listaContinentesDesc1;
                break;
            }
        }

        String sparqlQueryString1
                = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>\n"
                + "PREFIX dbpprop: <http://dbpedia.org/property/>"
                + "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>"
                + "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX yago:  <http://dbpedia.org/class/yago/>"
                + "\n"
                + "SELECT ?continentes\n"
                + "\n"
                + "WHERE {   ?continentes rdf:type yago:" + continente + ";"
                + "            \n"
                + "            "
                + " "
                + "}";

        Query query = QueryFactory.create(sparqlQueryString1);
        QueryExecution qexec = QueryExecutionFactory
                .sparqlService("http://dbpedia.org/sparql", query);
        ResultSet results = qexec.execSelect();
        List<QuerySolution> listapaisestemp = ResultSetFormatter
                .toList(results);
        ArrayList<String> listapaises = new ArrayList();
        for (QuerySolution listapaisestemp1 : listapaisestemp) {
            listapaises.add(listapaisestemp1.toString());
        }

        ResultSet resultslanguage;

        ArrayList<String> listaidiomas = new ArrayList();
        ArrayList<String> listCountriesLang = new ArrayList<>();

        String Queryoflanguage
                = "PREFIX rdf: "
                + "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
                + "PREFIX dbpprop: <http://dbpedia.org/property/>"
                + "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>"
                + "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX yago:  <http://dbpedia.org/class/yago/>"
                + "SELECT DISTINCT ?idioma WHERE {"
                + "?continentes rdf:type yago:" + continente + ";"
                + "dbpedia-owl:language ?idioma"
                + "}";
        Query query1 = QueryFactory.create(Queryoflanguage);
        QueryExecution qexec1 = QueryExecutionFactory
                .sparqlService("http://dbpedia.org/sparql", query1);
        resultslanguage = qexec1.execSelect();
        List<QuerySolution> listaidiomastemp
                = ResultSetFormatter.toList(resultslanguage);

        for (QuerySolution listaidiomastemp1 : listaidiomastemp) {
            String temp2 = listaidiomastemp1.toString();
            temp2 = temp2.substring(41, temp2.length() - 3);
            listaidiomas.add(temp2);
        }

        for (String listaidioma : listaidiomas) {
            System.out.println("Is " + listaidioma +
                    " " + "a language in your country ?");
            String myResponse = keyboard.nextLine();
            if (myResponse.equals("y") || myResponse.equals("Y")) {
                String idioma = listaidioma;
                for (String temp : listapaises) {
                    temp = temp.substring(46, temp.length() - 3);
                    Queryoflanguage
                            = "PREFIX rdf: "
                            + "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                            + "PREFIX dbpedia-owl: "
                            + "<http://dbpedia.org/ontology/>"
                            + "PREFIX dbpprop: "
                            + "<http://dbpedia.org/property/>"
                            + "PREFIX foaf:  "
                            + "<http://xmlns.com/foaf/0.1/>"
                            + "PREFIX rdfs:  "
                            + "<http://www.w3.org/2000/01/rdf-schema#>"
                            + "PREFIX yago:  <http://dbpedia.org/class/yago/>"
                            + "SELECT DISTINCT ?idioma WHERE {"
                            + "?continentes rdf:type yago:" + continente + ";"
                            + "foaf:name '" + temp + "'@en;"
                            + "dbpedia-owl:language ?idioma"
                            + "}";
                    query1 = QueryFactory.create(Queryoflanguage);
                    qexec1 = QueryExecutionFactory
                            .sparqlService("http://dbpedia.org/sparql", query1);
                    resultslanguage = qexec1.execSelect();
                    listaidiomastemp.clear();
                    listaidiomastemp
                            = ResultSetFormatter.toList(resultslanguage);

                    for (QuerySolution listaidiomastemp1 : listaidiomastemp) {
                        String temp2 = listaidiomastemp1.toString();
                        temp2 = temp2.substring(41, temp2.length() - 3);
                        if (temp2.equals(idioma)) {

                            listCountriesLang.add(temp);

                        }
                    }
                }
                break;
            }
        }

        if (listCountriesLang.size() == 1) {

            System.out.println("The country that you are thinking, is "
                    + listCountriesLang.get(0));
            System.exit(0);

        }

        ArrayList<String> listCurrency = new ArrayList<>();

        for (String listCountriesLang1 : listCountriesLang) {
            Queryoflanguage = "PREFIX rdf: "
                    + "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + "PREFIX dbpedia-owl: "
                    + "<http://dbpedia.org/ontology/>"
                    + "PREFIX dbpprop: "
                    + "<http://dbpedia.org/property/>"
                    + "PREFIX foaf:  "
                    + "<http://xmlns.com/foaf/0.1/>"
                    + "PREFIX rdfs:  "
                    + "<http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX yago:  <http://dbpedia.org/class/yago/>"
                    + "SELECT DISTINCT ?currency WHERE {"
                    + "?continentes rdf:type yago:" + continente + ";"
                    + "foaf:name '" + listCountriesLang1 + "'@en;" +
                    "dbpprop:currency ?currency" + "}";
            query1 = QueryFactory.create(Queryoflanguage);
            qexec1 = QueryExecutionFactory
                    .sparqlService("http://dbpedia.org/sparql", query1);
            resultslanguage = qexec1.execSelect();
            listaidiomastemp.clear();
            listaidiomastemp
                    = ResultSetFormatter.toList(resultslanguage);
            for (QuerySolution listaidiomastemp1 : listaidiomastemp) {
                String temp2 = listaidiomastemp1.toString();
                if (temp2.substring(0, 19).equals("( ?currency = <http")) {

                    temp2 = temp2.substring(43, temp2.length() - 3);

                    listCurrency.add(temp2);

                } else {

                    temp2 = temp2.substring(15, temp2.length() - 6);

                    listCurrency.add(temp2);

                }
            }
        }

        ArrayList<String> countriesCurrenctLang = new ArrayList<>();

        for (int j = 0; j < listCurrency.size(); j++) {

            System.out.println("Is the " + listCurrency.get(j)
                    + " the currency of your country ? y/n");

            String myResponse = keyboard.nextLine();

            if (myResponse.equals("y") || myResponse.equals("Y")) {

                String currency = listCurrency.get(j);

                for (String listCountriesLang1 : listCountriesLang) {
                    Queryoflanguage = "PREFIX rdf: "
                            + "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                            + "PREFIX dbpedia-owl: "
                            + "<http://dbpedia.org/ontology/>"
                            + "PREFIX dbpprop: "
                            + "<http://dbpedia.org/property/>"
                            + "PREFIX foaf:  "
                            + "<http://xmlns.com/foaf/0.1/>"
                            + "PREFIX rdfs:  "
                            + "<http://www.w3.org/2000/01/rdf-schema#>"
                            + "PREFIX yago:  <http://dbpedia.org/class/yago/>"
                            + "SELECT DISTINCT ?currency WHERE {"
                            + "?continentes rdf:type yago:" + continente + ";"
                            + "foaf:name '" + listCountriesLang1 + "'@en;"
                            + "dbpprop:currency ?currency" + "}";
                    query1 = QueryFactory.create(Queryoflanguage);
                    qexec1 = QueryExecutionFactory
                            .sparqlService("http://dbpedia.org/sparql", query1);
                    resultslanguage = qexec1.execSelect();
                    listaidiomastemp.clear();
                    listaidiomastemp
                            = ResultSetFormatter.toList(resultslanguage);
                    for (QuerySolution listaidiomastemp1 : listaidiomastemp) {
                        String temp2 = listaidiomastemp1.toString();
                        if (temp2.substring(0, 19)
                                .equals("( ?currency = <http")) {
                            temp2 = temp2.substring(43, temp2.length() - 3);
                            if (temp2.equals(currency)) {
                                countriesCurrenctLang.add(listCountriesLang1);
                            }
                        } else {
                            temp2 = temp2.substring(15, temp2.length() - 6);
                            if (temp2.equals(currency)) {
                                countriesCurrenctLang.add(listCountriesLang1);
                            }
                        }
                    }
                }

                if (countriesCurrenctLang.size() == 1) {

                    System.out.println("The country that you are thinking, is "
                            + countriesCurrenctLang.get(0));
                    System.exit(0);

                }


                for (String countriesCurrenctLang1 : countriesCurrenctLang) {
                    String queryCapital = "                    "
                   + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                        + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
                        + "PREFIX dbpprop: <http://dbpedia.org/property/>"
                        + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
                        + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                        + "PREFIX yago: <http://dbpedia.org/class/yago/>"
                        + "SELECT ?capital WHERE {"
                        + "?continentes rdf:type yago:" + continente + ";"
                        + "foaf:name '" + countriesCurrenctLang1 + "'@en;"
                        + "dbpprop:capital ?capital"
                        + "}";
                    Query query2 = QueryFactory.create(queryCapital);
                    QueryExecution qexec2 = QueryExecutionFactory
                    .sparqlService("http://dbpedia.org/sparql", query2);
                    ResultSet resultscapitales = qexec2.execSelect();
                    List<QuerySolution> listacapitalestemp
                        = ResultSetFormatter.toList(resultscapitales);
                    ArrayList<String> listacapitales = new ArrayList();
                    for (QuerySolution listacapitales1 : listacapitalestemp) {
                        String temp2 = listacapitales1.toString();
                        if(temp2.substring(0, 18)
                                .equals("( ?capital = <http")){
                            temp2 = temp2.substring(42, temp2.length() - 3);
                        }else{
                            temp2 = temp2.substring(14, temp2.length()-6);
                            
                            
                        }
                        
                        listacapitales.add(temp2);
                    }
                    
                    for(String capital : listacapitales){
                        System.out.println("Is "+ capital +
                                " the capital of the country? y/n?");
                        String myResponse1 = keyboard.nextLine();
                        if (myResponse1.equals("y") || myResponse1.equals("Y")){
                            System.out.println("The country that you"
                                    + " are thinking, is "
                            + countriesCurrenctLang1);
                            break;
                        }
                    }

                }

                System.exit(0);

            }

        }

        System.out.println("You LIER!! , your country has to have one of "
                + "the previous languages");

    }
}

/*
                    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                    PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>
                    PREFIX dbpprop: <http://dbpedia.org/property/>
                    PREFIX foaf: <http://xmlns.com/foaf/0.1/>
                    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                    PREFIX yago: <http://dbpedia.org/class/yago/>
                    SELECT ?money WHERE {
                    ?continentes rdf:type yago:EuropeanCountries;
                    dbpprop:currencyCode ?money
                    }
*/