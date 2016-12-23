# Bahnhof.Direct

Provides API for the Deutsche Bahn stations and stops.

# Usage

## Start

```
java -jar bahnhofdirect-api-<VERSION>.jar 
```

## Endpoints

* http://localhost:5000/haltestelle/{lon}/{lat} - given the coordinates, search for the nearest stop.

# Data

Incorporates [stops data of DB Station&Service AG](http://data.deutschebahn.com/dataset/data-haltestellen), license [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/).

> **Lizenzbeschreibung:** Dieser Datensatz wird bereitgestellt unter der Lizenz Creative Commons Attribution 4.0 International (CC BY 4.0). Wenn die Daten der Deutschen Bahn (DB) Bestandteil des OpenStreetMap-Datenbankwerkes werden, genügt eine Nennung der Deutschen Bahn AG in der Liste der Beitragenden. Eine Nennung der DB bei jeder Verwendung der Daten auch durch Lizenznehmer des oben genannten Datenbankwerks ist dann nicht mehr erforderlich. Eine indirekte Nennung (Verweis auf Herausgeber des Datenbankwerks, der wiederum auf die DB verweist) genügt.

> **Haftungsausschluss:** Übersicht Haltestellen DB Station&Service AG Dieser Datenbestand kann Fehler enthalten und/oder unvollständig sein. DB Station&Service AG übernimmt keine Haftung und leistet keinerlei Gewähr.

# License

[MIT License](LICENSE)