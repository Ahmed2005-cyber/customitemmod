# Custom Item Mod (Fabric, Minecraft 1.21.1)

Fügt ein eigenes Item — einen "Ruby Gem" — ins Spiel ein. Registrierung über
das moderne RegistryKey-System, eigene Textur, Item-Modell, Lokalisierung
(EN/DE) und Einordnung in den vorhandenen "Ingredients"-Kreativ-Tab.

## Voraussetzungen

- JDK 21 (Fabric 1.21.x braucht Java 21, nicht Java 17)
- Internetzugriff auf `maven.fabricmc.net`, `libraries.minecraft.net`,
  Mojang- und Yarn-Mapping-Server (die Gradle-Wrapper-Konfiguration lädt
  das automatisch beim ersten Build)

## Erste Schritte

```bash
# Falls gradlew noch keine Ausführungsrechte hat:
chmod +x gradlew

# Projekt bauen (lädt beim ersten Mal Minecraft, Yarn-Mappings, Fabric API
# und Loom herunter — kann ein paar Minuten dauern):
./gradlew build

# Direkt im Client testen:
./gradlew runClient
```

Das fertige Mod-Jar liegt danach unter `build/libs/customitemmod-1.0.0.jar`
und kann in den `mods`-Ordner einer normalen Fabric-Loader-Installation
kopiert werden.

## Wichtiger Hinweis zum Gradle-Wrapper

Die `gradle-wrapper.jar`-Binärdatei selbst ist hier NICHT enthalten (sie
konnte in dieser Umgebung nicht aus dem Netz geladen werden). Bevor der
Wrapper (`./gradlew`) funktioniert, einmal ausführen:

```bash
gradle wrapper --gradle-version 8.8
```

(erfordert eine lokal installierte Gradle-Version — z. B. via SDKMAN:
`sdk install gradle 8.8`). Danach überschreibt das den vorhandenen
`gradle/wrapper/gradle-wrapper.properties` mit denselben Werten und legt
zusätzlich die fehlende `.jar` an.

## Projektstruktur

```
src/main/java/com/example/customitemmod/
  CustomItemMod.java   -> ModInitializer, Einstiegspunkt, Kreativ-Tab-Hook
  ModItems.java        -> Item-Registrierung über RegistryKey

src/main/resources/
  fabric.mod.json       -> Mod-Metadaten, Entry-Point-Deklaration
  assets/customitemmod/
    lang/en_us.json      -> Item-Name Englisch
    lang/de_de.json       -> Item-Name Deutsch
    models/item/ruby_gem.json  -> Item-Modell (verweist auf Textur)
    textures/item/ruby_gem.png -> 16x16 Platzhalter-Textur
```

## Warum das Registrierungssystem im Code kommentiert ist

Zwischen älteren Fabric-Tutorials (viele online noch für 1.19/1.20) und
1.21.x hat sich die Item-Registrierung grundlegend geändert: `Item.Settings()`
verlangt jetzt zwingend einen vorher erzeugten `RegistryKey<Item>` über
`.registryKey(...)`, sonst wirft das Spiel beim Start eine
`IllegalStateException`. Das ist in `ModItems.java` als Kommentar
dokumentiert — genau der Punkt, an dem ein aus dem Netz kopiertes,
veraltetes Tutorial nicht mehr kompiliert oder zur Laufzeit crasht.

## Eigenen Kreativ-Tab statt vorhandenem verwenden

Standardmäßig landet das Item im vorhandenen "Ingredients"-Tab. Ein
Codebeispiel für einen komplett eigenen Tab steht als Kommentarblock am
Ende von `CustomItemMod.java`.
