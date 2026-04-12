module edu.nob.liceo.triviado_proyectofin_nicolasotero {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.nob.liceo.triviado_proyectofin_nicolasotero to javafx.fxml;
    exports edu.nob.liceo.triviado_proyectofin_nicolasotero;
}