package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final TownController townController;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final CarController carController;
    private final RaceEntryController raceEntryController;
    private final RaceController raceController;
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;

    @Autowired
    public Terminal(TownController townController, DistrictController districtController,
                    RacerController racerController, CarController carController, RaceEntryController raceEntryController,
                    RaceController raceController, FileIO fileIO, ConsoleIO consoleIO) {
        this.townController = townController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceEntryController = raceEntryController;
        this.raceController = raceController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
    }

    @Override
    public void run(String... args) throws Exception {
        this.consoleIO.write(this.townController.importDataFromJSON(this.fileIO.read(Config.TOWN_IMPORT_JSON)));
        this.consoleIO.write(this.districtController.importDataFromJSON(this.fileIO.read(Config.DISTRICT_IMPORT_JSON)));
        this.consoleIO.write(this.racerController.importDataFromJson(this.fileIO.read(Config.RACER_IMPORT_JSON)));
        this.consoleIO.write(this.carController.importDataFromJson(this.fileIO.read(Config.CAR_IMPORT_JSON)));
        this.consoleIO.write(this.raceEntryController.importDataFromXML(this.fileIO.read(Config.RACE_ENTRY_IMPORT_XML)));
        this.consoleIO.write(this.raceController.importDataFromXML(this.fileIO.read(Config.RACE_IMPORT_XML)));
        this.fileIO.write(this.townController.exportDataToJSON(), "/files/json/output/racingTowns.json");
        this.fileIO.write(this.racerController.exportDataToJSON(), "/files/json/output/racingCars.json");
        this.fileIO.write(this.racerController.exportDataToXML(), "/files/xml/output/most-wanted.xml");
    }
}
