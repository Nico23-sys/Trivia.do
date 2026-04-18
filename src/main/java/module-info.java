module edu.nob.liceo.triviado_proyectofin_nicolasotero {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.envers;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens edu.nob.liceo.triviado_proyectofin_nicolasotero to javafx.fxml;
    opens edu.nob.liceo.triviado_proyectofin_nicolasotero.controllers to javafx.fxml;
    opens edu.nob.liceo.triviado_proyectofin_nicolasotero.model to org.hibernate.orm.core, jakarta.persistence;
    opens edu.nob.liceo.triviado_proyectofin_nicolasotero.util to org.hibernate.orm.core, jakarta.persistence;
    exports edu.nob.liceo.triviado_proyectofin_nicolasotero;
}