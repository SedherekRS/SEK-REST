/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.dgtic.apps;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Banxico{

    //Variables del webservice http://www.banxico.org.mx/DgieWSWeb/DgieWS?WSDL
    private final String URL        = "http://www.banxico.org.mx/DgieWSWeb/DgieWS";
    private final String NS         = "http://ws.dgie.banxico.org.mx";
    private final String OPERATION  = "tiposDeCambioBanxico";
    private final String CHARSET    = "UTF-8";
    private final String ENVELOPE = "<?xml version=\"1.0\" encoding=\"" + CHARSET + "\" standalone=\"no\"?>"
        + "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apachesoap=\"http://xml.apache.org/xml-soap\" "
        + "xmlns:impl=\"" + NS + "\" xmlns:intf=\"" + NS + "\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" "
        + "xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:wsdlsoap=\"http://schemas.xmlsoap.org/wsdl/soap/\" "
        + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >"
        + "<SOAP-ENV:Body><mns:" + OPERATION + " xmlns:mns=\"" + NS + "\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
        + "</mns:" + OPERATION + "></SOAP-ENV:Body></SOAP-ENV:Envelope>";
    private final static String REGEX = 
          "<bm:Series\\s++TITULO\\s*+=\\s*+\"(?<titulo>[^\"]*+)\""
        + "\\s++IDSERIE\\s*+=\\s*+\"SF43718\"[^>]*+>"
        + "\\s*+<bm:Obs\\s++TIME_PERIOD\\s*+=\\s*+\"(?<fecha>[^\"]*+)\""
        + "\\s++OBS_VALUE\\s*+=\\s*+\"(?<cotizacion>[^\"]*)";

    /*public static void main(String[] args) throws Exception {
        Banxico http = new Banxico();
        String resultado = http.sendPost();
        float r= http.procesarTexto(resultado);  
    }*/

    // HTTP POST
    public String sendPost() throws Exception {

        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Configuracion del Header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Content-Encoding", "deflate");
        con.setRequestProperty("Content-Type", "text/xml; charset=" + CHARSET);
        con.setRequestProperty("SOAPAction", OPERATION);

        // Enviar el request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        
        System.out.println(this.ENVELOPE);
        
        wr.writeBytes(ENVELOPE);
        wr.flush();
        wr.close();

        // Leer la respuesta
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Devolver el XML
        return response.toString();
    }

    // Imprimir los datos
   public float procesarTexto(final String uxml) {
       float valor=0;
        // Sacar las entities 
        //  (se rompe el XML pero es mas facil procesarlo directamente)
        String xml = unescapeCommonEntities(uxml);

        // Obtener los campos buscados con una expresion regular sobre todo el xml
        Pattern idPatt = Pattern.compile(REGEX);
        Matcher m = idPatt.matcher(xml);
        if (m.find()) {
            //System.out.println("Cotizacion: " + m.group("cotizacion"));
            valor = Float.parseFloat(m.group("cotizacion"));
            //System.out.println("Fecha: " + m.group("fecha"));
            //System.out.println("Descripcion: " + m.group("titulo"));
        } else {
            System.out.println("ERROR!");
            
        }
        return valor;
        
    }

    // Funcion para decodificar las 5 entities mas comunes
    private static String unescapeCommonEntities( final String xmle )
    {
        return xmle.replaceAll( "&lt;", "<" )
                    .replaceAll( "&gt;", ">" )
                    .replaceAll( "&amp;", "&" )
                    .replaceAll( "&apos;", "'" )
                    .replaceAll( "&quot;", "\"" );
    }

}
