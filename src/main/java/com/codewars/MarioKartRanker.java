package com.codewars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class MarioKartRanker {

    @Test
    void demo() {
        var characters = new ArrayList<Combo>();
        int i = 0;
        int counter = 0;
        for (var character : Character.values()) {
            for (var kart : Kart.values()) {
                for (var tire : Tire.values()) {
                    for (var glider : Glider.values()) {
                        characters.add(new Combo(character, kart, tire, glider));
                        counter++;
                    }
                }
            }
            i++;
        }
        System.out.println("Combinations: " + counter);

        // Fastest Speed
        System.out.println("Best Overall Combo:::");
        System.out.println(characters.stream().sorted(Comparator.comparing(Combo::evaluateCharacter).reversed()).findFirst().orElseThrow());
        System.out.println();

        // Fastest Speed
        System.out.println("Fastest Speed + Acceleration:::");
        System.out.println(characters.stream().sorted(Comparator.comparing(Combo::getFirstMetrics).reversed()).findFirst().orElseThrow());
        System.out.println();

        System.out.println("Top 10 Best Overall:::");
        characters.stream()
                .filter(combo -> combo.getFirstMetrics() >= 7.75)
                .filter(combo -> combo.getSpeed() >= 3.5)
                .filter(combo -> combo.getAcceleration() >= 3.5)
//                .filter(combo -> !combo.getCombo().contains("Pipe Frame"))
//                .filter(combo -> !combo.getCombo().contains("Varmint"))
//                .filter(combo -> combo.getCombo().contains("Bike"))
//                .filter(combo -> combo.getTotal() >= 22.5)
                .sorted(Comparator.comparing(Combo::evaluateCharacter).reversed())
                .filter(distinctByKey(Combo::getSpeed))
                .sorted(Comparator.comparing(Combo::getSpeed).reversed())
                .limit(10).forEach(System.out::println);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Data
    static class Combo {

        public Combo(Character character, Kart kart, Tire tire, Glider glider) {
            this.combo = String.format("%s, %s, %s, %s", character.getCharacter(), kart.getKart(), tire.getTire(), glider.getGlider());
            this.speed = character.getSpeed() + kart.getSpeed() + tire.getSpeed() + glider.getSpeed();
            this.acceleration = character.getAcceleration() + kart.getAcceleration() + tire.getAcceleration() + glider.getAcceleration();
            this.weight = character.getWeight() + kart.getWeight() + tire.getWeight() + glider.getWeight();
            this.handling = character.getHandling() + kart.getHandling() + tire.getHandling() + glider.getHandling();
            this.tractionGrip = character.getTractionGrip() + kart.getTractionGrip() + tire.getTractionGrip() + glider.getTractionGrip();
            this.miniTurbo = character.getMiniTurbo() + kart.getMiniTurbo() + tire.getMiniTurbo() + glider.getMiniTurbo();
            this.total = speed + acceleration + weight + handling + tractionGrip + miniTurbo;
        }

        public double getFirstMetrics() {
            return speed + acceleration;
        }

        /* These metrics are worth 2/3 of speed and acceleration. */
        public double getSecondMetrics() {
            return (weight + handling + tractionGrip + miniTurbo) * 0.67;
        }

        public double evaluateCharacter() {
            return getFirstMetrics() + getSecondMetrics();
        }

        private String combo;
        private double speed;
        private double acceleration;
        private double weight;
        private double handling;
        private double tractionGrip;
        private double miniTurbo;
        private double total;

        @Override
        public String toString() {
            return combo + ":\n" +
                    "speed=" + speed +
                    ", acceleration=" + acceleration +
                    ", weight=" + weight +
                    ", handling=" + handling +
                    ", tractionGrip=" + tractionGrip +
                    ", miniTurbo=" + miniTurbo +
                    ", total=" + total +
                    '\n';
        }
    }

    @AllArgsConstructor
    @Getter
    enum Character {
        BABY_PEACH("Baby Peach, Baby Daisy", 2.25, 4, 2, 5, 4.25, 4, 21.5),
        BABY_ROSALINA("Baby Rosalina, Lemmy Koopa", 2.25, 4.25, 2, 4.75, 3.75, 4, 21),
        BABY_MARIO("Baby Mario/Luigi, Dry Bones", 2.5, 4.25, 2, 4.5, 4, 3.75, 21),
        KOOPA_TROOPA("Koopa Troopa, Lakitu, Bowser Jr.", 2.75, 4, 2.5, 4.5, 4.25, 3.75, 21.75),
        TOADETTE("Toadette, Wendy, Isabelle", 2.75, 4.25, 2.5, 4.25, 3.5, 3.75, 21),
        TOAD("Toad, ShyGuy, Larry", 3, 4, 2.75, 4.25, 4, 3.5, 21.5),
        CAT_PEACH("Cat Peach, Inkling Girl, Villager Girl", 3.25, 4, 2.75, 4, 3.75, 3.5, 21.25),
        PEACH("Peach, Daisy, Yoshi", 3.5, 3.75, 3, 3.75, 3.75, 3.5, 21.25),
        TANOOKI_MARIO("Tanooki Mario, Inkling Boy, Villager Boy", 3.5, 3.75, 3.25, 3.75, 3.25, 3.5, 21),
        LUIGI("Luigi, Iggy", 3.75, 3.5, 3.5, 3.75, 3.25, 3.25, 21),
        MARIO("Mario, Ludwig", 3.75, 3.5, 3.5, 3.5, 3.5, 3.25, 21),
        LINK("Link, King Boo, Rosalina", 4, 3.25, 3.75, 3.25, 3.75, 3.25, 21.25),
        DONKEY_KONG("Donkey Kong, Roy, Waluigi", 4.5, 3.25, 4.0, 3.0, 3.0, 3.0, 20.75),
        WARIO("Wario, Dry Bowser", 4.75, 3.0, 4.25, 2.75, 3.25, 2.75, 20.75),
        METAL_GOLD("Metal/Gold Mario, Pink Gold Peach", 4.25, 3.25, 4.5, 3.25, 3.25, 3.0, 21.5),
        BOWSER("Bowser, Morton", 4.75, 3.0, 4.5, 2.5, 3.0, 2.75, 20.5);

        private String character;
        private double speed;
        private double acceleration;
        private double weight;
        private double handling;
        private double tractionGrip;
        private double miniTurbo;
        private double total;
    }

    @AllArgsConstructor
    @Getter
    enum Tire {

        STANDARD("Standard", 0, 0, 0, 0, 0, 0, 0),
        MONSTER("Monster", 0, -0.5, +0.5, -0.75, +0.5, -0.25, -0.5),
        ROLLER("Roller", -0.5, +0.5, -0.5, +0.25, -0.25, +0.75, +0.25),
        SLIM("Slim", +0.25, -0.5, 0, +0.25, -1.0, -0.25, -1.25),
        SLICK("Slick", +0.5, -0.75, +0.25, -0.25, -1.25, -0.75, -2.25),
        METAL("Metal", +0.5, -1.0, +0.5, -0.25, -0.75, -0.75, -1),
        BUTTON("Button", -0.25, +0.25, -0.5, 0, -0.5, +0.5, 0),
        OFF_ROAD("Off-Road", +0.25, -0.25, +0.25, -0.5, +0.25, -0.5, -0.5),
        SPONGE("Sponge", -0.25, 0, -0.25, -0.25, +0.25, +0.25, -0.25),
        WOOD("Wood", +0.25, -0.5, 0, +0.25, -1.0, -0.25, -1.25),
        CUSHION("Cushion", -0.25, 0, -0.25, -0.25, +0.25, +0.25, -0.25),
        BLUE_STANDARD("Blue Standard", 0, 0, 0, 0, 0, 0, 0),
        HOT_MONSTER("Hot Monster", 0, -0.5, +0.5, -0.75, +0.5, -0.25, -0.5),
        AZURE_ROLLER("Azure Roller", -0.5, +0.5, -0.5, +0.25, -0.25, +0.75, +0.25),
        CRIMSON_SLIM("Crimson Slim", +0.25, -0.5, 0, +0.25, -1.0, -0.25, -1),
        CYBER_SLICK("Cyber Slick", +0.5, -0.75, +0.25, -0.25, -1.25, -0.75, -2.25),
        RETRO_OFF_ROAD("Retro Off-Road", +0.25, -0.25, +0.25, -0.5, +0.25, -0.5, -0.5),
        GOLD_TIRES("Gold Tires", +0.5, -1.0, +0.5, -0.25, -0.75, -0.75, -1.75),
        GLA_TIRES("GLA Tires", 0, 0, 0, 0, 0, 0, 0),
        TRIFORCE_TIRES("Triforce Tires", +0.25, -0.25, +0.25, -0.5, +0.25, -0.5, -0.5),
        LEAF_TIRES("Leaf Tires", -0.25, +0.25, -0.5, 0, -0.5, +0.5, -0.5);

        private String tire;
        private double speed;
        private double acceleration;
        private double weight;
        private double handling;
        private double tractionGrip;
        private double miniTurbo;
        private double total;
    }

    @AllArgsConstructor
    @Getter
    enum Glider {

        SUPER_GLIDER("Super Glider", 0, 0, 0, 0, 0, 0),
        CLOUD_GLIDER("Cloud Glider", -0.25, +0.25, -0.25, 0, 0, +0.25),
        WARIO_WING("Wario Wing", 0, 0, +0.25, 0, -0.25, 0),
        WADDLE_WING("Waddle Wing", 0, 0, 0, 0, 0, 0),
        PEACH_PARASOL("Peach Parasol", -0.25, +0.25, -0.25, 0, 0, +0.25),
        PARACHUTE("Parachute", -0.25, +0.25, -0.25, 0, 0, +0.25),
        PARAFOIL("Parafoil", -0.25, +0.25, 0, 0, -0.25, +0.25),
        FLOWER_GLIDER("Flower Glider", -0.25, +0.25, -0.25, 0, 0, +0.25),
        BOWSER_KITE("Bowser Kite", -0.25, +0.25, 0, 0, -0.25, +0.25),
        PLANE_GLIDER("Plane Glider", 0, 0, +0.25, 0, -0.25, 0),
        MKTV_PARAFOIL("MKTV Parafoil", -0.25, +0.25, 0, 0, -0.25, +0.25),
        GOLD_GLIDER("Gold Glider", 0, 0, +0.25, 0, -0.25, 0),
        HYLIAN_KITE("Hylian Kite", 0, 0, 0, 0, 0, 0),
        PAPER_GLIDER("Paper Glider", -0.25, +0.25, -0.25, 0, 0, +0.25);

        private String glider;
        private double speed;
        private double acceleration;
        private double weight;
        private double handling;
        private double tractionGrip;
        private double miniTurbo;
    }

    @AllArgsConstructor
    @Getter
    enum Kart {
        STANDARD_KART("Standard Kart", 0, 0, 0, 0, 0, 0, 0),
        PIPE_FRAME("Pipe Frame", -0.5, 0.5, -0.25, 0.5, 0.25, 0.5, 1),
        MACH_8_0("Mach 8", 0, -0.25, 0.25, -0.25, 0.25, 0, 0),
        CAT_CRUISER("Cat Cruiser", -0.25, 0.25, 0, 0.25, 0, 0.25, 0.5),
        STEEL_DRIVER("Steel Driver", 0.25, -0.75, 0.5, -0.5, 0, -0.5, -1),
        CIRCUIT_SPECIAL("Circuit Special", 0.5, -0.75, 0.25, -0.5, -0.5, -0.75, -1.75),
        TRI_SPEEDER("Tri-Speeder", 0.25, -0.75, 0.5, -0.5, 0, -0.5, -1),
        BADWAGON("Badwagon", 0.5, -1.0, 0.5, -0.75, 0.5, -1.0, -1.25),
        PRANCER("Prancer", 0.25, -0.5, -0.25, 0, -0.25, -0.25, -1),
        BIDDYBUGGY("Biddybuggy", -0.75, 0.75, -0.5, 0.5, 0.5, 0.25, 0.75),
        LANDSHIP("Landship", -0.5, 0.5, -0.5, -0.5, 0.75, 0.5, 0.25),
        SNEEKER("Sneeker", 0.25, -0.5, 0, 0, -0.75, -0, 25),
        SPORTS_COUPE("Sports Coupe", 0, -0.25, 0.25, -0.25, 0.25, 0, 0),
        GOLD_STANDARD("Gold Standard", 0.25, -0.5, 0, 0, -0.75, -0.25, -1.25),
        MERCEDES_GLA("Mercedes GLA", 0.5, -1, 0.5, -0.75, 0.5, -1.0, -1.25),
        MERCEDES_SILVER_ARROW("Mercedes Silver Arrow", -0.25, 0.25, -0.25, 0.25, 0.5, 0.25, 0.75),
        MERCEDES_300_SL_ROADSTER("Mercedes 300 SL Roadster", 0, 0, 0, 0, 0, 0, 0),
        BLUE_FALCON("Blue Falcon", 0.25, -0.25, -0.5, -0.25, 0.5, 0, -0.25),
        TANOOKI_KART("Tanooki Kart", -0.25, -0.5, 0.25, 0.25, 0, 1.0, 0.75),
        B_DASHER("B Dasher", 0.5, -0.75, 0.25, -0.5, -0.25, -0.5, -1.25),
        STREETLE("Streetle", -0.5, 0.5, -0.5, -0.5, -0.25, 0.75, -0.5),
        P_WING("P-Wing", 0.5, -0.75, 0.25, -0.5, -0.25, -0.5, -1.25),
        KOOPA_CLOWN("Koopa Clown", -0.25, -0.5, 0.25, 0.25, 0, 1.0, 0.75),
        STANDARD_BIKE("Standard Bike", -0.25, 0.25, -0.25, 0.25, 0.5, 0.25, 0.75),
        COMET("Comet", -0.25, 0.25, 0, 0.25, 0, 0.25, 0.5),
        SPORT_BIKE("Sport Bike", 0.25, -0.5, -0.25, 0, -0.25, -0.25, -1),
        THE_DUKE("The Duke", 0, 0, 0, 0, 0, 0, 0),
        FLAME_RIDER("Flame Rider", -0.25, 0.25, -0.25, 0.25, 0.5, 0.25, 0.75),
        VARMINT("Varmint", -0.5, 0.5, -0.25, 0.5, 0.25, 0.5, 1),
        MR_SCOOTY("Mr. Scooty", -0.75, 0.75, -0.5, 0.5, 0.25, 0.75, 1),
        JET_BIKE("Jet Bike", 0.25, -0.5, -0.25, 0, -0.25, -0.25, -1),
        YOSHI_BIKE("Yoshi Bike", -0.25, 0.25, 0, 0.25, 0, 0.25, 0.5),
        MASTER_CYCLE("Master Cycle", 0.25, -0.5, 0, 0, -0.75, -0.25, -1.25),
        CITY_TRIPPER("City Tripper", -0.5, 0.5, -0.25, 0.5, 0.25, 0.5, 1),
        STANDARD_ATV("Standard ATV", 0.5, -1.0, 0.5, -0.75, 0.5, -1.0, -1.25),
        WILD_WIGGLER("Wild Wiggler", -0.25, 0.25, -0.25, 0.25, 0.5, 0.25, 0.75),
        TEDDY_BUGGY("Teddy Buggy", -0.25, 0.25, 0, 0.25, 0, 0.25, 0.5),
        BONE_RATTLER("Bone Rattler", 0.25, -0.75, 0.5, -0.5, 0, -0.5, -1),
        INKSTRIKER("Inkstriker", 0, -0.25, 0.25, -0.25, 0.25, 0, 0),
        SPLAT_BUGGY("Splat Buggy", 0.25, -0.25, -0.5, -0.25, 0, -0.25, -1);

        private String kart;
        private double speed;
        private double acceleration;
        private double weight;
        private double handling;
        private double tractionGrip;
        private double miniTurbo;
        private double total;
    }
}
