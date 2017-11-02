/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.ingenieria.diagnosticapp.util;

import java.util.ArrayList;

/**
 *
 * @author michel
 */
public class MailBody {

    private final ArrayList<String> bodies = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<String[]> tabla = new ArrayList<>();
        tabla.add(new String[]{"Nombre", "Apellido", "Edad"});
        tabla.add(new String[]{"Juan", "Perez", "30"});
        tabla.add(new String[]{"Pepe", "Veraz", "40"});

        MailBody fmb = new MailBody();
        fmb.setTitle("Este es un <b>Titulo</b>");
        fmb.setFooter("Este es el <b>Footer</b>");
        fmb.setHeader("Este es el <b>Header</b>");
        //fmb.addBodyPart("Esta es un body Part (el primero)");
        //fmb.addBodyPart("Esta es un body Part (el segundo)");
        //fmb.addBodyPart(MailBody.createTable(tabla));

        System.out.println(fmb.build());

    }

    private static final String bodyTemplate = ""
            + "<table width='100%%' bgcolor='#f6f8f1' border='0' cellpadding='0' cellspacing='0'>\n"
            + "  <tbody>\n"
            + "    <tr>\n"
            + "      <td>\n"
            + "        <table bgcolor='#ffffff' style='width: 100%%; max-width: 600px;font-weight:100' align='center' cellpadding='0' cellspacing='0' border='0' width='600'>\n"
            + "          <tbody>\n"
            + "            <tr>\n"
            + "              <td bgcolor='#7F94B0' class='header' style='padding: 5px 5px 5px 5px;'>\n"
            + "                <table class='col425' align='left' border='0' cellpadding='0' cellspacing='0' style='width: 100%%; max-width: 600px;'>\n"
            + "                  <tbody>\n"
            + "                    <tr>\n"
            + "                      <td height='5'></td>\n"
            + "                    </tr>\n"
            + "                  </tbody>\n"
            + "                </table>\n"
            + "              </td>"
            + "            </tr>\n"
            + "            <tr>\n"
            + "            <td style='padding: 30px 30px 30px 30px;border-bottom: 1px solid #f2eeed;'>\n"
            + "              <table width='100%%' border='0' cellspacing='0' cellpadding='0' style='font-family: sans-serif'>\n"
            + "                <tbody>\n"
            + "%s" //title+headPart
            + "                </tbody>\n"
            + "              </table>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "%s" //todos los middlePart
            + "          <tr>\n"
            + "            <td style='font-size: 14px; line-height: 18px;padding: 30px 30px 30px 30px;border-bottom: 1px solid #f2eeed;color: #153643; font-family: sans-serif;font-weight: 100'>"
            + "%s" //footer
            + "            </td>\n"
            + "          </tr>\n"
            + "          <tr>\n"
            + "            <td bgcolor='#44525f' style='padding: 20px 30px 15px 30px;'>\n"
            + "              <table width='100%%' border='0' cellspacing='0' cellpadding='0'>\n"
            + "                <tbody>\n"
            + "                  <tr>\n"
            + "                    <td width='110'>\n"
            + "                      <img width='100' src='http://boletas.febos.cl/img/LOGO_FEBOS.png' />\n"
            + "                    </td>\n"
            + "                    <td align='center' style='font-family: sans-serif; font-size: 12px; color:white'>\n"
            + "                      <a href='http://www.febos.cl' style='color: #ffffff; text-decoration: underline;'>www.febos.cl</a><br/>\n"
            + "                      Developed by IA Solutions Ltda.<br/>\n"
            + "                    </td>\n"
            + "                    <td width='110'>\n"
            + "                    </td>\n"
            + "                  </tr>\n"
            + "                </tbody>\n"
            + "              </table>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "        </tbody>\n"
            + "      </table>\n"
            + "    </td>\n"
            + "  </tr>\n"
            + "</tbody>\n"
            + "</table>";

    private static final String titleTemplate
            = "                  <tr>\n"
            + "                    <td class='h2' style='color: #153643; font-family: sans-serif;padding: 0 0 15px 0; font-size: 15px; line-height: 28px; font-weight: bold;'>"
            + "%s" //titulo 
            + "                      <br/>\n"
            + "                    </td>\n"
            + "                  </tr>\n";

    private static final String headTemplate
            = "                  <tr>\n"
            + "                    <td style='font-size: 14px; line-height: 18px;padding: 30px 30px 0px 0px; font-family: sans-serif;font-weight: 100'>"
            + "%s" // mensaje de cabecera
            + "                    </td>\n"
            + "                  </tr>\n";

    private static final String partTemplate
            = "          <tr>\n"
            + "            <td style='padding: 30px 30px 30px 30px;border-bottom: 1px solid #f2eeed;color: #153643; font-family: sans-serif;font-weight: 100;font-size: 14px    '>\n"
            + "              <table width='100%%' border='0' cellspacing='0' cellpadding='0'>\n"
            + "                <tbody>\n"
            + "                  <tr>\n"
            + "                    <td class='bodycopy' style='font-size: 14px; line-height: 22px;'>"
            + "%s" //cuerpo(s) del mensaje
            + "                    </td>\n"
            + "                  </tr>\n"
            + "                </tbody>\n"
            + "              </table>\n"
            + "            </td>\n"
            + "          </tr>\n";
    private String title;
    private String footer;
    private String head;

    public void setTitle(String htmlContent) {
        this.title = htmlContent;
    }

    public void setFooter(String htmlContent) {
        this.footer = htmlContent;
    }

    public void setHeader(String htmlContent) {
        this.head = htmlContent;
    }

    /**
     * Genera un String compuesto por un HTML que contiene todo el cuerpo del
     * correo electronico
     *
     * @return String con contenido HTML
     */
    public String build() {
        StringBuilder subBody = new StringBuilder();
        for (String b : this.bodies) {
            subBody.append(b);
        }
        String tile = String.format(titleTemplate, this.title);
        String head = "";
        if (this.head == null || this.head.isEmpty()) {
            head = String.format(headTemplate, this.head);
        }

        String entireHTML = String.format(bodyTemplate, title + head, subBody, this.footer);
        return entireHTML;
    }

    /**
     * Agrega un nuevo cuerpo al correo, permite agrega N cuerpos, separados por
     * una linea gris
     */
    public void addBodyPart(String htmlContent) {
        String part = String.format(partTemplate, htmlContent);
        this.bodies.add(part);
    }

    /**
     * Genera un HTML del tipo TABLE, cada elemento del array contiene un array
     * de String, donde cada posicion del arreglo representa un campo de la
     * tabla, el primer elemento corresponde a los titulos
     *
     * @param rows
     * @return
     */
    public static String createTable(ArrayList<String[]> rows) {
        StringBuilder subBody = new StringBuilder();
        subBody.append("<table cellpadding='3' border='1' style='border-collapse: collapse;width:100%;font-size: 12px;box-shadow: #ccc 2px 2px 2px;font-weight: 100'>\n");
        int i = 0;
        String TEMPLATE_DOCUMENTS_LIST = "<tr>\n";
        String TEMPLATE_TITLES = "<tr style='background-color: #ddd;'>\n";
        int repeats = rows.get(0).length;
        for (int x = 0; x < repeats; x++) {
            TEMPLATE_DOCUMENTS_LIST += "\t<td>%s</td>\n";
            TEMPLATE_TITLES += "<td style='padding: 5px 5px 5px 5px;'><b>%s</b></td>\n";
        }
        TEMPLATE_DOCUMENTS_LIST += "</tr>\n";
        TEMPLATE_TITLES += "</tr>\n";
        for (String[] row : rows) {
            if (i == 0) {
                subBody.append(String.format(TEMPLATE_TITLES, (Object[]) row));
            } else {
                subBody.append(String.format(TEMPLATE_DOCUMENTS_LIST, (Object[]) row));
            }
            i++;
        }
        subBody.append("</table>\n");
        return subBody.toString();
    }

}
