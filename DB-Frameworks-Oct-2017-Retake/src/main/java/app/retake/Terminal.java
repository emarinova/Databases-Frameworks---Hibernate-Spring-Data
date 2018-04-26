package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {
    private final ConsoleIO consoleIO;
    private final FileIO fileIO;
    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final ProcedureController procedureController;

    @Autowired
    public Terminal(ConsoleIO consoleIO, FileIO fileIO, AnimalAidController animalAidController, AnimalController animalController, VetController vetController, ProcedureController procedureController) {
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) throws Exception {
        //this.consoleIO.write(this.animalAidController.importDataFromJSON(this.fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON)));
        //this.consoleIO.write(this.animalController.importDataFromJSON(this.fileIO.read(Config.ANIMALS_IMPORT_JSON)));
        //this.consoleIO.write(this.vetController.importDataFromXML(this.fileIO.read(Config.VETS_IMPORT_XML)));
        //this.consoleIO.write(this.procedureController.importDataFromXML(this.fileIO.read(Config.PROCEDURES_IMPORT_XML)));

        //this.fileIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"), "export.json");
        this.fileIO.write(this.procedureController.exportProcedures(), "export.xml");
    }
}
