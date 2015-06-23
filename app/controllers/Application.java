package controllers;

import play.*;

import play.data.DynamicForm;
import play.data.Form;

import play.data.validation.Constraints;

import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;

import play.mvc.*;

import views.html.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class Application extends Controller {
    public static Result home() {
        final String[] monthName = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
            };
        final Calendar cal = Calendar.getInstance();
        final String day = cal.get(Calendar.DAY_OF_MONTH) + "";
        final String month = monthName[cal.get(Calendar.MONTH)];
        final String year = cal.get(Calendar.YEAR) + "";

        return ok(home.render(day, month, year));
    }

    public static Result programas() {
        return ok(programas.render());
    }

    public static Result alojamiento() {
        return ok(alojamiento.render());
    }

    public static Result tips() {
        return ok(tips.render());
    }

    public static Result contacto() {
        return ok(contacto.render(null, null));
    }

    public static Result contactoSubmit() {
        System.out.println();

        final DynamicForm form = Form.form();
        final Map<String, String> data = form.bindFromRequest().data();
        final String nombre = data.get("nombre");
        final String emailStr = data.get("email");
        final String mensaje = data.get("mensaje");

        try {
            Email email = new Email();
            email.setSubject("Gap year argentina");
            email.setFrom(emailStr);
            email.addTo("info@gapyearargentina.com");
            email.addTo("Pia@gapyearargentina.com");
            email.addTo("pia@gapyearargentina.com");
            email.addTo("pia@gapyearargentina.com");
            email.addTo("lavezzopia@gmail.com");
            email.addTo("nschejtman93@gmail.com");
            email.setBodyHtml("<b>Nombre: </b>" + nombre + "<br>" +
                "<b>Email: </b>" + emailStr + "<br>" + "<b>Mensaje:</b> " +
                mensaje);
            MailerPlugin.send(email);
        } catch (Exception e) {
            e.printStackTrace();

            return ok(contacto.render("Hubo un error, vuelva a intentar porfavor", true));
        }

        return ok(contacto.render("Su mensaje ha sido enviado", false));
    }
}
